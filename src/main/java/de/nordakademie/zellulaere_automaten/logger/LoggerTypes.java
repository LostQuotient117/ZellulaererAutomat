package de.nordakademie.zellulaere_automaten.logger;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * An enumeration representing different types of loggers.
 * Each enum constant corresponds to a specific logger type and provides a
 * way to create instances of the {@link ILogger} interface.
 *
 * @author Jannick.Gottschalk
 * @author Daria Stolarczyk
 */
public enum LoggerTypes {

    NoLog(0),

    LogFile(1),

    LogConsole(2),

    LogConsoleAndFile(3);

    private final int value;

    /**
     * Constructor for Enumtypes for the Logger with a corresponding int value
     * @param value: the corresponding int value to the enum
     */
    LoggerTypes(int value) {
        this.value = value;
    }

    /**
     * Getter returns the value of the enum
     * @return the corresponding int for enum.
     */
    public int getValue() {
        return value;
    }

    /**
     *This method takes a value and finds the corresponding enum type for it.
     * Used for user input, when configuring the program.
     * @param value: chosen logger-mode.
     * @return loggerType: Chosen type for the logger.
     */
    public static LoggerTypes getType(int value) {
        return Arrays.stream(LoggerTypes.values())
                                        .filter(entry ->entry.getValue() == value)
                                        .findFirst()
                                        .orElseThrow(() ->
                                                new EnumConstantNotPresentException(LoggerTypes.class, "This type of logger does not exist"));
    }

    /**
     * Returns a string representation of the available logger types for user input.
     * Each logger type is represented by its corresponding integer value and name.
     *
     * @return a string listing all available logger types with their integer values and names.
     */
    public static String getAvailableLoggerTypesForUserInput() {
        return Arrays.stream(LoggerTypes.values())
                .map(loggerType -> loggerType.getValue() + " für " + loggerType.name())
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
