package de.nordakademie.zellulaere_automaten.grid;

import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.INeighborStrategy;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.StateCalculation;

public class ClassicGrid implements IGrid<Cell[][]>{

    //region variables
    private int rows;
    private int columns;

    private Cell[][] grid;
    private Cell[][] previousGrid;

    private INeighborStrategy neighborCalculationStrategy;
    private StateCalculation stateCalculationStrategy;
    //endregion

    //region Constructors
    public ClassicGrid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        this.grid = new Cell[rows][columns];
        this.previousGrid = new Cell[rows][columns];
    }
    public ClassicGrid(int rows, int columns, INeighborStrategy neighborCalculationStrategy, StateCalculation stateCalculationStrategy) {
        this.rows = rows;
        this.columns = columns;

        this.grid = new Cell[rows][columns];
        this.previousGrid = new Cell[rows][columns];

        this.neighborCalculationStrategy = neighborCalculationStrategy;
        this.stateCalculationStrategy = stateCalculationStrategy;
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
    public Cell[][] getGrid() {
        return grid;
    }

    public Cell[][] getPreviousGrid() {
        return previousGrid;
    }
    // endregion

    /**
     * This method goes through the grid data-structure and recalculates the alive value of its cells.
     * @return new grid state with calculated cell states
     */
    @Override
    public Cell[][] calculateNextGeneration() {
        copyGrid(grid, previousGrid);
        /*for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Cell cell = grid[row][col];
                ArrayList<Cell> neighbors = neighborCalculationStrategy.getNeighbors(cell);
                boolean newIsAlive = stateCalculationStrategy.calculateAlive(cell, neighbors);
                cell.setIsAlive(newIsAlive);
            }
        }*/
        return null;
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
    // endregion
}


