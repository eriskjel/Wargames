package edu.ntnu.idatt2001.gui.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Class that handles the interaction between the fxml file "admin-edit-tournament.fxml" and the backend.
 * This class houses all the methods and variables needed to perform the tasks
 */
public class LoadArmiesController {


    /**
     * stage of application
     */
    private Stage stage;

    public void uploadeArmy(ActionEvent actionEvent) {
        //TODO: add upload code
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
