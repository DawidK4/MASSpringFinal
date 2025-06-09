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
@ToString(exclude = {"generuje"}) // Exclude generuje to prevent StackOverflowError
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

    @ManyToOne
    @JoinColumn(name = "nieruchomosc_id")
    @NotNull
    private Nieruchomosc zawiera;

    @OneToMany(mappedBy = "umowaNajmu", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private java.util.List<Platnosc> generuje = new java.util.ArrayList<>();

    // --- Dodana asocjacja z Najemcą ---
    @ManyToOne(optional = false) // UmowaNajmu musi mieć 1 Najemcę
    @JoinColumn(name = "najemca_id", nullable = false)
    @NotNull // Dodatkowe walidacje dla pewności
    private Najemca najemca; // Pole do przechowywania referencji do Najemcy
    // ------------------------------------

    public enum StanUmowy {
        W_EDYCJI,
        SZKIC,
        AKTYWNA
    }
}