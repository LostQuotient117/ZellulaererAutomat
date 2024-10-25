package de.nordakademie.zellulaere_automaten.simulationMode;

import de.nordakademie.zellulaere_automaten.grid.ClassicGrid;
import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.simulationMode.Moore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;


public class MooreTest {

    private Cell mockCell;
    private IGrid mockGrid;
    private Moore moore;

    @BeforeEach
    public void setUp(){
        mockCell = Mockito.mock(Cell.class);
        mockGrid = Mockito.mock(IGrid.class);

    }
    @Test
    void testGetNeighbors() {

        Cell cell = new Cell(2, 2, false);

        ArrayList<Cell> expectedNeighbors = new ArrayList<>();
        expectedNeighbors.add(new Cell(1, 2, false));
        expectedNeighbors.add(new Cell(3, 2, false));
        expectedNeighbors.add(new Cell(2, 1, false));
        expectedNeighbors.add(new Cell(2, 3, false));
        expectedNeighbors.add(new Cell(1, 1, false));
        expectedNeighbors.add(new Cell(1, 3, false));
        expectedNeighbors.add(new Cell(3, 1, false));
        expectedNeighbors.add(new Cell(3, 3, false));

        ArrayList<Cell> actualNeighbors = moore.getNeighbors(cell);

        assertEquals(expectedNeighbors.size(), actualNeighbors.size(), "Neighbor count mismatch");
        assertTrue(actualNeighbors.containsAll(expectedNeighbors), "Neighbors do not match expected values");
    }

}