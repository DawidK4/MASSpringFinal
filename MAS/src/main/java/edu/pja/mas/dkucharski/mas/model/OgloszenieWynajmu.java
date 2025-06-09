package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString // No need to exclude here unless you add a collection
public class OgloszenieWynajmu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Tytuł ogłoszenia nie może być pusty!")
    @Max(400)
    private String tytul;

    @NotNull
    private String dataPublikacji;

    @Enumerated(EnumType.STRING)
    @NotNull
    private OgloszenieWynajmu.Status status;

    @ManyToOne
    @JoinColumn(name = "nieruchomosc_id")
    @NotNull
    private Nieruchomosc zawiera;

    // --- Nowa asocjacja z AgentNieruchomosci ---
    @ManyToOne(optional = false) // OgloszenieWynajmu MUSI mieć AgentaNieruchomosci
    @JoinColumn(name = "agent_nieruchomosci_id", nullable = false) // Nazwa kolumny klucza obcego
    @NotNull // Dodatkowa walidacja, aby upewnić się, że pole nie jest null
    private AgentNieruchomosci jestZarzadzanePrzez; // Pole do przechowywania referencji do AgentaNieruchomosci
    // ---------------------------------------------

    public enum Status {
        AKTUALNE,
        ZARCHIWIZOWANE
    }
}