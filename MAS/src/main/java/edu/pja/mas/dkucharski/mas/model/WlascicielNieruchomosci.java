package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(exclude = {"wlasnosciNieruchomosci"}) // Wykluczamy nowe pole
public class WlascicielNieruchomosci {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "NIP nie może być pusty!")
    @NotNull
    @Column(unique = true)
    private String NIP;

    @OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "osoba_id", nullable = false, unique = true)
    private Osoba osoba;

    // --- Nowa relacja do klasy asocjacyjnej ---
    @OneToMany(mappedBy = "wlascicielNieruchomosci", cascade = CascadeType.ALL, orphanRemoval = true)
    // Używamy Set, aby zapewnić unikalność powiązań (jeden właściciel - jedna nieruchomość - jeden udział)
    private Set<Wlasnosc> posiada = new HashSet<>();
    // ------------------------------------------

    // Metody pomocnicze do zarządzania asocjacją (opcjonalnie, ale dobra praktyka)
    public void addWlasnosc(Wlasnosc wlasnosc) {
        if (this.posiada == null) {
            this.posiada = new HashSet<>();
        }
        if (!this.posiada.contains(wlasnosc)) {
            this.posiada.add(wlasnosc);
            wlasnosc.setWlascicielNieruchomosci(this);
            // Tutaj nie ustawiamy id, bo ono jest ustawiane w konstruktorze WlasnoscNieruchomosci
        }
    }

    public void removeWlasnosc(Wlasnosc wlasnosc) {
        if (this.posiada != null && this.posiada.remove(wlasnosc)) {
            wlasnosc.setWlascicielNieruchomosci(null);
        }
    }
}