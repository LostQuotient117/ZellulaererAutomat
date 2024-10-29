package de.nordakademie.zellulaere_automaten.experiment.gameOfLife;

import de.nordakademie.zellulaere_automaten.experiment.BaseExperiment;
import de.nordakademie.zellulaere_automaten.experiment.IExperiment;
import de.nordakademie.zellulaere_automaten.grid.ClassicGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.Moore;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.GameOfLife;

import java.util.HashSet;
import java.util.Set;

public class GameOfLifeExperiment2Structure1 extends BaseExperiment implements IExperiment {

    public GameOfLifeExperiment2Structure1() {
        super(new ClassicGrid(100, 100, new Moore(), new GameOfLife()), new HashSet<>(), "GameOfLifeExperiment2Structure1");
    }

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

    // for GameOfLifeExperiment2Structure1Tests
    public Set<Cell> getStartConfig() {
        return startConfig;
    }
}