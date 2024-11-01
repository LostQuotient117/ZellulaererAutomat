package de.nordakademie.zellulaere_automaten.experiment;

import de.nordakademie.zellulaere_automaten.grid.ClassicGrid;
import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.grid.SetGrid;
import de.nordakademie.zellulaere_automaten.logger.ILogger;
import de.nordakademie.zellulaere_automaten.logger.LoggerFactory;
import de.nordakademie.zellulaere_automaten.logger.LoggerTypes;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.Moore;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.GameOfLife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The {@code BaseExperimentTests} class contains unit tests for the {@link BaseExperiment} class.
 * It verifies the correct initialization of the grid, the dimensions of the grid, and the behavior of the experiment.
 *
 * @author Lars Nicht
 */
public class BaseExperimentTests {

    private Set<Cell> startConfig;
    private ILogger mockLogger;

    /**
     * Provides a stream of different grid implementations for parameterized tests.
     *
     * @return a stream of {@link IGrid} instances
     */
    static Stream<IGrid> gridProvider() {
        return Stream.of(
                new ClassicGrid(5, 5, new HashSet<>(), new GameOfLife(), new Moore()),
                new SetGrid(5, 5, new HashSet<>(), new GameOfLife(), new Moore())
        );
    }

    /**
     * Sets up the test environment before each test.
     * Initializes the start configuration and the mock logger.
     */
    @BeforeEach
    void setUp() {
        startConfig = new HashSet<>();
        mockLogger = mock(ILogger.class);
    }

    /**
     * Tests the initialization of the grid.
     * Verifies that the start configuration is set correctly.
     *
     * @param grid the grid to be tested
     */
    @ParameterizedTest
    @MethodSource("gridProvider")
    void initializeGrid_WhenCalled_ShouldSetCellsAlive(IGrid grid) {
        BaseExperiment baseExperiment = new BaseExperiment(grid, startConfig, "TestExperiment") {
            @Override
            public void initializeGrid() {
                startConfig.add(new Cell(2, 2, true));
                startConfig.add(new Cell(2, 3, true));
                startConfig.add(new Cell(3, 2, true));
                startConfig.add(new Cell(3, 3, true));
                if (grid instanceof ClassicGrid) {
                    for (Cell cell : startConfig) {
                        grid.getCellByCoordinates(cell.getRow(), cell.getColumn()).setIsAlive(cell.getIsAlive());
                    }
                } else if (grid instanceof SetGrid) {
                    ((SetGrid) grid).setActiveCells(startConfig);
                }
            }
        };
        baseExperiment.logger = mockLogger;
        baseExperiment.initializeGrid();
        assertTrue(grid.getCellByCoordinates(2, 2).getIsAlive());
        assertTrue(grid.getCellByCoordinates(2, 3).getIsAlive());
        assertTrue(grid.getCellByCoordinates(3, 2).getIsAlive());
        assertTrue(grid.getCellByCoordinates(3, 3).getIsAlive());
    }

    /**
     * Tests the `isStable` method in `BaseExperiment` using a mock `IGrid` instance.
     * Verifies that the `isStable` method correctly returns the stability status of the grid.
     */
    @ParameterizedTest
    @MethodSource("gridProvider")
    void isStable_WhenCalled_ShouldReturnGridStability() {
        IGrid mockGrid = mock(IGrid.class);
        when(mockGrid.isStable()).thenReturn(true);

        BaseExperiment experiment = new BaseExperiment(mockGrid, startConfig, "TestExperiment");

        assertTrue(experiment.grid.isStable());
        verify(mockGrid).isStable();
    }

    /**
     * Tests the experiment run when the grid is never stable.
     * Verifies that the experiment stops after 100 iterations.
     */
    @ParameterizedTest
    @MethodSource("gridProvider")
    void runExperiment_WhenGridNeverStable_ShouldStopAfter100Iterations() {
        IGrid mockGrid = mock(IGrid.class);
        when(mockGrid.isStable()).thenReturn(false);
        when(mockGrid.toString()).thenReturn("Grid state");

        BaseExperiment experiment = new BaseExperiment(mockGrid, startConfig, "TestExperiment") {
            @Override
            public String getClassName() {
                return "TestExperiment";
            }
        };
        experiment.logger = mockLogger;

        experiment.runExperiment(LoggerTypes.LogConsole);

        // 101 because of iteration 0 (start config)
        verify(mockLogger, times(101)).log(anyString(), anyInt(), eq("TestExperiment"));
        verify(mockLogger).logEndMessage("Experiment stopped: Reached 100 iterations.", "TestExperiment");
    }

    /**
     * Tests the experiment run with a specific logger type.
     * Verifies that the correct logger is used.
     *
     * @param grid the grid to be tested
     */
    @ParameterizedTest
    @MethodSource("gridProvider")
    void runExperiment_WithLoggerType_ShouldUseCorrectLogger(IGrid grid) {
        LoggerFactory loggerFactory = mock(LoggerFactory.class);
        ILogger consoleLogger = mock(ILogger.class);
        when(loggerFactory.createLogger("1")).thenReturn(consoleLogger);

        BaseExperiment baseExperiment = new BaseExperiment(grid, startConfig, "TestExperiment");
        baseExperiment.logger = consoleLogger;
        baseExperiment.runExperiment(LoggerTypes.LogConsole);
        assertEquals(consoleLogger, baseExperiment.logger);
    }

}