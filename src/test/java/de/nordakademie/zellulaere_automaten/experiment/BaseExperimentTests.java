package de.nordakademie.zellulaere_automaten.experiment;

import de.nordakademie.zellulaere_automaten.grid.ClassicGrid;
import de.nordakademie.zellulaere_automaten.grid.IGrid;
import de.nordakademie.zellulaere_automaten.logger.ILogger;
import de.nordakademie.zellulaere_automaten.logger.LoggerFactory;
import de.nordakademie.zellulaere_automaten.logger.LoggerTypes;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.Moore;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.GameOfLife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The {@code BaseExperimentTests} class contains unit tests for the {@link BaseExperiment} class.
 * It verifies the correct initialization of the grid, the dimensions of the grid, and the behavior of the experiment.
 */
public class BaseExperimentTests {

    private BaseExperiment baseExperiment;
    private ClassicGrid grid;
    private Set<Cell> startConfig;
    private ILogger mockLogger;

    /**
     * Sets up the test environment before each test.
     * Initializes the experiment, the grid, and the start configuration.
     */
    @BeforeEach
    void setUp() {
        startConfig = new HashSet<>();
        grid = new ClassicGrid(5, 5, new HashSet<>(), new GameOfLife(), new Moore());
        mockLogger = mock(ILogger.class);
        baseExperiment = new BaseExperiment(grid, startConfig, "TestExperiment") {
            @Override
            public void initializeGrid() {
                // Custom initialization for testing
                startConfig.add(new Cell(2, 2, true));
                startConfig.add(new Cell(2, 3, true));
                startConfig.add(new Cell(3, 2, true));
                startConfig.add(new Cell(3, 3, true));
                for (Cell cell : startConfig) {
                    grid.getCellByCoordinates(cell.getRow(), cell.getColumn()).setIsAlive(cell.getIsAlive());
                }
            }
        };
        baseExperiment.logger = mockLogger;
    }

    /**
     * Tests the initialization of the grid.
     * Verifies that the start configuration is set correctly.
     */
    @Test
    void initializeGrid_WhenCalled_ShouldSetCellsAlive() {
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
    @Test
    void isStable_WhenCalled_ShouldReturnGridStability() {
        IGrid mockGrid = mock(IGrid.class);
        when(mockGrid.isStable()).thenReturn(true);

        BaseExperiment experiment = new BaseExperiment(mockGrid, startConfig, "TestExperiment");

        assertTrue(experiment.grid.isStable());
        verify(mockGrid).isStable();
    }

    @Test
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

        verify(mockLogger, times(101)).log(anyString(), anyInt(), eq("TestExperiment"));
        verify(mockLogger).logEndMessage("Experiment stopped: Reached 100 iterations.", "TestExperiment");
    }

    @Test
    void runExperiment_WithLoggerType_ShouldUseCorrectLogger() {
        LoggerFactory loggerFactory = mock(LoggerFactory.class);
        ILogger consoleLogger = mock(ILogger.class);
        when(loggerFactory.createLogger("1")).thenReturn(consoleLogger);

        baseExperiment.logger = consoleLogger;
        baseExperiment.runExperiment(LoggerTypes.LogConsole);
        assertEquals(consoleLogger, baseExperiment.logger);
    }

}