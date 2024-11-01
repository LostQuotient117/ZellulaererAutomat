package de.nordakademie.zellulaere_automaten;

import de.nordakademie.zellulaere_automaten.grid.GridType;
import de.nordakademie.zellulaere_automaten.logger.LoggerTypes;
import de.nordakademie.zellulaere_automaten.strategy.stateCalculation.CalculationType;

import java.util.Objects;
import java.util.Scanner;

public class InputHandler {

    public void getUserInputs(){
        //region labels
        final String welcomeMessage = "Willkommen zum Zellulären Automaten der Gruppe c7 der I22c.";
        final String askForCalcType = "Falls sie jedes der zur Verfügung stehenden Experimente ausführen wollen, geben Sie 'all' ein. Andernfalls wählen Sie bitte ein Calculation Type." + System.lineSeparator() + "Bitte geben Sie die Zahl für einen Type ein. Folgende stehen zur Verfügung:" + System.lineSeparator() + "%s";
        final String askForLogType = "Bitte wählen Sie einen Log-Type. Diese Log-Types stehen zur Verfügung:" + System.lineSeparator() + "%s";
        final String askForConfig = "Bitte geben Sie ihr gewolltes Experiment ein. Es stehen folgende Experimente zur Verfügung:" + System.lineSeparator() + "%s";
        final String askForGridType = "Bitte geben Sie ihr gewolltes Grid ein. Es stehen folgende Grids zur Verfügung:" + System.lineSeparator() + "%s";
        final String typeInvalid = " bitte geben Sie einen validen Parameter ein. Die Eingabe wird wiederholt.";
        //endregion
        //region variables
        String calcType = "";
        String logType = "";
        String configType = "";
        String gridType = "";
        //endregion
        System.out.println(welcomeMessage);
        Scanner scanner = new Scanner(System.in);
        //
        System.out.printf((askForCalcType) + "%n", CalculationType.getAvailableCalculationTypesForUserInput());
        calcType = scanner.nextLine();
        if (Objects.equals(calcType, "all")){
            executeAllExperiments(); //Todo: Implementieren
        }
        while (true){
            try{
                String calculationType = String.valueOf(CalculationType.getType(Integer.parseInt(calcType)));
                break;
            } catch (IllegalArgumentException | EnumConstantNotPresentException e){
                System.out.println(getUserInfo() + typeInvalid);
                calcType = scanner.nextLine();
            }
        }
        if (Objects.equals(calcType, "1")){
            System.out.printf((askForConfig) + "%n", "1 für Startkonfiguration 1," + System.lineSeparator() + "2 für Startkonfiguration 2," + System.lineSeparator() + "3 für Startkonfiguration 3");
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
                GridType gridTypes = GridType.getType(Integer.parseInt(gridType));
                break;
            } catch (IllegalArgumentException | EnumConstantNotPresentException e){
                System.out.println(getUserInfo() + typeInvalid);
                gridType = scanner.nextLine();
            }
        }

        System.out.printf((askForLogType) + "%n", LoggerTypes.getAvailableLoggerTypesForUserInput() + System.lineSeparator() + "0 für das schreiben keines Logs");
        logType = scanner.nextLine();
        while (true){
            try{
                if (!Objects.equals(logType, "0")) {
                    LoggerTypes loggerType = LoggerTypes.getType(Integer.parseInt(logType));
                }
                break;
            } catch (IllegalArgumentException | EnumConstantNotPresentException e){
                System.out.println(getUserInfo() + typeInvalid);
                logType = scanner.nextLine();
            }
        }
        System.out.println("Die Eingaben waren erfolgreich. Das Programm wird nun gestartet.");
        executeChosenExperiment(calcType, configType, gridType, logType);

    }
    //endregion
    private String getUserInfo(){
        return System.getProperty("user.name");
    }
    private void executeChosenExperiment(String calcType, String configType, String gridType, String logType){
        System.out.print("chosen"); //Todo: Implementieren
    }
    private void executeAllExperiments(){
        System.out.print("all"); //Todo: Implementieren
    }
}
