package de.nordakademie.zellulaere_automaten.grid;

import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.INeighborStrategy;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.ICellStateCalculation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SetGridTest {

    private ICellStateCalculation mockStateCalculation;
    private INeighborStrategy mockNeighborStrategy;

    @BeforeEach
    void setUpMocks() {
        mockStateCalculation = mock(ICellStateCalculation.class);
        mockNeighborStrategy = mock(INeighborStrategy.class);
    }

    @Nested
    class DefaultSetupTests {
        private SetGrid grid;
        private Set<Cell> initialCells;

        @BeforeEach
        void defaultSetUp() {
            initialCells = new HashSet<>();
            initialCells.add(new Cell(0, 0, true));
            initialCells.add(new Cell(1, 1, true));
            initialCells.add(new Cell(2, 2, true));
            grid = new SetGrid(3, 3, initialCells, mockStateCalculation, mockNeighborStrategy);
        }

        //region Getter Tests
        @Test
        void getRows_DefaultInitialization_ReturnsCorrectRows() {
            assertEquals(3, grid.getRows());
        }

        @Test
        void getColumns_DefaultInitialization_ReturnsCorrectColumns() {
            assertEquals(3, grid.getColumns());
        }

        @Test
        void getDataStructure_InitializedWithCells_ReturnsActiveCellsSet() {
            assertEquals(initialCells, grid.getDataStructure());
        }
        //endregion

        //region GetCellByCoordinates Tests
        @Test
        void getCellByCoordinates_CellExists_ReturnsAliveCell() {
            Cell cell = grid.getCellByCoordinates(0, 0);
            assertTrue(cell.getIsAlive());
        }

        @Test
        void getCellByCoordinates_CellDoesNotExist_ReturnsDeadCell() {
            Cell cell = grid.getCellByCoordinates(1, 0);
            assertFalse(cell.getIsAlive());
        }
        //endregion

        //region IsStable Tests
        @Test
        void isStable_NoChangeInGrid_ReturnsTrue() {
            grid.activeCellsLastIteration = new HashSet<>(grid.activeCells);
            assertTrue(grid.isStable());
        }

        @Test
        void isStable_ChangeInGrid_ReturnsFalse() {
            grid.activeCellsLastIteration = new HashSet<>(grid.activeCells);
            grid.activeCells.add(new Cell(0, 1, true));
            assertFalse(grid.isStable());
        }
        //endregion

        //region ToString Tests
        @Test
        void toString_GridWithCells_CorrectStringRepresentation() {
            String expected = "1 0 0 \n0 1 0 \n0 0 1 \n";
            assertEquals(expected, grid.toString());
        }
        //endregion
    }

    @Nested
    class EmptyGridTests {
        private SetGrid grid;

        @BeforeEach
        void setUpEmptyGrid() {
            grid = new SetGrid(3, 3, new HashSet<>(), mockStateCalculation, mockNeighborStrategy);
        }

        @Test
        void toString_EmptyGrid_AllCellsDeadRepresentation() {
            String expected = "0 0 0 \n0 0 0 \n0 0 0 \n";
            assertEquals(expected, grid.toString(), "Die String-Repräsentation sollte nur Nullen für ein leeres Grid enthalten.");
        }
    }

    @Nested
    class CalculateNextGenerationTests {
        private SetGrid grid;

        @BeforeEach
        void setUpOneLivingCell() {
            Set<Cell> initialCells = new HashSet<>();
            initialCells.add(new Cell(0, 0, true));
            grid = new SetGrid(3, 3, initialCells, mockStateCalculation, mockNeighborStrategy);
        }

        @Test
        void calculateNextGeneration_SingleAliveCell_NoSurvival() {
            Cell cell = new Cell(0, 0, true);
            ArrayList<Cell> neighbors = new ArrayList<>();
            when(mockNeighborStrategy.getNeighbors(cell, grid)).thenReturn(neighbors);
            when(mockStateCalculation.staysAlive(cell, neighbors)).thenReturn(false); // Zelle stirbt

            grid.calculateNextGeneration();

            assertTrue(grid.activeCells.isEmpty(), "Die Zelle sollte in der nächsten Generation tot sein.");
        }

        @Test
        void calculateNextGeneration_AliveCellWithTwoNeighbors_Survives() {
            Cell cell = new Cell(1, 1, true);
            ArrayList<Cell> neighbors = new ArrayList<>();
            neighbors.add(new Cell(0, 1, true));
            neighbors.add(new Cell(2, 1, true));
            when(mockNeighborStrategy.getNeighbors(cell, grid)).thenReturn(neighbors);
            when(mockStateCalculation.staysAlive(cell, neighbors)).thenReturn(true); // Zelle bleibt am Leben

            grid.calculateNextGeneration();

            assertTrue(grid.activeCells.contains(cell), "Die Zelle sollte in der nächsten Generation am Leben bleiben.");
        }

        @Test
        void calculateNextGeneration_DeadCellWithThreeNeighbors_BecomesAlive() {
            Cell cell = new Cell(1, 1, false);
            ArrayList<Cell> neighbors = new ArrayList<>();
            neighbors.add(new Cell(0, 1, true));
            neighbors.add(new Cell(2, 1, true));
            neighbors.add(new Cell(1, 0, true));

            when(mockNeighborStrategy.getNeighbors(cell, grid)).thenReturn(neighbors);
            when(mockStateCalculation.staysDead(cell, neighbors)).thenReturn(false);

            grid.calculateNextGeneration();

            assertTrue(grid.activeCells.contains(new Cell(1, 1, true)), "Die tote Zelle sollte in der nächsten Generation lebendig werden.");
        }

        @Test
        void calculateNextGeneration_NoChanges_GridIsStable() {
            Cell cell = new Cell(1, 1, true);
            ArrayList<Cell> neighbors = new ArrayList<>();
            neighbors.add(new Cell(0, 1, true));
            neighbors.add(new Cell(2, 1, true));

            when(mockNeighborStrategy.getNeighbors(cell, grid)).thenReturn(neighbors);
            when(mockStateCalculation.staysAlive(cell, neighbors)).thenReturn(true);

            grid.calculateNextGeneration();

            Set<Cell> previousGeneration = new HashSet<>(grid.activeCells);
            grid.calculateNextGeneration();

            assertEquals(previousGeneration, grid.activeCells, "Das Grid sollte stabil sein und keine Änderungen aufweisen.");
        }
    }
}
