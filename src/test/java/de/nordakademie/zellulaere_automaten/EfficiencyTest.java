package de.nordakademie.zellulaere_automaten;

import de.nordakademie.zellulaere_automaten.experiment.ExperimentFactory;
import de.nordakademie.zellulaere_automaten.experiment.IExperiment;
import de.nordakademie.zellulaere_automaten.logger.LoggerTypes;
import org.springframework.boot.SpringApplication;

import java.time.Duration;
import java.time.Instant;

/**
 * The {@code EfficiencyTest} class contains unit tests to measure the efficiency of various algorithms and data structures.
 * It aims to ensure that the implementations perform within acceptable time and space limits.
 *
 * Note: GameOfLifeExperiment2Structure2, ParityExperiment1Structure2 and ParityExperiment2Structure2 take a bit longer.
 */
public class EfficiencyTest {

    public static void main(String[] args) {
        SpringApplication.run(ZellulaereAutomatenApplication.class, args);

        // Create an instance of the ExperimentFactory to generate experiments
        ExperimentFactory experimentFactory = new ExperimentFactory();

        // Define an array of experiments to be run
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

        // Iterate over each experiment(LogConsole), run it, and log the execution time
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