package de.nordakademie.zellulaere_automaten;

import de.nordakademie.zellulaere_automaten.experiment.ExperimentFactory;
import de.nordakademie.zellulaere_automaten.experiment.IExperiment;
import de.nordakademie.zellulaere_automaten.logger.LoggerTypes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZellulaereAutomatenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZellulaereAutomatenApplication.class, args);

		// Create an instance of ExperimentFactory for testing purposes(to be changed)
		ExperimentFactory experimentFactory = new ExperimentFactory();

		// Run an experiment by name for testing purposes(to be changed)
		IExperiment experiment1 = experimentFactory.createExperiment("GameOfLifeExperiment1Structure1");
		experiment1.runExperiment(LoggerTypes.LogConsole);

	}

}
