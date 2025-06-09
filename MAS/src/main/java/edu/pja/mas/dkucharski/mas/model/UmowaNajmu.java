package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UmowaNajmu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String dataStart;

    @NotNull
    private String dataKoniec;

    private double zaliczka;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StanUmowy stanUmowy;

    public enum StanUmowy {
        W_EDYCJI,
        SZKIC,
        AKTYWNA
    }
}