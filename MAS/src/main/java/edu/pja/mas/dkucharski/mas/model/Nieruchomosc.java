package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
@ToString(exclude = {"jestZmienianaPrzez", "podlega", "jestZawarteW", "wlasnosciNieruchomosci"}) // Wykluczamy nowe pole
public abstract class Nieruchomosc {
    /*
    🧠 Justification for JOINED strategy:
    Clear normalization between User, Customer, and Employee; avoids data duplication and keeps integrity.
    💡 Counter-example: SINGLE_TABLE may lead to many nullable columns and data redundancy in large systems.
    SINGLE_TABLE is suitable when the tables do not differ much due to little null column values
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Address must not be blank!")
    @NotNull
    private String adres;

    @NotNull
    private Double wielkosc;

    @Min(1)
    private int liczbaPokoi;

    @Min(0)
    private double cenaNajmu;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusDostepnosci statusDostepnosci;

    @OneToMany(mappedBy = "nieruchomosc", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<HistoriaZmiany> jestZmienianaPrzez = new HashSet<>();

    @OneToMany(mappedBy = "nieruchomosc", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<UmowaNajmu> podlega = new HashSet<>();

    @OneToMany(mappedBy = "nieruchomosc", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<OgloszenieWynajmu> jestZawarteW = new HashSet<>();

    // --- Nowa relacja do klasy asocjacyjnej ---
    @OneToMany(mappedBy = "nieruchomosc", cascade = CascadeType.ALL, orphanRemoval = true)
    // Używamy Set, aby zapewnić unikalność powiązań (jedna nieruchomość - jeden właściciel - jeden udział)
    private Set<Wlasnosc> jestPosiadanaPrzez = new HashSet<>();
    // ------------------------------------------

    public enum StatusDostepnosci {
        DOSTEPNE,
        ZAJETE
    }

    // Metody pomocnicze do zarządzania asocjacją (opcjonalnie, ale dobra praktyka)
    public void addWlasnosc(Wlasnosc wlasnosc) {
        if (this.jestPosiadanaPrzez == null) {
            this.jestPosiadanaPrzez = new HashSet<>();
        }
        if (!this.jestPosiadanaPrzez.contains(wlasnosc)) {
            this.jestPosiadanaPrzez.add(wlasnosc);
            wlasnosc.setNieruchomosc(this);
            // Tutaj nie ustawiamy id, bo ono jest ustawiane w konstruktorze WlasnoscNieruchomosci
        }
    }

    public void removeWlasnosc(Wlasnosc wlasnosc) {
        if (this.jestPosiadanaPrzez != null && this.jestPosiadanaPrzez.remove(wlasnosc)) {
            wlasnosc.setNieruchomosc(null);
        }
    }
}