package de.nordakademie.zellulaere_automaten.grid;

import de.nordakademie.zellulaere_automaten.experiment.Tuple;
import de.nordakademie.zellulaere_automaten.model.Cell;

import java.util.ArrayList;
import java.util.Set;

public class SetGrid implements IGrid{
    private int rows;
    private int columns;
    Set<Cell> activeCells;
    Set<Cell> activeCellsLastIteration;

    public SetGrid(int rows, int cols, Set<Cell> startConfiguration)
    {
        this.rows = rows;
        this.columns = cols;
        activeCells = startConfiguration;
    }

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

    /**
     * This method goes through the grid data-structure and recalculates the alive value of its cells.
     * @return new grid state with calculated cell states
     */
    @Override
    public Object calculateNextGeneration() {
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
        Cell tempCell = new Cell(x, y, true);
        return activeCells.stream()
                .filter(cell ->  cell.equals(tempCell))
                .findFirst()
                .orElse(new Cell(x, y, false));
    }

    /**
     * Checks if grid is in a stable constellation
     * @return true: when the set has not changed
     */
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
                    stringBuilder.append("1 ");
                else
                    stringBuilder.append("0 ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}