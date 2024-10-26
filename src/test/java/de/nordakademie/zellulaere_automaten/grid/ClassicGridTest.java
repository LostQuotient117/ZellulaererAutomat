package de.nordakademie.zellulaere_automaten.grid;

import de.nordakademie.zellulaere_automaten.model.Cell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassicGridTest {

    //region Test isStable
    /**
     * Test for the isStable method when both grids are identical.
     * The expected result is that the grid is stable (true).
     */
    @Test
    void isStable_IdenticalGrids_ReturnsTrue() {
        ClassicGrid grid = new ClassicGrid(3, 3);
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getColumns(); col++) {
                Cell cell = new Cell(row, col, true); // All cells are alive
                grid.getGrid()[row][col] = cell;
                grid.getPreviousGrid()[row][col] = new Cell(row, col, true); // Set previous grid to the same
            }
        }
        assertTrue(grid.isStable());
    }

    /**
     * Test for the isStable method when the grids have different states.
     * The expected result is that the grid is not stable (false).
     */
    @Test
    void isStable_DifferentGrids_ReturnsFalse() {
        ClassicGrid grid = new ClassicGrid(3, 3);
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getColumns(); col++) {
                Cell cell = new Cell(row, col, true); // Current grid cells are alive
                grid.getGrid()[row][col] = cell;
                grid.getPreviousGrid()[row][col] = new Cell(row, col, false); // Previous grid cells are dead
            }
        }
        assertFalse(grid.isStable());
    }
    // endregion

    //region Test copyGrid
    /**
     * Test for the copyGrid method when copying from one grid to another.
     * The expected result is that the target grid has the same content as the source grid.
     */
    @Test
    void copyGrid_SourceCopiedToTarget_GridsAreEqual() {
        int rows = 3;
        int columns = 3;
        ClassicGrid grid = new ClassicGrid(rows, columns);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                boolean isAlive = (row + col) % 2 == 0; // Alternating alive and dead cells
                grid.getGrid()[row][col] = new Cell(row, col, isAlive);
            }
        }

        grid.copyGrid(grid.getGrid(), grid.getPreviousGrid());

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                assertEquals(grid.getGrid()[row][col], grid.getPreviousGrid()[row][col]);
            }
        }
    }

    /**
     * Test for the copyGrid method when modifying the original grid.
     * The expected result is that the copied grid remains unchanged.
     */
    @Test
    void copyGrid_ModifySourceGrid_CopyRemainsUnchanged() {
        int rows = 3;
        int columns = 3;
        ClassicGrid grid = new ClassicGrid(rows, columns);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                boolean isAlive = (row + col) % 2 == 0;
                grid.getGrid()[row][col] = new Cell(row, col, isAlive);
            }
        }

        grid.copyGrid(grid.getGrid(), grid.getPreviousGrid());
        grid.getGrid()[0][0].setIsAlive(!grid.getGrid()[0][0].getIsAlive());

        assertNotEquals(grid.getGrid()[0][0], grid.getPreviousGrid()[0][0]);
    }
    //endregion

    // region Test ToString
    /**
     * Test for the toString method when the grid is initialized with alternating alive and dead cells.
     * The expected result is a correctly formatted string representation of the grid.
     */
    @Test
    void toString_AlternatingCells_CorrectlyFormatted() {
        ClassicGrid grid = new ClassicGrid(3, 3);

        grid.getGrid()[0][0] = new Cell(0, 0, true);
        grid.getGrid()[0][1] = new Cell(0, 1, false);
        grid.getGrid()[0][2] = new Cell(0, 2, true);

        grid.getGrid()[1][0] = new Cell(1, 0, false);
        grid.getGrid()[1][1] = new Cell(1, 1, true);
        grid.getGrid()[1][2] = new Cell(1, 2, false);

        grid.getGrid()[2][0] = new Cell(2, 0, true);
        grid.getGrid()[2][1] = new Cell(2, 1, false);
        grid.getGrid()[2][2] = new Cell(2, 2, true);

        String expectedOutput = "1 0 1 \n0 1 0 \n1 0 1 \n";
        String actualOutput = grid.toString();

        assertEquals(expectedOutput, actualOutput);
    }
    //endregion

    //region Test getCellByCoordinates
    /**
     * Test for the getCellByCoordinates method when the requested coordinates are valid.
     * The expected result is that the correct cell is returned.
     */
    @Test
    void getCellByCoordinates_ValidCoordinates_ReturnsCorrectCell() {
        ClassicGrid grid = new ClassicGrid(3, 3);

        Cell expectedCell = new Cell(1, 1, true);
        grid.getGrid()[1][1] = expectedCell;

        Cell actualCell = grid.getCellByCoordinates(1, 1);

        assertEquals(expectedCell, actualCell);
    }

    /**
     * Test for the getCellByCoordinates method when the requested coordinates are out of bounds.
     * The expected result is that an IndexOutOfBoundsException is thrown.
     */
    @Test
    void getCellByCoordinates_OutOfBounds_ThrowsException() {
        ClassicGrid grid = new ClassicGrid(3, 3);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            grid.getCellByCoordinates(5, 5);
        });
    }
    //endregion
}
