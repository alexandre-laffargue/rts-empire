package com.rtsgame.views;

import com.rtsgame.models.GameManager;
import com.rtsgame.views.GameView;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.HashMap;
import java.util.Map;

public class BuildingButtons {
    private HBox buttonBox;
    private GameView gameView; // Référence à GameView

    public BuildingButtons(GameManager gameManager, GameView gameView) {
        this.gameView = gameView;
        buttonBox = new HBox();
        buttonBox.setPadding(new Insets(10));
        buttonBox.setSpacing(5);

        // créer un bouton destructeur
        Button destroyButton = new Button();
        destroyButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/Destroy.png"))));
        destroyButton.setTooltip(new Tooltip("Destroy building"));
        destroyButton.setOnAction(e -> gameView.setSelectedButton("destroy"));
        buttonBox.getChildren().add(destroyButton);

        // créer un bouton pour améliorer les bâtiments
        Button upgradeButton = new Button();
        upgradeButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/Upgrade.png"))));
        upgradeButton.setTooltip(new Tooltip("Upgrade building"));
        upgradeButton.setOnAction(e -> gameView.setSelectedButton("upgrade"));
        buttonBox.getChildren().add(upgradeButton);
    
        // créer un bouton pour sélectionner les bâtiments
        Button selectButton = new Button();
        selectButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/Select.png"))));
        selectButton.setTooltip(new Tooltip("Select building"));
        selectButton.setOnAction(e -> gameView.setSelectedButton("select"));
        buttonBox.getChildren().add(selectButton);

        // Map pour associer les noms de bâtiments à leurs icônes et infobulles

        Map<String, BuildingInfo> buildingInfo = new HashMap<>();
        buildingInfo.put("WoodenCabin", new BuildingInfo("WoodenCabin.png", "Wooden Cabin"));
        buildingInfo.put("House", new BuildingInfo("House.png", "House"));
        buildingInfo.put("ApartmentBuilding", new BuildingInfo("ApartmentBuilding.png", "Apartment Building"));
        buildingInfo.put("Farm", new BuildingInfo("Farm.png", "Farm"));
        buildingInfo.put("Quarry", new BuildingInfo("Quarry.png", "Quarry"));
        buildingInfo.put("LumberMill", new BuildingInfo("LumberMill.png", "Lumber Mill"));
        buildingInfo.put("CementPlant", new BuildingInfo("CementPlant.png", "Cement Plant"));
        buildingInfo.put("SteelMill", new BuildingInfo("SteelMill.png", "Steel Mill"));
        buildingInfo.put("ToolFactory", new BuildingInfo("ToolFactory.png", "Tool Factory"));
        

        for (String buildingType : gameManager.getAvailableBuildingTypes()) {
            try {
                BuildingInfo info = buildingInfo.get(buildingType);
                Button button = new Button();
                button.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/" + info.getIconPath()))));
                button.setTooltip(new Tooltip(info.getTooltipText()));
                button.setOnAction(e -> gameView.setSelectedButton(buildingType));
                buttonBox.getChildren().add(button);
            } catch (NullPointerException e) {
                System.err.println("Erreur : informations manquantes pour le bâtiment " + buildingType);
            }
        }
    }

    public HBox getButtonBox() {
        return buttonBox;
    }

}