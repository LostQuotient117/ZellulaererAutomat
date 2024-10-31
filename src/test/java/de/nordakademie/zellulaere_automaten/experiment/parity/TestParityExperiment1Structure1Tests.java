package de.nordakademie.zellulaere_automaten.experiment.parity;

import de.nordakademie.zellulaere_automaten.grid.ClassicGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestParityExperiment1Structure1Tests {

    private TestParityExperiment1Structure1 experiment;
    private ClassicGrid grid;

    @BeforeEach
    void setUp() {
        experiment = new TestParityExperiment1Structure1();
        experiment.initializeGrid();
        grid = (ClassicGrid) experiment.grid;
    }

    @Test
    void firstStepGeneration_ShouldMatchExpectedPattern() {
        Set<Cell> startConfig = experiment.getStartConfig();
        assertNotNull(startConfig);
        assertFalse(startConfig.isEmpty());

        grid.calculateNextGeneration();

        // row 1 in the middle
        assertFalse(grid.getCellByCoordinates(8, 8).getIsAlive());
        assertTrue(grid.getCellByCoordinates(8, 9).getIsAlive());
        assertTrue(grid.getCellByCoordinates(8, 10).getIsAlive());
        assertFalse(grid.getCellByCoordinates(8, 11).getIsAlive());

        // row 2 in the middle
        assertTrue(grid.getCellByCoordinates(9, 8).getIsAlive());
        assertFalse(grid.getCellByCoordinates(9, 9).getIsAlive());
        assertFalse(grid.getCellByCoordinates(9, 10).getIsAlive());
        assertTrue(grid.getCellByCoordinates(9, 11).getIsAlive());

        // row 3 in the middle
        assertTrue(grid.getCellByCoordinates(10, 8).getIsAlive());
        assertFalse(grid.getCellByCoordinates(10, 9).getIsAlive());
        assertFalse(grid.getCellByCoordinates(10, 10).getIsAlive());
        assertTrue(grid.getCellByCoordinates(10, 11).getIsAlive());

        // row 4 in the middle
        assertFalse(grid.getCellByCoordinates(11, 8).getIsAlive());
        assertTrue(grid.getCellByCoordinates(11, 9).getIsAlive());
        assertTrue(grid.getCellByCoordinates(11, 10).getIsAlive());
        assertFalse(grid.getCellByCoordinates(11, 11).getIsAlive());
    }

    @Test
    void getGridDimensions_WhenCalled_ShouldReturnCorrectDimensions() {
        assertEquals(20, grid.getRows());
        assertEquals(20, grid.getColumns());
    }

}
