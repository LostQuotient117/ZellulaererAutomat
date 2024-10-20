package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import de.nordakademie.zellulaere_automaten.logger.ILogger;
import de.nordakademie.zellulaere_automaten.model.Cell;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;

public abstract class Log implements ILogger {
    @Override
    public void log(Object gridInput) {

    }

    protected abstract void writeLog(String formattedGrid); //richtige Variable mit übergeben. Möglicherweise schon formatiert?

    public String formatArraytoString(Array[][] Cell){
        //here comes the magic

        String formattedString = "";
        return formattedString;
    }

    public String formatHashMaptoString(HashMap<Cell, List<Cell>> inputHash){
        //here comes the magic

        String formattedString = "";
        return formattedString;
    }
}
