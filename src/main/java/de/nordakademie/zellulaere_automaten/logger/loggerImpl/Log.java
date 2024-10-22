package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import de.nordakademie.zellulaere_automaten.logger.ILogger;
import de.nordakademie.zellulaere_automaten.model.Cell;

public abstract class Log implements ILogger {
    @Override
    public void log(Object gridInput) {
    //hier eine Schleife um jede Zeile des Grids zu drucken. Vlt zeilenweise format---toString ausführen und das Ergebniss in eine eigene procedure geben die dann druckt
    }

    protected abstract void writeLog(String formattedGrid); //richtige Variable mit übergeben. Möglicherweise schon formatiert?

    public boolean inputIsArray(Object input) {
        return (input instanceof Cell[][]);
    }

    //public String formatArraytoString(Array[][] Cell){
        //here comes the magic

    //    String formattedString = "";
    //    return formattedString;
    //}

    //public String formatHashMaptoString(HashMap<Cell, List<Cell>> inputHash){
        //here comes the magic

    //    String formattedString = "";
    //    return formattedString;
    //}
}
