package de.nordakademie.zellulaere_automaten.neighbors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.Moore;

/**
 * Test class for the {@link Moore} class, which implements the Moore neighborhood strategy
 * for a cellular automaton. This test suite verifies that the correct number of neighboring
 * cells is returned for different positions on the grid, including cells in the middle,
 * on the edge, and in the corner.
 *
 * The tests use a mocked {@link IGrid} instance to simulate grid behavior and verify
 * that the {@code getNeighbors} method in the {@link Moore} class correctly identifies
 * the surrounding cells for each position.
 * @author Daria Stolarczyk
 */
class MooreTest {

    private IGrid grid;
    private Moore moore;

    @BeforeEach
    public void setUp() {
        grid = Mockito.mock(IGrid.class);
        moore = new Moore();
    }

    @Test
    void getNeighbors_CellInMiddle_ReturnsEightNeighbors() {
        Cell cell = new Cell(1, 1, true);
        Mockito.when(grid.getRows()).thenReturn(3);
        Mockito.when(grid.getColumns()).thenReturn(3);

        // Mocking direct neighbors
        Mockito.when(grid.getCellByCoordinates(0, 1)).thenReturn(new Cell(0, 1, true)); // above
        Mockito.when(grid.getCellByCoordinates(2, 1)).thenReturn(new Cell(2, 1, true)); // below
        Mockito.when(grid.getCellByCoordinates(1, 0)).thenReturn(new Cell(1, 0, true)); // left
        Mockito.when(grid.getCellByCoordinates(1, 2)).thenReturn(new Cell(1, 2, true)); // right

        // Mocking diagonal neighbors
        Mockito.when(grid.getCellByCoordinates(0, 0)).thenReturn(new Cell(0, 0, true)); // top-left
        Mockito.when(grid.getCellByCoordinates(0, 2)).thenReturn(new Cell(0, 2, true)); // top-right
        Mockito.when(grid.getCellByCoordinates(2, 0)).thenReturn(new Cell(2, 0, true)); // bottom-left
        Mockito.when(grid.getCellByCoordinates(2, 2)).thenReturn(new Cell(2, 2, true)); // bottom-right

        List<Cell> result = moore.getNeighbors(cell, grid);

        assertEquals(8, result.size());
    }

    @Test
    void getNeighbors_CellOnEdge_ReturnsFiveNeighbors() {
        Cell cell = new Cell(1, 0, true);
        Mockito.when(grid.getRows()).thenReturn(3);
        Mockito.when(grid.getColumns()).thenReturn(3);

        // Mocking direct neighbors
        Mockito.when(grid.getCellByCoordinates(0, 0)).thenReturn(new Cell(0, 0, true)); // above
        Mockito.when(grid.getCellByCoordinates(2, 0)).thenReturn(new Cell(2, 0, true)); // below
        Mockito.when(grid.getCellByCoordinates(1, 1)).thenReturn(new Cell(1, 1, true)); // right

        // Mocking diagonal neighbors
        Mockito.when(grid.getCellByCoordinates(0, 1)).thenReturn(new Cell(0, 1, true)); // top-right
        Mockito.when(grid.getCellByCoordinates(2, 1)).thenReturn(new Cell(2, 1, true)); // bottom-right

        List<Cell> result = moore.getNeighbors(cell, grid);

        assertEquals(5, result.size());
    }

    @Test
    void getNeighbors_CellInCorner_ReturnsThreeNeighbors() {
        Cell cell = new Cell(0, 0, true);
        Mockito.when(grid.getRows()).thenReturn(3);
        Mockito.when(grid.getColumns()).thenReturn(3);

        // Mocking direct neighbors
        Mockito.when(grid.getCellByCoordinates(1, 0)).thenReturn(new Cell(1, 0, true)); // below
        Mockito.when(grid.getCellByCoordinates(0, 1)).thenReturn(new Cell(0, 1, true)); // right

        // Mocking diagonal neighbor
        Mockito.when(grid.getCellByCoordinates(1, 1)).thenReturn(new Cell(1, 1, true)); // bottom-right

        List<Cell> result = moore.getNeighbors(cell, grid);

        assertEquals(3, result.size());
    }
}
