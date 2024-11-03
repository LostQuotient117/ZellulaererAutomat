package de.nordakademie.zellulaere_automaten.model;

import java.util.Objects;


/**
 * The {@code Cell} class represents a cell in a cellular automaton.
 * It contains properties such as row, column, alive status, and a list of neighboring cells.
 * The class provides getter and setter methods to access and modify these properties.
 *
 * @author Lars Nicht
 * @author Daria Stolarczyk
 */
public class Cell {
    private int row;
    private int column;
    private boolean isAlive;

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
    // endregion

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
    // endregion

    // region Logic

    /**
     * Returns a string representation of the cell's state.
     * If the cell is alive, it returns "1"; otherwise, it returns "0".
     *
     * @return "1" if the cell is alive, otherwise "0"
     */
    @Override
    public String toString() {
        return isAlive ? "1" : "0";
    }

    /**
     * Compares this cell to the specified object.
     * The result is {@code true} if and only if the argument is not null,
     * is of the same class, and has the same row and column values.
     *
     * @param obj the object to compare this cell against
     * @return {@code true} if the given object represents a cell with the same row and column; {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) //Checks whether the two variables refer to the same object in memory
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Cell otherCell = (Cell) obj;
        return row == otherCell.row && column == otherCell.column && isAlive == otherCell.isAlive;
    }

    /**
     * Returns a hash code value for the cell based on its row and column.
     * Cells with the same row and column will have the same hash code.
     *
     * @return a hash code value for this cell
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    // endregion
}
