package de.nordakademie.zellulaere_automaten.experiment.gameOfLife;

import de.nordakademie.zellulaere_automaten.grid.ClassicGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameOfLifeExperiment2Structure1Tests {

    private GameOfLifeExperiment2Structure1 experiment;
    private ClassicGrid grid;

    @BeforeEach
    void setUp() {
        experiment = new GameOfLifeExperiment2Structure1();
        experiment.initializeGrid();
        grid = (ClassicGrid) experiment.grid;
    }

    @Test
    void initializeGrid_WhenCalled_ShouldSetStartConfig() {
        Set<Cell> startConfig = experiment.getStartConfig();
        assertNotNull(startConfig);
        assertFalse(startConfig.isEmpty());

        // Check alternating pattern for even and odd rows
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getColumns(); col++) {
                boolean expectedState = (row % 2 == 0) ? (col % 2 == 1) : (col % 2 == 0);
                assertEquals(expectedState, grid.getCellByCoordinates(row, col).getIsAlive());
            }
        }
    }

    @Test
    void getGridDimensions_WhenCalled_ShouldReturnCorrectDimensions() {
        assertEquals(100, grid.getRows());
        assertEquals(100, grid.getColumns());
    }

}
