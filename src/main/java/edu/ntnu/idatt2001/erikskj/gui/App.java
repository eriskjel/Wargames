package edu.ntnu.idatt2001.erikskj.gui;

import edu.ntnu.idatt2001.erikskj.register.RegistryClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/load-armies.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);
        stage.setTitle("Wargames");
        stage.getIcons().add(new Image("file:src/main/resources/img/swords.png"));
        stage.setScene(scene);
        stage.show();
        RegistryClient.armyRegister.removeAll();
    }


    public static void main(String[] args) {
        launch();
    }


}