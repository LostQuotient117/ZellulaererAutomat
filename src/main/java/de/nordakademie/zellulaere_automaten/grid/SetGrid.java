package de.nordakademie.zellulaere_automaten.grid;

import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.INeighborStrategy;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.ICellStateCalculation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The {@code SetGrid} class represents a grid of cells in a cellular automaton.
 * It uses a {@code Set} to store active (alive) cells, allowing efficient calculation of the next generation.
 * The state of each cell is determined by a specified state calculation strategy,
 * and neighbors are determined by a specified neighborhood strategy.
 */
public class SetGrid implements IGrid{
    private int rows;
    private int columns;

    /** Set of currently active (alive) cells in the grid. */
    Set<Cell> activeCells;
    /** Set of active cells from the previous generation, used to determine grid stability. */
    Set<Cell> activeCellsLastIteration;
    /** Strategy for calculating the next state of a cell based on its neighbors. */
    private final ICellStateCalculation stateCalculationStrategy;
    /** Strategy for determining the neighbors of a cell. */
    private final INeighborStrategy neighborStrategy;

    /**
     * Constructs a new SetGrid with specified dimensions, initial active cells,
     * a state calculation strategy, and a neighborhood strategy.
     * @param rows                  the number of rows in the grid
     * @param cols                  the number of columns in the grid
     * @param startConfiguration    the initial configuration of active cells
     * @param stateCalculationStrategy the strategy for calculating the state of cells
     * @param neighborStrategy      the strategy for determining neighbors of a cell
     */
    public SetGrid(int rows, int cols, Set<Cell> startConfiguration,
                   ICellStateCalculation stateCalculationStrategy, INeighborStrategy neighborStrategy)
    {
        this.rows = rows;
        this.columns = cols;

        activeCells = new HashSet<>(startConfiguration);
        activeCellsLastIteration = new HashSet<>();

        this.stateCalculationStrategy = stateCalculationStrategy;
        this.neighborStrategy = neighborStrategy;
    }

    /**
     * Sets the active cells in the grid to the specified start configuration.
     *
     * @param startConfig the set of cells to be set as active in the grid
     */
    public void setActiveCells(Set<Cell> startConfig) {
        this.activeCells = new HashSet<>(startConfig);
    }

    /**
     * Returns the number of rows in the grid.
     * @return the number of rows in the grid
     */
    @Override
    public int getRows() {
        return rows;
    }

    /**
     * Returns the number of columns in the grid.
     * @return the number of columns in the grid
     */
    @Override
    public int getColumns() {
        return columns;
    }

    /**
     * Returns the underlying data structure representing active cells in the grid.
     * @return the set of active cells
     */
    @Override
    public Object getDataStructure() {
        return activeCells;
    }

    @Override
    public Set<Cell> getPreviousGrid(){
        return activeCellsLastIteration;
    }

    /**
     * Calculates the next generation of cells in the grid based on the state calculation and neighbor strategies.
     * Updates the set of active cells to reflect the new generation.
     * @return the set of active cells in the next generation
     */
    @Override
    public Object calculateNextGeneration() {
        //Setup
        activeCellsLastIteration.clear();
        activeCellsLastIteration.addAll(activeCells);
        Set<Cell> nextGeneration = new HashSet<>();
        Set<Cell> cellsToCheck = new HashSet<>(activeCells);

        for (Cell cell : activeCells) {
            List<Cell> neighbors = neighborStrategy.getNeighbors(cell, this);
            cellsToCheck.addAll(neighbors);
        }

        for (Cell cell : cellsToCheck) {
            List<Cell> neighbors = neighborStrategy.getNeighbors(cell, this);

            if (cell.getIsAlive()) {
                if (stateCalculationStrategy.staysAlive(cell, neighbors)) {
                    nextGeneration.add(new Cell(cell.getRow(), cell.getColumn(), true));
                }
            } else {
                if (!stateCalculationStrategy.staysDead(cell, neighbors)) {
                    nextGeneration.add(new Cell(cell.getRow(), cell.getColumn(), true));
                }
            }
        }
        activeCells = nextGeneration;
        return activeCells;
    }

    /**
     * Returns the cell located at the specified coordinates in the grid.
     * If the cell is not active, returns a dead cell at the specified location.
     * @param x the row index of the cell
     * @param y the column index of the cell
     * @return the cell located at the specified (x, y) coordinates
     * @throws IndexOutOfBoundsException if the specified coordinates are out of bounds
     */
    @Override
    public Cell getCellByCoordinates(int x, int y) {
        Cell tempCell = new Cell(x, y, true);
        return activeCells.stream()
                .filter(cell ->  cell.equals(tempCell))
                .findFirst()
                .orElse(new Cell(x, y, false));
    }

    @Override
    public Cell getCellByCoordinatesFromPreviousGrid(int x, int y) {
        Cell tempCell = new Cell(x, y, true);
        return activeCellsLastIteration.stream()
                .filter(cell ->  cell.equals(tempCell))
                .findFirst()
                .orElse(new Cell(x, y, false));
    }

    /**
     * Checks if the grid has reached a stable state by comparing the current set of active cells
     * with the set from the last generation.
     * @return true if the grid is stable (no change in active cells), false otherwise
     */
    @Override
    public boolean isStable() {
        return activeCells.equals(activeCellsLastIteration);
    }

    /**
     * Returns a string representation of the grid. Each cell is represented by "1" if it is active (alive)
     * and "0" if it is inactive (dead). Each row is separated by a newline.
     * @return a string representation of the current state of the grid
     */
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Cell tempCell = new Cell(row, col, true);
                if (activeCells.contains(tempCell))
                    stringBuilder.append("1");
                else
                    stringBuilder.append("0");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}