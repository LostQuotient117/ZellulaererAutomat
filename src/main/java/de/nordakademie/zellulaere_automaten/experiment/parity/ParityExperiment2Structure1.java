package de.nordakademie.zellulaere_automaten.experiment.parity;

import de.nordakademie.zellulaere_automaten.experiment.BaseExperiment;
import de.nordakademie.zellulaere_automaten.experiment.IExperiment;
import de.nordakademie.zellulaere_automaten.grid.ClassicGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.Neumann;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.Parity;

import java.util.HashSet;
import java.util.Set;

/**
 * The {@code ParityExperiment2Structure1} class represents an experiment for the Parity automaton with a specific structure.
 * It extends the {@link BaseExperiment} class and implements the {@link IExperiment} interface.
 */
public class ParityExperiment2Structure1 extends BaseExperiment implements IExperiment {

    /**
     * Constructs a new {@code ParityExperiment2Structure1} experiment.
     * Initializes the grid with a specific size and configuration.
     */
    public ParityExperiment2Structure1() {
        super(new ClassicGrid(100, 100, new HashSet<>(), new Parity(), new Neumann()), new HashSet<>(), "ParityExperiment2Structure1");
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

        for (Cell cell : startConfig) {
            grid.getCellByCoordinates(cell.getRow(), cell.getColumn()).setIsAlive(cell.getIsAlive());
        }
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