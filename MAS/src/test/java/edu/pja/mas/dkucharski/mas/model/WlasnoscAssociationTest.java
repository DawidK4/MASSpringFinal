package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class WlasnoscAssociationTest {

    @Entity
    @DiscriminatorValue("TestWlasciciel")
    public static class TestWlasciciel extends WlascicielNieruchomosci {}

    @Entity
    @DiscriminatorValue("TestNieruchomosc")
    public static class TestNieruchomosc extends Nieruchomosc {}

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Should persist and retrieve Wlasnosc association with share attribute")
    void testWlasnoscAssociation() {
        TestWlasciciel owner1 = new TestWlasciciel();
        owner1.setNIP("1234567890");

        TestWlasciciel owner2 = new TestWlasciciel();
        owner2.setNIP("0987654321");

        TestNieruchomosc property = new TestNieruchomosc();
        property.setAdres("Test Address");
        property.setWielkosc(100.0);
        property.setLiczbaPokoi(5);
        property.setCenaNajmu(3000.0);
        property.setStatusDostepnosci(Nieruchomosc.StatusDostepnosci.DOSTEPNE);

        entityManager.persist(owner1);
        entityManager.persist(owner2);
        entityManager.persist(property);

        Wlasnosc wlasnosc1 = Wlasnosc.builder()
                .wlasciciel(owner1)
                .nieruchomosc(property)
                .share(0.6)
                .build();
        wlasnosc1.setId(new WlasnoscId(owner1.getId(), property.getId()));

        Wlasnosc wlasnosc2 = Wlasnosc.builder()
                .wlasciciel(owner2)
                .nieruchomosc(property)
                .share(0.4)
                .build();
        wlasnosc2.setId(new WlasnoscId(owner2.getId(), property.getId()));

        owner1.getWlasnosci().add(wlasnosc1);
        owner2.getWlasnosci().add(wlasnosc2);
        property.getWlasnosci().add(wlasnosc1);
        property.getWlasnosci().add(wlasnosc2);

        entityManager.persist(wlasnosc1);
        entityManager.persist(wlasnosc2);
        entityManager.flush();
        entityManager.clear();

        TestNieruchomosc foundProperty = entityManager.find(TestNieruchomosc.class, property.getId());
        assertThat(foundProperty.getWlasnosci()).hasSize(2);

        double totalShare = foundProperty.getWlasnosci().stream()
                .mapToDouble(Wlasnosc::getShare)
                .sum();
        assertThat(totalShare).isEqualTo(1.0);

        TestWlasciciel foundOwner1 = entityManager.find(TestWlasciciel.class, owner1.getId());
        assertThat(foundOwner1.getWlasnosci()).hasSize(1);
        assertThat(foundOwner1.getWlasnosci().iterator().next().getShare()).isEqualTo(0.6);
    }
}