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

    // region toStringTests
    /**
     * Tests the {@code toString()} method for a dead cell.
     * This test verifies that when the {@code isAlive} property of the cell is false, the method returns "0".
     */
    @Test
    void toString_DeadCell_ShouldReturnZeroString(){
        Cell otherCell = new Cell(1, 1, false);
        assertEquals("0", otherCell.toString());
    }

    /**
     * Tests the {@code toString()} method for a live cell.
     * This test verifies that when the {@code isAlive} property of the cell is true, the method returns "1".
     */
    @Test
    void toString_AliveCell_ShouldReturnStringWithNoOne(){
        Cell otherCell = new Cell(1, 1, true);
        assertEquals("1", otherCell.toString());
    }
    //endregion

    // region equals tests

    /**
     * Tests the {@code equals()} method for the same object.
     * This test verifies that a {@code Cell} object is equal to itself, returning true when both references
     * are to the same instance.
     */
    @Test
    void equals_SameObject_ReturnsTrue() {
        Cell cell = new Cell(1, 1, true);
        assertEquals(cell, cell);
    }

    /**
     * Tests the {@code equals()} method when comparing to a {@code null} object.
     * This test verifies that a {@code Cell} object is not equal to {@code null}, returning false.
     */
    @Test
    void equals_NullObject_ReturnsFalse() {
        Cell cell = new Cell(1, 1, true);
        assertNotEquals(null, cell);
    }

    /**
     * Tests the {@code equals()} method when comparing to an object of a different class.
     * This test verifies that a {@code Cell} object is not equal to an object of a different class,
     * returning false.
     */
    @Test
    void equals_DifferentClassObject_ReturnsFalse() {
        Cell cell = new Cell(1, 1, true);
        String differentObject = "I am not a Cell";
        assertNotEquals(cell, differentObject);
    }

    /**
     * Tests the {@code equals()} method when two cells have the same {@code row} and {@code column}.
     * This test verifies that two {@code Cell} objects with the same {@code row} and {@code column} are
     * considered equal, even if their {@code isAlive} status is different.
     */
    @Test
    void equals_SameRowAndColumn_ReturnsTrue() {
        Cell cell1 = new Cell(1, 1, true);
        Cell cell2 = new Cell(1, 1, false);  // Different alive status, but should still be equal based on row and column
        assertEquals(cell1, cell2);
    }

    /**
     * Tests the {@code equals()} method when two cells have different {@code row} values.
     * This test verifies that two {@code Cell} objects with different {@code row} values are not equal.
     */
    @Test
    void equals_DifferentRow_ReturnsFalse() {
        Cell cell1 = new Cell(1, 1, true);
        Cell cell2 = new Cell(2, 1, true);  // Different row
        assertNotEquals(cell1, cell2);
    }

    /**
     * Tests the {@code equals()} method when two cells have different {@code column} values.
     * This test verifies that two {@code Cell} objects with different {@code column} values are not equal.
     */
    @Test
    void equals_DifferentColumn_ReturnsFalse() {
        Cell cell1 = new Cell(1, 1, true);
        Cell cell2 = new Cell(1, 2, true);  // Different column
        assertNotEquals(cell1, cell2);
    }
    // endregion

    // region hashCoce test

    /**
     * Tests that the hashCode of the same object remains consistent across multiple calls.
     * This test verifies that calling {@code hashCode()} multiple times on the same {@code Cell} object
     * returns the same value each time, ensuring the consistency of the hash code.
     */
    @Test
    void hashCode_ConsistentHashCode_ReturnsSameValue() {
        Cell cell = new Cell(1, 1, true);
        int initialHashCode = cell.hashCode();
        assertEquals(initialHashCode, cell.hashCode());
        assertEquals(initialHashCode, cell.hashCode());
    }

    /**
     * Tests that two objects which are equal according to {@code equals()} have the same hash code.
     * This test verifies that if two {@code Cell} objects are equal, they must have the same hash code.
     */
    @Test
    void hashCode_EqualObjects_ReturnSameHashCode() {
        Cell cell1 = new Cell(1, 1, true);
        Cell cell2 = new Cell(1, 1, false); // row and column are the same, isAlive is ignored in equals
        assertEquals(cell1.hashCode(), cell2.hashCode());
    }

    /**
     * Tests that two objects which are not equal according to {@code equals()} have different hash codes.
     * This test verifies that if two {@code Cell} objects are not equal, they should ideally have different hash codes.
     */
    @Test
    void hashCode_UnequalObjects_ReturnDifferentHashCodes() {
        Cell cell1 = new Cell(1, 1, true);
        Cell cell2 = new Cell(2, 1, true); // Different row
        assertNotEquals(cell1.hashCode(), cell2.hashCode());
    }
    //endregion
    // endregion

}
