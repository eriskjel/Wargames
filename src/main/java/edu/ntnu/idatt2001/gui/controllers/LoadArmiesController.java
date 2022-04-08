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
    private ArrayList<Army> armies = new ArrayList<>();
    private String pathLoaded;
    private FileHandler fileHandler = new FileHandler();
    ObservableList<UnitModel> observableList = FXCollections.observableArrayList();
    @FXML private Label lblFileSelected;
    private String fileName;
    /**
     * stage of application
     */
    private Stage stage;

    public void uploadArmy(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        //fileChooser.showOpenDialog(stage);



        File file = fileChooser.showOpenDialog(stage);
        pathLoaded = file.getAbsolutePath();
        fileName = file.getName();

        lblFileSelected.setText(pathLoaded);
        lblFileSelected.setStyle("-fx-font-weight: bold;");

        fillTable();
    }

    public void fillTable(){
        clearTable();

        //create army by reading file, with path specified from user input
        Army army = fileHandler.readFromFile(pathLoaded);

        //sets army name above table for display
        lblArmyName.setText(army.getName());

        //ImageView infantryIcon = new ImageView(new Image(this.getClass().getResourceAsStream("src/main/resources/img/p4BiuhTsDJGEqF8-Black-Sword-PNG-Transparent-Image.png")));
        //ImageView rangedIcon = new ImageView(new Image(this.getClass().getResourceAsStream("src/main/resources/img/Daco_3282610.png")));
        //ImageView cavalryIcon = new ImageView(new Image(this.getClass().getResourceAsStream("src/main/resources/img/Daco_3282610.png")));
        //ImageView commanderIcon = new ImageView(new Image(this.getClass().getResourceAsStream("src/main/resources/img/pngfind.com-fitness-icon-png-1646611.png")));

        for (int i = 0; i < army.getArrayWithUnitNames().size(); i++) {
            UnitModel unitModel = new UnitModel(army.getArrayWithUnitNames().get(i), army.getNumUnitsByType(army.getArrayWithUnitNames().get(i)), getIconByType(army.getArrayWithUnitNames().get(i)));
            tableArmyPreview.getItems().add(unitModel);
        }
    }

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
                System.err.println("Something went wrong.");
                return null;
        }
    }

    /**
     * clears table
     */
    public void clearTable(){
        tableArmyPreview.getItems().clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //this.colIcon.setPrefWidth(80);
        this.colIcon.setCellValueFactory(new PropertyValueFactory<>("Icon"));
        this.colUnit.setCellValueFactory(new PropertyValueFactory<>("Unit"));
        this.colQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        this.tableArmyPreview.setItems(observableList);
    }

    public void removeArmy(ActionEvent actionEvent) {
        clearTable();

        pathLoaded = "";

        lblFileSelected.setText("No file selected");
    }

    public void saveArmy(ActionEvent actionEvent) throws IOException {

        Army army = fileHandler.readFromFile(pathLoaded);
        army.setFileName(fileName);
        RegistryClient.armyRegister.add(army);
        goToViewArmies(actionEvent);
    }


    /**
     * Method that loads a new fxml file and sets it as the current scene
     * @param actionEvent event
     * @throws IOException
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
