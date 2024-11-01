package de.nordakademie.zellulaere_automaten;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The {@link ZellulaereAutomatenApplication} class serves as the entry point for the Spring Boot application.
 * It initializes the application context and starts the process of getting user inputs
 * to run a series of experiments.
 *
 * @author Jannick Gottschalk
 * @author Lars Nicht
 */
@SpringBootApplication
public class ZellulaereAutomatenApplication {

	/**
	 * The main method serves as the entry point for the Spring Boot application.
	 * It initializes the application context and starts the process of getting user inputs
	 * to run a series of experiments.
	 *
	 * @param args command-line arguments (not used)
	 */
	public static void main(String[] args) {
		SpringApplication.run(ZellulaereAutomatenApplication.class, args);

		InputHandler inputHandler = new InputHandler();
		inputHandler.getUserInputs();
	}
}
