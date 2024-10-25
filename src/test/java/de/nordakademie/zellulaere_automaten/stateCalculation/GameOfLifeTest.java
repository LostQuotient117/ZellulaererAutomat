/*
package de.nordakademie.zellulaere_automaten.stateCalculation;
import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.simulationMode.SimulationMode;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.GameOfLife;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import org.mockito.Mockito;

@Test
public class GameOfLifeTest {

    private Cell cell;
    private ArrayList<Cell> neighbors;

    @Before
    public void setUp() {
        cell = mock(Cell.class);
        neighbors = new ArrayList<>();
    }

    @Test
    public void testGameOfLifeCellDiesDueToUnderpopulation() {
        GameOfLife gameOfLife = new GameOfLife(mock(SimulationMode.class));

        Cell neighbor1 = mock(Cell.class);
        when(neighbor1.isAlive()).thenReturn(true);
        neighbors.add(neighbor1);

        when(cell.isAlive()).thenReturn(true);

        gameOfLife.calculateCellState(cell, neighbors);

        verify(cell).setAliveStatus(false);
    }

    @Test
    public void testGameOfLifeCellStaysAlive() {
        GameOfLife gameOfLife = new GameOfLife(mock(SimulationMode.class));

        Cell neighbor1 = mock(Cell.class);
        when(neighbor1.isAlive()).thenReturn(true);
        Cell neighbor2 = mock(Cell.class);
        when(neighbor2.isAlive()).thenReturn(true);
        Cell neighbor3 = mock(Cell.class);
        when(neighbor3.isAlive()).thenReturn(true);

        neighbors.add(neighbor1);
        neighbors.add(neighbor2);
        neighbors.add(neighbor3);

        when(cell.isAlive()).thenReturn(true);

        gameOfLife.calculateCellState(cell, neighbors);

        verify(cell, never()).setAliveStatus(false);
    }

    @Test
    public void testGameOfLifeCellBecomesAlive() {
        GameOfLife gameOfLife = new GameOfLife(mock(SimulationMode.class));

        Cell neighbor1 = mock(Cell.class);
        when(neighbor1.isAlive()).thenReturn(true);
        Cell neighbor2 = mock(Cell.class);
        when(neighbor2.isAlive()).thenReturn(true);
        Cell neighbor3 = mock(Cell.class);
        when(neighbor3.isAlive()).thenReturn(true);

        neighbors.add(neighbor1);
        neighbors.add(neighbor2);
        neighbors.add(neighbor3);

        when(cell.isAlive()).thenReturn(false);

        gameOfLife.calculateCellState(cell, neighbors);

        verify(cell).setAliveStatus(true);
    }

    @Test
    public void testGameOfLifeCellDiesDueToOverpopulation() {
        GameOfLife gameOfLife = new GameOfLife(mock(SimulationMode.class));

        Cell neighbor1 = mock(Cell.class);
        when(neighbor1.isAlive()).thenReturn(true);
        Cell neighbor2 = mock(Cell.class);
        when(neighbor2.isAlive()).thenReturn(true);
        Cell neighbor3 = mock(Cell.class);
        when(neighbor3.isAlive()).thenReturn(true);
        Cell neighbor4 = mock(Cell.class);
        when(neighbor4.isAlive()).thenReturn(true);

        neighbors.add(neighbor1);
        neighbors.add(neighbor2);
        neighbors.add(neighbor3);
        neighbors.add(neighbor4);

        when(cell.isAlive()).thenReturn(true);

        gameOfLife.calculateCellState(cell, neighbors);

        verify(cell).setAliveStatus(false);
    }
}
*/
