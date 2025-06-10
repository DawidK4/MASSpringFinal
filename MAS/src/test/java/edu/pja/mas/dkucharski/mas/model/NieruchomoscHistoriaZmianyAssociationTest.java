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
public class NieruchomoscHistoriaZmianyAssociationTest {

    @Entity
    public static class TestNieruchomosc extends Nieruchomosc {}

    @Autowired
    private TestEntityManager entityManager;

    private TestNieruchomosc nieruchomosc;
    private HistoriaZmiany historiaZmiany;

    @BeforeEach
    void setUp() {
        nieruchomosc = new TestNieruchomosc();
        nieruchomosc.setAdres("Test Address");
        nieruchomosc.setWielkosc(80.0);
        nieruchomosc.setLiczbaPokoi(4);
        nieruchomosc.setCenaNajmu(2000.0);
        nieruchomosc.setStatusDostepnosci(Nieruchomosc.StatusDostepnosci.DOSTEPNE);

        historiaZmiany = new HistoriaZmiany();
        historiaZmiany.setDataZmiany("2024-06-01");
        historiaZmiany.setOpis("Test change");
        historiaZmiany.setZapisujeZmianyDla(nieruchomosc);

        nieruchomosc.getJestZawieranaPrzez().add(historiaZmiany);
    }

    @Test
    @DisplayName("Should persist and retrieve association between Nieruchomosc and HistoriaZmiany")
    void testAssociationPersistence() {
        entityManager.persist(nieruchomosc);
        entityManager.persist(historiaZmiany);
        entityManager.flush();
        entityManager.clear();

        TestNieruchomosc found = entityManager.find(TestNieruchomosc.class, nieruchomosc.getId());
        assertThat(found.getJestZawieranaPrzez()).hasSize(1);
        HistoriaZmiany foundHistoria = found.getJestZawieranaPrzez().iterator().next();
        assertThat(foundHistoria.getOpis()).isEqualTo("Test change");
        assertThat(foundHistoria.getZapisujeZmianyDla().getId()).isEqualTo(found.getId());
    }
}