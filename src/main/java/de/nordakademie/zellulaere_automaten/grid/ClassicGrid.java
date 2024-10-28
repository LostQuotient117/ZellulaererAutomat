package de.nordakademie.zellulaere_automaten.grid;

import de.nordakademie.zellulaere_automaten.experiment.Tuple;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.simulationMode.SimulationMode;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.StateCalculation;

import java.util.ArrayList;

public class ClassicGrid implements IGrid{
    private int rows;
    private int columns;

    private Cell[][] grid;
    private Cell[][] previousGrid;

    private SimulationMode neighborCalculationStrategy;
    private StateCalculation stateCalculationStrategy;

    public ClassicGrid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        this.grid = new Cell[rows][columns];
        this.previousGrid = new Cell[rows][columns];
    }
    public ClassicGrid(int rows, int columns, SimulationMode neighborCalculationStrategy, StateCalculation stateCalculationStrategy) {
        this.rows = rows;
        this.columns = columns;

        this.grid = new Cell[rows][columns];
        this.previousGrid = new Cell[rows][columns];

        this.neighborCalculationStrategy = neighborCalculationStrategy;
        this.stateCalculationStrategy = stateCalculationStrategy;
    }

    // region Getter & Setter

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Cell[][] getGrid() {
        return grid;
    }
    public Cell[][] getPreviousGrid() {
        return previousGrid;
    }
    // endregion

    /**
     * This method goes through the grid data-structure and recalculates the alive value of its cells.
     *
     * @return new grid state with calculated cell states
     */
    @Override
    public IGrid calculateNextGeneration() {
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


