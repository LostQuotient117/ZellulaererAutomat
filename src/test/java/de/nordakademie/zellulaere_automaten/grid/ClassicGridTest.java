package de.nordakademie.zellulaere_automaten.grid;

import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.INeighborStrategy;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.Neumann;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.GameOfLife;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.ICellStateCalculation;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.Parity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import de.nordakademie.zellulaere_automaten.strategy.neighbors.Moore;
import static org.junit.jupiter.api.Assertions.*;

class ClassicGridTest {

    private ClassicGrid grid;
    private INeighborStrategy neighborStrategy;
    private ICellStateCalculation stateCalculationStrategy;
    private Set<Cell> startConfig;

    @Nested
    class DefaultTesting{
        @BeforeEach
        void setUp() {
            neighborStrategy = Mockito.mock(INeighborStrategy.class);
            stateCalculationStrategy = Mockito.mock(ICellStateCalculation.class);
            startConfig = new HashSet<>();
            grid = new ClassicGrid(3, 3, startConfig, stateCalculationStrategy, neighborStrategy);
        }

        //region Test isStable
        @Test
        void isStable_IdenticalGrids_ReturnsTrue() {
            for (int row = 0; row < grid.getRows(); row++) {
                for (int col = 0; col < grid.getColumns(); col++) {
                    Cell cell = new Cell(row, col, true);
                    grid.getDataStructure()[row][col] = cell;
                    grid.getPreviousGrid()[row][col] = new Cell(row, col, true);
                }
            }
            assertTrue(grid.isStable());
        }

        @Test
        void isStable_DifferentGrids_ReturnsFalse() {
            for (int row = 0; row < grid.getRows(); row++) {
                for (int col = 0; col < grid.getColumns(); col++) {
                    grid.getDataStructure()[row][col] = new Cell(row, col, true);
                    grid.getPreviousGrid()[row][col] = new Cell(row, col, false);
                }
            }
            assertFalse(grid.isStable());
        }
        //endregion

        //region Test copyGrid
        @Test
        void copyGrid_SourceCopiedToTarget_GridsAreEqual() {
            for (int row = 0; row < grid.getRows(); row++) {
                for (int col = 0; col < grid.getColumns(); col++) {
                    boolean isAlive = (row + col) % 2 == 0;
                    grid.getDataStructure()[row][col] = new Cell(row, col, isAlive);
                }
            }

            grid.copyGrid(grid.getDataStructure(), grid.getPreviousGrid());

            for (int row = 0; row < grid.getRows(); row++) {
                for (int col = 0; col < grid.getColumns(); col++) {
                    assertEquals(grid.getDataStructure()[row][col], grid.getPreviousGrid()[row][col]);
                }
            }
        }

        @Test
        void copyGrid_ModifySourceGrid_CopyRemainsUnchanged() {
            for (int row = 0; row < grid.getRows(); row++) {
                for (int col = 0; col < grid.getColumns(); col++) {
                    boolean isAlive = (row + col) % 2 == 0;
                    grid.getDataStructure()[row][col] = new Cell(row, col, isAlive);
                }
            }

            grid.copyGrid(grid.getDataStructure(), grid.getPreviousGrid());
            grid.getDataStructure()[0][0].setIsAlive(!grid.getDataStructure()[0][0].getIsAlive());

            assertNotEquals(grid.getDataStructure()[0][0], grid.getPreviousGrid()[0][0]);
        }
        //endregion

        //region Test toString
        @Test
        void toString_AlternatingCells_CorrectlyFormatted() {
            grid.getDataStructure()[0][0] = new Cell(0, 0, true);
            grid.getDataStructure()[0][1] = new Cell(0, 1, false);
            grid.getDataStructure()[0][2] = new Cell(0, 2, true);
            grid.getDataStructure()[1][0] = new Cell(1, 0, false);
            grid.getDataStructure()[1][1] = new Cell(1, 1, true);
            grid.getDataStructure()[1][2] = new Cell(1, 2, false);
            grid.getDataStructure()[2][0] = new Cell(2, 0, true);
            grid.getDataStructure()[2][1] = new Cell(2, 1, false);
            grid.getDataStructure()[2][2] = new Cell(2, 2, true);

            String expectedOutput = "101\n010\n101\n";
            assertEquals(expectedOutput, grid.toString());
        }
        //endregion

        //region Test getCellByCoordinates
        @Test
        void getCellByCoordinates_ValidCoordinates_ReturnsCorrectCell() {
            Cell newActiveCell = new Cell(1, 1, true);
            grid.getDataStructure()[1][1] = newActiveCell;
            Cell expectedCell = grid.getCellByCoordinates(1, 1);
            assertEquals(newActiveCell, expectedCell);
        }

        @Test
        void getCellByCoordinates_OutOfBounds_ThrowsException() {
            assertThrows(IndexOutOfBoundsException.class, () -> grid.getCellByCoordinates(5, 5));
        }
        //endregion

    }

    //region Test calculateNextGeneration
    @Nested
    class CalculateNextGenerationWIthParityTests {
        @BeforeEach
        void setUp() {
            neighborStrategy = new Neumann();
            stateCalculationStrategy = new Parity();
            startConfig = new HashSet<>();
            grid = new ClassicGrid(3, 3, startConfig, stateCalculationStrategy, neighborStrategy);
        }

        @Test
        void calculateNextGeneration_ParityAndDeadGrid_ReturnFalse() {
            grid.calculateNextGeneration();

            for (int row = 0; row < grid.getRows(); row++) {
                for (int col = 0; col < grid.getColumns(); col++) {
                    assertFalse(grid.getDataStructure()[row][col].getIsAlive());
                }
            }
        }

        @Test
        void calculateNextGeneration_ParityAndOneALiveCell_ReturnTrue() {
            Cell initialCell = new Cell(1, 1, true);
            grid.getDataStructure()[1][1] = initialCell;

            grid.calculateNextGeneration();
            List<Cell> expectedAliveCells = Arrays.asList(
                    new Cell(0, 1, true),
                    new Cell(1, 0, true),
                    new Cell(2, 1, true),
                    new Cell(1, 2, true)
            );
            for (int row = 0; row < grid.getDataStructure().length; row++) {
                for (int col = 0; col < grid.getDataStructure()[0].length; col++) {
                    Cell cell = grid.getDataStructure()[row][col];
                    boolean shouldBeAlive = expectedAliveCells.contains(cell);
                    assertEquals(shouldBeAlive, cell.getIsAlive(),
                            "Cell at (" + row + ", " + col + ") state mismatch.");
                }
            }
        }
    }
    //endregion

    @Nested
    class CalculateNextGenerationWithGameOfLifeTests {
        @BeforeEach
        void setUp() {
            neighborStrategy = new Moore();
            stateCalculationStrategy = new GameOfLife();
            startConfig = new HashSet<>();
            grid = new ClassicGrid(5, 5, startConfig, stateCalculationStrategy, neighborStrategy);
        }

        @Test
        void calculateNextGeneration_EmptyGrid_AllCellsDead() {
            grid.calculateNextGeneration();

            for (int row = 0; row < grid.getRows(); row++) {
                for (int col = 0; col < grid.getColumns(); col++) {
                    assertFalse(grid.getDataStructure()[row][col].getIsAlive());
                }
            }
        }

        @Test
        void calculateNextGeneration_OneAliveCell_NoCellsAlive() {
            Cell initialCell = new Cell(2, 2, true);
            grid.getDataStructure()[2][2] = initialCell;

            grid.calculateNextGeneration();

            for (int row = 0; row < grid.getRows(); row++) {
                for (int col = 0; col < grid.getColumns(); col++) {
                    assertFalse(grid.getDataStructure()[row][col].getIsAlive(),
                            "Cell at (" + row + ", " + col + ") should be dead.");
                }
            }
        }

        @Test
        void calculateNextGeneration_BlinkerPattern_AlternatesCorrectly() {
            grid.getDataStructure()[1][2].setIsAlive(true);
            grid.getDataStructure()[2][2].setIsAlive(true);
            grid.getDataStructure()[3][2].setIsAlive(true);

            grid.calculateNextGeneration();

            List<Cell> expectedAliveCells = Arrays.asList(
                    new Cell(2, 1, true),
                    new Cell(2, 2, true),
                    new Cell(2, 3, true)
            );

            for (int row = 0; row < grid.getRows(); row++) {
                for (int col = 0; col < grid.getColumns(); col++) {
                    Cell cell = grid.getDataStructure()[row][col];
                    boolean shouldBeAlive = expectedAliveCells.contains(cell);
                    assertEquals(shouldBeAlive, cell.getIsAlive(),
                            "Cell at (" + row + ", " + col + ") state mismatch.");
                }
            }
        }

        @Test
        void calculateNextGeneration_BlockPattern_StaticPattern() {
            grid.getDataStructure()[1][1].setIsAlive(true);
            grid.getDataStructure()[1][2].setIsAlive(true);
            grid.getDataStructure()[2][1].setIsAlive(true);
            grid.getDataStructure()[2][2].setIsAlive(true);

            grid.calculateNextGeneration();

            List<Cell> expectedAliveCells = Arrays.asList(
                    new Cell(1, 1, true),
                    new Cell(1, 2, true),
                    new Cell(2, 1, true),
                    new Cell(2, 2, true)
            );

            for (int row = 0; row < grid.getRows(); row++) {
                for (int col = 0; col < grid.getColumns(); col++) {
                    Cell cell = grid.getDataStructure()[row][col];
                    boolean shouldBeAlive = expectedAliveCells.contains(cell);
                    assertEquals(shouldBeAlive, cell.getIsAlive(),
                            "Cell at (" + row + ", " + col + ") state mismatch.");
                }
            }
        }
    }
}
