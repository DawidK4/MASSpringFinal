package edu.pja.mas.dkucharski.mas.controller;

import edu.pja.mas.dkucharski.mas.model.Najemca;
import edu.pja.mas.dkucharski.mas.model.Nieruchomosc;
import edu.pja.mas.dkucharski.mas.model.UmowaNajmu;
import edu.pja.mas.dkucharski.mas.model.Wlasnosc;
import edu.pja.mas.dkucharski.mas.repository.NajemcaRepository;
import edu.pja.mas.dkucharski.mas.repository.NieruchomoscRepository;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class MainViewController {

    @FXML
    private ListView<String> listView;

    @FXML
    private ListView<String> najemcaListView;

    @FXML
    private Button zatwierdzButton;

    private final NieruchomoscRepository nieruchomoscRepository;
    private final NajemcaRepository najemcaRepository;

    private List<Nieruchomosc> nieruchomosci;
    private List<Najemca> najemcy;

    private Nieruchomosc selectedNieruchomosc;
    private Najemca selectedNajemca;
    private UmowaNajmu umowaNajmu;

    @Autowired
    public MainViewController(NieruchomoscRepository nieruchomoscRepository, NajemcaRepository najemcaRepository) {
        this.nieruchomoscRepository = nieruchomoscRepository;
        this.najemcaRepository = najemcaRepository;
    }

    @FXML
    public void initialize() {
        nieruchomosci = nieruchomoscRepository.findAllWithWlasnosciAndOwners();
        List<String> items = nieruchomosci.stream().map(n -> {
            String ownersStr = n.getWlasnosci().stream()
                    .map(Wlasnosc::getWlasciciel)
                    .map(o -> o.getOsoba().getImie() + " " + o.getOsoba().getNazwisko())
                    .distinct()
                    .collect(Collectors.joining(", "));
            return "Adres: " + n.getAdres() + ", Właściciele: " + ownersStr;
        }).collect(Collectors.toList());
        listView.setItems(FXCollections.observableArrayList(items));

        najemcy = StreamSupport
                .stream(najemcaRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        List<String> najemcaItems = najemcy.stream()
                .map(n -> n.getOsoba().getImie() + " " + n.getOsoba().getNazwisko())
                .collect(Collectors.toList());
        najemcaListView.setItems(FXCollections.observableArrayList(najemcaItems));

        listView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            int idx = newVal.intValue();
            selectedNieruchomosc = (idx >= 0 && idx < nieruchomosci.size()) ? nieruchomosci.get(idx) : null;
        });

        najemcaListView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            int idx = newVal.intValue();
            selectedNajemca = (idx >= 0 && idx < najemcy.size()) ? najemcy.get(idx) : null;
        });

        UmowaNajmu umowaNajmu = new UmowaNajmu();
        umowaNajmu.setStanUmowy(UmowaNajmu.StanUmowy.SZKIC);
        System.out.println("UmowaNajmu initialized with state: " + umowaNajmu.getStanUmowy());
        this.umowaNajmu = umowaNajmu;

        zatwierdzButton.setOnAction(event -> onZatwierdzClicked());
    }

    private void onZatwierdzClicked() {
        if (selectedNieruchomosc == null || selectedNajemca == null) {
            showError("Please select both a property and a tenant.");
            return;
        }
        if (selectedNieruchomosc.getStatusDostepnosci() == Nieruchomosc.StatusDostepnosci.ZAJETE) {
            showError("Nieruchomosc is already zajeta");
            return;
        }
        openNextWindow();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Autowired
    private ConfigurableApplicationContext context;

    private void openNextWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CreateUmowaNajmu.fxml"));
            loader.setControllerFactory(context::getBean);

            Parent root = loader.load();
            CreateUmowaNajmuController controller = loader.getController();
            controller.initData(selectedNieruchomosc, selectedNajemca, umowaNajmu);

            Stage currentStage = (Stage) zatwierdzButton.getScene().getWindow();
            double width = currentStage.getWidth();
            double height = currentStage.getHeight() - 175;

            Stage stage = new Stage();
            stage.setTitle("Nowa Umowa Najmu");
            stage.setScene(new Scene(root));
            stage.show();

            stage.setWidth(width);
            stage.setHeight(height);
        } catch (Exception e) {
            e.printStackTrace();
            showError("Wystąpił błąd przy otwieraniu nowego okna: " + e.getMessage());
        }
    }
}