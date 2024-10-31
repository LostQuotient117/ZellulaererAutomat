package de.nordakademie.zellulaere_automaten.neighbors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.Neumann;

class NeumannTest {

    private IGrid grid;
    private Neumann neumann;

    /**
     * Sets up the mock grid and the Neumann instance before each test.
     */
    @BeforeEach
    public void setUp() {
        grid = Mockito.mock(IGrid.class);
        neumann = new Neumann();
    }

    /**
     * Test for the getNeighbors method when the cell is in the middle of a 3x3 grid.
     * The expected result is that all four direct neighbors are returned.
     */
    @Test
    void getNeighbors_CellInMiddle_ReturnsFourNeighbors() {
        Cell cell = new Cell(1, 1, true);
        Mockito.when(grid.getRows()).thenReturn(3);
        Mockito.when(grid.getColumns()).thenReturn(3);
        Mockito.when(grid.getCellByCoordinates(0, 1)).thenReturn(new Cell(0, 1, true)); // above
        Mockito.when(grid.getCellByCoordinates(2, 1)).thenReturn(new Cell(2, 1, true)); // below
        Mockito.when(grid.getCellByCoordinates(1, 0)).thenReturn(new Cell(1, 0, true)); // left
        Mockito.when(grid.getCellByCoordinates(1, 2)).thenReturn(new Cell(1, 2, true)); // right

        List<Cell> result = neumann.getNeighbors(cell, grid);

        assertEquals(4, result.size());
    }

    /**
     * Test for the getNeighbors method when the cell is on the edge of a 3x3 grid.
     * The expected result is that only three direct neighbors are returned.
     */
    @Test
    void getNeighbors_CellOnEdge_ReturnsThreeNeighbors() {
        Cell cell = new Cell(1, 0, true);
        Mockito.when(grid.getRows()).thenReturn(3);
        Mockito.when(grid.getColumns()).thenReturn(3);
        Mockito.when(grid.getCellByCoordinates(0, 0)).thenReturn(new Cell(0, 0, true)); // above
        Mockito.when(grid.getCellByCoordinates(2, 0)).thenReturn(new Cell(2, 0, true)); // below
        Mockito.when(grid.getCellByCoordinates(1, 1)).thenReturn(new Cell(1, 1, true)); // right

        List<Cell> result = neumann.getNeighbors(cell, grid);

        assertEquals(3, result.size());
    }

    /**
     * Test for the getNeighbors method when the cell is in the corner of a 3x3 grid.
     * The expected result is that only two direct neighbors are returned.
     */
    @Test
    void getNeighbors_CellInCorner_ReturnsTwoNeighbors() {
        Cell cell = new Cell(0, 0, true);
        Mockito.when(grid.getRows()).thenReturn(3);
        Mockito.when(grid.getColumns()).thenReturn(3);
        Mockito.when(grid.getCellByCoordinates(1, 0)).thenReturn(new Cell(1, 0, true)); // below
        Mockito.when(grid.getCellByCoordinates(0, 1)).thenReturn(new Cell(0, 1, true)); // right

        List<Cell> result = neumann.getNeighbors(cell, grid);

        assertEquals(2, result.size());
    }
}
