package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "wlasciciel", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private Set<Wlasnosc> wlasnosci = new HashSet<>();

    @OneToOne(optional = false)
    @JoinColumn(name = "osoba_id", unique = true)
    @EqualsAndHashCode.Exclude
    private Osoba osoba;
}