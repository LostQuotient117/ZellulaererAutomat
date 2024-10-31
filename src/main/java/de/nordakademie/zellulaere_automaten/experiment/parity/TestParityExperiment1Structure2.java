package de.nordakademie.zellulaere_automaten.experiment.parity;

import de.nordakademie.zellulaere_automaten.experiment.BaseExperiment;
import de.nordakademie.zellulaere_automaten.experiment.IExperiment;
import de.nordakademie.zellulaere_automaten.grid.ClassicGrid;
import de.nordakademie.zellulaere_automaten.grid.SetGrid;
import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.Neumann;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.Parity;

import java.util.HashSet;
import java.util.Set;

public class TestParityExperiment1Structure2 extends BaseExperiment implements IExperiment {

    public TestParityExperiment1Structure2() {
        super(new ClassicGrid(20, 20, new HashSet<>(), new Parity(), new Neumann()), new HashSet<>(), "TestParityExperiment1Structure2");
    }

    @Override
    public void initializeGrid() {
        int middleRow = grid.getRows() / 2;
        int middleColumn = grid.getColumns() / 2;
        startConfig.add(new Cell(middleRow, middleColumn, true));
        startConfig.add(new Cell(middleRow - 1, middleColumn, true));
        startConfig.add(new Cell(middleRow, middleColumn - 1, true));
        startConfig.add(new Cell(middleRow - 1, middleColumn - 1, true));

        // Update the grid with the new startConfig
        ((SetGrid) this.grid).setActiveCells(startConfig);
    }


    public Set<Cell> getStartConfig() {
        return startConfig;
    }

}
