package de.nordakademie.zellulaere_automaten.grid;

import de.nordakademie.zellulaere_automaten.experiment.Tuple;
import de.nordakademie.zellulaere_automaten.model.Cell;

import java.util.ArrayList;

public class ClassicGrid implements IGrid{
    private int rows;
    private int columns;

    private Cell[][] grid;
    private ArrayList<Cell> previousGenActiveCells;
    private ArrayList<Cell> activeCells;
    private ArrayList<Cell> previousGenDeadCells;
    private ArrayList<Cell> deadCells;

    public ClassicGrid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new Cell[rows][columns];
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
    // endregion

    /**
     * This method goes through the grid data-structure and recalculates the alive value of its cells.
     *
     * @return new grid state with calculated cell states
     */
    @Override
    public IGrid calculateNextGeneration() {
        return null;
    }

    /**
     * Checks if grid is in a stable constellation
     *
     * @return true: when the grid has not changed
     */
    @Override
    public boolean isStable() {
        return false;
    }

    // region helper functions
    private boolean isEqualCell(Cell first, Cell second) {
        return first.getColumn() == second.getColumn()
                && first.getRow() == second.getRow()
                && first.getIsAlive() == second.getIsAlive();
    }

    private boolean sameAmountOfCells(ArrayList<Cell> current, ArrayList<Cell> previous){
        return current.size() == previous.size();
    }

    // Currying
    private boolean sameAmountOfActiveCells(){
        return sameAmountOfCells(this.activeCells, this.previousGenActiveCells);
    }
    private boolean sameAmountOfDeadCells(){
        return sameAmountOfCells(this.deadCells, this.previousGenDeadCells);
    }


    private boolean checkForCellEqualityInList(ArrayList<Cell> current, ArrayList<Cell> previous){
        return current.stream()
                .allMatch(currentCell -> previous.stream().anyMatch(lastGenCell -> isEqualCell(currentCell, lastGenCell)));
    }

    private boolean checkForCellEqualityInActiveList(){
        return checkForCellEqualityInList(this.activeCells, this.previousGenActiveCells);
    }
    private boolean checkForCellEqualityInDeadList(){
        return checkForCellEqualityInList(this.deadCells, this.previousGenDeadCells);
    }
    // endregion
}
