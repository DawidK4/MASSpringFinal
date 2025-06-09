package edu.pja.mas.dkucharski.mas.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable // Oznacza, że ta klasa może być osadzonym identyfikatorem
@Data // Lombok generuje gettery, settery, equals i hashCode
@NoArgsConstructor
@AllArgsConstructor
public class WlasnoscId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long nieruchomoscId;
    private Long wlascicielNieruchomosciId;
}