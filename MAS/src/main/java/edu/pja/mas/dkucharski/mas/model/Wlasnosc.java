package edu.pja.mas.dkucharski.mas.model;

import edu.pja.mas.dkucharski.mas.model.Nieruchomosc;
import edu.pja.mas.dkucharski.mas.model.WlascicielNieruchomosci;
import edu.pja.mas.dkucharski.mas.model.WlasnoscId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Wlasnosc {

    @EmbeddedId
    private WlasnoscId id = new WlasnoscId();

    @ManyToOne
    @MapsId("wlascicielId")
    private WlascicielNieruchomosci wlasciciel;

    @ManyToOne
    @MapsId("nieruchomoscId")
    private Nieruchomosc nieruchomosc;

    @NotNull
    private double share;
}