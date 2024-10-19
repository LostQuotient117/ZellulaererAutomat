package de.nordakademie.zellulaere_automaten.model;

import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.StateCalculation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TestCell {

    private Cell cell;

    //left out for now because of complexity and errors
    //private StateCalculation mockStateCalculation;

    @BeforeEach
    void setUp() {
        //left out for now because of complexity and errors
        //mockStateCalculation = Mockito.mock(StateCalculation.class);
        cell = new Cell(1, 1, true, List.of());
    }

    @Test
    void testGetRow() {
        assertEquals(1, cell.getRow());
    }

    @Test
    void testGetColumn() {
        assertEquals(1, cell.getColumn());
    }

    @Test
    void testGetIsAlive() {
        assertTrue(cell.getIsAlive());
    }

    @Test
    void testGetNeighborList() {
        assertNotNull(cell.getNeighborList());
    }

    //left out for now because of complexity and errors
    /*
    @Test
    void testGetStateCalculation() {
        assertEquals(mockStateCalculation, cell.getStateCalculation());
    }
     */

    @Test
    void testSetRow() {
        cell.setRow(2);
        assertEquals(2, cell.getRow());
    }

    @Test
    void testSetColumn() {
        cell.setColumn(2);
        assertEquals(2, cell.getColumn());
    }

    @Test
    void testSetIsAlive() {
        cell.setIsAlive(false);
        assertFalse(cell.getIsAlive());
    }

    @Test
    void testSetNeighborList() {
        List<Cell> neighbors = List.of(new Cell(0, 0, false, List.of()));
        cell.setNeighborList(neighbors);
        assertEquals(neighbors, cell.getNeighborList());
    }

    //left out for now because of complexity and errors
    /*
    @Test
    void testSetStateCalculation() {
        StateCalculation newMockStateCalculation = Mockito.mock(StateCalculation.class);
        cell.setStateCalculation(newMockStateCalculation);
        assertEquals(newMockStateCalculation, cell.getStateCalculation());
    }
     */
}
