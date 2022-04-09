package edu.ntnu.idatt2001.gui.controllers;

import edu.ntnu.idatt2001.gui.App;
import edu.ntnu.idatt2001.register.ArmyRegister;
import edu.ntnu.idatt2001.register.RegistryClient;
import edu.ntnu.idatt2001.war.Army;
import edu.ntnu.idatt2001.file.FileHandler;
import edu.ntnu.idatt2001.gui.models.UnitModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.apache.commons.io.FileUtils;

/**
 * Class that handles the interaction between the fxml file "admin-edit-tournament.fxml" and the backend.
 * This class houses all the methods and variables needed to perform the tasks
 */
public class LoadArmiesController implements Initializable {

    @FXML private TableView tableArmyPreview;
    @FXML private TableColumn colUnit;
    @FXML private TableColumn colQuantity;
    @FXML private Label lblArmyName;
    @FXML private TableColumn colIcon;
    @FXML private Label lblFileSelected;
    @FXML private Stage stage;
    private String pathLoaded;
    private FileHandler fileHandler = new FileHandler();
    ObservableList<UnitModel> observableList = FXCollections.observableArrayList();
    private String fileName;


    /**
     * opens dialog box to user, prompting user to pick a csv file containing desired army to upload
     * @param actionEvent
     */
    public void uploadArmy(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);
        pathLoaded = file.getAbsolutePath();


        fileName = file.getName();

        //displays to gui which file has been uploaded as well as file path
        lblFileSelected.setText(pathLoaded);
        lblFileSelected.setStyle("-fx-font-weight: bold;");

        fillTable();
    }

    /**
     * displays the army currently loaded from the csv file that user uploaded
     */
    public void fillTable(){
        clearTable();

        //create army by reading file, with path specified from user input
        Army army = fileHandler.readFromFile(pathLoaded);

        //sets army name above table for display
        lblArmyName.setText(army.getName());

        //adds models to tableview
        for (int i = 0; i < army.getArrayWithUnitNames().size(); i++) {
            UnitModel unitModel = new UnitModel(army.getArrayWithUnitNames().get(i), army.getNumUnitsByType(army.getArrayWithUnitNames().get(i)), getIconByType(army.getArrayWithUnitNames().get(i)));
            tableArmyPreview.getItems().add(unitModel);
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
     * removes army from the tableview, and resets the path-loaded variable and furthermore displays that no army is selected
     */
    public void removeArmy() {
        clearTable();

        pathLoaded = "";

        lblFileSelected.setText("No file selected");
    }

    /**
     * creates an army object by reading the csv file uploaded
     * @param actionEvent event
     * @throws IOException exception
     */
    public void saveArmy(ActionEvent actionEvent) throws IOException {
        //creates army
        Army army = fileHandler.readFromFile(pathLoaded);
        army.setFileName(fileName);

        //adds army to armyRegister
        RegistryClient.armyRegister.add(army);

        //loads new fxml file
        goToViewArmies(actionEvent);
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
