package edu.ntnu.idatt2001.gui.controllers;

import edu.ntnu.idatt2001.file.FileHandler;
import edu.ntnu.idatt2001.gui.App;
import edu.ntnu.idatt2001.gui.models.LoadedArmyModel;
import edu.ntnu.idatt2001.gui.models.UnitModel;
import edu.ntnu.idatt2001.register.ArmyRegister;
import edu.ntnu.idatt2001.register.RegistryClient;
import edu.ntnu.idatt2001.war.Army;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Class that handles the interaction between the fxml file "admin-edit-tournament.fxml" and the backend.
 * This class houses all the methods and variables needed to perform the tasks
 */
public class ViewArmiesController implements Initializable {

    public Label lblArmyName;
    public TableView tableLoadedArmies;
    public TableColumn colArmyName;
    public TableColumn colTotalUnits;
    public TableColumn colTotalHealth;
    public TableColumn colFileName;
    private FileHandler fileHandler = new FileHandler();
    ObservableList<UnitModel> observableList = FXCollections.observableArrayList();
    /**
     * stage of application
     */
    private Stage stage;

    /**
     * Fills table when fxml table is loaded
     * @param url url
     * @param resourceBundle bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clearTable();
        this.colArmyName.setCellValueFactory(new PropertyValueFactory<>("ArmyName"));
        this.colTotalUnits.setCellValueFactory(new PropertyValueFactory<>("TotalUnits"));
        this.colTotalHealth.setCellValueFactory(new PropertyValueFactory<>("TotalHealth"));
        this.colFileName.setCellValueFactory(new PropertyValueFactory<>("FileName"));
        this.tableLoadedArmies.setItems(observableList);
        fillTable();
    }

    /**
     *
     */
    public void fillTable(){
        clearTable();


        for (int i = 0; i < RegistryClient.armyRegister.getArmiesSize(); i++) {
            LoadedArmyModel loadedArmyModel = new LoadedArmyModel(
                    RegistryClient.armyRegister.getArmies().get(i).getName(),
                    RegistryClient.armyRegister.getArmies().get(i).getUnits().size(),
                    RegistryClient.armyRegister.getArmies().get(i).getSumHealth(),
                    RegistryClient.armyRegister.getArmies().get(i).getFileName());
            tableLoadedArmies.getItems().add(loadedArmyModel);
        }

    }

    /**
     * clears table
     */
    public void clearTable(){
        tableLoadedArmies.getItems().clear();
    }

    public void removeAllArmies(ActionEvent actionEvent) throws IOException {
        RegistryClient.armyRegister.removeAll();
        File file = new File("src/main/resources/armyRegister");
        FileUtils.cleanDirectory(file);
        clearTable();
    }

    /**
     * Method that loads a new fxml file and sets it as the current scene
     * @param actionEvent event
     * @throws IOException
     */
    public void goToLoadArmies(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/load-armies.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);

        stage.setTitle("View your armies");
        stage.setScene(scene);
        stage.show();
    }


    public void removeSelectedArmy(ActionEvent actionEvent) {
        //
    }
}
