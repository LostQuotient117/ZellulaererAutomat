package de.nordakademie.zellulaere_automaten.experiment.gameOfLife;

import de.nordakademie.zellulaere_automaten.grid.SetGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameOfLifeExperiment3Structure2Tests {

    private GameOfLifeExperiment3Structure2 experiment;
    private SetGrid grid;

    @BeforeEach
    void setUp() {
        experiment = new GameOfLifeExperiment3Structure2();
        experiment.initializeGrid();
        grid = (SetGrid) experiment.grid;
    }

    @Test
    void initializeGrid_WhenCalled_ShouldSetStartConfig() {
        Set<Cell> startConfig = experiment.getStartConfig();
        assertNotNull(startConfig);
        assertTrue(startConfig.isEmpty());

        // Check that all cells are dead
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getColumns(); col++) {
                assertFalse(grid.getCellByCoordinates(row, col).getIsAlive());
            }
        }
    }

    @Test
    void getGridDimensions_WhenCalled_ShouldReturnCorrectDimensions() {
        assertEquals(300, grid.getRows());
        assertEquals(300, grid.getColumns());
    }

}
