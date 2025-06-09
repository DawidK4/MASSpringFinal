package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // Używamy Buildera dla łatwego tworzenia instancji
@ToString // Do debugowania
// Klasa asocjacyjna, która reprezentuje posiadanie nieruchomości przez właściciela
// i zawiera atrybut asocjacji (udział)
public class Wlasnosc {

    // --- Klucz złożony dla tej asocjacji ---
    // Najlepszym rozwiązaniem jest użycie @IdClass lub @EmbeddedId
    // Użyjemy @EmbeddedId dla czytelności i enkapsulacji klucza
    @EmbeddedId
    private WlasnoscId id;

    // --- Klucze obce do encji źródłowych ---
    @ManyToOne
    @MapsId("nieruchomoscId") // Mapuje to ManyToOne do części klucza złożonego
    @JoinColumn(name = "nieruchomosc_id")
    @NotNull
    @ToString.Exclude
    private Nieruchomosc nieruchomosc;

    @ManyToOne
    @MapsId("wlascicielNieruchomosciId") // Mapuje to ManyToOne do części klucza złożonego
    @JoinColumn(name = "wlasciciel_nieruchomosci_id")
    @NotNull
    @ToString.Exclude
    private WlascicielNieruchomosci wlascicielNieruchomosci;
    // ----------------------------------------

    // --- Atrybut asocjacji ---
    @NotNull(message = "Udział nie może być pusty!")
    @DecimalMin(value = "0.0", message = "Udział nie może być mniejszy niż 0.0")
    private Double udzial;

    // --- Konstruktor do ułatwienia tworzenia, zwłaszcza z builderem ---
    public Wlasnosc(Nieruchomosc nieruchomosc, WlascicielNieruchomosci wlascicielNieruchomosci, Double udzial) {
        this.nieruchomosc = nieruchomosc;
        this.wlascicielNieruchomosci = wlascicielNieruchomosci;
        this.udzial = udzial;
        this.id = new WlasnoscId(nieruchomosc.getId(), wlascicielNieruchomosci.getId());
    }
}