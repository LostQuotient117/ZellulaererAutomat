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
 * The {@code GameOfLifeExperiment1Structure1} class represents an experiment for the Game of Life with a specific structure.
 * It extends the {@link BaseExperiment} class and implements the {@link IExperiment} interface.
 *
 * @author Lars Nicht
 */
public class GameOfLifeExperiment1Structure1 extends BaseExperiment implements IExperiment {

    /**
     * Constructs a new {@code GameOfLifeExperiment1Structure1} experiment.
     * Initializes the grid with a specific size and configuration.
     */
    public GameOfLifeExperiment1Structure1() {
        super(new ClassicGrid(40, 41, new HashSet<>(), new GameOfLife(), new Moore()),new HashSet<>(), "GameOfLifeExperiment1Structure1");
    }

    /**
     * Initializes the grid with a specific starting pattern.
     * The pattern is placed in the middle of the grid.
     */
    @Override
    public void initializeGrid() {
        int middleRow = grid.getRows() / 2;
        int middleColumn = grid.getColumns() / 2;

        //starting pattern
        int[][] pattern = {
                {0, 1, 1, 0, 1, 1, 0},
                {0, 1, 1, 0, 1, 1, 0},
                {0, 0, 1, 0, 1, 0, 0},
                {1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1},
                {1, 1, 0, 0, 0, 1, 1}
        };

        //placement in the middle
        for (int rowIndex = 0; rowIndex < pattern.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < pattern[rowIndex].length; columnIndex++) {
                if (pattern[rowIndex][columnIndex] == 1) {
                    startConfig.add(new Cell(middleRow - 3 + rowIndex, middleColumn - 3 + columnIndex, true));
                }
            }
        }

        // adding the cells to the start config
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