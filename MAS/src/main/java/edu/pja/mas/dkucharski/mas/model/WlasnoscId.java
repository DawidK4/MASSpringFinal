package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WlasnoscId implements Serializable {
    private Long wlascicielId;
    private Long nieruchomoscId;
}