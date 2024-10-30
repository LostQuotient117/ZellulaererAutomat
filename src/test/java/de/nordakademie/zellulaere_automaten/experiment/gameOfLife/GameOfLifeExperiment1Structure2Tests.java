package de.nordakademie.zellulaere_automaten.experiment.gameOfLife;

import de.nordakademie.zellulaere_automaten.grid.SetGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.Moore;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.GameOfLife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The {@code GameOfLifeExperiment1Structure2Tests} class contains unit tests for the {@link GameOfLifeExperiment1Structure2} class.
 * It verifies the correct initialization of the grid and the dimensions of the grid.
 */
public class GameOfLifeExperiment1Structure2Tests {

    private GameOfLifeExperiment1Structure2 experiment;
    private SetGrid grid;

    /**
     * Sets up the test environment before each test.
     * Initializes the experiment and the grid.
     */
    @BeforeEach
    void setUp() {
        experiment = new GameOfLifeExperiment1Structure2();
        experiment.initializeGrid();
        Set<Cell> startConfig = experiment.getStartConfig();
        grid = new SetGrid(40, 41, startConfig, new GameOfLife(), new Moore());
    }

    /**
     * Tests the initialization of the grid.
     * Verifies that the start configuration is set correctly.
     */
    @Test
    void initializeGrid_WhenCalled_ShouldSetStartConfig() {
        Set<Cell> startConfig = experiment.getStartConfig();
        assertNotNull(startConfig);
        assertFalse(startConfig.isEmpty());

        // Check starting config cells in the pattern (row 1)
        assertFalse(grid.getCellByCoordinates(17, 17).getIsAlive());
        assertTrue(grid.getCellByCoordinates(17, 18).getIsAlive());
        assertTrue(grid.getCellByCoordinates(17, 19).getIsAlive());
        assertFalse(grid.getCellByCoordinates(17, 20).getIsAlive());
        assertTrue(grid.getCellByCoordinates(17, 21).getIsAlive());
        assertTrue(grid.getCellByCoordinates(17, 22).getIsAlive());
        assertFalse(grid.getCellByCoordinates(17, 23).getIsAlive());

        // Check starting config cells in the pattern (row 2)
        assertFalse(grid.getCellByCoordinates(18, 17).getIsAlive());
        assertTrue(grid.getCellByCoordinates(18, 18).getIsAlive());
        assertTrue(grid.getCellByCoordinates(18, 19).getIsAlive());
        assertFalse(grid.getCellByCoordinates(18, 20).getIsAlive());
        assertTrue(grid.getCellByCoordinates(18, 21).getIsAlive());
        assertTrue(grid.getCellByCoordinates(18, 22).getIsAlive());
        assertFalse(grid.getCellByCoordinates(18, 23).getIsAlive());

        // Check starting config cells in the pattern (row 3)
        assertFalse(grid.getCellByCoordinates(19, 17).getIsAlive());
        assertFalse(grid.getCellByCoordinates(19, 18).getIsAlive());
        assertTrue(grid.getCellByCoordinates(19, 19).getIsAlive());
        assertFalse(grid.getCellByCoordinates(19, 20).getIsAlive());
        assertTrue(grid.getCellByCoordinates(19, 21).getIsAlive());
        assertFalse(grid.getCellByCoordinates(19, 22).getIsAlive());
        assertFalse(grid.getCellByCoordinates(19, 23).getIsAlive());

        // Check starting config cells in the pattern (row 4)
        assertTrue(grid.getCellByCoordinates(20, 17).getIsAlive());
        assertFalse(grid.getCellByCoordinates(20, 18).getIsAlive());
        assertTrue(grid.getCellByCoordinates(20, 19).getIsAlive());
        assertFalse(grid.getCellByCoordinates(20, 20).getIsAlive());
        assertTrue(grid.getCellByCoordinates(20, 21).getIsAlive());
        assertFalse(grid.getCellByCoordinates(20, 22).getIsAlive());
        assertTrue(grid.getCellByCoordinates(20, 23).getIsAlive());

        // Check starting config cells in the pattern (row 5)
        assertTrue(grid.getCellByCoordinates(21, 17).getIsAlive());
        assertFalse(grid.getCellByCoordinates(21, 18).getIsAlive());
        assertTrue(grid.getCellByCoordinates(21, 19).getIsAlive());
        assertFalse(grid.getCellByCoordinates(21, 20).getIsAlive());
        assertTrue(grid.getCellByCoordinates(21, 21).getIsAlive());
        assertFalse(grid.getCellByCoordinates(21, 22).getIsAlive());
        assertTrue(grid.getCellByCoordinates(21, 23).getIsAlive());

        // Check starting config cells in the pattern (row 6)
        assertTrue(grid.getCellByCoordinates(22, 17).getIsAlive());
        assertTrue(grid.getCellByCoordinates(22, 18).getIsAlive());
        assertFalse(grid.getCellByCoordinates(22, 19).getIsAlive());
        assertFalse(grid.getCellByCoordinates(22, 20).getIsAlive());
        assertFalse(grid.getCellByCoordinates(22, 21).getIsAlive());
        assertTrue(grid.getCellByCoordinates(22, 22).getIsAlive());
        assertTrue(grid.getCellByCoordinates(22, 23).getIsAlive());

    }

    /**
     * Tests the dimensions of the grid.
     * Verifies that the grid has the correct number of rows and columns.
     */
    @Test
    void getGridDimensions_WhenCalled_ShouldReturnCorrectDimensions() {
        assertEquals(40, grid.getRows());
        assertEquals(41, grid.getColumns());
    }

}
