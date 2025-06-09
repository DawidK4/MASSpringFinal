package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

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

    @Min(0)
    private double kwotaNetto;

    @NotBlank(message = "Status must not be blank!")
    @NotNull
    private String dataPlatnosci;

    @NotBlank(message = "Status must not be blank!")
    @NotNull
    private String dataWygenerowaniaPlatnosci;

    @NotBlank(message = "Status must not be blank!")
    @NotNull
    private String terminPlatnosci;

    @Min(0)
    private double kara;

    public enum Status{
        ZAPLACONA,
        OCZEKUJACA
    }
}
