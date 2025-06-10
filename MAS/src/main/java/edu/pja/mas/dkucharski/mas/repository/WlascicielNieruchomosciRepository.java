package edu.pja.mas.dkucharski.mas.repository;

import edu.pja.mas.dkucharski.mas.model.Najemca;
import edu.pja.mas.dkucharski.mas.model.Nieruchomosc;
import edu.pja.mas.dkucharski.mas.model.WlascicielNieruchomosci;
import org.springframework.data.repository.CrudRepository;

public interface WlascicielNieruchomosciRepository extends CrudRepository<WlascicielNieruchomosci, Long> {
}
