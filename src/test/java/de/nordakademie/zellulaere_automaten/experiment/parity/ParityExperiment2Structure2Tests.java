package de.nordakademie.zellulaere_automaten.experiment.parity;

import de.nordakademie.zellulaere_automaten.grid.SetGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The {@code ParityExperiment2Structure2Tests} class contains unit tests for the {@link ParityExperiment2Structure2} class.
 * It verifies the correct initialization of the grid and the dimensions of the grid.
 */
public class ParityExperiment2Structure2Tests {

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
     * Tests the dimensions of the grid.
     * Verifies that the grid has the correct number of rows and columns.
     */
    @Test
    void getGridDimensions_WhenCalled_ShouldReturnCorrectDimensions() {
        assertEquals(100, grid.getRows());
        assertEquals(100, grid.getColumns());
    }

}
