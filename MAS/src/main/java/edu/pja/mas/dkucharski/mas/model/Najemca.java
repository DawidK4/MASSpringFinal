package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Najemca {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "PESEL nie może być pusty!")
    @NotNull
    @Column(unique = true) // PESEL powinien być unikalny
    private String PESEL;

    @OneToOne(optional = false)
    @JoinColumn(name = "osoba_id", unique = true)
    private Osoba osoba;


    @OneToMany(mappedBy = "jestPodpisanaPrzez", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    private List<UmowaNajmu> podpisal = new ArrayList<>();
}