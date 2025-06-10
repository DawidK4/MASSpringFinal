package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AgentNieruchomosciOgloszenieWynajmuAssociationTest {

    @Entity
    @DiscriminatorValue("TestAgent")
    public static class TestAgent extends AgentNieruchomosci {}

    @Entity
    @DiscriminatorValue("TestNieruchomosc")
    public static class TestNieruchomosc extends Nieruchomosc {}

    @Autowired
    private TestEntityManager entityManager;

    private TestAgent agent;
    private OgloszenieWynajmu ogloszenie1;
    private OgloszenieWynajmu ogloszenie2;

    @BeforeEach
    void setUp() {
        agent = new TestAgent();
        agent.setNumerLicencji("AG12345");

        TestNieruchomosc nieruchomosc = new TestNieruchomosc();
        nieruchomosc.setAdres("Test Address");
        nieruchomosc.setWielkosc(50.0);
        nieruchomosc.setLiczbaPokoi(2);
        nieruchomosc.setCenaNajmu(2000.0);
        nieruchomosc.setStatusDostepnosci(Nieruchomosc.StatusDostepnosci.DOSTEPNE);

        entityManager.persist(nieruchomosc);

        ogloszenie1 = new OgloszenieWynajmu();
        ogloszenie1.setTytul("Listing 1");
        ogloszenie1.setDataPublikacji("2024-06-10");
        ogloszenie1.setStatus(OgloszenieWynajmu.Status.AKTUALNE);
        ogloszenie1.setJestZarzadzanyPrzez(agent);
        ogloszenie1.setZawiera(nieruchomosc);

        ogloszenie2 = new OgloszenieWynajmu();
        ogloszenie2.setTytul("Listing 2");
        ogloszenie2.setDataPublikacji("2024-06-11");
        ogloszenie2.setStatus(OgloszenieWynajmu.Status.AKTUALNE);
        ogloszenie2.setJestZarzadzanyPrzez(agent);
        ogloszenie2.setZawiera(nieruchomosc);

        agent.getZarzadza().add(ogloszenie1);
        agent.getZarzadza().add(ogloszenie2);
    }

    @Test
    @DisplayName("Should persist and retrieve association between AgentNieruchomosci and OgloszenieWynajmu")
    void testAssociationPersistence() {
        entityManager.persist(agent);
        entityManager.persist(ogloszenie1);
        entityManager.persist(ogloszenie2);
        entityManager.flush();
        entityManager.clear();

        TestAgent found = entityManager.find(TestAgent.class, agent.getId());
        assertThat(found.getZarzadza()).hasSize(2);

        Iterator<OgloszenieWynajmu> it = found.getZarzadza().iterator();
        OgloszenieWynajmu foundOgl1 = it.next();
        OgloszenieWynajmu foundOgl2 = it.next();

        assertThat(foundOgl1.getJestZarzadzanyPrzez().getId()).isEqualTo(found.getId());
        assertThat(foundOgl2.getJestZarzadzanyPrzez().getId()).isEqualTo(found.getId());
    }
}