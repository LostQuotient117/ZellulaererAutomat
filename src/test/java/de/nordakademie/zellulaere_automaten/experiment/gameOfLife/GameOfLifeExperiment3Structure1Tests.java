package de.nordakademie.zellulaere_automaten.experiment.gameOfLife;

import de.nordakademie.zellulaere_automaten.grid.ClassicGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The {@code GameOfLifeExperiment3Structure1Tests} class contains unit tests for the {@link GameOfLifeExperiment3Structure1} class.
 * It verifies the correct initialization of the grid and the dimensions of the grid.
 *
 * @author Lars Nicht
 */
public class GameOfLifeExperiment3Structure1Tests {

    private GameOfLifeExperiment3Structure1 experiment;
    private ClassicGrid grid;

    /**
     * Sets up the test environment before each test.
     * Initializes the experiment and the grid.
     */
    @BeforeEach
    void setUp() {
        experiment = new GameOfLifeExperiment3Structure1();
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
        assertTrue(startConfig.isEmpty());

        // Check that all cells are dead
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getColumns(); col++) {
                assertFalse(grid.getCellByCoordinates(row, col).getIsAlive());
            }
        }
    }

    /**
     * Tests the first step generation of the grid.
     * Verifies that the grid is fully dead after the first generation.
     */
    @Test
    void firstStepGeneration_WhenCalled_ShouldMatchExpectedPattern() {
        experiment.initializeGrid();
        grid.calculateNextGeneration();

        // Verify that all cells are dead
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getColumns(); col++) {
                assertFalse(grid.getCellByCoordinates(row, col).getIsAlive(),
                        String.format("Cell at (%d, %d) is not dead", row, col));
            }
        }
    }

    /**
     * Tests the dimensions of the grid.
     * Verifies that the grid has the correct number of rows and columns.
     */
    @Test
    void getGridDimensions_WhenCalled_ShouldReturnCorrectDimensions() {
        assertEquals(300, grid.getRows());
        assertEquals(300, grid.getColumns());
    }

}
