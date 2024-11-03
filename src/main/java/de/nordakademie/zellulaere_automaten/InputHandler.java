package de.nordakademie.zellulaere_automaten;

import de.nordakademie.zellulaere_automaten.experiment.ExperimentFactory;
import de.nordakademie.zellulaere_automaten.experiment.IExperiment;
import de.nordakademie.zellulaere_automaten.grid.GridType;
import de.nordakademie.zellulaere_automaten.logger.LoggerTypes;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.CalculationType;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.Scanner;

/**
 * The {@link InputHandler} class is responsible for handling user inputs and executing experiments based on those inputs.
 * It prompts the user to select {@code calculation type}, {@code log type}, {@code configuration type}, and {@code grid type}, validates the inputs,
 * and then executes the chosen experiment or all experiments.
 * The class also provides methods to execute all predefined experiments and log the time taken for each experiment
 * as well as the total time taken.
 *
 * @author Jannick Gottschalk
 * @author Lars Nicht
 */
public class InputHandler {
    //region globalLabels
    final String typeInvalid = " bitte geben Sie einen validen Parameter ein. Die Eingabe wird wiederholt.";
    //endregion
    /**
     * This method handles user inputs for selecting calculation type, log type, configuration type, and grid type.
     * It prompts the user for each input, validates the input, and then executes the chosen experiment or all experiments
     * based on the user's selection.
     */
    public void getUserInputs(){
        //region labels
        final String welcomeMessage = "Willkommen zum Zellulären Automaten der Gruppe c7 der I22c.";
        final String askForCalcType = "Falls sie jedes der zur Verfügung stehenden Experimente ausführen wollen, geben Sie 'all' ein. Andernfalls wählen Sie bitte ein Modell." + System.lineSeparator() + "Bitte geben Sie die Zahl für Ihr gewünschtes Modell ein. Folgende stehen zur Verfügung:" + System.lineSeparator() + "%s";
        final String askForConfig = System.lineSeparator() + "Bitte geben Sie ihr gewolltes Experiment ein. Es stehen folgende Experimente zur Verfügung:" + System.lineSeparator() + "%s";
        final String askForGridType = System.lineSeparator() + "Bitte geben Sie ihr gewolltes Grid ein. Es stehen folgende Grids zur Verfügung:" + System.lineSeparator() + "%s";
        //endregion
        //region variables
        String calcType;
        String logType;
        String configType = "";
        String gridType;
        //endregion
        System.out.println(System.lineSeparator() + welcomeMessage + System.lineSeparator());
        Scanner scanner = new Scanner(System.in);
        //
        System.out.printf((askForCalcType) + "%n", CalculationType.getAvailableCalculationTypesForUserInput());
        calcType = scanner.nextLine();
        if (Objects.equals(calcType, "all")){
            logType = askForLoggerType();
            executeAllExperiments(logType);
            System.exit(0);
        }
        while (true){
            try{
                CalculationType.getType(Integer.parseInt(calcType));
                break;
            } catch (IllegalArgumentException | EnumConstantNotPresentException e){
                System.out.println(getUserInfo() + typeInvalid);
                calcType = scanner.nextLine();
            }
        }
        if (Objects.equals(calcType, "1")){
            System.out.printf((askForConfig) + "%n", "1 für Startkonfiguration 1" + System.lineSeparator() + "2 für Startkonfiguration 2" + System.lineSeparator() + "3 für Startkonfiguration 3");
            configType = scanner.nextLine();
            while (true){
                if (Objects.equals(configType, "1") || Objects.equals(configType, "2") || Objects.equals(configType, "3")){
                    break;
                } else {
                    System.out.println(getUserInfo() + typeInvalid);
                    configType = scanner.nextLine();
                }
            }
        } else if (Objects.equals(calcType, "2")){
            System.out.printf((askForConfig) + "%n", "1 für Startkonfiguration 1, 2 für Startkonfiguration 2");
            configType = scanner.nextLine();
            while (true){
                if (Objects.equals(configType, "1") || Objects.equals(configType, "2")){
                    break;
                } else {
                    System.out.println(getUserInfo() + typeInvalid);
                    configType = scanner.nextLine();
                }
            }
        }
        System.out.printf((askForGridType) + "%n", GridType.getAvailableGridTypesForUserInput());
        gridType = scanner.nextLine();
        while (true){
            try{
                GridType.getType(Integer.parseInt(gridType));
                break;
            } catch (IllegalArgumentException | EnumConstantNotPresentException e){
                System.out.println(getUserInfo() + typeInvalid);
                gridType = scanner.nextLine();
            }
        }
        logType = askForLoggerType();
        System.out.println(System.lineSeparator() + "Die Eingaben waren erfolgreich. Das Programm wird nun gestartet.");
        executeChosenExperiment(calcType, configType, gridType, logType);

    }

    //endregion
    /**
     * Retrieves the username of the current user.
     * @return the username of the current user
     */
    private String getUserInfo(){
        return System.getProperty("user.name");
    }

    /**
     * Executes the chosen experiment based on the provided calculation type, configuration type, grid type, and log type.
     * @param calcType   the calculation type for the experiment
     * @param configType the configuration type for the experiment
     * @param gridType   the grid type for the experiment
     * @param logType    the log type for the experiment
     */
    private void executeChosenExperiment(String calcType, String configType, String gridType, String logType){
        LoggerTypes loggerType = LoggerTypes.getType(Integer.parseInt(logType));
        ExperimentFactory experimentFactory = new ExperimentFactory();
        experimentFactory.createExperiment(buildStringForExperiment(calcType, configType, gridType, logType)).runExperiment(loggerType);
    }
    /**
     * Prompts the user to select a log type and validates the input.
     * This method displays the available log types from {@link LoggerTypes}, reads the user's input,
     * and ensures the input corresponds to a valid log type.
     * If the input is invalid, the user is prompted to enter a valid log type.
     *
     * @return the selected log type as a string
     */
    private String askForLoggerType(){
        final String askForLogType = System.lineSeparator() + "Bitte wählen Sie einen Log-Type. Diese Log-Types stehen zur Verfügung:" + System.lineSeparator() + "%s";
        String logType;
        Scanner scanner = new Scanner(System.in);
        System.out.printf((askForLogType) + "%n", LoggerTypes.getAvailableLoggerTypesForUserInput() + System.lineSeparator());
        logType = scanner.nextLine();
        while (true){
            try{
                if (!Objects.equals(logType, "0")) {
                    LoggerTypes.getType(Integer.parseInt(logType));
                }
                break;
            } catch (IllegalArgumentException | EnumConstantNotPresentException e){
                System.out.println(getUserInfo() + typeInvalid);
                logType = scanner.nextLine();
            }
        }
        return logType;
    }

    /**
     * Builds a string representation for the experiment based on the provided calculation type, configuration type, grid type, and log type.
     * @param calcType   the calculation type for the experiment
     * @param configType the configuration type for the experiment
     * @param gridType   the grid type for the experiment
     * @param logType    the log type for the experiment
     * @return a string representing the experiment in the format "CalculationTypeExperimentConfigTypeStructureGridType"
     */
    private String buildStringForExperiment(String calcType, String configType, String gridType, String logType){
        String output = CalculationType.getType(Integer.parseInt(calcType)).name() + "Experiment" + configType + "Structure" + gridType;
        if (Objects.equals(logType, "1") || Objects.equals(logType, "3")){
            System.out.println("Ihre Datei erhält den Namen: " + output + ".log");
        }
        return output;
    }

    /**
     * Executes all predefined experiments and logs the time taken for each experiment as well as the total time taken.
     * The experiments are run using the specified log type.
     * @param logTypeInput the log type to be used for logging the experiments
     */
    private void executeAllExperiments(String logTypeInput) {
        LoggerTypes loggerType = LoggerTypes.getType(Integer.parseInt(logTypeInput));
        IExperiment[] experiments = getIExperiments();

        Instant start = Instant.now();

        for (IExperiment experiment : experiments) {
            Instant experimentStart = Instant.now();
            experiment.runExperiment(loggerType);
            Instant experimentEnd = Instant.now();
            Duration experimentTimeElapsed = Duration.between(experimentStart, experimentEnd);
            System.out.println("Time taken for " + experiment.getClass().getSimpleName() + ": " + experimentTimeElapsed.toMillis() + " milliseconds");
        }

        Instant end = Instant.now();
        Duration totalTimeElapsed = Duration.between(start, end);
        System.out.println("Total time taken for all experiments: " + totalTimeElapsed.toMillis() + " milliseconds");
    }

    /**
     * Retrieves an array of predefined experiments.
     * @return an array of {@link IExperiment} objects representing the predefined experiments.
     */
    private static IExperiment[] getIExperiments() {
        ExperimentFactory experimentFactory = new ExperimentFactory();
        return new IExperiment[]{
                experimentFactory.createExperiment("GameOfLifeExperiment1Structure1"),
                experimentFactory.createExperiment("GameOfLifeExperiment1Structure2"),
                experimentFactory.createExperiment("GameOfLifeExperiment2Structure1"),
                experimentFactory.createExperiment("GameOfLifeExperiment2Structure2"),
                experimentFactory.createExperiment("GameOfLifeExperiment3Structure1"),
                experimentFactory.createExperiment("GameOfLifeExperiment3Structure2"),
                experimentFactory.createExperiment("ParityExperiment1Structure1"),
                experimentFactory.createExperiment("ParityExperiment1Structure2"),
                experimentFactory.createExperiment("ParityExperiment2Structure1"),
                experimentFactory.createExperiment("ParityExperiment2Structure2")
        };
    }
}
