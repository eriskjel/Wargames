package edu.ntnu.idatt2001.erikskj.gui.controllers;

import edu.ntnu.idatt2001.erikskj.factory.UnitFactory;
import edu.ntnu.idatt2001.erikskj.file.FileHandler;
import edu.ntnu.idatt2001.erikskj.gui.FXMLLoaderClass;
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
 * Class that handles the interaction between the fxml file "admin-edit-tournament.fxml" and the backend.
 * This class houses all the methods and variables needed to perform the tasks
 */
public class CreateArmyController implements Initializable {

    public ImageView removeInfantry;
    @FXML private TextField sumInfantry;
    @FXML private TextField sumCommander;
    @FXML private TextField sumCavalry;
    @FXML private TextField sumRanged;
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
    @FXML private ObservableList<UnitModel> observableList = FXCollections.observableArrayList();
    private FileHandler fileHandler = new FileHandler();
    private final ArrayList<Unit> units = new ArrayList<>();
    private final UnitFactory unitFactory = new UnitFactory();


    /**
     * clears table
     */
    public void clearTable(){
        tableArmyPreview.getItems().clear();
    }

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
        updateUnitCount();
        System.out.println(units);
    }

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

    public void updateArmyName(){
        String armyName = inpArmyName.getText();
    }

    public void updateUnitCount(){
        this.sumInfantry.setText(String.valueOf(units.stream().filter(unit -> unit.getUnitType().equals("Infantry")).count()));
        this.sumRanged.setText(String.valueOf(units.stream().filter(unit -> unit.getUnitType().equals("Ranged")).count()));
        this.sumCavalry.setText(String.valueOf(units.stream().filter(unit -> unit.getUnitType().equals("Cavalry")).count()));
        this.sumCommander.setText(String.valueOf(units.stream().filter(unit -> unit.getUnitType().equals("Commander")).count()));
        fillTable();
    }

    public void displayWarningCannotRemoveUnit(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning! Could not remove unit.");
        alert.setHeaderText(null);
        alert.setContentText("There are not any units of the type you're trying to remove, therefore you can not remove another unit.");
        alert.showAndWait();
    }


    public void removeInfantryUnit(MouseEvent mouseEvent) {
        if (Integer.parseInt(this.sumInfantry.getText()) == 0){
            displayWarningCannotRemoveUnit();
        }
        else{
            units.stream().filter(unit -> unit.getUnitType().equals("Infantry")).findAny().map(units::remove);
            updateUnitCount();
        }
    }

    public void removeRangedUnit(MouseEvent mouseEvent) {
        if (Integer.parseInt(this.sumRanged.getText()) == 0){
            displayWarningCannotRemoveUnit();
        }
        else{
            units.stream().filter(unit -> unit.getUnitType().equals("Ranged")).findAny().map(units::remove);
            updateUnitCount();
        }
    }

    public void removeCavalryUnit(MouseEvent mouseEvent) {
        if (Integer.parseInt(this.sumCavalry.getText()) == 0){
            displayWarningCannotRemoveUnit();
        }
        else{
            units.stream().filter(unit -> unit.getUnitType().equals("Cavalry")).findAny().map(units::remove);
            updateUnitCount();
        }
    }

    public void removeCommanderUnit(MouseEvent mouseEvent) {
        if (Integer.parseInt(this.sumCommander.getText()) == 0){
            displayWarningCannotRemoveUnit();
        }
        else{
            units.stream().filter(unit -> unit.getUnitType().equals("Commander")).findAny().map(units::remove);
            updateUnitCount();
        }
    }

    public boolean canArmyBeAdded(){
        if (Integer.parseInt(sumInfantry.getText()) == 0 && Integer.parseInt(sumRanged.getText()) == 0 && Integer.parseInt(sumCavalry.getText()) == 0 && Integer.parseInt(sumCommander.getText()) == 0){
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

    public void saveArmy(ActionEvent event) throws IOException {
        if (canArmyBeAdded()){
            Army army = new Army(this.inpArmyName.getText(), units);
            //army.setFileName(army.getName() + "-" + army.getArmyID() + ".csv");
            RegistryClient.armyRegister.add(army, true);
        }
        RegistryClient.fxmlLoaderClass.goToViewArmies(event);
    }

    public void displayWarningArmyCannotBeAdded(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning! Could not save army");
        alert.setHeaderText(null);
        alert.setContentText("You have not added any units to your army. Please add at least one unit to save an army.");
        alert.showAndWait();
    }

    public void displayWarningInvalidArmyName(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning! Could not save army");
        alert.setHeaderText(null);
        alert.setContentText("You have not given your army a name. Please add a name before saving.");
        alert.showAndWait();
    }


    public void fillTable(){
        clearTable();
        //adding infantry
        if (Integer.parseInt(sumInfantry.getText()) != 0){
            UnitModel infantryModel = new UnitModel("Infantry Unit", (int) units.stream().filter(unit -> unit.getUnitType().equals("Infantry")).count(), getIconByType("Infantry"));
            tableArmyPreview.getItems().add(infantryModel);
        }

        if (Integer.parseInt(sumRanged.getText()) != 0){
            UnitModel rangedModel = new UnitModel("Ranged Unit", (int) units.stream().filter(unit -> unit.getUnitType().equals("Ranged")).count(), getIconByType("Ranged"));
            tableArmyPreview.getItems().add(rangedModel);
        }

        if (Integer.parseInt(sumCavalry.getText()) != 0){
            UnitModel cavalryModel = new UnitModel("Cavalry Unit", (int) units.stream().filter(unit -> unit.getUnitType().equals("Cavalry")).count(), getIconByType("Cavalry"));
            tableArmyPreview.getItems().add(cavalryModel);
        }

        if (Integer.parseInt(sumCommander.getText()) != 0){
            UnitModel commanderModel = new UnitModel("Commander Unit", (int) units.stream().filter(unit -> unit.getUnitType().equals("Commander")).count(), getIconByType("Commander"));
            tableArmyPreview.getItems().add(commanderModel);
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

}
