package de.nordakademie.zellulaere_automaten.experiment;

import de.nordakademie.zellulaere_automaten.logger.LoggerTypes;

/**
 * The {@code IExperiment} interface defines the contract for experiments.
 * It requires implementing classes to provide a method for running the experiment.
 */
public interface IExperiment {

    /**
     * Runs the experiment with the specified logger type.
     *
     * @param loggerType the type of logger to be used for logging the experiment
     */
    void runExperiment(LoggerTypes loggerType);
}