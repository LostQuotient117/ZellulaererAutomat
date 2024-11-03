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

/**
 * Test class for the {@link Neumann} class, which implements the Neumann neighborhood
 * strategy for a cellular automaton. This test suite verifies that the correct number
 * of direct (orthogonal) neighbors is returned for cells in different positions within
 * a grid, including the middle, edge, and corner.
 *
 * The tests use a mocked {@link IGrid} instance to simulate the grid behavior and verify
 * the functionality of the {@code getNeighbors} method in the {@link Neumann} class.
 * @author Daria Stolarczyk
 */
class NeumannTest {

    private IGrid grid;
    private Neumann neumann;

    @BeforeEach
    public void setUp() {
        grid = Mockito.mock(IGrid.class);
        neumann = new Neumann();
    }

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
