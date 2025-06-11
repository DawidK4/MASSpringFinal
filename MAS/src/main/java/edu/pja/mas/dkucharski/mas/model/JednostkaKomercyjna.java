package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class JednostkaKomercyjna extends Nieruchomosc{

    @NotBlank(message = "Przeznaczenie nie może być puste!")
    @Size(max = 200)
    private String przeznaczenie;

    @Min(1)
    private int liczbaPokoiUzytkowych;
}
