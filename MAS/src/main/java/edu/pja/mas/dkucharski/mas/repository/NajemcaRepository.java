package edu.pja.mas.dkucharski.mas.repository;

import edu.pja.mas.dkucharski.mas.model.Najemca;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NajemcaRepository extends CrudRepository<Najemca, Long> {
}
