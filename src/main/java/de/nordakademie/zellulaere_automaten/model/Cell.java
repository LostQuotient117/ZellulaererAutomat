package de.nordakademie.zellulaere_automaten.model;

import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.StateCalculation;

import java.util.List;


public class Cell {
    private int row;
    private int column;
    private boolean isAlive;
    private List<Cell> neighborList;
    // private StateCalculation stateCalculation;

    public Cell(int row, int column, boolean isAlive, List<Cell> neighborList) {
        this.row = row;
        this.column = column;
        this.isAlive = isAlive;
        this.neighborList = neighborList;
    }


    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public List<Cell> getNeighborList() {
        return neighborList;
    }

    /*
    public StateCalculation getStateCalculation() {
        return stateCalculation;
    }
     */

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setIsAlive(boolean alive) {
        isAlive = alive;
    }

    public void setNeighborList(List<Cell> neighborList) {
        this.neighborList = neighborList;
    }

    /*
    public void setStateCalculation(StateCalculation stateCalculation) {
        this.stateCalculation = stateCalculation;
    }

     */
}
