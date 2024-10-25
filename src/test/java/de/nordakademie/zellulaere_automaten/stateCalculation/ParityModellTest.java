/*
package de.nordakademie.zellulaere_automaten.stateCalculation;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.simulationMode.SimulationMode;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.ParityModell;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class ParityModellTest {

    private Cell cell;
    private ArrayList<Cell> neighbors;

    @Before
    public void setUp() {
        cell = mock(Cell.class);
        neighbors = new ArrayList<>();
    }

    @Test
    public void testParityModellCellBecomesAlive() {
        ParityModell parityModell = new ParityModell(mock(SimulationMode.class));

        Cell neighbor1 = mock(Cell.class);
        when(neighbor1.isAlive()).thenReturn(true);
        Cell neighbor2 = mock(Cell.class);
        when(neighbor2.isAlive()).thenReturn(true);
        Cell neighbor3 = mock(Cell.class);
        when(neighbor3.isAlive()).thenReturn(true);

        neighbors.add(neighbor1);
        neighbors.add(neighbor2);
        neighbors.add(neighbor3);

        parityModell.calculateCellState(cell, neighbors);

        verify(cell).setAliveStatus(true);
    }

    @Test
    public void testParityModellCellBecomesDead() {
        ParityModell parityModell = new ParityModell(mock(SimulationMode.class));

        Cell neighbor1 = mock(Cell.class);
        when(neighbor1.isAlive()).thenReturn(true);
        Cell neighbor2 = mock(Cell.class);
        when(neighbor2.isAlive()).thenReturn(true);

        neighbors.add(neighbor1);
        neighbors.add(neighbor2);

        parityModell.calculateCellState(cell, neighbors);

        verify(cell).setIsAlive(false);
    }*/
