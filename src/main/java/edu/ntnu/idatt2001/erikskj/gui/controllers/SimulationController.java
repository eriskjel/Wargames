package edu.ntnu.idatt2001.erikskj.gui.controllers;

import edu.ntnu.idatt2001.erikskj.enums.Terrain;
import edu.ntnu.idatt2001.erikskj.file.FileHandler;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Class that handles the interaction between the fxml file "admin-edit-tournament.fxml" and the backend.
 * This class houses all the methods and variables needed to perform the tasks
 */
public class SimulationController implements Initializable {

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
    @FXML private ObservableList<UnitModel> observableListArmy1 = FXCollections.observableArrayList();
    @FXML private ObservableList<UnitModel> observableListArmy2 = FXCollections.observableArrayList();
    @FXML private TextArea containerBattleInfo;
    private static int army1ID;
    private static int army2ID;
    private Terrain terrain;
    private int sleepTime;




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


        Army army1 = RegistryClient.armyRegister.getArmyByID(army1ID);
        Army army2 = RegistryClient.armyRegister.getArmyByID(army2ID);
        for (int i = 0; i < army1.getArrayWithUnitNames().size(); i++) {
            UnitModel unitModel = new UnitModel(army1.getArrayWithUnitNames().get(i), army1.getNumUnitsByType(army1.getArrayWithUnitNames().get(i)), getIconByType(army1.getArrayWithUnitNames().get(i)));
            tableArmy1.getItems().add(unitModel);
        }
        for (int i = 0; i < army2.getArrayWithUnitNames().size(); i++) {
            UnitModel unitModel = new UnitModel(army2.getArrayWithUnitNames().get(i), army2.getNumUnitsByType(army2.getArrayWithUnitNames().get(i)), getIconByType(army2.getArrayWithUnitNames().get(i)));
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
     * method that returns the correct icon based on what type of unit the army has
     * @param unit string containing unit type name
     * @return ImageView with icon matching unit
     */
    public ImageView getIconByType(String unit){
        switch (unit) {
            case "Infantry":
                return new ImageView(new Image(this.getClass().getResourceAsStream("/img/infantry.png")));
            case "Ranged":
                return new ImageView(new Image(this.getClass().getResourceAsStream("/img/ranged.png")));
            case "Cavalry":
                return new ImageView(new Image(this.getClass().getResourceAsStream("/img/cavalry.png")));
            case "Commander":
                return new ImageView(new Image(this.getClass().getResourceAsStream("/img/commander.png")));
            default:
                System.err.println("Something went wrong when rendering icon to unit.");
                return null;
        }
    }

    public void setTerrainToHills(ActionEvent actionEvent) {
        this.terrain = Terrain.HILL;
    }

    public void setTerrainToPlains(ActionEvent actionEvent) {
        this.terrain = Terrain.PLAINS;
    }

    public void setTerrainToForest(ActionEvent actionEvent) {
        this.terrain = Terrain.FOREST;
    }

    public void setSpeedToSlow(ActionEvent actionEvent) {
        this.sleepTime = 1000;
    }

    public void setSpeedToFast(ActionEvent actionEvent) {
        this.sleepTime = 500;
    }

    public void setSpeedToInstant(ActionEvent actionEvent) {
        this.sleepTime = 0;
    }

    public void simulate() throws InterruptedException {
        this.containerBattleInfo.clear();
        Battle battle = new Battle(RegistryClient.armyRegister.getArmyByID(army1ID),RegistryClient.armyRegister.getArmyByID(army2ID), terrain);
        battle.simulate();
        printBattleInfo(battle.getBattleInfo());
    }

    public void printBattleInfo(String battleInfo) throws InterruptedException {
        int sleep = this.sleepTime;

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

    public void resetArmy1(){
        RegistryClient.armyRegister.refillUnitsHealth(army1ID);
    }

    public void resetArmy2(){
        RegistryClient.armyRegister.refillUnitsHealth(army2ID);
    }
}
