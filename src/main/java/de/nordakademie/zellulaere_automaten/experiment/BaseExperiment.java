package de.nordakademie.zellulaere_automaten.experiment;

import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.logger.ILogger;
import de.nordakademie.zellulaere_automaten.logger.LoggerFactory;
import de.nordakademie.zellulaere_automaten.logger.LoggerTypes;
import de.nordakademie.zellulaere_automaten.model.Cell;

import java.util.Set;

public class BaseExperiment implements IExperiment {
    public IGrid grid;
    public Set<Cell> startConfig;
    protected String logExperimentName;
    protected ILogger logger;

    public BaseExperiment(IGrid grid, Set<Cell> startConfig, String logExperimentName) {
        this.grid = grid;
        this.startConfig = startConfig;
        this.logExperimentName = logExperimentName;
    }

    public void initializeGrid() {}

    public void runExperiment(LoggerTypes loggerType) {
        LoggerFactory loggerFactory = new LoggerFactory();
        this.logger = loggerFactory.createLogger(String.valueOf(loggerType.getValue()));

        initializeGrid();
        for (int i = 0; i <= 100; i++) {
            logger.log(grid.toString(), i);
            if (grid.isStable()) {
                logger.logEndMessage("Experiment stopped: Grid is stable. \n");
                break;
            }
            grid.calculateNextGeneration();
        }
        logger.logEndMessage("Experiment stopped: Reached 100 iterations. \n");
    }


}