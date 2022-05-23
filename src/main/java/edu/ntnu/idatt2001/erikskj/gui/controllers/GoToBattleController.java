package edu.ntnu.idatt2001.erikskj.gui.controllers;

import edu.ntnu.idatt2001.erikskj.file.FileHandler;
import edu.ntnu.idatt2001.erikskj.gui.IconGetter;
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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class that handles the interaction between the fxml file "battle" and the backend.
 * This class houses all the methods and variables needed to perform the tasks
 */
public class GoToBattleController implements Initializable {

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
    @FXML private final ObservableList<LoadedArmyModel> observableListArmies = FXCollections.observableArrayList();
    @FXML private final ObservableList<UnitModel> observableListArmy1 = FXCollections.observableArrayList();
    @FXML private final ObservableList<UnitModel> observableListArmy2 = FXCollections.observableArrayList();
    private int army1ID;
    private int army2ID;
    private final FileHandler fileHandler = new FileHandler();
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
                    RegistryClient.armyRegister.getArmies().get(i).getListOfUnitsAlive().size(),
                    RegistryClient.armyRegister.getArmies().get(i).getSumHealth(),
                    RegistryClient.armyRegister.getArmies().get(i).getArmyFile(),
                    RegistryClient.armyRegister.getArmies().get(i).getArmyID());
            tableLoadedArmies.getItems().add(loadedArmyModel);
        }
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

    /**
     * determines which table to load army data into, and fetches the selected army and sends this to help method
     */
    public void loadSelectedArmy(){
        ObservableList<LoadedArmyModel> armyModel = tableLoadedArmies.getSelectionModel().getSelectedItems();

        if (table1Empty){
            army1ID = armyModel.get(0).getArmyID();
            fillArmyTable(armyModel.get(0).getArmyID(), 1);
            table1Empty = false;
        }
        else if (table2Empty){
            army2ID = armyModel.get(0).getArmyID();
            fillArmyTable(armyModel.get(0).getArmyID(), 2);
            table2Empty = false;
        }
        else{
            displayWarningMax2Armies();
        }

    }

    /**
     *  fills gui table with data from the selected army.
     * @param id army id
     * @param n table number
     */
    public void fillArmyTable(int id, int n){
        //Army army = fileHandler.readFromFile(path);
        Army army = RegistryClient.armyRegister.getArmyByID(id);

        IconGetter iconGetter = new IconGetter();


        //adds models to tableview
        for (int i = 0; i < army.getArrayWithUnitNames().size(); i++) {
            UnitModel unitModel = new UnitModel(army.getArrayWithUnitNames().get(i), army.getNumAliveUnitsByType(army.getArrayWithUnitNames().get(i)), iconGetter.getIconByType(army.getArrayWithUnitNames().get(i)));
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
     * displays a warning dialog box to the user, indicating that two armies already have been loaded for battle
     */
    public void displayWarningMax2Armies(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning! Cannot load another army.");
        alert.setHeaderText(null);
        alert.setContentText("You have already loaded 2 armies. Please remove one, to add another.");
        alert.showAndWait();
    }

    /**
     * removes army from the loaded table and sets that the table is empty and ready for new army
     */
    public void removeArmy1(){
        observableListArmy1.clear();
        table1Empty = true;
        armyName1.setText("");
    }

    /**
     * removes army from the loaded table and sets that the table is empty and ready for new army
     */
    public void removeArmy2(){
        observableListArmy2.clear();
        table2Empty = true;
        armyName2.setText("");
    }

    /**
     * sets army ids in simulation controller to the two armies that will be fighting and loads a new fxml file
     * @param actionEvent event
     * @throws IOException exception
     */
    public void goToSimulation(ActionEvent actionEvent) throws IOException {
        if (checkIfUserCanGoToBattle()){
            SimulationController.setArmyIDs(army1ID, army2ID);
            RegistryClient.fxmlLoaderClass.goToSimulation(actionEvent);
        }
    }

    /**
     * checks if user can go to battle page.
     * checks if two armies are loaded, and if the two armies are unique
     * @return true if user can go two battle with armies loaded, false if not
     */
    public boolean checkIfUserCanGoToBattle() {
        if (table1Empty || table2Empty) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning! Cannot go to battle!");
            alert.setHeaderText(null);
            alert.setContentText("You need to load two armies to go to battle!");
            alert.showAndWait();
            return false;
        } else if (army1ID == army2ID) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning! Cannot go to battle!");
            alert.setHeaderText(null);
            alert.setContentText("You cannot choose the same army twice, and go to battle!\nPlease pick to unique armies.");
            alert.showAndWait();
            return false;
        }
        else{
            return true;
        }
    }
}
