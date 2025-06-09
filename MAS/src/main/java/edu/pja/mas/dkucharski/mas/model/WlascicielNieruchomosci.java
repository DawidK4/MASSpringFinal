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
    private Set<WlasnoscNieruchomosci> wlasnosciNieruchomosci = new HashSet<>();
    // ------------------------------------------

    // Metody pomocnicze do zarządzania asocjacją (opcjonalnie, ale dobra praktyka)
    public void addWlasnosc(WlasnoscNieruchomosci wlasnosc) {
        if (this.wlasnosciNieruchomosci == null) {
            this.wlasnosciNieruchomosci = new HashSet<>();
        }
        if (!this.wlasnosciNieruchomosci.contains(wlasnosc)) {
            this.wlasnosciNieruchomosci.add(wlasnosc);
            wlasnosc.setWlascicielNieruchomosci(this);
            // Tutaj nie ustawiamy id, bo ono jest ustawiane w konstruktorze WlasnoscNieruchomosci
        }
    }

    public void removeWlasnosc(WlasnoscNieruchomosci wlasnosc) {
        if (this.wlasnosciNieruchomosci != null && this.wlasnosciNieruchomosci.remove(wlasnosc)) {
            wlasnosc.setWlascicielNieruchomosci(null);
        }
    }
}