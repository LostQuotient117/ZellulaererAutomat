package de.nordakademie.zellulaere_automaten;

import de.nordakademie.zellulaere_automaten.experiment.ExperimentFactory;
import de.nordakademie.zellulaere_automaten.experiment.IExperiment;
import de.nordakademie.zellulaere_automaten.logger.LoggerTypes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class ZellulaereAutomatenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZellulaereAutomatenApplication.class, args);

		String filename = "src/main/java/de/nordakademie/zellulaere_automaten/logger/loggerOutput/Log.log";

		//deletion of old file for the new test
		File file = new File(filename);
		file.delete();

		// Create an instance of ExperimentFactory for testing purposes(to be changed)
		ExperimentFactory experimentFactory = new ExperimentFactory();

		// Run an experiment by name for testing purposes(to be changed)
		IExperiment experiment1 = experimentFactory.createExperiment("GameOfLifeExperiment1Structure1");
		experiment1.runExperiment(LoggerTypes.LogFile);

	}

}
