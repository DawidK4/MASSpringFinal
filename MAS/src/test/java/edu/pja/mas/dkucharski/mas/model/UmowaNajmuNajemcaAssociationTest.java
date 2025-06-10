package edu.pja.mas.dkucharski.mas.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UmowaNajmuNajemcaAssociationTest {

    private Najemca najemca;
    private UmowaNajmu umowa1;
    private UmowaNajmu umowa2;

    @BeforeEach
    void setUp() {
        najemca = Najemca.builder()
                .PESEL("12345678901")
                .osoba(null) // mock or set a valid Osoba if needed
                .build();

        umowa1 = UmowaNajmu.builder()
                .dataStart("2024-06-01")
                .dataKoniec("2024-12-01")
                .zaliczka(1000.0)
                .stanUmowy(UmowaNajmu.StanUmowy.AKTYWNA)
                .dotyczy(null) // mock or set a valid Nieruchomosc if needed
                .podpisanaPrzez(najemca)
                .build();

        umowa2 = UmowaNajmu.builder()
                .dataStart("2025-01-01")
                .dataKoniec("2025-06-01")
                .zaliczka(1200.0)
                .stanUmowy(UmowaNajmu.StanUmowy.W_EDYCJI)
                .dotyczy(null)
                .podpisanaPrzez(najemca)
                .build();

        najemca.getPodpisal().add(umowa1);
        najemca.getPodpisal().add(umowa2);
    }

    @Test
    void testNajemcaHasMultipleUmowyNajmu() {
        List<UmowaNajmu> umowy = najemca.getPodpisal();
        assertEquals(2, umowy.size());
        assertTrue(umowy.contains(umowa1));
        assertTrue(umowy.contains(umowa2));
    }

    @Test
    void testUmowaNajmuReferencesCorrectNajemca() {
        assertSame(najemca, umowa1.getPodpisanaPrzez());
        assertSame(najemca, umowa2.getPodpisanaPrzez());
    }
}
