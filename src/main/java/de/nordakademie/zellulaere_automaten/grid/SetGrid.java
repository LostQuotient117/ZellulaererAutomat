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

    /**
     * TODO: Zelle Override
     * Checks if grid is in a stable constellation
     *
     * @return true: when the grid has not changed
     */
    @Override
    public boolean isStable() {
        return false;
    }
}