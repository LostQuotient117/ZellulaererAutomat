package de.nordakademie.zellulaere_automaten.experiment.gameOfLife;

import de.nordakademie.zellulaere_automaten.grid.ClassicGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The {@code GameOfLifeExperiment1Structure1Tests} class contains unit tests for the {@link GameOfLifeExperiment1Structure1} class.
 * It verifies the correct initialization of the grid and the dimensions of the grid.
 *
 * @author Lars Nicht
 */
public class GameOfLifeExperiment1Structure1Tests {

    private GameOfLifeExperiment1Structure1 experiment;
    private ClassicGrid grid;

    /**
     * Sets up the test environment before each test.
     * Initializes the experiment and the grid.
     */
    @BeforeEach
    void setUp() {
        experiment = new GameOfLifeExperiment1Structure1();
        experiment.initializeGrid();
        grid = (ClassicGrid) experiment.grid;
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
     * Tests the first step generation of the grid.
     * Verifies that the grid matches the expected pattern after the first generation.
     */
    @Test
    void firstStepGeneration_WhenCalled_ShouldMatchExpectedPattern() {
        Set<Cell> startConfig = experiment.getStartConfig();
        assertNotNull(startConfig);
        assertFalse(startConfig.isEmpty());

        grid.calculateNextGeneration();

        int middleRow = grid.getRows() / 2 - 1;
        int middleColumn = grid.getColumns() / 2 - 4;

        assertFalse(grid.getCellByCoordinates(middleRow - 2, middleColumn).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow - 2, middleColumn + 1).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow - 2, middleColumn + 2).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow - 2, middleColumn + 3).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow - 2, middleColumn + 4).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow - 2, middleColumn + 5).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow - 2, middleColumn + 6).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow - 2, middleColumn + 7).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow - 2, middleColumn + 8).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow - 1, middleColumn).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow - 1, middleColumn + 1).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow - 1, middleColumn + 2).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow - 1, middleColumn + 3).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow - 1, middleColumn + 4).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow - 1, middleColumn + 5).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow - 1, middleColumn + 6).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow - 1, middleColumn + 7).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow - 1, middleColumn + 8).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow, middleColumn).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow, middleColumn + 1).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow, middleColumn + 2).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow, middleColumn + 3).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow, middleColumn + 4).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow, middleColumn + 5).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow, middleColumn + 6).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow, middleColumn + 7).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow, middleColumn + 8).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow + 1, middleColumn).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow + 1, middleColumn + 1).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow + 1, middleColumn + 2).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 1, middleColumn + 3).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow + 1, middleColumn + 4).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 1, middleColumn + 5).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow + 1, middleColumn + 6).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow + 1, middleColumn + 7).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow + 1, middleColumn + 8).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 2, middleColumn).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 2, middleColumn + 1).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow + 2, middleColumn + 2).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 2, middleColumn + 3).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow + 2, middleColumn + 4).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 2, middleColumn + 5).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow + 2, middleColumn + 6).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 2, middleColumn + 7).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 2, middleColumn + 8).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow + 3, middleColumn).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 3, middleColumn + 1).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 3, middleColumn + 2).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow + 3, middleColumn + 3).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow + 3, middleColumn + 4).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow + 3, middleColumn + 5).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 3, middleColumn + 6).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 3, middleColumn + 7).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow + 3, middleColumn + 8).getIsAlive());
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
