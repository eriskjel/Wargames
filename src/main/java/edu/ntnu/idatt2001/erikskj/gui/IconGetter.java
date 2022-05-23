package edu.ntnu.idatt2001.erikskj.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * class that returns icons based on unit type
 */
public class IconGetter {
    /**
     * returns the correct icon based on the unit type
     * @param unitType unit type
     * @return ImageView of icon based on the unittype
     */
    public ImageView getIconByType(String unitType){
        switch (unitType) {
            case "Infantry":
                return new ImageView(new Image(this.getClass().getResourceAsStream("/img/infantry.png")));
            case "Ranged":
                return new ImageView(new Image(this.getClass().getResourceAsStream("/img/ranged.png")));
            case "Cavalry":
                return new ImageView(new Image(this.getClass().getResourceAsStream("/img/cavalry.png")));
            case "Commander":
                return new ImageView(new Image(this.getClass().getResourceAsStream("/img/commander.png")));
            default:
                System.err.println("Something went wrong when rendering icon to unit.");
                return null;
        }
    }
}
