package edu.ntnu.idatt2001.gui.controllers;

import edu.ntnu.idatt2001.Army;
import edu.ntnu.idatt2001.file.FileHandler;
import edu.ntnu.idatt2001.gui.models.UnitModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Class that handles the interaction between the fxml file "admin-edit-tournament.fxml" and the backend.
 * This class houses all the methods and variables needed to perform the tasks
 */
public class LoadArmiesController implements Initializable {

    public TableView tableArmyPreview;
    public TableColumn colUnit;
    public TableColumn colQuantity;
    private ArrayList<Army> armies = new ArrayList<>();
    private String pathLoaded;
    private FileHandler fileHandler = new FileHandler();
    ObservableList<UnitModel> observableList = FXCollections.observableArrayList();



    public Label lblFileSelected;
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

        lblFileSelected.setText(pathLoaded);

        fillTable();
    }

    public void fillTable(){
        Army army = fileHandler.readFromFile(pathLoaded);


        /*
        for (int i = 0; i < army.getNumOfDifferentUnits(); i++) {
            UnitModel unitModel = new UnitModel(army);
        }

         */
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.colUnit.setCellValueFactory(new PropertyValueFactory<>("Date"));
        this.colQuantity.setCellValueFactory(new PropertyValueFactory<>("Name"));
        this.tableArmyPreview.setItems(observableList);
        //refreshTable();

    }


    /**
     * Method that loads a new fxml file and sets it as the current scene
     * @param actionEvent event
     * @throws IOException
     */
    /*
    @FXML
    public void goToAddTournament(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("admin-add-tournament.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);

        //this.addTournamentBtnTournamentHub.setStyle("-fx-background-color: #2B78E4 !important;");
        //this.addTournamentBtnTournamentHub.setStyle("-fx-text-fill: red");
        stage.setTitle("Add tournament!");
        stage.setScene(scene);
        stage.show();
    }

     */

}
