package edu.ntnu.idatt2001.gui.controllers;

import edu.ntnu.idatt2001.file.FileHandler;
import edu.ntnu.idatt2001.gui.App;
import edu.ntnu.idatt2001.gui.models.LoadedArmyModel;
import edu.ntnu.idatt2001.gui.models.UnitModel;
import edu.ntnu.idatt2001.register.RegistryClient;
import edu.ntnu.idatt2001.war.Army;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
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

    @FXML
    private TableView tableViewArmy;
    @FXML private TableColumn colUnit;
    @FXML private TableColumn colQuantity;
    @FXML private TableColumn colIcon;
    public Label lblArmyName;
    ObservableList<UnitModel> observableList = FXCollections.observableArrayList();
    private static String currentArmyFileName;
    private FileHandler fileHandler = new FileHandler();
    @FXML private Stage stage;

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
     * Method that loads a new fxml file and sets it as the current scene
     * @param actionEvent event
     * @throws IOException exception
     */
    public void goToLoadArmies(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/load-armies.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);

        stage.setTitle("View your armies");
        stage.setScene(scene);
        stage.show();
    }


    public void removeSelectedArmy(ActionEvent actionEvent) throws IOException {
        ObservableList<LoadedArmyModel> allArmies, singleArmy;
        allArmies = tableViewArmy.getItems();
        singleArmy = tableViewArmy.getSelectionModel().getSelectedItems();

        for (int i = 0; i < RegistryClient.armyRegister.getArmies().size(); i++) {
            if (RegistryClient.armyRegister.getArmies().get(i).getArmyID() == singleArmy.get(0).getArmyID()){
                RegistryClient.armyRegister.remove(RegistryClient.armyRegister.getArmies().get(i));
            }
        }
        singleArmy.forEach(allArmies::remove);
        RegistryClient.armyRegister.setArmyIDs();
        RegistryClient.armyRegister.resetAndWriteArmyToFile();
    }

    /**
     * Method that loads a new fxml file and sets it as the current scene
     * @param actionEvent event
     * @throws IOException exception
     */
    public void goToViewArmies(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/view-armies.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);

        stage.setTitle("View your armies");
        stage.setScene(scene);
        stage.show();
    }
}
