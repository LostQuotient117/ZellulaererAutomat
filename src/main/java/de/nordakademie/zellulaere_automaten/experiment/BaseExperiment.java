package de.nordakademie.zellulaere_automaten.experiment;

import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.logger.ILogger;
import de.nordakademie.zellulaere_automaten.logger.LoggerFactory;
import de.nordakademie.zellulaere_automaten.logger.LoggerTypes;
import de.nordakademie.zellulaere_automaten.model.Cell;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Set;

/**
 * The {@code BaseExperiment} class provides a base implementation for experiments.
 * It implements the {@link IExperiment} interface and provides common functionality
 * for initializing and running experiments.
 *
 * @author Lars Nicht
 */
public class BaseExperiment implements IExperiment {
    public IGrid grid;
    public Set<Cell> startConfig;
    protected String logExperimentName;
    protected ILogger logger;

    /**
     * Constructs a new {@code BaseExperiment} with the specified grid, start configuration, and log experiment name.
     *
     * @param grid              the grid to be used in the experiment
     * @param startConfig       the initial configuration of cells
     * @param logExperimentName the name of the experiment for logging purposes
     */
    public BaseExperiment(IGrid grid, Set<Cell> startConfig, String logExperimentName) {
        this.grid = grid;
        this.startConfig = startConfig;
        this.logExperimentName = logExperimentName;
    }

    /**
     * Initializes the grid with the start configuration.
     * This method should be overridden by subclasses to provide specific initialization logic.
     */
    public void initializeGrid() {
    }

    /**
     * Runs the experiment with the specified logger type.
     * This method initializes the grid, logs the grid state for each iteration, and stops when the grid is stable or reaches 100 iterations.
     * It also clears any existing log file before starting the experiment.
     *
     * @param loggerType the type of logger to be used for logging the experiment
     */
    public void runExperiment(LoggerTypes loggerType) {
        if (this.logger == null) {
            LoggerFactory loggerFactory = new LoggerFactory();
            this.logger = loggerFactory.createLogger(String.valueOf(loggerType.getValue()));
        }

        String className = getClassName();
        initializeGrid();

        if (loggerType == LoggerTypes.LogFile) {
            String projectRoot = Paths.get("").toAbsolutePath().toString();
            String downloadPath = Paths.get(projectRoot, (className + ".log")).toString();
            File file = new File(downloadPath);
            if (file.exists()) {
                try {
                    new FileWriter(file, false).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        boolean isStable = false;
        for (int i = 0; i <= 100; i++) {
            logger.log(grid.toString(), i, className);
            if (grid.isStable()) {
                isStable = true;
                break;
            }
            grid.calculateNextGeneration();
        }
        if (isStable) {
            logger.logEndMessage("Experiment stopped: Grid is stable.", className);
        } else {
            logger.logEndMessage("Experiment stopped: Reached 100 iterations.", className);
        }
    }

    /**
     * Returns the simple name of the class.
     *
     * @return the simple name of the class
     */
    public String getClassName() {
        return this.getClass().getSimpleName();
    }
}