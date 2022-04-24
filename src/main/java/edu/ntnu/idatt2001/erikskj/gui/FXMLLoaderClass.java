package edu.ntnu.idatt2001.erikskj.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * class that handles all the methods for when the gui need to set a new fxml file as the current scene.
 * this is an improvement in comparison to having the many duplicate methods in every controller
 * therefore this class wil handle all of it, all now the controllers only need to call on the methods of this class
 * to load a new fxml file
 */
public class FXMLLoaderClass {



    private Stage stage;


    /**
     * Method that loads "load-armies" fxml file and sets it as the current scene
     * @param actionEvent event
     * @throws IOException exception
     */
    public void goToLoadArmies(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/load-armies.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);

        stage.setTitle("View your armies");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method that loads "view-armies" fxml file and sets it as the current scene
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


    /**
     * Method that loads "view-specific-army" fxml file and sets it as the current scene
     * @param actionEvent event
     * @throws IOException exception
     */
    public void goToSpecificArmy(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/view-specific-army.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);

        stage.setTitle("View your army");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method that loads "view-specific-army" fxml file and sets it as the current scene
     * @param actionEvent event
     * @throws IOException exception
     */
    public void goToCreateArmy(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/create-army.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);

        stage.setTitle("Create an army");
        stage.setScene(scene);
        stage.show();
    }
}
