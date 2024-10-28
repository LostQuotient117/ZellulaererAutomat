package de.nordakademie.zellulaere_automaten.grid;

import de.nordakademie.zellulaere_automaten.model.Cell;

import java.util.Set;

public class GridFactory {
    public void createGrid(String userInputTypeOfGrid, String userInputCountRows, String userInputCountColumns, Set<Cell> startConfig){
        int chosenGridMode = Integer.parseInt(userInputTypeOfGrid);
        int chosenRowCount = Integer.parseInt(userInputCountRows);
        int chosenColumnCount = Integer.parseInt(userInputCountColumns);

        GridType loggerTypes = GridType.getType(chosenGridMode);
       /* return switch (loggerTypes) {
            case ClassicGrid -> new ClassicGrid(chosenRowCount, chosenColumnCount);
            case HashMapGrid -> new ListGrid(chosenRowCount, chosenColumnCount, startConfig);
        };*/
    }
}
