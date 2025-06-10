package edu.pja.mas.dkucharski.mas.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

@Component
public class MainViewController {

    @FXML
    private Label welcomeLabel;

    @FXML
    public void initialize() {
        welcomeLabel.setText("Welcome to MAS JavaFX + Spring Boot App!");
    }

    // Example: Inject a Spring service
    // @Autowired
    // private SomeService someService;

    // Add more @FXML methods to handle UI actions
}