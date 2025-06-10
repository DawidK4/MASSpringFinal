package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wlasnosc {

    @EmbeddedId
    private WlasnoscId id = new WlasnoscId();

    @ManyToOne
    @MapsId("wlascicielId")
    private WlascicielNieruchomosci wlasciciel;

    @ManyToOne
    @MapsId("nieruchomoscId")
    private Nieruchomosc nieruchomosc;

    private double share;
}