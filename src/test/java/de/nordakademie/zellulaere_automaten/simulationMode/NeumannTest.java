/*
package de.nordakademie.zellulaere_automaten.simulationMode;
import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


public class NeumannTest{

    private Cell mockCell;
    private IGrid mockGrid;
    private NeumannTest test;

    @BeforeEach
    public void setUp(){
        mockCell = Mockito.mock(Cell.class);
        mockGrid = Mockito.mock(IGrid.class);

    }
    @Test
    public void testGetNeighbors(Cell cell) {
        int row = 3;
        int column = 3;
        Mockito.when(mockGrid.getRow()).thenReturn(row);
        Mockito.when(mockGrid.getColumn()).thenReturn(column);


        Cell cell = new cell (1,1);


        List<Cell> neighbors = test.testGetNeighbors(cell);

        assertEquals(4, neighbors.size());

        assertEquals(1, neighbors.get(0).getRow());
        assertEquals(0, neighbors.get(0).getColumn());
        assertEquals(1, neighbors.get(1).getRow());
        assertEquals(2, neighbors.get(1).getColumn());
        assertEquals(0, neighbors.get(2).getRow());
        assertEquals(1, neighbors.get(2).getColumn());
        assertEquals(2, neighbors.get(3).getRow());
        assertEquals(1, neighbors.get(3).getColumn());


    }

}
*/
