package de.nordakademie.zellulaere_automaten.grid;

import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.INeighborStrategy;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.ICellStateCalculation;

import java.util.ArrayList;

public class ClassicGrid implements IGrid<Cell[][]> {

    //region variables
    private int rows;
    private int columns;

    private Cell[][] grid;
    private Cell[][] previousGrid;

    private INeighborStrategy neighborCalculationStrategy;
    private ICellStateCalculation stateCalculationStrategy;
    //endregion

    //region Constructors
    public ClassicGrid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        this.grid = new Cell[rows][columns];
        this.previousGrid = new Cell[rows][columns];
    }

    /**
     * Constructs a new ClassicGrid with specified dimensions, neighbor calculation strategy, and state calculation strategy.
     * <p>
     * This constructor initializes the grid and previous grid with the specified number of rows and columns.
     * It also sets the neighbor calculation strategy and state calculation strategy.
     * Each cell in the grid and previous grid is initialized to a dead state.
     * </p>
     *
     * @param rows                          the number of rows in the grid
     * @param columns                       the number of columns in the grid
     * @param neighborCalculationStrategy   the strategy for calculating neighbors of a cell
     * @param ICellStateCalculationStrategy the strategy for calculating the state of a cell
     */
    public ClassicGrid(int rows, int columns, INeighborStrategy neighborCalculationStrategy, ICellStateCalculation ICellStateCalculationStrategy) {
        this.rows = rows;
        this.columns = columns;

        this.grid = new Cell[rows][columns];
        this.previousGrid = new Cell[rows][columns];

        this.neighborCalculationStrategy = neighborCalculationStrategy;
        this.stateCalculationStrategy = ICellStateCalculationStrategy;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                this.grid[row][col] = new Cell(row, col, false);
                this.previousGrid[row][col] = new Cell(row, col, false);
            }
        }
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

    public Cell[][] getPreviousGrid() {
        return previousGrid;
    }
    // endregion

    /**
     * Calculates the next generation of cells in the grid.
     * <p>
     * This method iterates through the grid data structure and recalculates the alive state of each cell
     * based on its neighbors and the state calculation strategy.
     * </p>
     * <p>
     * The current grid state is copied to the previous grid before calculation. For each cell, the method
     * retrieves its neighbors using the neighbor calculation strategy and determines its new alive state
     * using the state calculation strategy.
     * </p>
     *
     * @return the new grid state with updated cell states
     */
    @Override
    public Cell[][] calculateNextGeneration() {
        copyGrid(grid, previousGrid);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Cell cell = grid[row][col];
                ArrayList<Cell> neighbors = neighborCalculationStrategy.getNeighbors(cell, this);
                boolean newIsAlive;
                if (cell.getIsAlive()) {
                    newIsAlive = stateCalculationStrategy.staysAlive(cell, neighbors);
                } else {
                    newIsAlive = !stateCalculationStrategy.staysDead(cell, neighbors);
                }
                cell.setIsAlive(newIsAlive);
            }
        }
        return grid;
    }

    /**
     * Returns the cell located at the specified coordinates in the grid.
     *
     * @param x the row index of the cell
     * @param y the column index of the cell
     * @return the cell located at the specified (x, y) coordinates
     * @throws IndexOutOfBoundsException if the specified coordinates are out of bounds
     */
    @Override
    public Cell getCellByCoordinates(int x, int y) {
        if (x < 0 || x >= rows || y < 0 || y >= columns) {
            throw new IndexOutOfBoundsException("Coordinates out of bounds: (" + x + ", " + y + ")");
        }
        return grid[x][y];
    }

    /**
     * Returns a string representation of the grid, where each cell's state is represented
     * by its toString method. A newline is added after each row to separate the rows.
     *
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
     *
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
    // endregion
}


