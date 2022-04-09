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
import java.util.ResourceBundle;

/**
 * Class that handles the interaction between the fxml file "admin-edit-tournament.fxml" and the backend.
 * This class houses all the methods and variables needed to perform the tasks
 */
public class ViewArmiesController implements Initializable {

    public TableView tableLoadedArmies;
    public TableColumn colArmyName;
    public TableColumn colTotalUnits;
    public TableColumn colTotalHealth;
    public TableColumn colFileName;
    ObservableList<UnitModel> observableList = FXCollections.observableArrayList();

    /**
     * method that runs when fxml file is loaded
     * makes sure that table is filled at startup
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
     * fills table with every army loaded in application
     */
    public void fillTable(){
        //clears table
        clearTable();

        //loops through army register and fills table with LoadedArmyModels
        for (int i = 0; i < RegistryClient.armyRegister.getArmies().size(); i++) {
            LoadedArmyModel loadedArmyModel = new LoadedArmyModel(
                    RegistryClient.armyRegister.getArmies().get(i).getName(),
                    RegistryClient.armyRegister.getArmies().get(i).getUnits().size(),
                    RegistryClient.armyRegister.getArmies().get(i).getSumHealth(),
                    RegistryClient.armyRegister.getArmies().get(i).getFileName(),
                    RegistryClient.armyRegister.getArmies().get(i).getArmyID());
            tableLoadedArmies.getItems().add(loadedArmyModel);
        }

    }

    /**
     * clears gui table
     */
    public void clearTable(){
        tableLoadedArmies.getItems().clear();
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

    /**
     * removes selected army from gui table as well as from the army register
     * @throws IOException exception
     */
    public void removeSelectedArmy() throws IOException {
        ObservableList<LoadedArmyModel> allArmies, singleArmy;
        allArmies = tableLoadedArmies.getItems();
        singleArmy = tableLoadedArmies.getSelectionModel().getSelectedItems();

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
     * gets the armyID from the selected army in the gui. then sends army file location to controller which will display said army contents.
     * then method loads the new fxml file which will display the contents of the army
     * @param actionEvent event
     * @throws IOException exception
     */
    public void viewSelectedArmy(ActionEvent actionEvent) throws IOException {
        ObservableList<LoadedArmyModel> armyModel = tableLoadedArmies.getSelectionModel().getSelectedItems();
        int armyID = armyModel.get(0).getArmyID();

        ViewSpecificArmyController.setCurrentArmyFileName(RegistryClient.armyRegister.getArmyByID(armyID).getFilePath());

        //loads new fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/view-specific-army.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1300, 680);

        stage.setTitle("View your armies");
        stage.setScene(scene);
        stage.show();

    }
}
