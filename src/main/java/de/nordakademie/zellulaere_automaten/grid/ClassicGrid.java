package de.nordakademie.zellulaere_automaten.grid;

import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.INeighborStrategy;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.ICellStateCalculation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * The {@code ClassicGrid} class represents a cellular automaton grid using a two-dimensional array of {@link Cell} objects.
 * It provides functionality to initialize the grid, calculate the next generation based on neighbor and state calculation strategies,
 * and check if the grid has reached a stable state.
 *
 * This implementation uses the {@link INeighborStrategy} to determine cell neighbors and {@link ICellStateCalculation} to
 * calculate the next state of each cell in the grid. The class supports different grid sizes and initial configurations.
 *
 * Key features:
 * - Initializing the grid with specified rows, columns, and an initial configuration of active cells.
 * - Calculating the next generation by applying the neighbor and state calculation strategies.
 * - Checking if the grid is stable by comparing the current and previous generations.
 * @author Daria Stolarczyk
 */
public class ClassicGrid implements IGrid<Cell[][]>{

    //region variables
    private final int rows;
    private final int columns;

    private Cell[][] grid;
    private Cell[][] previousGrid;

    private final INeighborStrategy neighborCalculationStrategy;
    private final ICellStateCalculation stateCalculationStrategy;
    //endregion

    //region Constructors
    public ClassicGrid(int rows, int columns, Set<Cell> startConfig, ICellStateCalculation ICellStateCalculationStrategy, INeighborStrategy neighborCalculationStrategy) {
        this.rows = rows;
        this.columns = columns;

        this.grid = new Cell[rows][columns];
        this.previousGrid = new Cell[rows][columns];

        this.neighborCalculationStrategy = neighborCalculationStrategy;
        this.stateCalculationStrategy = ICellStateCalculationStrategy;

        initialize(startConfig);
    }
    //endregion

    // region Getter & Setter

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public Cell[][] getDataStructure() {
        return grid;
    }

    @Override
    public Cell[][] getPreviousGrid() {
        return previousGrid;
    }
    // endregion

    @Override
    public Cell[][] calculateNextGeneration() {
        copyGrid(grid, previousGrid);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Cell cell = previousGrid[row][col];
                List<Cell> neighbors = neighborCalculationStrategy.getNeighbors(cell, this);

                boolean newIsAlive;
                if (cell.getIsAlive())
                    newIsAlive = stateCalculationStrategy.staysAlive(cell, neighbors);
                else
                    newIsAlive = !stateCalculationStrategy.staysDead(cell, neighbors);

                grid[row][col] = new Cell(row, col, newIsAlive);
            }
        }
        return grid;
    }

    @Override
    public Cell getCellByCoordinates(int x, int y) {
        return grid[x][y];
    }

    @Override
    public Cell getCellByCoordinatesFromPreviousGrid(int x, int y) {
        return previousGrid[x][y];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                stringBuilder.append(grid[row][col].toString());
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean isStable() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (!grid[row][col].equals(previousGrid[row][col])) {
                    return false;
                }
            }
        }
        return true;
    }

    // region helper functions
    protected void copyGrid(Cell[][] origin, Cell[][] copy) {
        for (int row = 0; row < origin.length; row++) {
            for (int col = 0; col < origin[row].length; col++) {
                copy[row][col] = new Cell(
                        origin[row][col].getRow(),
                        origin[row][col].getColumn(),
                        origin[row][col].getIsAlive()
                );
            }
        }
    }

    private void initialize(Set<Cell> startConfig) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                boolean isAlive = startConfig.contains(new Cell(row, col, true));
                grid[row][col] = new Cell(row, col, isAlive);
            }
        }
    }
    // endregion
}


