package de.nordakademie.zellulaere_automaten.logger;

import java.util.function.Supplier;

/**
 * An enumeration representing different types of loggers.
 * <p>
 * Each enum constant corresponds to a specific logger type and provides a
 * way to create instances of the {@link ILogger} interface.
 * </p>
 */
public enum LoggerTypes {
    /**
     * Represents the {@link LogFile} logger type.
     */
    LogFile(1),
    /**
     * Represents the {@link ConsoleLogger} logger type.
     */
    ConsoleLogger(2);

    private final int value;

    /**
     * Constructs a {@link LoggerTypes} enum constant with the specified
     * logger constructor.
     * @param value a {@link Supplier} that provides instances of
     * {@link ILogger}.
     */
    LoggerTypes(int value) {
        this.value = value;
    }
    /**
     * Creates and returns a new instance of the logger associated with
     * this enum constant.
     * @return a new instance of {@link ILogger} for the corresponding logger type.
     */
    public int getLogger() {
        return value;
    }

    public static LoggerTypes fromValue(int value) {
        for (LoggerTypes loggerType : LoggerTypes.values()) {
            if (loggerType.getLogger() == value) {
                return loggerType; // Gibt den passenden Enum-Eintrag zurück
            }
        }
        throw new IllegalArgumentException("Kein LoggerType mit dem Wert " + value + " gefunden");
    }
}
