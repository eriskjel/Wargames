package edu.ntnu.idatt2001.erikskj.gui.view;

import edu.ntnu.idatt2001.erikskj.file.FileHandler;
import edu.ntnu.idatt2001.erikskj.register.RegistryClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


/**
 * Class with main function of application. Loads fxml file, sets the title and shows the scene
 */
public class App extends Application {

    /**
     * starts the application
     * @param stage stage
     * @throws IOException exception
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/create-army.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);
        stage.setTitle("Wargames");
        stage.getIcons().add(new Image("file:src/main/resources/img/swords.png"));
        stage.setScene(scene);
        stage.show();
        new FileHandler().initDirectory();
        RegistryClient.armyRegister.readArmiesFromDir();
    }


    public static void main(String[] args) {
        launch();
    }


}