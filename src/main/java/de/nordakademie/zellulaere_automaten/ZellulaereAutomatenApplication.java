package de.nordakademie.zellulaere_automaten;

import de.nordakademie.zellulaere_automaten.experiment.ExperimentFactory;
import de.nordakademie.zellulaere_automaten.experiment.IExperiment;
import de.nordakademie.zellulaere_automaten.logger.LoggerTypes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.time.Duration;
import java.time.Instant;

@SpringBootApplication
public class ZellulaereAutomatenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZellulaereAutomatenApplication.class, args);

		ExperimentFactory experimentFactory = new ExperimentFactory();

		IExperiment[] experiments = {
				experimentFactory.createExperiment("GameOfLifeExperiment1Structure1"),
				experimentFactory.createExperiment("GameOfLifeExperiment1Structure2"),
				experimentFactory.createExperiment("GameOfLifeExperiment2Structure1"),
				experimentFactory.createExperiment("GameOfLifeExperiment2Structure2"),
				experimentFactory.createExperiment("GameOfLifeExperiment3Structure1"),
				experimentFactory.createExperiment("GameOfLifeExperiment3Structure2"),
				experimentFactory.createExperiment("ParityExperiment1Structure1"),
				experimentFactory.createExperiment("ParityExperiment1Structure2"),
				experimentFactory.createExperiment("ParityExperiment2Structure1"),
				experimentFactory.createExperiment("ParityExperiment2Structure2")
		};

		for (IExperiment experiment : experiments) {
			Instant start = Instant.now();
			experiment.runExperiment(LoggerTypes.LogFile);
			Instant end = Instant.now();
			Duration timeElapsed = Duration.between(start, end);
			System.out.println("Time taken for " + experiment.getClass().getSimpleName() + ": " + timeElapsed.toMillis() + " milliseconds");
		}

		System.out.println("All experiments have been created and run.");



	}
}
