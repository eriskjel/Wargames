module Wargames {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires java.desktop;
    requires java.logging;

    opens edu.ntnu.idatt2001.gui to javafx.fxml;

    exports edu.ntnu.idatt2001.gui;
    exports edu.ntnu.idatt2001.gui.controllers;
    opens edu.ntnu.idatt2001.gui.controllers to javafx.fxml;
}