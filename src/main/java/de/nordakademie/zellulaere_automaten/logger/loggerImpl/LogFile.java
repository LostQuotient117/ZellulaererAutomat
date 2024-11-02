package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

import de.nordakademie.zellulaere_automaten.InputHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * The {@link LogFile} class extends the {@link Log} class to provide
 * functionality for writing log data to a file.
 */
public class LogFile extends Log {

    /**
     * Writes the formatted grid and iteration number to a log file.
     * This method formats the log data and writes it to a file. If an
     * {@code IOException} occurs during the file writing process, it is
     * caught and the stack trace is printed.
     *
     * @param formattedGrid the formatted grid string to be logged
     * @param iteration the iteration number to be included in the log
     */
    @Override
    protected void writeLog(String formattedGrid, int iteration, String className) {
        try {
            exportGridToFile(buildStringForFile(formattedGrid, iteration), className);
        } catch (IOException e) {
            System.out.println("An error occurred while writing the log data to the log file. This should not happen. Throwback to main method.");
            InputHandler inputHandler = new InputHandler();
            inputHandler.getUserInputs();
        }
    }
    /**
 * Writes the end message to a log file.
 * This method writes the provided end message to the log file. If an
 * {@code IOException} occurs during the file writing process, it is
 * caught and the stack trace is printed.
 *
 * @param endMessage the end message to be logged
 */
@Override
protected void writeLog(String endMessage, String className) {
    try {
        exportGridToFile(endMessage, className);
    } catch (IOException e) {
        System.out.println("An error occurred while writing the end message to the log file. This should not happen. Throwback to main method.");
        InputHandler inputHandler = new InputHandler();
        inputHandler.getUserInputs();
    }
}
    /**
     * Builds a string for logging to a file.
     * This method constructs a string that includes the iteration number
     * and the formatted grid, separated by a line separator.
     *
     * @param formattedGrid the formatted grid string
     * @param iteration the iteration number
     * @return the constructed string for logging
     */
    private String buildStringForFile(String formattedGrid, int iteration){
        return "### (" + iteration + ")" + System.lineSeparator() +
                formattedGrid;
    }
    /**
     * Exports the constructed log string to a file.
     * This method writes the provided string to a log file. If an
     * {@code IOException} occurs during the file writing process, it is
     * propagated to the caller.
     *
     * @param buildedStringForFile the string to be written to the file
     * @throws IOException if an I/O error occurs
     */
    private void exportGridToFile(String buildedStringForFile, String className) throws IOException {
        String projectRoot = Paths.get("").toAbsolutePath().toString();
        String downloadPath = Paths.get(projectRoot, (className +".log")).toString();
        FileWriter fileWriter = new FileWriter(downloadPath, true);
        fileWriter.write(buildedStringForFile);
        fileWriter.write(System.lineSeparator());
        fileWriter.close();
    }
}
