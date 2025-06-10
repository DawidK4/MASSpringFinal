package edu.pja.mas.dkucharski.mas;

import edu.pja.mas.dkucharski.mas.model.Dom;
import edu.pja.mas.dkucharski.mas.model.Nieruchomosc;
import edu.pja.mas.dkucharski.mas.repository.NieruchomoscRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final NieruchomoscRepository nieruchomoscRepository;

    public DataInitializer(NieruchomoscRepository nieruchomoscRepository) {
        this.nieruchomoscRepository = nieruchomoscRepository;
    }

    @Override
    public void run(String... args) {
        if (nieruchomoscRepository.count() == 0) {
            Nieruchomosc dom1 = Dom.builder()
                    .adres("Warszawa")
                    .wielkosc(150.0)
                    .liczbaPokoi(5)
                    .cenaNajmu(300000)
                    .statusDostepnosci(Nieruchomosc.StatusDostepnosci.DOSTEPNE)
                    .build();

            Nieruchomosc dom2 = Dom.builder()
                    .adres("Kraków")
                    .wielkosc(200.0)
                    .liczbaPokoi(6)
                    .cenaNajmu(400000)
                    .statusDostepnosci(Nieruchomosc.StatusDostepnosci.DOSTEPNE)
                    .build();

            nieruchomoscRepository.save(dom1);
            nieruchomoscRepository.save(dom2);
        }
    }
}