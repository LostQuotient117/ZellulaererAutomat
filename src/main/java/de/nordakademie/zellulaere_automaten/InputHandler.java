package de.nordakademie.zellulaere_automaten;

import de.nordakademie.zellulaere_automaten.logger.LoggerTypes;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.CalculationType;

import java.util.Objects;
import java.util.Scanner;

public class InputHandler {
    //region labels
    private final String welcomeMessage = "Willkommen zum Zellulären Automaten der Gruppe c7 der I22c";
    private final String askForCalcType = "Falls sie jedes der zur Verfügung stehenden Experimente ausführen wollen, geben Sie 'all' ein. Andernfalls wählen Sie bitte ein Calculation Type. Geben Sie ";
    private final String askForExpeimentTypeInvalid = " bitte geben Sie einen validen Parameter ein. Die Eingabe wird wiederholt.";
    private final String askForLogType = "Bitte wählen Sie einen Log-Type. Bitte geben Sie die Zahl für einen Type ein. Diese Zahlen und Types stehen zur Verfügung" + System.lineSeparator() + "%s";
    //endregion

    public void getUserInputs(){
        //region variables
        String calcType = "";
        String logType = "";
        //endregion
        System.out.println(welcomeMessage);
        Scanner scanner = new Scanner(System.in);
        //
        System.out.println((askForCalcType) + CalculationType.getAvailableCalculationTypesForUserInput()); //Todo: Brauchen wir eine validierung für Moore oder Neumann? Das wird ja eigentlich je GameOfLife oder Parity entscheiden?!
        calcType = scanner.nextLine();
        if (Objects.equals(calcType, "all")){
            executeAllExperiments(); //Todo: Implementieren
        }
        while (true){
            try{
                String calculationType = String.valueOf(CalculationType.getType(Integer.parseInt(calcType))); //Todo: Use for String Builder. Überprüfen ob das so funktioniert
                break;
            } catch (IllegalArgumentException | EnumConstantNotPresentException e){
                System.out.println(getUserInfo() + askForExpeimentTypeInvalid);
                calcType = scanner.nextLine();
            }
        }
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
    private void executeAllExperiments(){
        System.out.print("all"); //Todo: Implementieren
    }
}
