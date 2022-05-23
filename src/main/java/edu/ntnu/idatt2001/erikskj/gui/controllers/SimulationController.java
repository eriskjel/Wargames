package edu.ntnu.idatt2001.erikskj.gui.controllers;

import edu.ntnu.idatt2001.erikskj.enums.Terrain;
import edu.ntnu.idatt2001.erikskj.gui.IconGetter;
import edu.ntnu.idatt2001.erikskj.gui.models.UnitModel;
import edu.ntnu.idatt2001.erikskj.register.RegistryClient;
import edu.ntnu.idatt2001.erikskj.war.Army;
import edu.ntnu.idatt2001.erikskj.war.Battle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Class that handles the interaction between the fxml file "simulation" and the backend.
 * This class houses all the methods and variables needed to perform the tasks
 */
public class SimulationController implements Initializable {

    @FXML private Button btnHills;
    @FXML private Button btnPlains;
    @FXML private Button btnForest;
    @FXML private Button btnSlow;
    @FXML private Button btnMedium;
    @FXML private Button btnFast;
    @FXML private Button btnInstant;
    @FXML private Label lblArmy1Name;
    @FXML private Label lblArmy2Name;
    @FXML private TableView tableArmy1;
    @FXML private TableColumn colUnit;
    @FXML private TableColumn colQuantity;
    @FXML private TableColumn colIcon;
    @FXML private TableView tableArmy2;
    @FXML private TableColumn colUnit1;
    @FXML private TableColumn colQuantity1;
    @FXML private TableColumn colIcon1;
    @FXML private final ObservableList<UnitModel> observableListArmy1 = FXCollections.observableArrayList();
    @FXML private final ObservableList<UnitModel> observableListArmy2 = FXCollections.observableArrayList();
    @FXML private TextArea containerBattleInfo;
    private static int army1ID;
    private static int army2ID;
    private Terrain terrain;
    private int sleepTime;
    private boolean loggingSpeedChosen = false;


    /**
     * method that sets armyID. given from goToBattleController
     * @param id1 army1ID
     * @param id2 army2ID
     */
    public static void setArmyIDs(int id1, int id2){
        army1ID = id1;
        army2ID = id2;
    }

    /**
     * method that runs when fxml file is loaded
     * makes sure that table is filled at startup
     * @param url url
     * @param resourceBundle bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clearTable();

        //initialize army1 table
        this.colIcon.setCellValueFactory(new PropertyValueFactory<>("Icon"));
        this.colUnit.setCellValueFactory(new PropertyValueFactory<>("Unit"));
        this.colQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        this.tableArmy1.setItems(observableListArmy1);

        //initialize army2 table
        this.colIcon1.setCellValueFactory(new PropertyValueFactory<>("Icon"));
        this.colUnit1.setCellValueFactory(new PropertyValueFactory<>("Unit"));
        this.colQuantity1.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        this.tableArmy2.setItems(observableListArmy2);

        this.lblArmy1Name.setText(RegistryClient.armyRegister.getArmyByID(army1ID).getName());
        this.lblArmy2Name.setText(RegistryClient.armyRegister.getArmyByID(army2ID).getName());

        fillAllArmiesTable();
    }

    /**
     * fills table with every army loaded in application
     */
    public void fillAllArmiesTable(){
        //clears table
        clearTable();
        IconGetter iconGetter = new IconGetter();


        Army army1 = RegistryClient.armyRegister.getArmyByID(army1ID);
        Army army2 = RegistryClient.armyRegister.getArmyByID(army2ID);
        for (int i = 0; i < army1.getArrayWithUnitNames().size(); i++) {
            UnitModel unitModel = new UnitModel(army1.getArrayWithUnitNames().get(i), army1.getNumAliveUnitsByType(army1.getArrayWithUnitNames().get(i)), iconGetter.getIconByType(army1.getArrayWithUnitNames().get(i)));
            tableArmy1.getItems().add(unitModel);
        }
        for (int i = 0; i < army2.getArrayWithUnitNames().size(); i++) {
            UnitModel unitModel = new UnitModel(army2.getArrayWithUnitNames().get(i), army2.getNumAliveUnitsByType(army2.getArrayWithUnitNames().get(i)), iconGetter.getIconByType(army2.getArrayWithUnitNames().get(i)));
            tableArmy2.getItems().add(unitModel);
        }
    }

    /**
     * clears gui table
     */
    public void clearTable(){
        tableArmy1.getItems().clear();
        tableArmy2.getItems().clear();
    }


    /**
     * calls on the FXMLLoaderClass to load the new fxml file
     * this particular loads the load army fxml file
     * @param event event
     * @throws IOException exception
     */
    public void goToLoadArmies(ActionEvent event) throws IOException {
        RegistryClient.fxmlLoaderClass.goToLoadArmies(event);
    }

    /**
     * calls on the FXMLLoaderClass to load the new fxml file
     * @param event event
     * @throws IOException exception
     */
    public void goToViewArmies(ActionEvent event) throws IOException {
        RegistryClient.fxmlLoaderClass.goToViewArmies(event);
    }

    /**
     * calls on the FXMLLoaderClass to load the new fxml file
     * this particular loads the create army fxml file
     * @param actionEvent event
     * @throws IOException exception
     */
    public void goToCreateArmy(ActionEvent actionEvent) throws IOException {
        RegistryClient.fxmlLoaderClass.goToCreateArmy(actionEvent);
    }


    /**
     * sets the terrain to hill and sets the style of the button to look "pressed"
     */
    public void setTerrainToHills() {
        this.terrain = Terrain.HILL;
        this.btnHills.setStyle(
                "-fx-text-fill: white;" +
                "-fx-background-color: #9b9b9b;" +
                "-fx-border-radius: 4px;" +
                "-fx-font-weight: bold;" +
                "-fx-underline: false;"
        );
        this.btnPlains.setStyle("");
        this.btnForest.setStyle("");
    }

    /**
     * sets the terrain to plain and sets the style of the button to look "pressed"
     */
    public void setTerrainToPlains() {
        this.terrain = Terrain.PLAINS;
        this.btnHills.setStyle("");
        this.btnPlains.setStyle("-fx-text-fill: white;" +
                "-fx-background-color: #9b9b9b;" +
                "-fx-border-radius: 4px;" +
                "-fx-font-weight: bold;" +
                "-fx-underline: false;");
        this.btnForest.setStyle("");
    }

    /**
     * sets the terrain to forest and sets the style of the button to look "pressed"
     */
    public void setTerrainToForest() {
        this.terrain = Terrain.FOREST;
        this.btnHills.setStyle("");
        this.btnPlains.setStyle("");
        this.btnForest.setStyle("-fx-text-fill: white;" +
                "-fx-background-color: #9b9b9b;" +
                "-fx-border-radius: 4px;" +
                "-fx-font-weight: bold;" +
                "-fx-underline: false;");
    }

    /**
     * sets the sleep time to 1000 and sets the style of the button to look "pressed"
     */
    public void setSpeedToSlow() {
        loggingSpeedChosen = true;
        this.sleepTime = 1000;
        this.btnSlow.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-background-color: #9b9b9b;" +
                        "-fx-border-radius: 4px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-underline: false;"
        );
        this.btnMedium.setStyle("");
        this.btnFast.setStyle("");
        this.btnInstant.setStyle("");
    }

    /**
     * sets the sleep time to 300 and sets the style of the button to look "pressed"
     */
    public void setSpeedToMedium(ActionEvent actionEvent) {
        loggingSpeedChosen = true;
        this.sleepTime = 300;
        this.btnSlow.setStyle("");
        this.btnMedium.setStyle("-fx-text-fill: white;" +
                "-fx-background-color: #9b9b9b;" +
                "-fx-border-radius: 4px;" +
                "-fx-font-weight: bold;" +
                "-fx-underline: false;");
        this.btnFast.setStyle("");
        this.btnInstant.setStyle("");
    }

    /**
     * sets the sleep time to 50 and sets the style of the button to look "pressed"
     */
    public void setSpeedToFast(ActionEvent actionEvent) {
        loggingSpeedChosen = true;
        this.sleepTime = 50;
        this.btnSlow.setStyle("");
        this.btnMedium.setStyle("");
        this.btnFast.setStyle("-fx-text-fill: white;" +
                "-fx-background-color: #9b9b9b;" +
                "-fx-border-radius: 4px;" +
                "-fx-font-weight: bold;" +
                "-fx-underline: false;");
        this.btnInstant.setStyle("");
    }

    /**
     * sets the sleep time to 0 and sets the style of the button to look "pressed"
     */
    public void setSpeedToInstant() {
        loggingSpeedChosen = true;
        this.sleepTime = 0;
        this.btnSlow.setStyle("");
        this.btnMedium.setStyle("");
        this.btnFast.setStyle("");
        this.btnInstant.setStyle("-fx-text-fill: white;" +
                "-fx-background-color: #9b9b9b;" +
                "-fx-border-radius: 4px;" +
                "-fx-font-weight: bold;" +
                "-fx-underline: false;");
    }

    /**
     * if battle can start then the Battle object is created and the simulate method is called. Battlelog is printed, and the gui tables are updated
     * with the new unit statuses
     */
    public void simulate() {
        if (checkIfBattleCanStart()){
            this.containerBattleInfo.clear();
            Battle battle = new Battle(RegistryClient.armyRegister.getArmyByID(army1ID),RegistryClient.armyRegister.getArmyByID(army2ID), terrain);
            battle.simulate();
            printBattleInfo(battle.getBattleInfo());
            fillAllArmiesTable();
        }
    }

    /**
     * method that checks is simulation can start
     * armies need to have units alive, the terrain needs to be set as well as the logging speed
     * @return false if both armies do not have units alive and if terrain or logging speed is undefined. otherwise returns true
     */
    public boolean checkIfBattleCanStart(){
        if (!RegistryClient.armyRegister.getArmyByID(army1ID).hasUnitsAlive() || !RegistryClient.armyRegister.getArmyByID(army2ID).hasUnitsAlive()){
            displayWarningArmyIsDead();
            return false;
        }
        else if(terrain == null){
            displayWarningTerrainIsNull();
            return false;
        }

        else if(!loggingSpeedChosen){
            displayWarningLoggingSpeedNotChosen();
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * displays warning dialouge box prompting that one of the armies does not have any units left
     */
    public void displayWarningArmyIsDead(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning! Cannot start battle!");
        alert.setHeaderText(null);
        alert.setContentText("One the armies loaded for battle does not have any units alive to battle!\nReset the army or load a new one to battle.");
        alert.showAndWait();
    }

    /**
     * displays a warning dialouge box prompting that the terrain is not set
     */
    public void displayWarningTerrainIsNull(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning! Cannot start battle!");
        alert.setHeaderText(null);
        alert.setContentText("Terrain is not defined. You need to pick a terrain to battle on.");
        alert.showAndWait();
    }

    /**
     * displays a warning dialouge box prompting that the logging speed is not set
     */
    public void displayWarningLoggingSpeedNotChosen(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning! Cannot start battle!");
        alert.setHeaderText(null);
        alert.setContentText("Logging speed is not defined. Please pick a speed to log the battle info.");
        alert.showAndWait();
    }

    /**
     * method responsible for printing the battleinfo at a slowed down rate
     * this is achieved by creating a thread, and furthermore using sleep method
     * @param battleInfo String of battleinfo
     */
    public void printBattleInfo(String battleInfo){
        int sleep = this.sleepTime;

        if (sleep != 0){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Runnable updater = new Runnable() {
                        @Override
                        public void run() {}
                    };

                    String print = "";
                    Scanner scanner = new Scanner(battleInfo);

                    while(scanner.hasNextLine()){
                        try{
                            if (sleep != 0){
                                print = scanner.nextLine() + "\n";
                                containerBattleInfo.appendText(print);
                                Thread.sleep(sleep);
                            }
                            else{
                                print += scanner.nextLine() + "\n";
                            }
                        }
                        catch (Exception e){
                            containerBattleInfo.setText(e.getMessage());
                        }
                        Platform.runLater(updater);
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        }
        else{
            containerBattleInfo.appendText(battleInfo);
        }




    }

    /**
     * resets army1 and resets the gui table
     */
    public void resetArmy1(){
        RegistryClient.armyRegister.getArmyByID(army1ID).resetArmy();
        fillAllArmiesTable();
    }

    /**
     * resets army2 and resets the gui table
     */
    public void resetArmy2(){
        RegistryClient.armyRegister.getArmyByID(army2ID).resetArmy();
        fillAllArmiesTable();
    }

    /**
     * resets both armies and resets the gui table
     */
    public void resetBothArmies(){
        RegistryClient.armyRegister.getArmyByID(army1ID).resetArmy();
        RegistryClient.armyRegister.getArmyByID(army2ID).resetArmy();
        fillAllArmiesTable();
    }
}
