package de.nordakademie.zellulaere_automaten;

import de.nordakademie.zellulaere_automaten.experiment.ExperimentFactory;
import de.nordakademie.zellulaere_automaten.experiment.IExperiment;
import de.nordakademie.zellulaere_automaten.logger.LoggerTypes;
import org.springframework.boot.SpringApplication;

import java.time.Duration;
import java.time.Instant;

/**
 * The {@code EfficiencyValidation} class contains unit tests to measure and verify the efficiency
 * of various grid implementations and experiments.
 *
 * <p>This class is designed to evaluate the time complexity and resource usage of the
 * experiments, including initialization, state calculation and grid usage.</p>
 *
 * <p>Each experiment initializes a grid with a specific configuration, performs a series of
 * operations, and measures the time taken to complete these operations.</p>
 *
 * <p>Note: The EfficiencyTest is intended to be run in a controlled environment to
 * minimize external factors that could affect the performance measurements.</p>
 *
 * @Author: Lars Nicht
 */
public class EfficiencyValidation {

    public static void main(String[] args) {
        SpringApplication.run(ZellulaereAutomatenApplication.class, args);

        Instant start = Instant.now();
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
            Instant experimentStart = Instant.now();
            experiment.runExperiment(LoggerTypes.getType(1));
            Instant experimentEnd = Instant.now();
            Duration experimentTimeElapsed = Duration.between(experimentStart, experimentEnd);
            System.out.println("Time taken for " + experiment.getClass().getSimpleName() + ": " + experimentTimeElapsed.toMillis() + " milliseconds");
        }

        Instant end = Instant.now();
        Duration totalTimeElapsed = Duration.between(start, end);
        System.out.println("\nTotal time taken for all experiments: " + totalTimeElapsed.toMillis() + " milliseconds or "
                + totalTimeElapsed.toSeconds() + " seconds!");


    }
}