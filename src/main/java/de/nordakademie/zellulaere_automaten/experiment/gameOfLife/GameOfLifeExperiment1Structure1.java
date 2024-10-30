package de.nordakademie.zellulaere_automaten.experiment.gameOfLife;

import de.nordakademie.zellulaere_automaten.experiment.BaseExperiment;
import de.nordakademie.zellulaere_automaten.experiment.IExperiment;
import de.nordakademie.zellulaere_automaten.grid.ClassicGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.Moore;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.GameOfLife;

import java.util.HashSet;
import java.util.Set;

public class GameOfLifeExperiment1Structure1 extends BaseExperiment implements IExperiment {

    //defintion of what the grid should use as parameters
    public GameOfLifeExperiment1Structure1() {
        super(new ClassicGrid(40, 41, new HashSet<>(), new GameOfLife(), new Moore()),new HashSet<>(), "GameOfLifeExperiment1Structure1");
    }

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

    // for GameOfLifeExperiment1Structure1Tests
    public Set<Cell> getStartConfig() {
        return startConfig;
    }

}