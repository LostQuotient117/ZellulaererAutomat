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

public class BaseExperimentTests {

    private BaseExperiment baseExperiment;
    private ClassicGrid grid;
    private Set<Cell> startConfig;

    @BeforeEach
    void setUp() {
        startConfig = new HashSet<>();
        grid = new ClassicGrid(5, 5, new Moore(), new GameOfLife());
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

    @Test
    void initializeGrid_WhenCalled_ShouldSetCellsAlive() {
        baseExperiment.initializeGrid();
        assertTrue(grid.getCellByCoordinates(2, 2).getIsAlive());
        assertTrue(grid.getCellByCoordinates(2, 3).getIsAlive());
        assertTrue(grid.getCellByCoordinates(3, 2).getIsAlive());
    }

    @Test
    void runExperiment_WhenCalled_ShouldMakeGridStable() {
        baseExperiment.initializeGrid();
        baseExperiment.runExperiment(LoggerTypes.getType(1));
        assertTrue(grid.isStable());
    }

}