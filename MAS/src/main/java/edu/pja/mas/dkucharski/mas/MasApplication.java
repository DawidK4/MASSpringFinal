package edu.pja.mas.dkucharski.mas;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MasApplication {
	public static void main(String[] args) {
		Application.launch(JavaFxApp.class, args);
	}
}