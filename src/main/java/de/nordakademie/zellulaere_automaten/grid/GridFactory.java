package de.nordakademie.zellulaere_automaten.grid;

public class GridFactory {
    public IGrid createGrid(String userInput){
        int chosenGridMode = Integer.parseInt(userInput);
        GridType loggerTypes = GridType.getType(chosenGridMode);
        return switch (loggerTypes) {
            case ClassicGrid -> new ClassicGrid();
            case HashMapGrid -> new HashMapGrid();
        };
    }
}
