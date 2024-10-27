package de.nordakademie.zellulaere_automaten.stateCalculation;

import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.Parity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ParityTest {

    private Parity parity;

    @BeforeEach
    void setUp() {
        parity = new Parity();
    }

    @Test
    void staysAlive_EvenNumberOfAliveNeighbors_ReturnsFalse() {
        Cell cell = new Cell(0, 0, true);
        ArrayList<Cell> neighbors = new ArrayList<>(Arrays.asList(
                new Cell(0, 1, true),
                new Cell(1, 0, true),
                new Cell(1, 1, false)
        ));

        assertFalse(parity.staysAlive(cell, neighbors));
    }

    @Test
    void staysAlive_OddNumberOfAliveNeighbors_ReturnsTrue() {
        Cell cell = new Cell(0, 0, true);
        ArrayList<Cell> neighbors = new ArrayList<>(Arrays.asList(
                new Cell(0, 1, true),
                new Cell(1, 0, true),
                new Cell(1, 1, true)
        ));

        assertTrue(parity.staysAlive(cell, neighbors));
    }

    @Test
    void staysDead_EvenNumberOfAliveNeighbors_ReturnsTrue() {
        Cell cell = new Cell(0, 0, false);
        ArrayList<Cell> neighbors = new ArrayList<>(Arrays.asList(
                new Cell(0, 1, true),
                new Cell(1, 0, true),
                new Cell(1, 1, false)
        ));

        assertTrue(parity.staysDead(cell, neighbors));
    }

    @Test
    void staysDead_OddNumberOfAliveNeighbors_ReturnsFalse() {
        Cell cell = new Cell(0, 0, false);
        ArrayList<Cell> neighbors = new ArrayList<>(Arrays.asList(
                new Cell(0, 1, true),
                new Cell(1, 0, true),
                new Cell(1, 1, true)
        ));

        assertFalse(parity.staysDead(cell, neighbors));
    }
}
