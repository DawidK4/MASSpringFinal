package edu.pja.mas.dkucharski.mas.model;

import edu.pja.mas.dkucharski.mas.model.UmowaNajmu;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.Builder;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class Platnosc {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Min(0)
    private double kwotaBrutto;

    @Column(nullable = false)
    private double kwotaNetto;

    private LocalDate dataPlatnosci;

    @NotNull
    @Builder.Default
    private LocalDate dataWygenerowaniaPlatnosci = LocalDate.now();

    @Column
    private LocalDate terminPlatnosci;

    @Min(0)
    private double kara;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "umowa_najmu_id", nullable = false)
    @NotNull
    private UmowaNajmu dotyczy;

    @PrePersist
    public void prePersist() {
        this.kwotaNetto = 0.8 * this.kwotaBrutto;
        this.terminPlatnosci = dataWygenerowaniaPlatnosci != null
                ? dataWygenerowaniaPlatnosci.plusDays(30)
                : null;
    }

    public enum Status {
        ZAPLACONA,
        OCZEKUJACA
    }
}