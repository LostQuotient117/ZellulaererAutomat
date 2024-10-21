package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

public class LogConsole extends Log {
    @Override
    protected void writeLog(String formattedGrid) {

    }
    /**
     *This function is responsible for printing the first line of a grid,
     *which indicates the current iteration
     * @param step int
     */
    public void stepWriterConsole(int step) {
        if (step < 0){
            throw new IllegalArgumentException("Step number must be a positive integer");
        }
        System.out.println("### (" + step + ")");
    }
}
