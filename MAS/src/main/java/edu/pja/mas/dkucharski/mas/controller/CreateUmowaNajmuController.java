package edu.pja.mas.dkucharski.mas.controller;

import edu.pja.mas.dkucharski.mas.model.Najemca;
import edu.pja.mas.dkucharski.mas.model.Nieruchomosc;
import edu.pja.mas.dkucharski.mas.model.UmowaNajmu;
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

    @Autowired
    private UmowaNajmuRepository umowaNajmuRepository;

    public void initData(Nieruchomosc nieruchomosc, Najemca najemca) {
        this.nieruchomosc = nieruchomosc;
        this.najemca = najemca;
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

        // Validate dates
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

        // Validate zaliczka (optional)
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

        // Validate associations
        if (najemca == null || nieruchomosc == null) {
            showError("Missing tenant or property.");
            return;
        }

        // Create and save UmowaNajmu
        UmowaNajmu umowa = new UmowaNajmu();
        umowa.setDotyczy(nieruchomosc);
        umowa.setJestPodpisanaPrzez(najemca);
        umowa.setDataStart(startDate.toString());
        umowa.setDataKoniec(endDate.toString());
        umowa.setStanUmowy(UmowaNajmu.StanUmowy.AKTYWNA);
        umowa.setZaliczka(zaliczka);

        umowaNajmuRepository.save(umowa);

        showInfo("Lease agreement created successfully.");
        // Optionally, close the window or reset fields
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