package de.nordakademie.zellulaere_automaten.stateCalculation;

import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.Parity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link Parity} class, which implements a cell state calculation
 * strategy based on the parity (odd or even count) of alive neighbors. This test suite
 * verifies that cells follow the correct survival and birth rules according to the
 * parity of their neighbors.
 *
 * The tests cover:
 * - The behavior of alive cells, ensuring they stay alive with an odd number of alive neighbors
 *   and die with an even number of alive neighbors.
 * - The behavior of dead cells, ensuring they remain dead with an even number of alive neighbors
 *   and become alive with an odd number of alive neighbors.
 * @author Daria Stolarczyk
 */
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
