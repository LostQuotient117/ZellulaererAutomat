package de.nordakademie.zellulaere_automaten.grid;

import de.nordakademie.zellulaere_automaten.model.Cell;
import de.nordakademie.zellulaere_automaten.strategy.neighbors.INeighborStrategy;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.ICellStateCalculation;

import java.util.Set;

/**
 * The {@code GridFactory} class is responsible for creating instances of different types of grids
 * ({@link ClassicGrid} or {@link SetGrid}) based on the provided grid type.
 * This factory utilizes the {@link GridType} enum to determine which grid implementation to instantiate.
 *
 * The factory method allows the creation of a grid with specified dimensions, an initial configuration of active cells,
 * a state calculation strategy, and a neighbor strategy, which provides flexibility in grid configuration and behavior.
 *
 * Key features:
 * - Supports multiple grid types: {@link ClassicGrid} and {@link SetGrid}.
 * - Configurable with custom neighbor and state calculation strategies.
 * - Designed to facilitate easy grid instantiation for cellular automata simulations.
 * @author Daria Stolarczyk
 */
public class GridFactory {
    public IGrid createGrid(int typeOfGrid, int countRows, int countColumns, Set<Cell> startConfig, ICellStateCalculation stateCalculationStrategy, INeighborStrategy neighborStrategy){
        GridType loggerTypes = GridType.getType(typeOfGrid);
        return switch (loggerTypes) {
            case ClassicGrid -> new ClassicGrid(countRows, countColumns, startConfig, stateCalculationStrategy, neighborStrategy);
            case SetGrid -> new SetGrid(countRows, countColumns, startConfig, stateCalculationStrategy, neighborStrategy);
        };
    }
}
