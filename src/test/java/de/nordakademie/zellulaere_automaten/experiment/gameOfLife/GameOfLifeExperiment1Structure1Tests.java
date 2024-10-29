package de.nordakademie.zellulaere_automaten.experiment.gameOfLife;

import de.nordakademie.zellulaere_automaten.grid.ClassicGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.Moore;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.GameOfLife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameOfLifeExperiment1Structure1Tests {

    private GameOfLifeExperiment1Structure1 experiment;
    private ClassicGrid grid;

    @BeforeEach
    void setUp() {
        experiment = new GameOfLifeExperiment1Structure1();
        grid = new ClassicGrid(40, 41, new Moore(), new GameOfLife());
        experiment.initializeGrid();
    }

    @Test
    void initializeGrid_WhenCalled_ShouldSetStartConfig() {
        Set<Cell> startConfig = experiment.getStartConfig();
        assertNotNull(startConfig);
        assertFalse(startConfig.isEmpty());

        // Check specific cells in the pattern
        assertFalse(grid.getCellByCoordinates(17, 17).getIsAlive());
        assertFalse(grid.getCellByCoordinates(18, 17).getIsAlive());

    }

    @Test
    void getGridDimensions_WhenCalled_ShouldReturnCorrectDimensions() {
        assertEquals(40, grid.getRows());
        assertEquals(41, grid.getColumns());
    }

}
