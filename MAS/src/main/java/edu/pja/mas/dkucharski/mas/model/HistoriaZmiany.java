package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class HistoriaZmiany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String dataZmiany;

    @NotNull
    @NotBlank(message = "Imię poprzedniego właściciela nie może być puste")
    private String imiePoprzedniegoWlasciciela;

    @NotNull
    @NotBlank(message = "Nazwisko poprzedniego właściciela nie może być puste")
    private String nazwiskoPoprzedniegoWlasciciela;

    @ManyToOne
    @JoinColumn(name = "nieruchomosc_id", nullable = false)
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Nieruchomosc zapisujeZmianyDla;

    @Size(max = 400)
    private String opis;
}
