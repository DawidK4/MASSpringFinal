package edu.pja.mas.dkucharski.mas.repository;

import edu.pja.mas.dkucharski.mas.model.Osoba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OsobaRepository extends CrudRepository<Osoba, Long> {
}
