package edu.ntnu.idatt2001.erikskj.gui.controllers;

import edu.ntnu.idatt2001.erikskj.file.FileHandler;
import edu.ntnu.idatt2001.erikskj.gui.models.LoadedArmyModel;
import edu.ntnu.idatt2001.erikskj.gui.models.UnitModel;
import edu.ntnu.idatt2001.erikskj.register.RegistryClient;
import edu.ntnu.idatt2001.erikskj.war.Army;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Class that handles the interaction between the fxml file "admin-edit-tournament.fxml" and the backend.
 * This class houses all the methods and variables needed to perform the tasks
 */
public class GoToBattleController<directoryListing> implements Initializable {

    @FXML private Label armyName1;
    @FXML private Label armyName2;
    @FXML private TableView tableArmy1;
    @FXML private TableView tableArmy2;
    @FXML private TableColumn colIcon;
    @FXML private TableColumn colUnit;
    @FXML private TableColumn colQuantity;
    @FXML private TableColumn colIcon1;
    @FXML private TableColumn colUnit1;
    @FXML private TableColumn colQuantity1;
    @FXML private TableView tableLoadedArmies;
    @FXML private TableColumn colArmyName;
    @FXML private TableColumn colTotalUnits;
    @FXML private TableColumn colTotalHealth;
    @FXML private TableColumn colFileName;
    @FXML private ObservableList<LoadedArmyModel> observableListArmies = FXCollections.observableArrayList();
    @FXML private ObservableList<UnitModel> observableListArmy1 = FXCollections.observableArrayList();
    @FXML private ObservableList<UnitModel> observableListArmy2 = FXCollections.observableArrayList();
    private int army1ID;
    private int army2ID;
    private FileHandler fileHandler = new FileHandler();
    private boolean table1Empty = true;
    private boolean table2Empty = true;

    /**
     * method that runs when fxml file is loaded
     * makes sure that table is filled at startup
     * @param url url
     * @param resourceBundle bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clearTable();

        //initialize all armies table
        this.colArmyName.setCellValueFactory(new PropertyValueFactory<>("ArmyName"));
        this.colTotalUnits.setCellValueFactory(new PropertyValueFactory<>("TotalUnits"));
        this.colTotalHealth.setCellValueFactory(new PropertyValueFactory<>("TotalHealth"));
        this.colFileName.setCellValueFactory(new PropertyValueFactory<>("FileName"));
        this.tableLoadedArmies.setItems(observableListArmies);

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

        fillAllArmiesTable();
    }

    /**
     * fills table with every army loaded in application
     */
    public void fillAllArmiesTable(){
        //clears table
        clearTable();

        //loops through army register and fills table with LoadedArmyModels

        for (int i = 0; i < RegistryClient.armyRegister.getArmies().size(); i++) {
            LoadedArmyModel loadedArmyModel = new LoadedArmyModel(
                    RegistryClient.armyRegister.getArmies().get(i).getName(),
                    RegistryClient.armyRegister.getArmies().get(i).getUnits().size(),
                    RegistryClient.armyRegister.getArmies().get(i).getSumHealth(),
                    RegistryClient.armyRegister.getArmies().get(i).getArmyFile(),
                    RegistryClient.armyRegister.getArmies().get(i).getArmyID());
            tableLoadedArmies.getItems().add(loadedArmyModel);
        }



        /*
        File dir = new File("src/main/resources/armyRegister");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                Army army = fileHandler.readFromFile(child.getPath());

            }
        }

         */


        /*
        ArrayList<Army> armies = fileHandler.readArmyFromRegister();
        for (int i = 0; i < armies.size(); i++) {
            LoadedArmyModel loadedArmyModel = new LoadedArmyModel(
                    armies.get(i).getName(),
                    armies.get(i).getUnits().size(),
                    armies.get(i).getSumHealth(),
                    armies.get(i).getArmyFile(),
                    armies.get(i).getArmyID());
            tableLoadedArmies.getItems().add(loadedArmyModel);
        }

         */




    }



    /**
     * clears gui table
     */
    public void clearTable(){
        tableLoadedArmies.getItems().clear();
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




    public void loadSelectedArmy(){
        ObservableList<LoadedArmyModel> armyModel = tableLoadedArmies.getSelectionModel().getSelectedItems();

        /*
        if (table1Empty){
            army1ID = armyModel.get(0).getArmyID();
            fillArmyTable(army1ID, armyModel.get(0).getArmyName(), 1);
            table1Empty = false;
        }
        else if (table2Empty){
            army2ID = armyModel.get(0).getArmyID();
            fillArmyTable(army2ID, armyModel.get(0).getArmyName(), 2);
            table2Empty = false;
        }
        else{
            displayWarningMax2Armies();
        }

         */

        if (table1Empty){
            army1ID = armyModel.get(0).getArmyID();
            fillArmyTable(armyModel.get(0).getFilePath(), 1);
            table1Empty = false;
        }
        else if (table2Empty){
            army2ID = armyModel.get(0).getArmyID();
            fillArmyTable(armyModel.get(0).getFilePath(), 2);
            table2Empty = false;
        }
        else{
            displayWarningMax2Armies();
        }

    }

    public void displayWarningMax2Armies(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning! Cannot load another army.");
        alert.setHeaderText(null);
        alert.setContentText("You have already loaded 2 armies. Please remove one, to add another.");
        alert.showAndWait();
    }

    public void fillArmyTable(int id, String name, int n){
        String pathAndName = "src/main/resources/armyRegister/" + name + "-" + id + ".csv";
        Army army = fileHandler.readFromFile(pathAndName);


        //adds models to tableview
        for (int i = 0; i < army.getArrayWithUnitNames().size(); i++) {
            UnitModel unitModel = new UnitModel(army.getArrayWithUnitNames().get(i), army.getNumUnitsByType(army.getArrayWithUnitNames().get(i)), getIconByType(army.getArrayWithUnitNames().get(i)));
            if (n == 1){
                tableArmy1.getItems().add(unitModel);
                armyName1.setText(name);

            }
            else if(n == 2){
                tableArmy2.getItems().add(unitModel);
                armyName2.setText(name);
            }
        }
    }

    public void fillArmyTable(String path, int n){
        Army army = fileHandler.readFromFile(path);


        //adds models to tableview
        for (int i = 0; i < army.getArrayWithUnitNames().size(); i++) {
            UnitModel unitModel = new UnitModel(army.getArrayWithUnitNames().get(i), army.getNumUnitsByType(army.getArrayWithUnitNames().get(i)), getIconByType(army.getArrayWithUnitNames().get(i)));
            if (n == 1){
                tableArmy1.getItems().add(unitModel);
                armyName1.setText(army.getName());

            }
            else if(n == 2){
                tableArmy2.getItems().add(unitModel);
                armyName2.setText(army.getName());
            }
        }
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

    public void removeArmy1(){
        observableListArmy1.clear();
        table1Empty = true;
        armyName1.setText("");
    }

    public void removeArmy2(){
        observableListArmy2.clear();
        table2Empty = true;
        armyName2.setText("");
    }

    public void goToSimulation(ActionEvent actionEvent) throws IOException {
        SimulationController.setArmyIDs(army1ID, army2ID);
        RegistryClient.fxmlLoaderClass.goToSimulation(actionEvent);
    }
}
