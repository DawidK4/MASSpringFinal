package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NieruchomoscTest {

    @Entity
    public static class TestNieruchomosc extends Nieruchomosc {
        // No extra fields needed for testing
    }

    private TestNieruchomosc nieruchomosc;

    @BeforeEach
    void setUp() {
        nieruchomosc = new TestNieruchomosc();
        nieruchomosc.setAdres("Test Address");
        nieruchomosc.setWielkosc(80.0);
        nieruchomosc.setLiczbaPokoi(4);
        nieruchomosc.setCenaNajmu(2000.0);
        nieruchomosc.setStatusDostepnosci(Nieruchomosc.StatusDostepnosci.DOSTEPNE);
    }

    @Test
    @DisplayName("Should set and get fields correctly")
    void testFieldAccessors() {
        assertThat(nieruchomosc.getAdres()).isEqualTo("Test Address");
        assertThat(nieruchomosc.getWielkosc()).isEqualTo(80.0);
        assertThat(nieruchomosc.getLiczbaPokoi()).isEqualTo(4);
        assertThat(nieruchomosc.getCenaNajmu()).isEqualTo(2000.0);
        assertThat(nieruchomosc.getStatusDostepnosci()).isEqualTo(Nieruchomosc.StatusDostepnosci.DOSTEPNE);
    }

    @Test
    @DisplayName("Should manage jestZawieranaPrzez association")
    void testJestZawieranaPrzezAssociation() {
        HistoriaZmiany historia = new HistoriaZmiany();
        historia.setDataZmiany("2024-06-01");
        historia.setOpis("Test change");
        historia.setZapisujeZmianyDla(nieruchomosc);

        nieruchomosc.getJestZawieranaPrzez().add(historia);

        assertThat(nieruchomosc.getJestZawieranaPrzez()).hasSize(1);
        assertThat(nieruchomosc.getJestZawieranaPrzez().iterator().next().getOpis()).isEqualTo("Test change");
    }
}