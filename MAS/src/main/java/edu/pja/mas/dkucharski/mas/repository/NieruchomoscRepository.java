package edu.pja.mas.dkucharski.mas.repository;

import edu.pja.mas.dkucharski.mas.model.Nieruchomosc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface NieruchomoscRepository extends CrudRepository<Nieruchomosc, Long> {
    @Query("SELECT n FROM Nieruchomosc n LEFT JOIN FETCH n.wlasnosci w LEFT JOIN FETCH w.wlasciciel wl LEFT JOIN FETCH wl.osoba")
    List<Nieruchomosc> findAllWithWlasnosciAndOwners();
}