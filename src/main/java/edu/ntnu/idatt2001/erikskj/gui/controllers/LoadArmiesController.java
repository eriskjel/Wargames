package edu.ntnu.idatt2001.erikskj.gui.controllers;

import edu.ntnu.idatt2001.erikskj.file.FileHandler;
import edu.ntnu.idatt2001.erikskj.gui.IconGetter;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class that handles the interaction between the fxml file "load-armies" and the backend.
 * This class houses all the methods and variables needed to perform the tasks
 */
public class LoadArmiesController implements Initializable {

    @FXML private TableView tableArmyPreview;
    @FXML private TableColumn colUnit;
    @FXML private TableColumn colQuantity;
    @FXML private TableColumn colIcon;
    @FXML private Label lblArmyName;
    @FXML private Label lblFileSelected;
    @FXML private final ObservableList<UnitModel> observableList = FXCollections.observableArrayList();
    @FXML private Stage stage;
    private final FileHandler fileHandler = new FileHandler();
    private File uploadedArmyFile;


    /**
     * opens dialog box to user, prompting user to pick a csv file containing desired army to upload
     * @param actionEvent
     */
    public void uploadArmy(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);


        if (!isFileCSV(file)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning! Cannot upload file!");
            alert.setHeaderText(null);
            alert.setContentText("The file you have chosen is not a .CSV file! Please upload a new one.");
            alert.showAndWait();
        }
        else{
            uploadedArmyFile = file;

            //displays to gui which file has been uploaded as well as file path
            lblFileSelected.setText(uploadedArmyFile.getAbsolutePath());
            lblFileSelected.setStyle("-fx-font-weight: bold;");

            fillTable();
        }
    }

    public boolean isFileCSV(File file){
        return FilenameUtils.getExtension(file.getName()).equals("csv");
    }

    /**
     * displays the army currently loaded from the csv file that user uploaded
     */
    public void fillTable(){
        clearTable();
        IconGetter iconGetter = new IconGetter();

        //create army by reading file, with path specified from user input
        Army army = fileHandler.readFromFile(uploadedArmyFile);

        //sets army name above table for display
        lblArmyName.setText(army.getName());

        //adds models to tableview
        for (int i = 0; i < army.getArrayWithUnitNames().size(); i++) {
            UnitModel unitModel = new UnitModel(army.getArrayWithUnitNames().get(i), army.getNumUnitsByType(army.getArrayWithUnitNames().get(i)), iconGetter.getIconByType(army.getArrayWithUnitNames().get(i)));
            tableArmyPreview.getItems().add(unitModel);
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
        lblFileSelected.setText("No file selected");
    }

    /**
     * creates an army object by reading the csv file uploaded
     * @param actionEvent event
     * @throws IOException exception
     */
    public void saveArmy(ActionEvent actionEvent) throws IOException {
        //creates army
        Army army = fileHandler.readFromFile(uploadedArmyFile);
        army.setIsUploaded(true);
        //army.setFileName(fileName);

        //adds army to armyRegister
        RegistryClient.armyRegister.add(army);

        //loads new fxml file
        goToViewArmies(actionEvent);
    }

    /**
     * calls on the FXMLLoader class to load a new fxml file
     * this particular loads the view-armies file
     * @param event event
     * @throws IOException exception
     */
    public void goToViewArmies(ActionEvent event) throws IOException {
        RegistryClient.fxmlLoaderClass.goToViewArmies(event);
    }


    /**
     * calls on the fxmlloader class to load a new fxml file
     * this particular loads the create-army file
     * @param actionEvent
     * @throws IOException
     */
    public void goToCreateArmy(ActionEvent actionEvent) throws IOException {
        RegistryClient.fxmlLoaderClass.goToCreateArmy(actionEvent);
    }

    /**
     * calls on the FXMLLoader class to load a new fxml file
     * @param event event
     * @throws IOException exception
     */
    public void goToBattle(ActionEvent event) throws IOException {
        RegistryClient.fxmlLoaderClass.goToBattle(event);
    }
}
