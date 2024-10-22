package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import java.io.FileWriter;
import java.io.IOException;

public class LogFile extends Log {
    @Override
    protected void writeLog(String formattedGrid, int iteration) {
        try {
            exportGridToFile(buildStringForFile(formattedGrid, iteration));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String buildStringForFile(String formattedGrid, int iteration){
        return "### (" + iteration + ")" + System.lineSeparator() +
                formattedGrid;
    }
    private void exportGridToFile(String buildedStringForFile) throws IOException {
        String filename = "src/main/java/de/nordakademie/zellulaere_automaten/logger/loggerOutput/Log.log";
        FileWriter fileWriter = new FileWriter(filename, true);
        fileWriter.write(buildedStringForFile);
        fileWriter.write(System.lineSeparator());
        fileWriter.close();
    }
}
