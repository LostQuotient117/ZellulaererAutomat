package de.nordakademie.zellulaere_automaten.grid;

import de.nordakademie.zellulaere_automaten.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SetGridTest {

    private SetGrid setGrid;
    private Set<Cell> initialCells;

    @BeforeEach
    void setUp() {
        initialCells = new HashSet<>();
        initialCells.add(new Cell(0, 0, true));
        initialCells.add(new Cell(1, 1, true));
        initialCells.add(new Cell(2, 2, true));
        setGrid = new SetGrid(3, 3, initialCells);
    }

    //region getters Test
    @Test
    void getRows_DefaultInitialization_ReturnsCorrectRows() {
        assertEquals(3, setGrid.getRows());
    }

    @Test
    void getColumns_DefaultInitialization_ReturnsCorrectColumns() {
        assertEquals(3, setGrid.getColumns());
    }

    @Test
    void getDataStructure_InitializedWithCells_ReturnsActiveCellsSet() {
        assertEquals(initialCells, setGrid.getDataStructure());
    }
    //endregion

    //region getCellByCoordinates Test
    @Test
    void getCellByCoordinates_CellExists_ReturnsAliveCell() {
        Cell cell = setGrid.getCellByCoordinates(0, 0);
        assertTrue(cell.getIsAlive());
    }

    @Test
    void getCellByCoordinates_CellDoesNotExist_ReturnsDeadCell() {
        Cell cell = setGrid.getCellByCoordinates(1, 0);
        assertFalse(cell.getIsAlive());
    }
    //endregion

    //region isStable Test
    @Test
    void isStable_NoChangeInGrid_ReturnsTrue() {
        setGrid.activeCellsLastIteration = new HashSet<>(setGrid.activeCells);
        assertTrue(setGrid.isStable());
    }

    @Test
    void isStable_ChangeInGrid_ReturnsFalse() {
        setGrid.activeCellsLastIteration = new HashSet<>(setGrid.activeCells);
        setGrid.activeCells.add(new Cell(0, 1, true));
        assertFalse(setGrid.isStable());
    }
    //endregion

    //region toString Test
    @Test
    void toString_GridWithCells_CorrectStringRepresentation() {
        String expected = "1 0 0 \n0 1 0 \n0 0 1 \n";
        assertEquals(expected, setGrid.toString());
    }

    @Test
    void toString_EmptyGrid_AllCellsDeadRepresentation() {
        setGrid = new SetGrid(3, 3, new HashSet<>());
        String expected = "0 0 0 \n0 0 0 \n0 0 0 \n";
        assertEquals(expected, setGrid.toString());
    }
    //endregion
}
