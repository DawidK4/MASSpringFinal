package edu.pja.mas.dkucharski.mas;

import edu.pja.mas.dkucharski.mas.model.*;
import edu.pja.mas.dkucharski.mas.repository.NieruchomoscRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final NieruchomoscRepository nieruchomoscRepository;

    public DataInitializer(NieruchomoscRepository nieruchomoscRepository
                            ) {
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
                    .wielkoscDzialki(100.0)
                    .miejscaWGarazu(2)
                    .build();

            Nieruchomosc dom2 = Dom.builder()
                    .adres("Kraków")
                    .wielkosc(200.0)
                    .liczbaPokoi(6)
                    .cenaNajmu(400000)
                    .statusDostepnosci(Nieruchomosc.StatusDostepnosci.DOSTEPNE)
                    .wielkoscDzialki(101.0)
                    .miejscaWGarazu(2)
                    .build();

            Nieruchomosc apartament1 = Apartament.builder()
                    .adres("Gdańsk")
                    .wielkosc(80.0)
                    .liczbaPokoi(3)
                    .cenaNajmu(200000)
                    .statusDostepnosci(Nieruchomosc.StatusDostepnosci.DOSTEPNE)
                    .pietro(2)
                    .pietro(1)
                    .build();

            Nieruchomosc apartament2 = Apartament.builder()
                    .adres("Wrocław")
                    .wielkosc(90.0)
                    .liczbaPokoi(4)
                    .cenaNajmu(250000)
                    .statusDostepnosci(Nieruchomosc.StatusDostepnosci.DOSTEPNE)
                    .pietro(3)
                    .pietro(2)
                    .build();

            Nieruchomosc jednostkaKomercyjna1 = JednostkaKomercyjna.builder()
                    .adres("Poznań")
                    .wielkosc(300.0)
                    .liczbaPokoi(10)
                    .cenaNajmu(500000)
                    .statusDostepnosci(Nieruchomosc.StatusDostepnosci.DOSTEPNE)
                    .przeznaczenie("Biuro")
                    .liczbaPokoiUzytkowych(8)
                    .build();

            Nieruchomosc jednostkaKomercyjna2 = JednostkaKomercyjna.builder()
                    .adres("Łódź")
                    .wielkosc(250.0)
                    .liczbaPokoi(8)
                    .cenaNajmu(450000)
                    .statusDostepnosci(Nieruchomosc.StatusDostepnosci.DOSTEPNE)
                    .przeznaczenie("Sklep")
                    .liczbaPokoiUzytkowych(6)
                    .build();

            nieruchomoscRepository.save(dom1);
            nieruchomoscRepository.save(dom2);
            nieruchomoscRepository.save(apartament1);
            nieruchomoscRepository.save(apartament2);
            nieruchomoscRepository.save(jednostkaKomercyjna1);
            nieruchomoscRepository.save(jednostkaKomercyjna2);
        }
    }
}