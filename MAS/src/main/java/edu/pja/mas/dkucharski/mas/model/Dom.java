package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
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
public class Dom extends Nieruchomosc{

    @Min(1)
    private double wielkoscDzialki;

    @Min(0)
    private int miejscaWGarazu;
}
