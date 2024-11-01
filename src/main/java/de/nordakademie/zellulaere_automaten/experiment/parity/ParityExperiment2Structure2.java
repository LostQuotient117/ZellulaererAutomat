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
 * The {@code ParityExperiment2Structure2} class represents an experiment for the Parity automaton with a specific structure.
 * It extends the {@link BaseExperiment} class and implements the {@link IExperiment} interface.
 *
 * @author Lars Nicht
 */
public class ParityExperiment2Structure2 extends BaseExperiment implements IExperiment {

    /**
     * Constructs a new {@code ParityExperiment2Structure2} experiment.
     * Initializes the grid with a specific size and configuration.
     */
    public ParityExperiment2Structure2() {
        super(new SetGrid(100, 100, new HashSet<>(), new Parity(), new Neumann()), new HashSet<>(), "ParityExperiment2Structure2");
    }

    /**
     * Initializes the grid with a specific starting pattern.
     * The pattern alternates cells between alive and dead.
     */
    @Override
    public void initializeGrid() {
        for (int rowIndex = 0; rowIndex < grid.getRows(); rowIndex++) {
            for (int columnIndex = 0; columnIndex < grid.getColumns(); columnIndex++) {
                boolean isAlive = (rowIndex % 2 == 0) ? (columnIndex % 2 == 1) : (columnIndex % 2 == 0);
                startConfig.add(new Cell(rowIndex, columnIndex, isAlive));
            }
        }

        // Update the grid with the new startConfig
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