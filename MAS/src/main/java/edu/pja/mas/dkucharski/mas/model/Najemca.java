package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(exclude = {"umowyNajmu"}) // Wykluczamy kolekcję, by uniknąć StackOverflowError
public class Najemca {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "PESEL nie może być pusty!")
    @NotNull
    @Column(unique = true) // PESEL powinien być unikalny
    private String PESEL;

    @OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "osoba_id", nullable = false, unique = true)
    private Osoba osoba;

    // --- Dodana asocjacja z UmowąNajmu (standardowa relacja OneToMany) ---
    @OneToMany(mappedBy = "najemca", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UmowaNajmu> umowyNajmu = new ArrayList<>();
    // ----------------------------------------------------------------------

    // Metody pomocnicze do zarządzania kolekcją (opcjonalnie, ale zalecane)
    public void addUmowaNajmu(UmowaNajmu umowa) {
        if (this.umowyNajmu == null) {
            this.umowyNajmu = new ArrayList<>();
        }
        if (!this.umowyNajmu.contains(umowa)) { // Sprawdź, czy umowa już nie istnieje
            this.umowyNajmu.add(umowa);
            umowa.setNajemca(this);
        }
    }

    public void removeUmowaNajmu(UmowaNajmu umowa) {
        if (this.umowyNajmu != null && this.umowyNajmu.remove(umowa)) {
            umowa.setNajemca(null);
        }
    }
}