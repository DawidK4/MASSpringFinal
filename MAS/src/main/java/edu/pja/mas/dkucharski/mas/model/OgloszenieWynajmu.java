package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class OgloszenieWynajmu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Tytuł ogłoszenia nie może być pusty!")
    @Size(max = 400)
    private String tytul;

    @NotNull
    private String dataPublikacji;

    @Enumerated(EnumType.STRING)
    @NotNull
    private OgloszenieWynajmu.Status status;

    @ManyToOne
    @JoinColumn(name = "nieruchomosc_id")
    @NotNull
    @ToString.Exclude
    private Nieruchomosc zawiera;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private AgentNieruchomosci jestZarzadzanyPrzez;

    public enum Status {
        AKTUALNE,
        ZARCHIWIZOWANE
    }
}