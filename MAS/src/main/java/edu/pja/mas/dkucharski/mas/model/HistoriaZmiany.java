package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

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
    private WlascicielNieruchomosci poprzedniWlasciciel;

    @Max(400)
    private String opis;

    @ManyToOne
    @JoinColumn(name = "nieruchomosc_id")
    @NotNull
    private Nieruchomosc nieruchomosc;
}
