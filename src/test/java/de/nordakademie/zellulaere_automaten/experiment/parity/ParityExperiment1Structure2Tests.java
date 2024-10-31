package de.nordakademie.zellulaere_automaten.experiment.parity;

import de.nordakademie.zellulaere_automaten.grid.SetGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The {@code ParityExperiment1Structure2Tests} class contains unit tests for the {@link ParityExperiment1Structure2} class.
 * It verifies the correct initialization of the grid and the dimensions of the grid.
 */
public class ParityExperiment1Structure2Tests {

    private ParityExperiment1Structure2 experiment;
    private SetGrid grid;

    /**
     * Sets up the test environment before each test.
     * Initializes the experiment and the grid.
     */
    @BeforeEach
    void setUp() {
        experiment = new ParityExperiment1Structure2();
        experiment.initializeGrid();
        grid = (SetGrid) experiment.grid;
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

        // Check starting config cells in the center
        assertTrue(grid.getCellByCoordinates(200, 200).getIsAlive());
        assertTrue(grid.getCellByCoordinates(199, 200).getIsAlive());
        assertTrue(grid.getCellByCoordinates(200, 199).getIsAlive());
        assertTrue(grid.getCellByCoordinates(199, 199).getIsAlive());
    }

    /**
     * Tests the first step generation of the grid.
     * Verifies that the grid matches the expected pattern after the first generation.
     */
    @Test
    void firstStepGeneration_ShouldMatchExpectedPattern() {
        Set<Cell> startConfig = experiment.getStartConfig();
        assertNotNull(startConfig);
        assertFalse(startConfig.isEmpty());

        grid.calculateNextGeneration();

        int middleRow = grid.getRows() / 2 - 1;
        int middleColumn = grid.getColumns() / 2 - 1;

        // row 1 in the middle
        assertFalse(grid.getCellByCoordinates(middleRow - 1, middleColumn - 1).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow - 1, middleColumn).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow - 1, middleColumn + 1).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow - 1, middleColumn + 2).getIsAlive());

        // row 2 in the middle
        assertTrue(grid.getCellByCoordinates(middleRow, middleColumn - 1).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow, middleColumn).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow, middleColumn + 1).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow, middleColumn + 2).getIsAlive());

        // row 3 in the middle
        assertTrue(grid.getCellByCoordinates(middleRow + 1, middleColumn - 1).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow + 1, middleColumn).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow + 1, middleColumn + 1).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 1, middleColumn + 2).getIsAlive());

        // row 4 in the middle
        assertFalse(grid.getCellByCoordinates(middleRow + 2, middleColumn - 1).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 2, middleColumn).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 2, middleColumn + 1).getIsAlive());
        assertFalse(grid.getCellByCoordinates(middleRow + 2, middleColumn + 2).getIsAlive());
    }

    /**
     * Tests the dimensions of the grid.
     * Verifies that the grid has the correct number of rows and columns.
     */
    @Test
    void getGridDimensions_WhenCalled_ShouldReturnCorrectDimensions() {
        assertEquals(400, grid.getRows());
        assertEquals(400, grid.getColumns());
    }

}
