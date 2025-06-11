package edu.pja.mas.dkucharski.mas.controller;

import edu.pja.mas.dkucharski.mas.model.Najemca;
import edu.pja.mas.dkucharski.mas.model.Nieruchomosc;
import edu.pja.mas.dkucharski.mas.model.Platnosc;
import edu.pja.mas.dkucharski.mas.model.UmowaNajmu;
import edu.pja.mas.dkucharski.mas.repository.PlatnoscRepository;
import edu.pja.mas.dkucharski.mas.repository.UmowaNajmuRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Component
public class CreateUmowaNajmuController {

    @FXML
    private TextField dataStartField;

    @FXML
    private TextField dataKoniecField;

    @FXML
    private TextField zaliczkaField;

    @FXML
    private Button createButton;

    private Nieruchomosc nieruchomosc;
    private Najemca najemca;
    private UmowaNajmu umowaNajmu;

    @Autowired
    private UmowaNajmuRepository umowaNajmuRepository;

    @Autowired
    private PlatnoscRepository platnoscRepository;

    public void initData(Nieruchomosc nieruchomosc, Najemca najemca, UmowaNajmu umowaNajmu) {
        this.nieruchomosc = nieruchomosc;
        this.najemca = najemca;
        this.umowaNajmu = umowaNajmu;
    }

    @FXML
    public void initialize() {
        if (createButton != null) {
            createButton.setOnAction(event -> onCreateClicked());
        }
    }

    private void onCreateClicked() {
        String startStr = dataStartField.getText();
        String endStr = dataKoniecField.getText();
        String zaliczkaStr = zaliczkaField.getText();

        LocalDate startDate, endDate;
        Double zaliczka = null;

        umowaNajmu.setStanUmowy(UmowaNajmu.StanUmowy.W_EDYCJI);

        try {
            startDate = LocalDate.parse(startStr);
            endDate = LocalDate.parse(endStr);
        } catch (DateTimeParseException e) {
            showError("Invalid date format. Use yyyy-MM-dd.");
            return;
        }

        if (endDate.isBefore(startDate)) {
            showError("End date must be after start date.");
            return;
        }

        if (zaliczkaStr != null && !zaliczkaStr.isBlank()) {
            try {
                zaliczka = Double.parseDouble(zaliczkaStr);
                if (zaliczka < 0) {
                    showError("Deposit cannot be negative.");
                    return;
                }
            } catch (NumberFormatException e) {
                showError("Deposit must be a number.");
                return;
            }
        }

        if (najemca == null || nieruchomosc == null) {
            showError("Missing tenant or property.");
            return;
        }

        umowaNajmu.setDotyczy(nieruchomosc);
        umowaNajmu.setJestPodpisanaPrzez(najemca);
        umowaNajmu.setDataStart(startDate.toString());
        umowaNajmu.setDataKoniec(endDate.toString());
        umowaNajmu.setZaliczka(zaliczka);
        umowaNajmu.setStanUmowy(UmowaNajmu.StanUmowy.AKTYWNA);

        umowaNajmuRepository.save(umowaNajmu);

        Platnosc platnosc = new Platnosc();
        platnosc.setDotyczy(umowaNajmu);
        platnosc.setKwotaBrutto(nieruchomosc.getCenaNajmu());
        platnosc.setStatus(Platnosc.Status.OCZEKUJACA);
        platnosc.setKara(0.0);

        platnoscRepository.save(platnosc);

        showInfo("Lease agreement and payment created successfully.");
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}