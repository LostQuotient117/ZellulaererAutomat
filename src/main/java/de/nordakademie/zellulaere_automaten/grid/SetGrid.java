package de.nordakademie.zellulaere_automaten.grid;

import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.INeighborStrategy;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.ICellStateCalculation;

import java.util.*;

/**
 * The {@code SetGrid} class represents a grid of cells in a cellular automaton.
 * It uses a {@code Set} to store active (alive) cells, allowing efficient calculation of the next generation.
 * The state of each cell is determined by a specified state calculation strategy,
 * and neighbors are determined by a specified neighborhood strategy.
 *
 * @author Daria Stolarczyk
 */
public class SetGrid implements IGrid{

    // region Klassenvariablen
    private int rows;
    private int columns;

    Set<Cell> activeCells;
    Set<Cell> activeCellsLastIteration;

    private final ICellStateCalculation stateCalculationStrategy;
    private final INeighborStrategy neighborStrategy;

    private final Map<Cell, List<Cell>> neighborCache;

    // endregion


    public SetGrid(int rows, int cols, Set<Cell> startConfiguration,
                   ICellStateCalculation stateCalculationStrategy, INeighborStrategy neighborStrategy)
    {
        this.rows = rows;
        this.columns = cols;

        activeCells = new HashSet<>(startConfiguration);
        activeCellsLastIteration = new HashSet<>();

        this.stateCalculationStrategy = stateCalculationStrategy;
        this.neighborStrategy = neighborStrategy;
        this.neighborCache = new HashMap<>();
    }

    // region Setters and Getters
    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public Object getDataStructure() {
        return activeCells;
    }

    @Override
    public Set<Cell> getPreviousGrid(){
        return activeCellsLastIteration;
    }

    @Override
    public Cell getCellByCoordinates(int x, int y) {
        Cell tempCell = new Cell(x, y, true);
        return activeCells.contains(tempCell) ? tempCell : new Cell(x, y, false);
    }
    /*@Override
    public Cell getCellByCoordinates(int x, int y) {
        Cell tempCell = new Cell(x, y, true);
        return activeCells.stream()
                .filter(cell ->  cell.equals(tempCell))
                .findFirst()
                .orElse(new Cell(x, y, false));
    }*/

    @Override
    public Cell getCellByCoordinatesFromPreviousGrid(int x, int y) {
        Cell tempCell = new Cell(x, y, true);
        return activeCellsLastIteration.contains(tempCell) ? tempCell : new Cell(x, y, false);
    }

    public void setActiveCells(Set<Cell> startConfig) {
        this.activeCells = new HashSet<>(startConfig);
    }
    // endregion

    // region basic logic
    @Override
    public boolean isStable() {
        return activeCells.equals(activeCellsLastIteration);
    }

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
    // endregion


    @Override
    public Object calculateNextGeneration() {
        neighborCache.clear();
        activeCellsLastIteration.clear();
        activeCellsLastIteration.addAll(activeCells);

        Set<Cell> nextGeneration = new HashSet<>();
        Set<Cell> cellsToCheck = new HashSet<>(activeCells);

        for (Cell cell : activeCells) {
            cellsToCheck.addAll(getNeighborsWithCache(cell));
        }

        for (Cell cell : cellsToCheck) {
            List<Cell> neighbors = getNeighborsWithCache(cell);

            if (cell.getIsAlive()) {
                if (stateCalculationStrategy.staysAlive(cell, neighbors)) {
                    nextGeneration.add(cell);
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



    /*@Override
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
    }*/

    private List<Cell> getNeighborsWithCache(Cell cell) {
        return neighborCache.computeIfAbsent(cell, c -> neighborStrategy.getNeighbors(c, this));
    }
}