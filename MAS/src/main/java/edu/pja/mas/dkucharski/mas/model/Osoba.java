package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.*;
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
public class Osoba {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Imię nie może być puste!")
    @NotNull
    private String imie;

    @NotBlank(message = "Nazwisko nie może być puste!")
    @NotNull
    private String nazwisko;

    @NotBlank(message = "Numer telefonu nie może być pusty!")
    @NotNull
    private String numerTelefonu;

    @NotBlank(message = "Adres e-mail nie może być pusty!")
    @NotNull
    private String email;
}
