package de.nordakademie.zellulaere_automaten.logger;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * An enumeration representing different types of loggers.
 * Each enum constant corresponds to a specific logger type and provides a
 * way to create instances of the {@link ILogger} interface.
 */
public enum LoggerTypes {
    LogFile(1),
    ConsoleLogger(2);
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
     * @param value: chosen logger-mode
     * @return: LoggerType
     */
    public static LoggerTypes fromValue(int value) {
        LoggerTypes loggerType = Arrays.stream(LoggerTypes.values())
                                        .filter(entry ->entry.getValue() == value)
                                        .findFirst()
                                        .orElse(null);
        if (loggerType == null)
            throw new EnumConstantNotPresentException(LoggerTypes.class, "This type of logger does not exist");
        return loggerType;
    }
}
