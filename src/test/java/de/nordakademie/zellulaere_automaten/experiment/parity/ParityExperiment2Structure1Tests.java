package de.nordakademie.zellulaere_automaten.experiment.parity;

import de.nordakademie.zellulaere_automaten.grid.ClassicGrid;
import de.nordakademie.zellulaere_automaten.grid.SetGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The {@code ParityExperiment2Structure1Tests} class contains unit tests for the {@link ParityExperiment2Structure1} class.
 * It verifies the correct initialization of the grid and the dimensions of the grid.
 *
 * @author Lars Nicht
 */
public class ParityExperiment2Structure1Tests {

    private ParityExperiment2Structure2 experiment;
    private SetGrid grid;

    /**
     * Sets up the test environment before each test.
     * Initializes the experiment and the grid.
     */
    @BeforeEach
    void setUp() {
        experiment = new ParityExperiment2Structure2();
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

        int rows = grid.getRows();
        int columns = grid.getColumns();
        boolean[][] expectedPattern = new boolean[rows][columns];

        // First row pattern
        for (int col = 0; col < columns; col++) {
            expectedPattern[0][col] = (col != 0 && col % 2 == 0);
        }

        // Last row pattern
        for (int col = 0; col < columns; col++) {
            expectedPattern[rows -1][col] = expectedPattern[0][(rows - 1) - col];
        }

        // Middle rows pattern
        for (int row = 1; row < rows - 1; row++) {
            if (row % 2 == 0) {
                for (int col = 0; col < columns - 1; col++) {
                    expectedPattern[row][col] = (col == 0);
                }
            } else {
                for (int col = 0; col < columns; col++) {
                    expectedPattern[row][col] = (col == columns -1);
                }
            }
        }

        // Verify the grid matches the expected pattern
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
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
