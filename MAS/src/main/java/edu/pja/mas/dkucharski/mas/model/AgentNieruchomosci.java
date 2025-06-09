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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(exclude = {"ogloszeniaWynajmu"}) // Wykluczamy kolekcję, aby uniknąć StackOverflowError
public class AgentNieruchomosci {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Numer licencji agenta nie może być pusty!")
    @NotNull
    @Column(unique = true)
    private String numerLicencji;

    @OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "osoba_id", nullable = false, unique = true)
    private Osoba osoba;

    // --- Nowa asocjacja z OgloszenieWynajmu ---
    @OneToMany(mappedBy = "agentNieruchomosci", cascade = CascadeType.ALL, orphanRemoval = true)
    // 'mappedBy' wskazuje pole w klasie OgloszenieWynajmu, które jest właścicielem relacji
    private Set<OgloszenieWynajmu> zarzadza = new HashSet<>();
    // ------------------------------------------

    // Metody pomocnicze do zarządzania dwukierunkową relacją (dobra praktyka)
    public void addOgloszenieWynajmu(OgloszenieWynajmu ogloszenie) {
        if (this.zarzadza == null) {
            this.zarzadza = new HashSet<>();
        }
        if (!this.zarzadza.contains(ogloszenie)) {
            this.zarzadza.add(ogloszenie);
            ogloszenie.setAgentNieruchomosci(this); // Ustawienie referencji powrotnej
        }
    }

    public void removeOgloszenieWynajmu(OgloszenieWynajmu ogloszenie) {
        if (this.zarzadza != null && this.zarzadza.remove(ogloszenie)) {
            ogloszenie.setAgentNieruchomosci(null); // Usunięcie referencji powrotnej
        }
    }
}