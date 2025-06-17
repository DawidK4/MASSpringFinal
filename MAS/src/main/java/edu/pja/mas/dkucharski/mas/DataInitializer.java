package edu.pja.mas.dkucharski.mas;

import edu.pja.mas.dkucharski.mas.model.*;
import edu.pja.mas.dkucharski.mas.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {
    private final NieruchomoscRepository nieruchomoscRepository;
    private final OgloszenieWynajmuRepository ogloszenieWynajmuRepository;
    private final WlascicielNieruchomosciRepository wlascicielRepository;
    private final NajemcaRepository najemcaRepository;
    private final AgentNieruchomosciRepository agentNieruchomosciRepository;
    private final JednostkaKomercyjnaRepository jednostkaKomercyjnaRepository;
    private final OsobaRepository osobaRepository;
    private final PlatnoscRepository platnoscRepository;
    private final UmowaNajmuRepository umowaNajmuRepository;
    private final WlasnoscRepository wlasnoscRepository;
    private final HistoriaZmianyRepository historiaZmianyRepository;

    public DataInitializer(NieruchomoscRepository nieruchomoscRepository,
                           OgloszenieWynajmuRepository ogloszenieWynajmuRepository,
                           WlascicielNieruchomosciRepository wlascicielNieruchomosciRepository,
                           NajemcaRepository najemcaRepository,
                           AgentNieruchomosciRepository agentNieruchomosciRepository,
                           JednostkaKomercyjnaRepository jednostkaKomercyjnaRepository,
                           OsobaRepository osobaRepository,
                           PlatnoscRepository platnoscRepository,
                           UmowaNajmuRepository umowaNajmuRepository,
                           WlasnoscRepository wlasnoscRepository,
                           HistoriaZmianyRepository historiaZmianyRepository) {

        this.nieruchomoscRepository = nieruchomoscRepository;
        this.ogloszenieWynajmuRepository = ogloszenieWynajmuRepository;
        this.wlascicielRepository = wlascicielNieruchomosciRepository;
        this.najemcaRepository = najemcaRepository;
        this.agentNieruchomosciRepository = agentNieruchomosciRepository;
        this.jednostkaKomercyjnaRepository = jednostkaKomercyjnaRepository;
        this.osobaRepository = osobaRepository;
        this.platnoscRepository = platnoscRepository;
        this.umowaNajmuRepository = umowaNajmuRepository;
        this.wlasnoscRepository = wlasnoscRepository;
        this.historiaZmianyRepository = historiaZmianyRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
//        // Create Osoba entities
//        Osoba osoba = Osoba.builder()
//                .imie("Jan")
//                .nazwisko("Kowalski")
//                .email("mail@mail")
//                .numerTelefonu("123456789")
//                .build();
//
//        Osoba osoba1 = Osoba.builder()
//                .imie("Anna")
//                .nazwisko("Nowak")
//                .email("mail@mail")
//                .numerTelefonu("987654321")
//                .build();
//
//        Osoba agent = Osoba.builder()
//                .imie("Piotr")
//                .nazwisko("Zielinski")
//                .email("mail@mail")
//                .numerTelefonu("555666777")
//                .build();
//
//        Osoba najemca = Osoba.builder()
//                .imie("Krzysztof")
//                .nazwisko("Wojcik")
//                .email("mail@mail")
//                .numerTelefonu("444333222")
//                .build();
//
//        Osoba osobaError = Osoba.builder()
//                .imie("Błędny")
//                .nazwisko("Osoba")
//                .email("mail@błędny")
//                .numerTelefonu("000111222").build();
//
//        // Save Osoba entities first
//        osobaRepository.save(osoba);
//        osobaRepository.save(osoba1);
//        osobaRepository.save(agent);
//        osobaRepository.save(najemca);
//        osobaRepository.save(osobaError);
//
//        // Create and save AgentNieruchomosci
//        AgentNieruchomosci agentNieruchomosci = AgentNieruchomosci.builder()
//                .osoba(agent)
//                .numerLicencji("AGENT12345")
//                .build();
//        agentNieruchomosciRepository.save(agentNieruchomosci);
//
//        // Create and save WlascicielNieruchomosci
//        WlascicielNieruchomosci wlasciciel1 = WlascicielNieruchomosci.builder()
//                .NIP("1234567890")
//                .osoba(osoba)
//                .build();
//        WlascicielNieruchomosci wlasciciel2 = WlascicielNieruchomosci.builder()
//                .NIP("0987654321")
//                .osoba(osoba1)
//                .build();
//        WlascicielNieruchomosci wlascicielNieruchomosciError = WlascicielNieruchomosci.builder()
//                .NIP("000111222")
//                .osoba(osobaError)
//                .build();
//        wlascicielRepository.save(wlasciciel1);
//        wlascicielRepository.save(wlasciciel2);
//        wlascicielRepository.save(wlascicielNieruchomosciError);
//
//        // Create and save Najemca
//        Najemca najemca1 = Najemca.builder()
//                .osoba(najemca)
//                .PESEL("12345678901")
//                .build();
//        Najemca najemcaError = Najemca.builder()
//                .osoba(osobaError)
//                .PESEL("32141324132")
//                .build();
//        najemcaRepository.save(najemca1);
//        najemcaRepository.save(najemcaError);
//
//        // Create and save Nieruchomosc
//        Dom dom = Dom.builder()
//                .adres("ul. Kwiatowa 1, Warszawa")
//                .wielkosc(100.0)
//                .liczbaPokoi(4)
//                .cenaNajmu(100.0)
//                .statusDostepnosci(Nieruchomosc.StatusDostepnosci.DOSTEPNE)
//                .wielkoscDzialki(100.0)
//                .miejscaWGarazu(3)
//                .build();
//        Apartament apartament = Apartament.builder()
//                .adres("ul. Sloneczna 2, Krakow")
//                .wielkosc(80.0)
//                .liczbaPokoi(3)
//                .cenaNajmu(80.0)
//                .statusDostepnosci(Nieruchomosc.StatusDostepnosci.ZAJETE)
//                .pietro(2)
//                .build();
//        JednostkaKomercyjna jednostkaKomercyjna = JednostkaKomercyjna.builder()
//                .adres("ul. Handlowa 3, Gdansk")
//                .wielkosc(200.0)
//                .cenaNajmu(200.0)
//                .statusDostepnosci(Nieruchomosc.StatusDostepnosci.DOSTEPNE)
//                .przeznaczenie("Sklep")
//                .liczbaPokoi(1)
//                .liczbaPokoiUzytkowych(5)
//                .build();
//        Dom error = Dom.builder()
//                .adres("ul. Błędna 4, Warszawa")
//                .wielkosc(150.0)
//                .liczbaPokoi(5)
//                .cenaNajmu(150.0)
//                .statusDostepnosci(Nieruchomosc.StatusDostepnosci.DOSTEPNE)
//                .wielkoscDzialki(200.0)
//                .miejscaWGarazu(2)
//                .build();
//        nieruchomoscRepository.save(dom);
//        nieruchomoscRepository.save(apartament);
//        nieruchomoscRepository.save(jednostkaKomercyjna);
//        nieruchomoscRepository.save(error);
//
//        // Create and save OgloszenieWynajmu and HistoriaZmiany
//        OgloszenieWynajmu ogloszenieWynajmu = OgloszenieWynajmu.builder()
//                .tytul("Wynajem domu w Warszawie")
//                .dataPublikacji("2023-10-01")
//                .zawiera(dom)
//                .status(OgloszenieWynajmu.Status.ZARCHIWIZOWANE)
//                .jestZarzadzanyPrzez(agentNieruchomosci)
//                .build();
//        ogloszenieWynajmuRepository.save(ogloszenieWynajmu);
//
//        HistoriaZmiany historiaZmiany = HistoriaZmiany.builder()
//                .dataZmiany("2023-10-01")
//                .imiePoprzedniegoWlasciciela("POprzedni Imie")
//                .nazwiskoPoprzedniegoWlasciciela("POprzedni Nazwisko")
//                .zapisujeZmianyDla(dom)
//                .opis("osdadasdfasfdsafasfsadfsa")
//                .build();
//        historiaZmianyRepository.save(historiaZmiany);
//
//        // Create and save Wlasnosc (ownerships)
//        Wlasnosc wlasnosc1 = new Wlasnosc();
//        wlasnosc1.setWlasciciel(wlasciciel2);
//        wlasnosc1.setNieruchomosc(dom);
//        wlasnosc1.setShare(70);
//
//        Wlasnosc wlasnosc2 = new Wlasnosc();
//        wlasnosc2.setWlasciciel(wlasciciel1);
//        wlasnosc2.setNieruchomosc(dom);
//        wlasnosc2.setShare(30);
//
//        Wlasnosc wlasnosc3 = new Wlasnosc();
//        wlasnosc3.setWlasciciel(wlasciciel1);
//        wlasnosc3.setNieruchomosc(apartament);
//        wlasnosc3.setShare(100);
//
//        Wlasnosc wlasnosc4 = new Wlasnosc();
//        wlasnosc4.setWlasciciel(wlasciciel1);
//        wlasnosc4.setNieruchomosc(jednostkaKomercyjna);
//        wlasnosc4.setShare(100);
//
//        Wlasnosc wlasnosc5 = new Wlasnosc();
//        wlasnosc5.setWlasciciel(wlascicielNieruchomosciError);
//        wlasnosc5.setNieruchomosc(error);
//        wlasnosc5.setShare(100);
//
//        wlasnoscRepository.save(wlasnosc1);
//        wlasnoscRepository.save(wlasnosc2);
//        wlasnoscRepository.save(wlasnosc3);
//        wlasnoscRepository.save(wlasnosc4);
//        wlasnoscRepository.save(wlasnosc5);
    }
}