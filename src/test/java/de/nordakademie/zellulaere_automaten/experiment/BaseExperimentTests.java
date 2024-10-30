package de.nordakademie.zellulaere_automaten.experiment;

import de.nordakademie.zellulaere_automaten.grid.ClassicGrid;
import de.nordakademie.zellulaere_automaten.logger.LoggerTypes;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.Moore;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.GameOfLife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@code BaseExperimentTests} class contains unit tests for the {@link BaseExperiment} class.
 * It verifies the correct initialization of the grid, the dimensions of the grid, and the behavior of the experiment.
 */
public class BaseExperimentTests {

    private BaseExperiment baseExperiment;
    private ClassicGrid grid;
    private Set<Cell> startConfig;

    /**
     * Sets up the test environment before each test.
     * Initializes the experiment, the grid, and the start configuration.
     */
    @BeforeEach
    void setUp() {
        startConfig = new HashSet<>();
        grid = new ClassicGrid(5, 5, new HashSet<>(), new GameOfLife(), new Moore());
        baseExperiment = new BaseExperiment(grid, startConfig, "TestExperiment") {
            @Override
            public void initializeGrid() {
                // Custom initialization for testing
                startConfig.add(new Cell(2, 2, true));
                startConfig.add(new Cell(2, 3, true));
                startConfig.add(new Cell(3, 2, true));
                for (Cell cell : startConfig) {
                    grid.getCellByCoordinates(cell.getRow(), cell.getColumn()).setIsAlive(cell.getIsAlive());
                }
            }
        };
    }

    /**
     * Tests the initialization of the grid.
     * Verifies that the start configuration is set correctly.
     */
    @Test
    void initializeGrid_WhenCalled_ShouldSetCellsAlive() {
        baseExperiment.initializeGrid();
        assertTrue(grid.getCellByCoordinates(2, 2).getIsAlive());
        assertTrue(grid.getCellByCoordinates(2, 3).getIsAlive());
        assertTrue(grid.getCellByCoordinates(3, 2).getIsAlive());
    }

    /**
     * Tests the behavior of the experiment when run.
     * Verifies that the grid becomes stable after running the experiment.
     */
    @Test
    void runExperiment_WhenCalled_ShouldMakeGridStable() {
        baseExperiment.initializeGrid();
        baseExperiment.runExperiment(LoggerTypes.getType(1));
        assertTrue(grid.isStable());
    }

}