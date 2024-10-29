package de.nordakademie.zellulaere_automaten.experiment.parity;

import de.nordakademie.zellulaere_automaten.grid.SetGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParityExperiment1Structure2Tests {

    private ParityExperiment1Structure2 experiment;
    private SetGrid grid;

    @BeforeEach
    void setUp() {
        experiment = new ParityExperiment1Structure2();
        experiment.initializeGrid();
        grid = (SetGrid) experiment.grid;
    }

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

    @Test
    void getGridDimensions_WhenCalled_ShouldReturnCorrectDimensions() {
        assertEquals(400, grid.getRows());
        assertEquals(400, grid.getColumns());
    }

}
