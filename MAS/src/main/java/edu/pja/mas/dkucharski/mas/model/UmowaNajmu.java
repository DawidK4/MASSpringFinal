package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne(optional = false)
    @JoinColumn(name = "nieruchomosc_id", nullable = false)
    @NotNull
    private Nieruchomosc dotyczy;

    @OneToMany(mappedBy = "dotyczy", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Platnosc> jestPrzypisanaDo = new HashSet<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "najemca_id", nullable = false)
    @NotNull
    private Najemca jestPodpisanaPrzez;

    public enum StanUmowy {
        W_EDYCJI,
        SZKIC,
        AKTYWNA,
        ZARCHIWIZOWANA
    }
}