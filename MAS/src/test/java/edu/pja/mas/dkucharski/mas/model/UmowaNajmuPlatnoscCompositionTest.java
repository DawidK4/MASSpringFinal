package edu.pja.mas.dkucharski.mas.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class UmowaNajmuPlatnoscCompositionTest {

    private UmowaNajmu umowa;
    private Platnosc platnosc1;
    private Platnosc platnosc2;

    @BeforeEach
    void setUp() {
        umowa = UmowaNajmu.builder()
                .dataStart("2024-06-01")
                .dataKoniec("2024-12-01")
                .zaliczka(1000.0)
                .stanUmowy(UmowaNajmu.StanUmowy.AKTYWNA)
                .dotyczy(null) // mock or set a valid Nieruchomosc if needed
                .build();

        platnosc1 = Platnosc.builder()
                .kwotaBrutto(100.0)
                .kwotaNetto(80.0)
                .dataPlatnosci("2024-06-10")
                .dataWygenerowaniaPlatnosci("2024-06-01")
                .terminPlatnosci("2024-06-15")
                .kara(0.0)
                .dotyczy(umowa)
                .build();

        platnosc2 = Platnosc.builder()
                .kwotaBrutto(200.0)
                .kwotaNetto(160.0)
                .dataPlatnosci("2024-07-10")
                .dataWygenerowaniaPlatnosci("2024-07-01")
                .terminPlatnosci("2024-07-15")
                .kara(0.0)
                .dotyczy(umowa)
                .build();

        umowa.getJestPrzypisanaDo().add(platnosc1);
        umowa.getJestPrzypisanaDo().add(platnosc2);
    }

    @Test
    void testPlatnoscBelongsToUmowa() {
        assertEquals(2, umowa.getJestPrzypisanaDo().size());
        for (Platnosc p : umowa.getJestPrzypisanaDo()) {
            assertSame(umowa, p.getDotyczy());
        }
    }

    @Test
    void testRemovePlatnoscFromUmowa() {
        Iterator<Platnosc> it = umowa.getJestPrzypisanaDo().iterator();
        Platnosc toRemove = it.next();
        it.remove();

        assertEquals(1, umowa.getJestPrzypisanaDo().size());
        // Optionally, check orphan removal in a real JPA context
    }

    @Test
    void testCompositionOrphanRemoval() {
        umowa.getJestPrzypisanaDo().clear();
        assertTrue(umowa.getJestPrzypisanaDo().isEmpty());
        // In a real JPA test, verify Platnosc is deleted from DB
    }
}