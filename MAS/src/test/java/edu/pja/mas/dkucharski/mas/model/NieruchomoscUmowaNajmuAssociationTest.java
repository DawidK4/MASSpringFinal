package edu.pja.mas.dkucharski.mas.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class NieruchomoscUmowaNajmuAssociationTest {

    @Autowired
    private TestEntityManager entityManager;

    private Nieruchomosc nieruchomosc;
    private UmowaNajmu umowa1;
    private UmowaNajmu umowa2;

    @BeforeEach
    void setUp() {
        nieruchomosc = Apartament.builder()
                .adres("Test Address")
                .wielkosc(100.0)
                .liczbaPokoi(3)
                .cenaNajmu(3000.0)
                .statusDostepnosci(Nieruchomosc.StatusDostepnosci.DOSTEPNE)
                .build();

        umowa1 = UmowaNajmu.builder()
                .dataStart("2024-06-01")
                .dataKoniec("2024-12-01")
                .zaliczka(1000.0)
                .stanUmowy(UmowaNajmu.StanUmowy.AKTYWNA)
                .dotyczy(nieruchomosc)
                .build();

        umowa2 = UmowaNajmu.builder()
                .dataStart("2024-07-01")
                .dataKoniec("2025-01-01")
                .zaliczka(1200.0)
                .stanUmowy(UmowaNajmu.StanUmowy.W_EDYCJI)
                .dotyczy(nieruchomosc)
                .build();

        nieruchomosc.getMa().add(umowa1);
        nieruchomosc.getMa().add(umowa2);
    }

    @Test
    @DisplayName("Should persist and retrieve association between Nieruchomosc and UmowaNajmu")
    void testAssociationPersistence() {
        entityManager.persist(nieruchomosc);
        entityManager.persist(umowa1);
        entityManager.persist(umowa2);
        entityManager.flush();
        entityManager.clear();

        Nieruchomosc foundNieruchomosc = entityManager.find(Nieruchomosc.class, nieruchomosc.getId());
        assertThat(foundNieruchomosc.getMa()).hasSize(2);

        for (UmowaNajmu umowa : foundNieruchomosc.getMa()) {
            assertThat(umowa.getDotyczy().getId()).isEqualTo(foundNieruchomosc.getId());
        }
    }
}