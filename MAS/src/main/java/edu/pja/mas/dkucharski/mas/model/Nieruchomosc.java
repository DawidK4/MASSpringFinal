package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
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
public abstract class Nieruchomosc {

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

    @OneToMany(mappedBy = "zapisujeZmianyDla", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<HistoriaZmiany> jestZawieranaPrzez = new HashSet<>();

    @OneToMany(mappedBy = "zawiera", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<OgloszenieWynajmu> jestCzescia = new HashSet<>();

    @OneToMany(mappedBy = "nieruchomosc", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private Set<Wlasnosc> wlasnosci = new HashSet<>();

    @OneToMany(mappedBy = "dotyczy", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<UmowaNajmu> ma = new HashSet<>();

    public enum StatusDostepnosci {
        DOSTEPNE,
        ZAJETE
    }
}