package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
public class OsobaCompositionTest {

    @Autowired
    private org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager entityManager;

    @Test
    void testOsobaWithParts() {
        Osoba osoba = Osoba.builder()
                .imie("Jan")
                .nazwisko("Kowalski")
                .numerTelefonu("123456789")
                .email("jan@kowalski.pl")
                .build();

        WlascicielNieruchomosci wlasciciel = WlascicielNieruchomosci.builder()
                .NIP("1234567890")
                .osoba(osoba)
                .build();

        Najemca najemca = Najemca.builder()
                .PESEL("90010112345")
                .osoba(osoba)
                .build();

        AgentNieruchomosci agent = AgentNieruchomosci.builder()
                .numerLicencji("AG123")
                .osoba(osoba)
                .build();

        osoba.setWlasciciel(wlasciciel);
        osoba.setNajemca(najemca);
        osoba.setAgent(agent);

        entityManager.persist(osoba);
        entityManager.flush();
        entityManager.clear();

        Osoba found = entityManager.find(Osoba.class, osoba.getId());
        assertThat(found.getWlasciciel()).isNotNull();
        assertThat(found.getNajemca()).isNotNull();
        assertThat(found.getAgent()).isNotNull();
    }

    @Test
    void testOsobaWithoutParts() {
        Osoba osoba = Osoba.builder()
                .imie("Anna")
                .nazwisko("Nowak")
                .numerTelefonu("987654321")
                .email("anna@nowak.pl")
                .build();

        entityManager.persist(osoba);
        entityManager.flush();
        entityManager.clear();

        Osoba found = entityManager.find(Osoba.class, osoba.getId());
        assertThat(found.getWlasciciel()).isNull();
        assertThat(found.getNajemca()).isNull();
        assertThat(found.getAgent()).isNull();
    }

    @Test
    void testCascadeDeleteOsobaRemovesParts() {
        Osoba osoba = Osoba.builder()
                .imie("Piotr")
                .nazwisko("Zielinski")
                .numerTelefonu("555555555")
                .email("piotr@zielinski.pl")
                .build();

        WlascicielNieruchomosci wlasciciel = WlascicielNieruchomosci.builder()
                .NIP("1112223334")
                .osoba(osoba)
                .build();

        osoba.setWlasciciel(wlasciciel);

        entityManager.persist(osoba);
        entityManager.flush();

        Long wlascicielId = wlasciciel.getId();
        entityManager.remove(osoba);
        entityManager.flush();
        entityManager.clear();

        WlascicielNieruchomosci foundWlasciciel = entityManager.find(WlascicielNieruchomosci.class, wlascicielId);
        assertThat(foundWlasciciel).isNull();
    }
}