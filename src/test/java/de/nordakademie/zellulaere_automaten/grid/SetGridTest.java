package de.nordakademie.zellulaere_automaten.grid;

import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.INeighborStrategy;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.Moore;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.GameOfLife;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.ICellStateCalculation;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.Parity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


/**
 * This test class verifies the functionality of the {@link SetGrid} class, which models a grid
 * of cells in a cellular automaton. It tests initialization, next-generation calculation,
 * stability checking, and string representation of the grid under different configurations.
 *
 * The tests are organized into nested classes to cover different scenarios:
 * - Default setup tests confirm basic properties like row and column counts, active cell retrieval,
 *   and stability when the grid state is unchanged.
 * - Empty grid tests ensure the correct behavior when no cells are active.
 * - Next generation tests evaluate the cell state transitions according to the Game of Life rules,
 *   checking whether cells survive, die, or become alive based on their neighbors.
 *
 * Different neighborhood and state calculation strategies are used to validate the flexibility
 * and correctness of the SetGrid implementation.
 * @author Daria Stolarczyk
 */
class SetGridTest {

    private SetGrid grid;
    private INeighborStrategy neighborStrategy;
    private ICellStateCalculation stateCalculationStrategy;
    private Set<Cell> initialCells;

    @Nested
    class DefaultSetupTests {

        @BeforeEach
        void setUp() {
            neighborStrategy = new Moore();
            stateCalculationStrategy = new GameOfLife();
            initialCells = new HashSet<>();
            initialCells.add(new Cell(0, 0, true));
            initialCells.add(new Cell(1, 1, true));
            initialCells.add(new Cell(2, 2, true));
            grid = new SetGrid(3, 3, initialCells, stateCalculationStrategy, neighborStrategy);
        }

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

        @Test
        void toString_GridWithCells_CorrectStringRepresentation() {
            String expected = "100\n010\n001\n";
            assertEquals(expected, grid.toString());
        }
    }

    @Nested
    class EmptyGridTests {

        @BeforeEach
        void setUp() {
            neighborStrategy = new Moore();
            stateCalculationStrategy = new GameOfLife();
            grid = new SetGrid(3, 3, new HashSet<>(), stateCalculationStrategy, neighborStrategy);
        }

        @Test
        void toString_EmptyGrid_AllCellsDeadRepresentation() {
            String expected = "000\n000\n000\n";
            assertEquals(expected, grid.toString(), "Die String-Repräsentation sollte nur Nullen für ein leeres Grid enthalten.");
        }
    }

    @Nested
    class CalculateNextGenerationWithGameOfLifeTests {

        @BeforeEach
        void setUp() {
            neighborStrategy = new Moore();
            stateCalculationStrategy = new GameOfLife();
            initialCells = new HashSet<>();
            grid = new SetGrid(3, 3, initialCells, stateCalculationStrategy, neighborStrategy);
        }

        @Test
        void calculateNextGeneration_SingleAliveCell_NoSurvival() {
            grid.activeCells.add(new Cell(0, 0, true));
            grid.calculateNextGeneration();
            assertTrue(grid.activeCells.isEmpty(), "Die Zelle sollte in der nächsten Generation tot sein.");
        }

        @Test
        void calculateNextGeneration_AliveCellWithTwoNeighbors_Survives() {
            grid.activeCells.add(new Cell(1, 1, true));
            grid.activeCells.add(new Cell(0, 1, true));
            grid.activeCells.add(new Cell(2, 1, true));

            grid.calculateNextGeneration();
            assertTrue(grid.activeCells.contains(new Cell(1, 1, true)), "Die Zelle sollte in der nächsten Generation am Leben bleiben.");
        }

        @Test
        void calculateNextGeneration_DeadCellWithThreeNeighbors_BecomesAlive() {
            grid.activeCells.add(new Cell(0, 1, true));
            grid.activeCells.add(new Cell(2, 1, true));
            grid.activeCells.add(new Cell(1, 0, true));

            grid.calculateNextGeneration();
            assertTrue(grid.activeCells.contains(new Cell(1, 1, true)), "Die tote Zelle sollte in der nächsten Generation lebendig werden.");
        }

        @Test
        void calculateNextGeneration_NoChanges_GridIsStable() {
            initialCells.add(new Cell(1, 1, true));
            initialCells.add(new Cell(0, 1, true));
            initialCells.add(new Cell(2, 1, true));

            grid.calculateNextGeneration();
            Set<Cell> previousGeneration = new HashSet<>(grid.activeCells);
            grid.calculateNextGeneration();

            assertEquals(previousGeneration, grid.activeCells, "Das Grid sollte stabil sein und keine Änderungen aufweisen.");
        }
    }
}
