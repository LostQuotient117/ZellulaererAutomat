package de.nordakademie.zellulaere_automaten.grid;

import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.INeighborStrategy;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.ICellStateCalculation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    /**
     * Calculates the next generation of cells by updating each cell's alive status
     * based on its neighbors and the defined state calculation strategy.
     * The current grid state is copied to `previousGrid` before any updates.
     * @return the updated grid with recalculated cell states for the next generation
     */
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

    /**
     * Returns the cell located at the specified coordinates in the grid.
     * @param x the row index of the cell
     * @param y the column index of the cell
     * @return the cell located at the specified (x, y) coordinates
     * @throws IndexOutOfBoundsException if the specified coordinates are out of bounds
     */
    @Override
    public Cell getCellByCoordinates(int x, int y) {
        return grid[x][y];
    }

    @Override
    public Cell getCellByCoordinatesFromPreviousGrid(int x, int y) {
        return previousGrid[x][y];
    }

    /**
     * Returns a string representation of the grid, where each cell's state is represented
     * by its toString method. A newline is added after each row to separate the rows.
     * @return a string representation of the current state of the grid
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                stringBuilder.append(grid[row][col].toString());
                stringBuilder.append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Checks if grid is in a stable constellation
     * @return true: when the grid has not changed
     */
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

    /**
     * Initializes the grid with new Cell instances for each position based on the given starting configuration.
     * For each cell in the grid, the method checks if the cell's position is contained in the start configuration.
     * If so, the cell is initialized as alive; otherwise, it is initialized as dead.
     *
     * @param startConfig a set of Cell objects representing the initial configuration of alive cells.
     *                    Each Cell in the set has its row and column position defined, and will be marked as alive
     *                    in the grid if found in this configuration.
     */
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


