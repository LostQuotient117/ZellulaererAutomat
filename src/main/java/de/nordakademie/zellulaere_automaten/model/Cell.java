package de.nordakademie.zellulaere_automaten.model;

import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.StateCalculation;

import java.util.List;
import java.util.Objects;


/**
 * The {@code Cell} class represents a cell in a cellular automaton.
 * It contains properties such as row, column, alive status, and a list of neighboring cells.
 * The class provides getter and setter methods to access and modify these properties.
 *
 * @author Lars Nicht
 */
public class Cell {
    private int row;
    private int column;
    private boolean isAlive;
    private List<Cell> neighborList;

    // private StateCalculation stateCalculation;

    /**
     * Constructs a new {@code Cell} with the specified properties.
     *
     * @param row     the row position of the cell
     * @param column  the column position of the cell
     * @param isAlive the alive status of the cell
     */
    public Cell(int row, int column, boolean isAlive) {
        this.row = row;
        this.column = column;
        this.isAlive = isAlive;
    }


    /**
     * Constructs a new {@code Cell} with the specified properties.
     *
     * @param row          the row position of the cell
     * @param column       the column position of the cell
     * @param isAlive      the alive status of the cell
     * @param neighborList the list of neighboring cells
     */
    public Cell(int row, int column, boolean isAlive, List<Cell> neighborList) {
        this.row = row;
        this.column = column;
        this.isAlive = isAlive;
        this.neighborList = neighborList;
    }

    // region Getter

    /**
     * Returns the row position of the cell.
     *
     * @return the row position of the cell
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column position of the cell.
     *
     * @return the column position of the cell
     */
    public int getColumn() {
        return column;
    }

    /**
     * Returns the isAlive status of the cell.
     *
     * @return {@code true} if the cell is alive, {@code false} otherwise
     */
    public boolean getIsAlive() {
        return isAlive;
    }

    /**
     * Returns the list of neighboring cells.
     *
     * @return the list of neighboring cells
     */
    public List<Cell> getNeighborList() {
        return neighborList;
    }
    // endregion

    /*
    public StateCalculation getStateCalculation() {
        return stateCalculation;
    }
     */

    // region Setter

    /**
     * Sets the row position of the cell.
     *
     * @param row the new row position of the cell
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Sets the column position of the cell.
     *
     * @param column the new column position of the cell
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Sets the isAlive status of the cell.
     *
     * @param alive the new alive status of the cell
     */
    public void setIsAlive(boolean alive) {
        isAlive = alive;
    }

    /**
     * Sets the list of neighboring cells.
     *
     * @param neighborList the new list of neighboring cells
     */
    public void setNeighborList(List<Cell> neighborList) {
        this.neighborList = neighborList;
    }
    // endregion

    /*
    public void setStateCalculation(StateCalculation stateCalculation) {
        this.stateCalculation = stateCalculation;
    }

     */

    // region Logic

    /**
     * Compares this cell with another cell.
     * They are considered equal if they have the same coordinates and alive status.
     *
     * @param otherCell the cell to compare with
     * @return {@code true} if the cells are equal, {@code false} otherwise
     */
    public boolean isEqualCell(Cell otherCell) {
        return this.column == otherCell.getColumn()
                && this.row == otherCell.getRow()
                && this.isAlive == otherCell.getIsAlive();
    }

    @Override
    public String toString() {
        return isAlive ? "1" : "0";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) //Checks whether the two variables refer to the same object in memory
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Cell otherCell = (Cell) obj;
        return row == otherCell.row && column == otherCell.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    // endregion
}
