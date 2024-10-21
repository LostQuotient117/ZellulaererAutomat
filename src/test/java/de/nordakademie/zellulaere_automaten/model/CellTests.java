package de.nordakademie.zellulaere_automaten.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * The {@code CellTests} class contains unit tests for the {@code Cell} class.
 * It uses JUnit 5 for testing.
 * The tests cover the getter and setter methods of the {@code Cell} class.
 * It also includes a test for the {@code isEqualCell} method.
 *
 * @author Lars Nicht
 */
class CellTests {

    private Cell cell;

    //left out for now because of complexity and errors
    //private StateCalculation mockStateCalculation;

    /**
     * Sets up the test environment before each test.
     * Initializes a new {@code Cell} instance.
     */
    @BeforeEach
    void setUp() {
        //left out for now because of complexity and errors
        //mockStateCalculation = Mockito.mock(StateCalculation.class);
        cell = new Cell(1, 1, true, List.of());
    }

    // region Getter Tests
    /**
     * Tests the {@code getRow} method of the {@code Cell} class.
     * Asserts that the row position is correctly returned.
     */
    @Test
    void getRow_WhenCalled_ShouldReturnRow() {
        assertEquals(1, cell.getRow());
    }

    /**
     * Tests the {@code getColumn} method of the {@code Cell} class.
     * Asserts that the column position is correctly returned.
     */
    @Test
    void getColumn_WhenCalled_ShouldReturnColumn() {
        assertEquals(1, cell.getColumn());
    }

    /**
     * Tests the {@code getIsAlive} method of the {@code Cell} class.
     * Asserts that the alive status is correctly returned.
     */
    @Test
    void getIsAlive_WhenCalled_ShouldReturnTrue() {
        assertTrue(cell.getIsAlive());
    }

    /**
     * Tests the {@code getNeighborList} method of the {@code Cell} class.
     * Asserts that the neighbor list is not null.
     */
    @Test
    void getNeighborList_WhenCalled_ShouldNotBeNull() {
        assertNotNull(cell.getNeighborList());
    }

    //left out for now because of complexity and errors
    /*
    @Test
    void testGetStateCalculation() {
        assertEquals(mockStateCalculation, cell.getStateCalculation());
    }
     */
    // endregion


    // region Setter Tests
    /**
     * Tests the {@code setRow} method of the {@code Cell} class.
     * Asserts that the row position is correctly set.
     */
    @Test
    void setRow_WithValidRow_ShouldUpdateRow() {
        cell.setRow(2);
        assertEquals(2, cell.getRow());
    }

    /**
     * Tests the {@code setColumn} method of the {@code Cell} class.
     * Asserts that the column position is correctly set.
     */
    @Test
    void setColumn_WithValidColumn_ShouldUpdateColumn() {
        cell.setColumn(2);
        assertEquals(2, cell.getColumn());
    }

    /**
     * Tests the {@code setIsAlive} method of the {@code Cell} class.
     * Asserts that the alive status is correctly set.
     */
    @Test
    void setIsAlive_WithFalse_ShouldUpdateIsAlive() {
        cell.setIsAlive(false);
        assertFalse(cell.getIsAlive());
    }


    /**
     * Tests the {@code setNeighborList} method of the {@code Cell} class.
     * Asserts that the neighbor list is correctly set.
     */
    @Test
    void setNeighborList_WithValidList_ShouldUpdateNeighborList() {
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
    // endregion


    // region Logic Tests

    // region isEqualCell Tests

    /**
     * Tests the {@code isEqualCell} method of the {@code Cell} class.
     * Asserts that two cells with the same coordinates and alive status are equal.
     */
    @Test
    void isEqualCell_WithSameCoordinatesAndAliveStatus_ShouldReturnTrue() {
        Cell otherCell = new Cell(1, 1, true);
        assertTrue(cell.isEqualCell(otherCell));
    }

    /**
     * Tests the {@code isEqualCell} method of the {@code Cell} class.
     * Asserts that two cells with different coordinates are not equal.
     */
    @Test
    void isEqualCell_WithDifferentCoordinates_ShouldReturnFalse() {
        Cell otherCell = new Cell(2, 1, true);
        assertFalse(cell.isEqualCell(otherCell));
    }

    /**
     * Tests the {@code isEqualCell} method of the {@code Cell} class.
     * Asserts that two cells with different alive status are not equal.
     */
    @Test
    void isEqualCell_WithDifferentAliveStatus_ShouldReturnFalse() {
        Cell otherCell = new Cell(1, 1, false);
        assertFalse(cell.isEqualCell(otherCell));
    }
    // endregion


    // endregion

}
