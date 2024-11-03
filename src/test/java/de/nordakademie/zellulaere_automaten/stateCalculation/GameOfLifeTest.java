package de.nordakademie.zellulaere_automaten.stateCalculation;

import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.GameOfLife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link GameOfLife} class, which implements the cell state calculation
 * rules for the "Game of Life" cellular automaton. This test suite verifies that cells
 * follow the correct survival and birth rules based on the number of alive neighbors.
 *
 * The tests cover:
 * - The behavior of alive cells, ensuring they stay alive with exactly two or three neighbors,
 *   and die with fewer than two or more than three neighbors.
 * - The behavior of dead cells, ensuring they become alive with exactly three neighbors and
 *   remain dead in other cases.
 * @author Daria Stolarczyk
 */
class GameOfLifeTest {

    private GameOfLife gameOfLife;

    @BeforeEach
    void setUp() {
        gameOfLife = new GameOfLife();
    }

    //region StaysAlive Test
    @Test
    void staysAlive_TwoAliveNeighbors_ReturnsTrue() {
        Cell cell = new Cell(0, 0, true);
        ArrayList<Cell> neighbors = new ArrayList<>(Arrays.asList(
                new Cell(0, 1, true),
                new Cell(1, 0, true),
                new Cell(1, 1, false)
        ));
        assertTrue(gameOfLife.staysAlive(cell, neighbors));
    }

    @Test
    void staysAlive_ThreeAliveNeighbors_ReturnsTrue() {
        Cell cell = new Cell(0, 0, true);
        ArrayList<Cell> neighbors = new ArrayList<>(Arrays.asList(
                new Cell(0, 1, true),
                new Cell(1, 0, true),
                new Cell(1, 1, true)
        ));
        assertTrue(gameOfLife.staysAlive(cell, neighbors));
    }

    @Test
    void staysAlive_LessThanTwoAliveNeighbors_ReturnsFalse() {
        Cell cell = new Cell(0, 0, true);
        ArrayList<Cell> neighbors = new ArrayList<>(Arrays.asList(
                new Cell(0, 1, true),
                new Cell(1, 0, false),
                new Cell(1, 1, false)
        ));
        assertFalse(gameOfLife.staysAlive(cell, neighbors));
    }

    @Test
    void staysAlive_MoreThanThreeAliveNeighbors_ReturnsFalse() {
        Cell cell = new Cell(0, 0, true);
        ArrayList<Cell> neighbors = new ArrayList<>(Arrays.asList(
                new Cell(0, 1, true),
                new Cell(1, 0, true),
                new Cell(1, 1, true),
                new Cell(0, -1, true)
        ));
        assertFalse(gameOfLife.staysAlive(cell, neighbors));
    }
    //endregion

    //region staysDead Test
    @Test
    void staysDead_ExactlyThreeAliveNeighbors_ReturnsFalse() {
        Cell cell = new Cell(0, 0, false);
        ArrayList<Cell> neighbors = new ArrayList<>(Arrays.asList(
                new Cell(0, 1, true),
                new Cell(1, 0, true),
                new Cell(1, 1, true)
        ));
        assertFalse(gameOfLife.staysDead(cell, neighbors));
    }

    @Test
    void staysDead_LessThanThreeAliveNeighbors_ReturnsTrue() {
        Cell cell = new Cell(0, 0, false);
        ArrayList<Cell> neighbors = new ArrayList<>(Arrays.asList(
                new Cell(0, 1, true),
                new Cell(1, 0, false),
                new Cell(1, 1, false)
        ));
        assertTrue(gameOfLife.staysDead(cell, neighbors));
    }

    @Test
    void staysDead_MoreThanThreeAliveNeighbors_ReturnsTrue() {
        Cell cell = new Cell(0, 0, false);
        ArrayList<Cell> neighbors = new ArrayList<>(Arrays.asList(
                new Cell(0, 1, true),
                new Cell(1, 0, true),
                new Cell(1, 1, true),
                new Cell(0, -1, true)
        ));
        assertTrue(gameOfLife.staysDead(cell, neighbors));
    }
    //endregion
}
