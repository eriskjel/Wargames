package edu.ntnu.idatt2001.erikskj.gui.controllers;

import edu.ntnu.idatt2001.erikskj.file.FileHandler;
import edu.ntnu.idatt2001.erikskj.gui.models.UnitModel;
import edu.ntnu.idatt2001.erikskj.register.RegistryClient;
import edu.ntnu.idatt2001.erikskj.war.Army;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.util.ResourceBundle;

/**
 * Class that handles the interaction between the fxml file "admin-edit-tournament.fxml" and the backend.
 * This class houses all the methods and variables needed to perform the tasks
 */
public class ViewSpecificArmyController implements Initializable {

    @FXML private TableView tableViewArmy;
    @FXML private TableColumn colUnit;
    @FXML private TableColumn colQuantity;
    @FXML private TableColumn colIcon;
    @FXML private Label lblArmyName;
    @FXML private ObservableList<UnitModel> observableList = FXCollections.observableArrayList();
    private static String currentArmyFileName;
    private FileHandler fileHandler = new FileHandler();

    /**
     * method that runs when fxml file is loaded
     * makes sure that table is filled at startup
     * @param url url
     * @param resourceBundle bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clearTable();
        this.colIcon.setCellValueFactory(new PropertyValueFactory<>("Icon"));
        this.colUnit.setCellValueFactory(new PropertyValueFactory<>("Unit"));
        this.colQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        this.tableViewArmy.setItems(observableList);
        fillTable();
    }


    /**
     * fills table with every army loaded in application
     */
    public void fillTable(){
        //clears table
        clearTable();

        //create army by reading file, with path specified from user input
        Army army = fileHandler.readFromFile(currentArmyFileName);

        //sets army name above table for display
        lblArmyName.setText(army.getName());

        //adds models to tableview
        for (int i = 0; i < army.getArrayWithUnitNames().size(); i++) {
            UnitModel unitModel = new UnitModel(army.getArrayWithUnitNames().get(i), army.getNumUnitsByType(army.getArrayWithUnitNames().get(i)), getIconByType(army.getArrayWithUnitNames().get(i)));
            tableViewArmy.getItems().add(unitModel);
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

    public static void setCurrentArmyFileName(String path){
        currentArmyFileName = path;
    }

    /**
     * clears table
     */
    public void clearTable(){
        tableViewArmy.getItems().clear();
    }

    /**
     * removes all armies from register, deletes all files from army directory
     * and clears table afterwards
     * @throws IOException exception
     */
    public void removeAllArmies() throws IOException {
        RegistryClient.armyRegister.removeAll();
        File file = new File("src/main/resources/armyRegister");
        FileUtils.cleanDirectory(file);
        clearTable();
    }

    /**
     * calls on the fxmlLoaderClass to load the new fxml file
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


    public void goToCreateArmy(ActionEvent actionEvent) throws IOException {
        RegistryClient.fxmlLoaderClass.goToCreateArmy(actionEvent);
    }
}
