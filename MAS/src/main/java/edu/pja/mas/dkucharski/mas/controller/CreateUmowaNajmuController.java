package edu.pja.mas.dkucharski.mas.controller;

import edu.pja.mas.dkucharski.mas.model.Najemca;
import edu.pja.mas.dkucharski.mas.model.Nieruchomosc;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class CreateUmowaNajmuController {

    @FXML
    private TextField dataStartField;

    @FXML
    private TextField dataKoniecField;

    @FXML
    private TextField zaliczkaField;

    private Nieruchomosc nieruchomosc;
    private Najemca najemca;

    public void initData(Nieruchomosc nieruchomosc, Najemca najemca) {
        this.nieruchomosc = nieruchomosc;
        this.najemca = najemca;

        // Możesz uzupełnić np. domyślne wartości pól lub dodać etykiety
    }
}
