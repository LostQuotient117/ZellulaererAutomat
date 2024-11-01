package de.nordakademie.zellulaere_automaten.experiment.parity;

import de.nordakademie.zellulaere_automaten.experiment.BaseExperiment;
import de.nordakademie.zellulaere_automaten.experiment.IExperiment;
import de.nordakademie.zellulaere_automaten.grid.SetGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.Neumann;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.Parity;

import java.util.HashSet;
import java.util.Set;

/**
 * The {@code ParityExperiment1Structure2} class represents an experiment for the Parity automaton with a specific structure.
 * It extends the {@link BaseExperiment} class and implements the {@link IExperiment} interface.
 *
 * @author Lars Nicht
 */
public class ParityExperiment1Structure2 extends BaseExperiment implements IExperiment {

    /**
     * Constructs a new {@code ParityExperiment1Structure2} experiment.
     * Initializes the grid with a specific size and configuration.
     */
    public ParityExperiment1Structure2() {
        super(new SetGrid(400, 400, new HashSet<>(), new Parity(), new Neumann()), new HashSet<>(), "ParityExperiment1Structure2");
    }

    /**
     * Initializes the grid with a specific starting pattern.
     * The pattern is placed in the middle of the grid.
     */
    @Override
    public void initializeGrid() {
        int middleRow = grid.getRows() / 2;
        int middleColumn = grid.getColumns() / 2;
        startConfig.add(new Cell(middleRow, middleColumn, true));
        startConfig.add(new Cell(middleRow - 1, middleColumn, true));
        startConfig.add(new Cell(middleRow, middleColumn - 1, true));
        startConfig.add(new Cell(middleRow - 1, middleColumn - 1, true));

        ((SetGrid) this.grid).setActiveCells(startConfig);
    }

    /**
     * Returns the start configuration of cells for testing purposes.
     *
     * @return the start configuration of cells
     */
    public Set<Cell> getStartConfig() {
        return startConfig;
    }

}