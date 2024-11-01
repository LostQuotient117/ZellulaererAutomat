package de.nordakademie.zellulaere_automaten.experiment.parity;

import de.nordakademie.zellulaere_automaten.grid.ClassicGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The {@code ParityExperiment1Structure1Tests} class contains unit tests for the {@link ParityExperiment1Structure1} class.
 * It verifies the correct initialization of the grid and the dimensions of the grid.
 */
public class ParityExperiment1Structure1Tests {

    private ParityExperiment1Structure1 experiment;
    private ClassicGrid grid;

    /**
     * Sets up the test environment before each test.
     * Initializes the experiment and the grid.
     */
    @BeforeEach
    void setUp() {
        experiment = new ParityExperiment1Structure1();
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

        int middleRow = grid.getRows() / 2;
        int middleColumn = grid.getColumns() / 2;

        assertTrue(grid.getCellByCoordinates(middleRow, middleColumn).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow - 1, middleColumn).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow, middleColumn - 1).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow - 1, middleColumn - 1).getIsAlive());
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
        int middleColumn = grid.getColumns() / 2 - 1;

        assertTrue(grid.getCellByCoordinates(middleRow - 1, middleColumn).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow - 1, middleColumn + 1).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow, middleColumn - 1).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow, middleColumn + 2).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 1, middleColumn - 1).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 1, middleColumn + 2).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 2, middleColumn).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 2, middleColumn + 1).getIsAlive());

        // Check all other cells to be dead
        Set<Integer> aliveCellsHashCodes = Arrays.stream(grid.getDataStructure())
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .map(Cell::hashCode)
                .collect(Collectors.toSet());
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getColumns(); col++) {
                Cell cell = new Cell(row, col, false);
                if (!aliveCellsHashCodes.contains(Objects.hash(cell.getRow(), cell.getColumn()))) {
                    assertFalse(grid.getCellByCoordinates(row, col).getIsAlive());
                }
            }
        }
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
