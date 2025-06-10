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
public class AgentNieruchomosci {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Numer licencji agenta nie może być pusty!")
    @NotNull
    @Column(unique = true)
    private String numerLicencji;

    @OneToMany(mappedBy = "jestZarzadzanyPrzez", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<OgloszenieWynajmu> zarzadza = new HashSet<>();

    @OneToOne(optional = false)
    @JoinColumn(name = "osoba_id", unique = true)
    private Osoba osoba;
}