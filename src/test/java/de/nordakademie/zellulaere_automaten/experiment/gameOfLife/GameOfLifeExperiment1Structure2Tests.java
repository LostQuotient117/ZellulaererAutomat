package de.nordakademie.zellulaere_automaten.experiment.gameOfLife;

import de.nordakademie.zellulaere_automaten.grid.SetGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.Moore;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.GameOfLife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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

        int middleRow = grid.getRows() / 2 - 1;
        int middleColumn = grid.getColumns() / 2 - 4;

        // Check alive cells
        assertTrue(grid.getCellByCoordinates(middleRow - 2, middleColumn + 2).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow - 2, middleColumn + 3).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow - 2, middleColumn + 5).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow - 2, middleColumn + 6).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow - 1, middleColumn + 2).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow - 1, middleColumn + 3).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow - 1, middleColumn + 5).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow - 1, middleColumn + 6).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow, middleColumn + 3).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow, middleColumn + 5).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 1, middleColumn + 1).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 1, middleColumn + 3).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 1, middleColumn + 5).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 1, middleColumn + 7).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 2, middleColumn + 1).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 2, middleColumn + 3).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 2, middleColumn + 5).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 2, middleColumn + 7).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 3, middleColumn + 1).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 3, middleColumn + 2).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 3, middleColumn + 6).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 3, middleColumn + 7).getIsAlive());

        // Check all other cells to be dead
        Set<Integer> aliveCellsHashCodes = startConfig.stream().map(Cell::hashCode).collect(Collectors.toSet());
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getColumns(); col++) {
                Cell cell = new Cell(row, col, false);
                if (!aliveCellsHashCodes.contains(cell.hashCode())) {
                    assertFalse(grid.getCellByCoordinates(row, col).getIsAlive());
                }
            }
        }
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

        // Check alive cells
        assertTrue(grid.getCellByCoordinates(middleRow - 2, middleColumn + 2).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow - 2, middleColumn + 3).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow - 2, middleColumn + 5).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow - 2, middleColumn + 6).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow, middleColumn + 3).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow, middleColumn + 5).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 1, middleColumn + 3).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 1, middleColumn + 5).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 2, middleColumn).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 2, middleColumn + 1).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 2, middleColumn + 3).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 2, middleColumn + 5).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 2, middleColumn + 7).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 2, middleColumn + 8).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 3, middleColumn + 1).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 3, middleColumn + 2).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 3, middleColumn + 6).getIsAlive());
        assertTrue(grid.getCellByCoordinates(middleRow + 3, middleColumn + 7).getIsAlive());

        // Check all other cells to be dead
        Set<Integer> aliveCellsHashCodes = ((Set<Cell>) grid.getDataStructure()).stream().map(Cell::hashCode).collect(Collectors.toSet());
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
        assertEquals(40, grid.getRows());
        assertEquals(41, grid.getColumns());
    }

}
