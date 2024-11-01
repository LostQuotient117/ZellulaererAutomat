package de.nordakademie.zellulaere_automaten.experiment.gameOfLife;

import de.nordakademie.zellulaere_automaten.grid.ClassicGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The {@code GameOfLifeExperiment2Structure1Tests} class contains unit tests for the {@link GameOfLifeExperiment2Structure1} class.
 * It verifies the correct initialization of the grid and the dimensions of the grid.
 *
 * @author Lars Nicht
 */
public class GameOfLifeExperiment2Structure1Tests {

    private GameOfLifeExperiment2Structure1 experiment;
    private ClassicGrid grid;

    /**
     * Sets up the test environment before each test.
     * Initializes the experiment and the grid.
     */
    @BeforeEach
    void setUp() {
        experiment = new GameOfLifeExperiment2Structure1();
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

        // Check alternating pattern for even and odd rows
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getColumns(); col++) {
                boolean expectedState = (row % 2 == 0) ? (col % 2 == 1) : (col % 2 == 0);
                assertEquals(expectedState, grid.getCellByCoordinates(row, col).getIsAlive());
            }
        }
    }

    /**
     * Tests the first step generation of the grid.
     * Verifies that the grid matches the expected pattern after the first generation.
     */
    @Test
    void firstStepGeneration_WhenCalled_ShouldMatchExpectedPattern() {
        experiment.initializeGrid();
        grid.calculateNextGeneration();

        // Expected pattern for the first generation
        boolean[][] expectedPattern = new boolean[100][100];
        for (int row = 0; row < 100; row++) {
            for (int col = 0; col < 100; col++) {
                if (row == 0 || row == 99) {
                    expectedPattern[row][col] = (col != 0 && col != 99);
                } else {
                    expectedPattern[row][col] = (col == 0 || col == 99);
                }
            }
        }

        // Verify the grid matches the expected pattern
        for (int row = 0; row < 100; row++) {
            for (int col = 0; col < 100; col++) {
                assertEquals(expectedPattern[row][col], grid.getCellByCoordinates(row, col).getIsAlive(),
                        String.format("Cell at (%d, %d) does not match the expected state", row, col));
            }
        }
    }

    /**
     * Tests the dimensions of the grid.
     * Verifies that the grid has the correct number of rows and columns.
     */
    @Test
    void getGridDimensions_WhenCalled_ShouldReturnCorrectDimensions() {
        assertEquals(100, grid.getRows());
        assertEquals(100, grid.getColumns());
    }

}
