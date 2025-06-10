package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class NieruchomoscOgloszenieWynajmuAssociationTest {

    @Entity
    public static class TestNieruchomosc extends Nieruchomosc {}

    @Autowired
    private TestEntityManager entityManager;

    private TestNieruchomosc nieruchomosc;
    private OgloszenieWynajmu ogloszenie;

    @BeforeEach
    void setUp() {
        nieruchomosc = new TestNieruchomosc();
        nieruchomosc.setAdres("Test Address");
        nieruchomosc.setWielkosc(80.0);
        nieruchomosc.setLiczbaPokoi(4);
        nieruchomosc.setCenaNajmu(2000.0);
        nieruchomosc.setStatusDostepnosci(Nieruchomosc.StatusDostepnosci.DOSTEPNE);

        ogloszenie = new OgloszenieWynajmu();
        ogloszenie.setTytul("Test Listing");
        ogloszenie.setDataPublikacji("2024-06-10");
        ogloszenie.setStatus(OgloszenieWynajmu.Status.AKTUALNE);
        ogloszenie.setZawiera(nieruchomosc);

        nieruchomosc.getJestCzescia().add(ogloszenie);
    }

    @Test
    @DisplayName("Should persist and retrieve association between Nieruchomosc and OgloszenieWynajmu")
    void testAssociationPersistence() {
        entityManager.persist(nieruchomosc);
        entityManager.persist(ogloszenie);
        entityManager.flush();
        entityManager.clear();

        TestNieruchomosc found = entityManager.find(TestNieruchomosc.class, nieruchomosc.getId());
        assertThat(found.getJestCzescia()).hasSize(1);
        OgloszenieWynajmu foundOgloszenie = found.getJestCzescia().iterator().next();
        assertThat(foundOgloszenie.getTytul()).isEqualTo("Test Listing");
        assertThat(foundOgloszenie.getZawiera().getId()).isEqualTo(found.getId());
    }
}