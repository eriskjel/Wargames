module Wargames {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires java.desktop;
    requires java.logging;
    requires org.apache.commons.io;

    opens edu.ntnu.idatt2001.erikskj.gui to javafx.fxml;
    exports edu.ntnu.idatt2001.erikskj.gui;

    opens edu.ntnu.idatt2001.erikskj.gui.models to javafx.fxml;
    exports edu.ntnu.idatt2001.erikskj.gui.models;

    exports edu.ntnu.idatt2001.erikskj.gui.controllers;
    opens edu.ntnu.idatt2001.erikskj.gui.controllers to javafx.fxml;

    exports edu.ntnu.idatt2001.erikskj.war;
    opens edu.ntnu.idatt2001.erikskj.war to javafx.fxml;

    exports edu.ntnu.idatt2001.erikskj.units;
    opens edu.ntnu.idatt2001.erikskj.units to javafx.fxml;

    exports edu.ntnu.idatt2001.erikskj.enums;
    opens edu.ntnu.idatt2001.erikskj.enums to javafx.fxml;

    exports edu.ntnu.idatt2001.erikskj.gui.view;
    opens edu.ntnu.idatt2001.erikskj.gui.view to javafx.fxml;
}