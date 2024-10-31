package de.nordakademie.zellulaere_automaten.experiment.gameOfLife;

import de.nordakademie.zellulaere_automaten.grid.ClassicGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameOfLifeExperiment1Structure1Tests {

    private TestGameOfLifeExperiment1Structure1 experiment;
    private ClassicGrid grid;

    @BeforeEach
    void setUp() {
        experiment = new TestGameOfLifeExperiment1Structure1();
        experiment.initializeGrid();
        grid = (ClassicGrid) experiment.grid;
    }

    @Test
    void firstStepGeneration_ShouldMatchExpectedPattern() {
        Set<Cell> startConfig = experiment.getStartConfig();
        assertNotNull(startConfig);
        assertFalse(startConfig.isEmpty());

        grid.calculateNextGeneration();

        System.out.println(grid);

        // Expected pattern after the first step based on the filled cells from the lower grid

        // Filled cells (true)
        assertTrue(grid.getCellByCoordinates(5, 6).getIsAlive());
        assertTrue(grid.getCellByCoordinates(5, 7).getIsAlive());
        assertTrue(grid.getCellByCoordinates(5, 9).getIsAlive());
        assertTrue(grid.getCellByCoordinates(5, 10).getIsAlive());

        assertTrue(grid.getCellByCoordinates(7, 7).getIsAlive());
        assertTrue(grid.getCellByCoordinates(7, 9).getIsAlive());

        assertTrue(grid.getCellByCoordinates(8, 7).getIsAlive());
        assertTrue(grid.getCellByCoordinates(8, 9).getIsAlive());

        assertTrue(grid.getCellByCoordinates(9, 4).getIsAlive());
        assertTrue(grid.getCellByCoordinates(9, 5).getIsAlive());
        assertTrue(grid.getCellByCoordinates(9, 7).getIsAlive());
        assertTrue(grid.getCellByCoordinates(9, 9).getIsAlive());
        assertTrue(grid.getCellByCoordinates(9, 11).getIsAlive());
        assertTrue(grid.getCellByCoordinates(9, 12).getIsAlive());


        assertTrue(grid.getCellByCoordinates(10, 5).getIsAlive());
        assertTrue(grid.getCellByCoordinates(10, 6).getIsAlive());
        assertTrue(grid.getCellByCoordinates(10, 10).getIsAlive());
        assertTrue(grid.getCellByCoordinates(10, 11).getIsAlive());

        // Empty cells (false)
        assertFalse(grid.getCellByCoordinates(5, 0).getIsAlive());
        assertFalse(grid.getCellByCoordinates(5, 1).getIsAlive());
        assertFalse(grid.getCellByCoordinates(5, 2).getIsAlive());
        assertFalse(grid.getCellByCoordinates(5, 3).getIsAlive());
        assertFalse(grid.getCellByCoordinates(5, 4).getIsAlive());
        assertFalse(grid.getCellByCoordinates(5, 5).getIsAlive());
        assertFalse(grid.getCellByCoordinates(5, 8).getIsAlive());
        assertFalse(grid.getCellByCoordinates(5, 11).getIsAlive());
        assertFalse(grid.getCellByCoordinates(5, 12).getIsAlive());
        assertFalse(grid.getCellByCoordinates(5, 13).getIsAlive());
        assertFalse(grid.getCellByCoordinates(5, 14).getIsAlive());
        assertFalse(grid.getCellByCoordinates(5, 15).getIsAlive());
        assertFalse(grid.getCellByCoordinates(5, 16).getIsAlive());

        assertFalse(grid.getCellByCoordinates(7, 0).getIsAlive());
        assertFalse(grid.getCellByCoordinates(7, 1).getIsAlive());
        assertFalse(grid.getCellByCoordinates(7, 2).getIsAlive());
        assertFalse(grid.getCellByCoordinates(7, 3).getIsAlive());
        assertFalse(grid.getCellByCoordinates(7, 4).getIsAlive());
        assertFalse(grid.getCellByCoordinates(7, 5).getIsAlive());
        assertFalse(grid.getCellByCoordinates(7, 6).getIsAlive());
        assertFalse(grid.getCellByCoordinates(7, 8).getIsAlive());
        assertFalse(grid.getCellByCoordinates(7, 10).getIsAlive());
        assertFalse(grid.getCellByCoordinates(7, 11).getIsAlive());
        assertFalse(grid.getCellByCoordinates(7, 12).getIsAlive());
        assertFalse(grid.getCellByCoordinates(7, 13).getIsAlive());
        assertFalse(grid.getCellByCoordinates(7, 14).getIsAlive());
        assertFalse(grid.getCellByCoordinates(7, 15).getIsAlive());
        assertFalse(grid.getCellByCoordinates(7, 16).getIsAlive());

        assertFalse(grid.getCellByCoordinates(8, 0).getIsAlive());
        assertFalse(grid.getCellByCoordinates(8, 1).getIsAlive());
        assertFalse(grid.getCellByCoordinates(8, 2).getIsAlive());
        assertFalse(grid.getCellByCoordinates(8, 3).getIsAlive());
        assertFalse(grid.getCellByCoordinates(8, 4).getIsAlive());
        assertFalse(grid.getCellByCoordinates(8, 5).getIsAlive());
        assertFalse(grid.getCellByCoordinates(8, 6).getIsAlive());
        assertFalse(grid.getCellByCoordinates(8, 8).getIsAlive());
        assertFalse(grid.getCellByCoordinates(8, 10).getIsAlive());
        assertFalse(grid.getCellByCoordinates(8, 11).getIsAlive());
        assertFalse(grid.getCellByCoordinates(8, 12).getIsAlive());
        assertFalse(grid.getCellByCoordinates(8, 13).getIsAlive());
        assertFalse(grid.getCellByCoordinates(8, 14).getIsAlive());
        assertFalse(grid.getCellByCoordinates(8, 15).getIsAlive());
        assertFalse(grid.getCellByCoordinates(8, 16).getIsAlive());

        assertFalse(grid.getCellByCoordinates(9, 0).getIsAlive());
        assertFalse(grid.getCellByCoordinates(9, 1).getIsAlive());
        assertFalse(grid.getCellByCoordinates(9, 2).getIsAlive());
        assertFalse(grid.getCellByCoordinates(9, 3).getIsAlive());
        assertFalse(grid.getCellByCoordinates(9, 6).getIsAlive());
        assertFalse(grid.getCellByCoordinates(9, 8).getIsAlive());
        assertFalse(grid.getCellByCoordinates(9, 10).getIsAlive());
        assertFalse(grid.getCellByCoordinates(9, 13).getIsAlive());
        assertFalse(grid.getCellByCoordinates(9, 14).getIsAlive());
        assertFalse(grid.getCellByCoordinates(9, 15).getIsAlive());
        assertFalse(grid.getCellByCoordinates(9, 16).getIsAlive());

        assertFalse(grid.getCellByCoordinates(10, 0).getIsAlive());
        assertFalse(grid.getCellByCoordinates(10, 1).getIsAlive());
        assertFalse(grid.getCellByCoordinates(10, 2).getIsAlive());
        assertFalse(grid.getCellByCoordinates(10, 3).getIsAlive());
        assertFalse(grid.getCellByCoordinates(10, 4).getIsAlive());
        assertFalse(grid.getCellByCoordinates(10, 7).getIsAlive());
        assertFalse(grid.getCellByCoordinates(10, 8).getIsAlive());
        assertFalse(grid.getCellByCoordinates(10, 9).getIsAlive());
        assertFalse(grid.getCellByCoordinates(10, 12).getIsAlive());
        assertFalse(grid.getCellByCoordinates(10, 13).getIsAlive());
        assertFalse(grid.getCellByCoordinates(10, 14).getIsAlive());
        assertFalse(grid.getCellByCoordinates(10, 15).getIsAlive());
        assertFalse(grid.getCellByCoordinates(10, 16).getIsAlive());
    }


    @Test
    void getGridDimensions_WhenCalled_ShouldReturnCorrectDimensions() {
        assertEquals(16, grid.getRows());
        assertEquals(17, grid.getColumns());
    }

}