package de.nordakademie.zellulaere_automaten.experiment.gameOfLife;

import de.nordakademie.zellulaere_automaten.experiment.BaseExperiment;
import de.nordakademie.zellulaere_automaten.experiment.IExperiment;
import de.nordakademie.zellulaere_automaten.grid.ClassicGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.Moore;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.GameOfLife;

import java.util.HashSet;
import java.util.Set;

/**
 * The {@code GameOfLifeExperiment2Structure1} class represents an experiment for the Game of Life with a specific structure.
 * It extends the {@link BaseExperiment} class and implements the {@link IExperiment} interface.
 */
public class GameOfLifeExperiment2Structure1 extends BaseExperiment implements IExperiment {

    /**
     * Constructs a new {@code GameOfLifeExperiment2Structure1} experiment.
     * Initializes the grid with a specific size and configuration.
     */
    public GameOfLifeExperiment2Structure1() {
        super(new ClassicGrid(100, 100, new HashSet<>(), new GameOfLife(), new Moore()), new HashSet<>(), "GameOfLifeExperiment2Structure1");
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