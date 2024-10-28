package de.nordakademie.zellulaere_automaten;

import de.nordakademie.zellulaere_automaten.logger.LoggerTypes;

import java.util.Objects;
import java.util.Scanner;

public class InputHandler {
    //region labels
    private final String welcomeMessage = "Willkommen zum Zellulären Automaten der Gruppe c7 der I22c";
    private final String askForExpeimentType = "Bitte wählen Sie ein Experiment. Geben Sie '1' für GameOfLife ein und '2' für Parity"; //TODO: ersetzen mit Factory-Ausgabe: waiting for Lars
    private final String askForExpeimentTypeInvalid = " bitte geben Sie einen validen Parameter ein. Die Eingabe wird wiederholt.";
    private final String askForLogType = "Bitte wählen Sie einen Log-Type. Bitte geben Sie die Zahl für einen Type ein. Diese Zahlen und Types stehen zur Verfügung" + System.lineSeparator() + "%s";
    //endregion
    //region variables
    private String experimentType = "";
    private String logType = "";
    //endregion
    public void getUserInputs(){
    System.out.println(welcomeMessage);
    //Experiment
    System.out.println(askForExpeimentType);
    Scanner scanner = new Scanner(System.in);
    experimentType = scanner.nextLine();
    //Todo: Validierung Experiment
    //while-Schleife validiert den experimentType
    //while (true){
    //    try {
    //        validateExperimentType(experimentType);
    //        break;
    //    } catch (IllegalArgumentException e){
    //        System.out.println(e.getMessage());
    //    }
    //}
    //TODO: Validierung von Algorithm

    //TODO: Validierung von Config

    //TODO: Validierung von Grid (wohl da nur die Art des Grids weil die Größe in den Startkonfigs gemacht wird

    System.out.printf((askForLogType) + "%n", LoggerTypes.getAvailableLoggerTypesForUserInput() + System.lineSeparator() + "0 für das schreiben keines Logs");
    logType = scanner.nextLine();
    while (true){
        try{
            if (!Objects.equals(logType, "0")) {
                LoggerTypes loggerType = LoggerTypes.getType(Integer.parseInt(logType));
            }
            break;
        } catch (IllegalArgumentException | EnumConstantNotPresentException e){
            System.out.println(getUserInfo() + askForExpeimentTypeInvalid);
            logType = scanner.nextLine();
        }
    }

    }
    //endregion
    private String getUserInfo(){
        return System.getProperty("user.name");
    }
}
