package de.nordakademie.zellulaere_automaten.experiment.gameOfLife;

import de.nordakademie.zellulaere_automaten.experiment.BaseExperiment;
import de.nordakademie.zellulaere_automaten.experiment.IExperiment;
import de.nordakademie.zellulaere_automaten.grid.ClassicGrid;
import de.nordakademie.zellulaere_automaten.grid.SetGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.Moore;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.GameOfLife;

import java.util.HashSet;
import java.util.Set;

public class TestGameOfLifeExperiment1Structure2 extends BaseExperiment implements IExperiment {

    public TestGameOfLifeExperiment1Structure2() {
        super(new SetGrid(16, 17, new HashSet<>(), new GameOfLife(), new Moore()), new HashSet<>(), "TestGameOfLifeExperiment1Structure2");
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
        ((SetGrid) this.grid).setActiveCells(startConfig);
    }

    // for TestGameOfLifeExperiment1Structure1Tests
    public Set<Cell> getStartConfig() {
        return startConfig;
    }

}
