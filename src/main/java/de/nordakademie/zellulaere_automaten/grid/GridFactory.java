package de.nordakademie.zellulaere_automaten.grid;

import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.INeighborStrategy;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.ICellStateCalculation;

import java.util.Set;

public class GridFactory {
    public IGrid createGrid(int typeOfGrid, int countRows, int countColumns, Set<Cell> startConfig, ICellStateCalculation stateCalculationStrategy, INeighborStrategy neighborStrategy){
        GridType loggerTypes = GridType.getType(typeOfGrid);
        return switch (loggerTypes) {
            case ClassicGrid -> new ClassicGrid(countRows, countColumns, startConfig, stateCalculationStrategy, neighborStrategy);
            case HashMapGrid -> new SetGrid(countRows, countColumns, startConfig, stateCalculationStrategy, neighborStrategy);
        };
    }
}
