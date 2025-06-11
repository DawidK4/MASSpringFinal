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
//        // Create Osoba
//        Osoba osoba1 = Osoba.builder().imie("Anna").nazwisko("Kowalska").numerTelefonu("123456789").email("anna@example.com").build();
//        Osoba osoba2 = Osoba.builder().imie("Jan").nazwisko("Nowak").numerTelefonu("987654321").email("jan@example.com").build();
//        osobaRepository.save(osoba1);
//        osobaRepository.save(osoba2);
//
//        // Create WlascicielNieruchomosci
//        WlascicielNieruchomosci wlasciciel1 = WlascicielNieruchomosci.builder().NIP("111213121111").osoba(osoba1).build();
//        WlascicielNieruchomosci wlasciciel2 = WlascicielNieruchomosci.builder().NIP("22222223122").osoba(osoba2).build();
//        wlascicielRepository.save(wlasciciel1);
//        wlascicielRepository.save(wlasciciel2);
//
//        // Create Najemca
//        Najemca najemca1 = Najemca.builder().PESEL("9001213112345").osoba(osoba1).build();
//        Najemca najemca2 = Najemca.builder().PESEL("83321020254321").osoba(osoba2).build();
//        najemcaRepository.save(najemca1);
//        najemcaRepository.save(najemca2);
//
//        // Create AgentNieruchomosci
//        AgentNieruchomosci agent1 = AgentNieruchomosci.builder().numerLicencji("LIC23123").osoba(osoba1).build();
//        AgentNieruchomosci agent2 = AgentNieruchomosci.builder().numerLicencji("LIC45236").osoba(osoba2).build();
//        agentNieruchomosciRepository.save(agent1);
//        agentNieruchomosciRepository.save(agent2);
//
//        // Create Nieruchomosc (Dom, Apartament, JednostkaKomercyjna)
//        Dom dom1 = Dom.builder().adres("Warszawa 1").wielkosc(120.0).liczbaPokoi(4).cenaNajmu(3000).statusDostepnosci(Nieruchomosc.StatusDostepnosci.DOSTEPNE).wielkoscDzialki(500).miejscaWGarazu(2).build();
//        Dom dom2 = Dom.builder().adres("Kraków 2").wielkosc(140.0).liczbaPokoi(5).cenaNajmu(3500).statusDostepnosci(Nieruchomosc.StatusDostepnosci.DOSTEPNE).wielkoscDzialki(600).miejscaWGarazu(1).build();
//        Apartament apart1 = Apartament.builder().adres("Gdańsk 3").wielkosc(60.0).liczbaPokoi(2).cenaNajmu(2000).statusDostepnosci(Nieruchomosc.StatusDostepnosci.DOSTEPNE).pietro(1).build();
//        Apartament apart2 = Apartament.builder().adres("Wrocław 4").wielkosc(80.0).liczbaPokoi(3).cenaNajmu(2500).statusDostepnosci(Nieruchomosc.StatusDostepnosci.DOSTEPNE).pietro(2).build();
//        JednostkaKomercyjna kom1 = JednostkaKomercyjna.builder().adres("Poznań 5").wielkosc(300.0).liczbaPokoi(10).cenaNajmu(5000).statusDostepnosci(Nieruchomosc.StatusDostepnosci.DOSTEPNE).przeznaczenie("Biuro").liczbaPokoiUzytkowych(8).build();
//        JednostkaKomercyjna kom2 = JednostkaKomercyjna.builder().adres("Łódź 6").wielkosc(250.0).liczbaPokoi(8).cenaNajmu(4500).statusDostepnosci(Nieruchomosc.StatusDostepnosci.DOSTEPNE).przeznaczenie("Sklep").liczbaPokoiUzytkowych(6).build();
//        nieruchomoscRepository.save(dom1);
//        nieruchomoscRepository.save(dom2);
//        nieruchomoscRepository.save(apart1);
//        nieruchomoscRepository.save(apart2);
//        nieruchomoscRepository.save(kom1);
//        nieruchomoscRepository.save(kom2);
//
//        // Create Wlasnosc with composite key
//        WlasnoscId wlasnoscId1 = new WlasnoscId();
//        wlasnoscId1.setWlascicielId(wlasciciel1.getId());
//        wlasnoscId1.setNieruchomoscId(dom1.getId());
//        Wlasnosc wlasnosc1 = Wlasnosc.builder()
//                .id(wlasnoscId1)
//                .wlasciciel(wlasciciel1)
//                .nieruchomosc(dom1)
//                .share(0.6)
//                .build();
//
//        WlasnoscId wlasnoscId2 = new WlasnoscId();
//        wlasnoscId2.setWlascicielId(wlasciciel2.getId());
//        wlasnoscId2.setNieruchomoscId(dom2.getId());
//        Wlasnosc wlasnosc2 = Wlasnosc.builder()
//                .id(wlasnoscId2)
//                .wlasciciel(wlasciciel2)
//                .nieruchomosc(dom2)
//                .share(0.4)
//                .build();
//
//        wlasnoscRepository.save(wlasnosc1);
//        wlasnoscRepository.save(wlasnosc2);
//
//        // Create OgloszenieWynajmu
//        OgloszenieWynajmu ogloszenie1 = OgloszenieWynajmu.builder().tytul("Dom do wynajęcia Warszawa").dataPublikacji("2024-06-01").status(OgloszenieWynajmu.Status.AKTUALNE).zawiera(dom1).jestZarzadzanyPrzez(agent1).build();
//        OgloszenieWynajmu ogloszenie2 = OgloszenieWynajmu.builder().tytul("Apartament do wynajęcia Gdańsk").dataPublikacji("2024-06-02").status(OgloszenieWynajmu.Status.AKTUALNE).zawiera(apart1).jestZarzadzanyPrzez(agent2).build();
//        ogloszenieWynajmuRepository.save(ogloszenie1);
//        ogloszenieWynajmuRepository.save(ogloszenie2);
//
//        // Create UmowaNajmu
//        UmowaNajmu umowa1 = UmowaNajmu.builder().dataStart("2024-07-01").dataKoniec("2025-07-01").zaliczka(1000).stanUmowy(UmowaNajmu.StanUmowy.AKTYWNA).dotyczy(dom1).jestPodpisanaPrzez(najemca1).build();
//        UmowaNajmu umowa2 = UmowaNajmu.builder().dataStart("2024-08-01").dataKoniec("2025-08-01").zaliczka(1200).stanUmowy(UmowaNajmu.StanUmowy.AKTYWNA).dotyczy(apart1).jestPodpisanaPrzez(najemca2).build();
//        umowaNajmuRepository.save(umowa1);
//        umowaNajmuRepository.save(umowa2);
//
//        // Create Platnosc
//        Platnosc platnosc1 = Platnosc.builder()
//                .kwotaBrutto(3000)
//                .dataPlatnosci(LocalDate.parse("2024-07-05"))
//                .dataWygenerowaniaPlatnosci(LocalDate.parse("2024-07-01"))
//                .kara(0)
//                .status(Platnosc.Status.ZAPLACONA)
//                .dotyczy(umowa1)
//                .build();
//
//        Platnosc platnosc2 = Platnosc.builder()
//                .kwotaBrutto(2000)
//                .dataWygenerowaniaPlatnosci(LocalDate.parse("2024-08-01"))
//                .kara(0)
//                .status(Platnosc.Status.OCZEKUJACA)
//                .dotyczy(umowa2)
//                .build();
//        platnoscRepository.save(platnosc1);
//        platnoscRepository.save(platnosc2);
////
//        // Create HistoriaZmiany
//        HistoriaZmiany historia1 = HistoriaZmiany.builder().dataZmiany("2024-05-01").imiePoprzedniegoWlasciciela("Piotr").nazwiskoPoprzedniegoWlasciciela("Zielinski").zapisujeZmianyDla(dom1).opis("Zmiana właściciela.").build();
//        HistoriaZmiany historia2 = HistoriaZmiany.builder().dataZmiany("2024-05-02").imiePoprzedniegoWlasciciela("Maria").nazwiskoPoprzedniegoWlasciciela("Kowal").zapisujeZmianyDla(apart1).opis("Aktualizacja danych.").build();
//        jednostkaKomercyjnaRepository.save(kom1); // just to ensure repo is used
//        jednostkaKomercyjnaRepository.save(kom2);
//        historiaZmianyRepository.save(historia1);
//        historiaZmianyRepository.save(historia2);
    }
}