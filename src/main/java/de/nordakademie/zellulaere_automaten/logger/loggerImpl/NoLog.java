package de.nordakademie.zellulaere_automaten.logger.loggerImpl;

/**
 * The {@link NoLog} class is a specialized implementation of the {@link Log} class.
 * This class overrides the logging methods to ensure that no logs are printed to the console.
 * Instead, it prints a message indicating that logging is disabled for the specified class.
 */
public class NoLog extends Log {
    private static String lastMessage = "";

    /**
     * Clears the last message stored in the {@code lastMessage} variable.
     * This method resets the {@code lastMessage} to an empty string, ensuring
     * that subsequent log messages are not suppressed due to a previous message.
     */
    public static void clearLastMessage() {
        lastMessage = "";
    }

    /**
     * Overrides the {@code writeLog} method to ensure that no logs are printed to the console.
     * Instead, it prints a message indicating that logging is disabled for the specified class.
     *
     * @param formattedGrid the formatted grid string
     * @param iteration the iteration count
     * @param className the name of the class
     */
    @Override
    protected void writeLog(String formattedGrid, int iteration, String className) {
        String message = "No Log will be printed for " + className + ". Please choose another Loggertype to see the result.";
        if (!message.equals(lastMessage)) {
            System.out.println(message);
            lastMessage = message;
        }
    }

    /**
     * Overrides the {@code writeLog} method to ensure that no logs are printed to the console.
     * Instead, it prints a message indicating that logging is disabled for the specified class.
     *
     * @param endMessage the end message
     * @param className the name of the class
     */
    @Override
    protected void writeLog(String endMessage, String className) {
        String message = "No Log will be printed for " + className + ". Please choose another Loggertype to see the result.";
        if (!message.equals(lastMessage)) {
            System.out.println(message);
            lastMessage = message;
        }
    }
}
