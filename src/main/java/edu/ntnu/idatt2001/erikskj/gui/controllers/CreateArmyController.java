package edu.ntnu.idatt2001.erikskj.gui.controllers;

import edu.ntnu.idatt2001.erikskj.factory.UnitFactory;
import edu.ntnu.idatt2001.erikskj.file.FileHandler;
import edu.ntnu.idatt2001.erikskj.gui.FXMLLoaderClass;
import edu.ntnu.idatt2001.erikskj.gui.IconGetter;
import edu.ntnu.idatt2001.erikskj.gui.models.UnitModel;
import edu.ntnu.idatt2001.erikskj.register.RegistryClient;
import edu.ntnu.idatt2001.erikskj.units.Unit;
import edu.ntnu.idatt2001.erikskj.war.Army;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Class that handles the interaction between the fxml file "create-army" and the backend.
 * This class houses all the methods and variables needed to perform the tasks on the fxml file
 */
public class CreateArmyController implements Initializable {

    @FXML private TextField inpInfantry;
    @FXML private TextField inpRanged;
    @FXML private TextField inpCavalry;
    @FXML private TextField inpCommander;
    @FXML private TextField inpInfantryName;
    @FXML private TextField inpCommanderName;
    @FXML private TextField inpCavalryName;
    @FXML private TextField inpRangedName;
    @FXML private TextField inpArmyName;
    @FXML private TableView tableArmyPreview;
    @FXML private TableColumn colUnit;
    @FXML private TableColumn colQuantity;
    @FXML private TableColumn colIcon;
    @FXML private final ObservableList<UnitModel> observableList = FXCollections.observableArrayList();
    private final ArrayList<Unit> units = new ArrayList<>();
    private final UnitFactory unitFactory = new UnitFactory();



    /**
     * method that runs as soon as the fxml file is loaded. this particular override initializes the tableview in the gui
     * @param url url
     * @param resourceBundle url
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.colIcon.setCellValueFactory(new PropertyValueFactory<>("Icon"));
        this.colUnit.setCellValueFactory(new PropertyValueFactory<>("Unit"));
        this.colQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        this.tableArmyPreview.setItems(observableList);
    }

    /**
     * calls on the FXMLLoader class to load a new fxml file
     * @param event event
     * @throws IOException exception
     */
    public void goToViewArmies(ActionEvent event) throws IOException {
        RegistryClient.fxmlLoaderClass.goToViewArmies(event);
    }

    /**
     * calls on the FXMLLoader class to load a new fxml file
     * @param event event
     * @throws IOException exception
     */
    public void goToBattle(ActionEvent event) throws IOException {
        RegistryClient.fxmlLoaderClass.goToBattle(event);
    }


    /**
     * calls on the FXMLLoader class to load a new fxml file
     * @param event event
     * @throws IOException exception
     */
    public void goToLoadArmies(ActionEvent event) throws IOException {
        RegistryClient.fxmlLoaderClass.goToLoadArmies(event);
    }

    /**
     * method that adds all the units inputted from the user in the gui, to the local unit arraylist.
     * It then calls to reset the input fields and fill the gui table
     */
    public void addStackOfUnits(){
        if (!inpInfantryName.getText().equals("") && !inpInfantry.getText().equals("")) {
            //adds infantry units
            units.addAll(unitFactory.getListOfUnits("InfantryUnit", Integer.parseInt(inpInfantry.getText()), inpInfantryName.getText(), 100));
        }

        if (!inpRangedName.getText().equals("") && !inpRanged.getText().equals("")) {
            //adds ranged units
            units.addAll(unitFactory.getListOfUnits("RangedUnit", Integer.parseInt(inpRanged.getText()), inpRangedName.getText(), 100));
        }

        if (!inpCavalryName.getText().equals("") && !inpCavalry.getText().equals("")) {
            //adds cavalry units
            units.addAll(unitFactory.getListOfUnits("CavalryUnit", Integer.parseInt(inpCavalry.getText()), inpCavalryName.getText(), 100));
        }

        if (!inpCommanderName.getText().equals("") && !inpCommander.getText().equals("")) {
            //adds commander units
            units.addAll(unitFactory.getListOfUnits("CommanderUnit", Integer.parseInt(inpCommander.getText()), inpCommanderName.getText(), 180));
        }
        resetInputFields();
        fillTable();
    }

    /**
     * resets all input field in gui
     */
    public void resetInputFields(){
        inpInfantryName.setText("");
        inpInfantry.setText("");

        inpRangedName.setText("");
        inpRanged.setText("");

        inpCavalryName.setText("");
        inpCavalry.setText("");

        inpCommanderName.setText("");
        inpCommander.setText("");
    }

    /**
     * clears table
     */
    public void clearTable(){
        tableArmyPreview.getItems().clear();
    }

    /**
     * displays the army name on the gui
     */
    public void updateArmyName(){
        String armyName = inpArmyName.getText();
    }

    /**
     * method that checks if army can be added. if unit size is zero or the army name is not defined then the method returns false and calls
     * on a warning display method
     * @return true if army can be added without any errors, false if otherwise
     */
    public boolean canArmyBeAdded(){
        if (units.size() == 0){
            displayWarningArmyCannotBeAdded();
            return false;
        }
        else if(inpArmyName.getText().equals("")){
            displayWarningInvalidArmyName();
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * saves army to armyregister, and directs user to view armies fxml file
     * @param event event
     * @throws IOException exception
     */
    public void saveArmy(ActionEvent event) throws IOException {
        if (canArmyBeAdded()){
            Army army = new Army(this.inpArmyName.getText(), units, false);
            //army.setFileName(army.getName() + "-" + army.getArmyID() + ".csv");
            RegistryClient.armyRegister.add(army);
        }
        RegistryClient.fxmlLoaderClass.goToViewArmies(event);
    }

    /**
     * displays a warning box to the user prompting that there are not any units in the army
     */
    public void displayWarningArmyCannotBeAdded(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning! Could not save army");
        alert.setHeaderText(null);
        alert.setContentText("You have not added any units to your army. Please add at least one unit to save an army.");
        alert.showAndWait();
    }

    /**
     * displays a warning box to the user prompting that the army name is not defined
     */
    public void displayWarningInvalidArmyName(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning! Could not save army");
        alert.setHeaderText(null);
        alert.setContentText("You have not given your army a name. Please add a name before saving.");
        alert.showAndWait();
    }

    /**
     * counts number of unit by each type
     * @param unitType type of unit
     * @return int with number of units by type
     */
    public int countUnits(String unitType){
        return (int) units.stream().filter(unit -> unit.getUnitType().equals(unitType)).count();
    }

    /**
     * fills gui table with models, based on what the user registered when creating an army
     */
    public void fillTable(){
        clearTable();
        IconGetter iconGetter = new IconGetter();
        //adding infantry
        if (countUnits("Infantry") != 0){
            UnitModel infantryModel = new UnitModel("Infantry Unit", countUnits("Infantry"), iconGetter.getIconByType("Infantry"));
            tableArmyPreview.getItems().add(infantryModel);
        }

        if (countUnits("Ranged") != 0){
            UnitModel rangedModel = new UnitModel("Ranged Unit", countUnits("Ranged"), iconGetter.getIconByType("Ranged"));
            tableArmyPreview.getItems().add(rangedModel);
        }

        if (countUnits("Cavalry") != 0){
            UnitModel cavalryModel = new UnitModel("Cavalry Unit", countUnits("Cavalry"), iconGetter.getIconByType("Cavalry"));
            tableArmyPreview.getItems().add(cavalryModel);
        }

        if (countUnits("Commander") != 0){
            UnitModel commanderModel = new UnitModel("Commander Unit", countUnits("Commander"), iconGetter.getIconByType("Commander"));
            tableArmyPreview.getItems().add(commanderModel);
        }
    }
}
