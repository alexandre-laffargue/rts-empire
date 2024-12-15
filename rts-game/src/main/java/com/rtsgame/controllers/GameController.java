package com.rtsgame.controllers;

import com.rtsgame.models.buildings.Building;
import com.rtsgame.models.GameManager;
import com.rtsgame.views.GameView;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class GameController {

    private GameManager gameManager;
    private GameView gameView;
    private int selectedX;
    private int selectedY;
    private Building selectedBuilding;

    public GameController() {
        this.gameManager = GameManager.getInstance();
        gameManager.setGameController(this);
        this.gameView = new GameView(this, gameManager);
    }

    public GameView getGameView() {
        return gameView;
    }

    public void startGame() {
        gameManager.startGame();
    }

    public void handleBuildAction(ActionEvent event) {
        String buildingType = gameView.getSelectedBuildingType();
        System.out.println("Build action triggered :" + buildingType);

        int x = gameView.getSelectedX();
        int y = gameView.getSelectedY();

        gameManager.constructBuilding(buildingType, x, y);
        gameView.updateMap();
    }

    public void handleRemoveAction(MouseEvent event) {
        Building buildingToRemove = gameView.getSelectedBuilding();
        gameManager.removeBuilding(buildingToRemove);

        gameView.updateMap();
    }

    public void handleUpgradeBuildingAction(ActionEvent event) {
        Building buildingToUpgrade = gameView.getSelectedBuilding();
        gameManager.upgradeBuilding(buildingToUpgrade);

        gameView.updateMap();
    }

    
    public void nextTurn() {
        System.out.println("Next turn...");
        gameManager.nextTurn();
        Platform.runLater(() -> {
            gameView.updateResources();
            gameView.updateMap();
        });}

    public void setSelectedTile(int x, int y, Building building) {
        this.selectedX = x;
        this.selectedY = y;
        this.selectedBuilding = building;

        System.out.println("Building on tile: " + (building != null ? building.getName() : "None"));

        System.out.println("Selected button: " + gameView.getSelectedButton());
        
        // si un bouton est sélectionné, gérer l'action correspondante
        if (gameView.getSelectedButton() != null) {
            switch (gameView.getSelectedButton()) {
                case "destroy":
                    handleRemoveAction(null);
                    break;
                case "upgrade":
                    handleUpgradeBuildingAction(null);
                    break;
                case "select":
                    handleUpgradeBuildingAction(null);
                    break;
                case "WoodenCabin":
                    gameView.setSelectedBuildingType("WoodenCabin");
                    handleBuildAction(null);
                    break;
                case "House":
                    gameView.setSelectedBuildingType("House");
                    handleBuildAction(null);
                    break;
                case "ApartmentBuilding":
                    gameView.setSelectedBuildingType("ApartmentBuilding");
                    handleBuildAction(null);
                    break;
                case "Farm":
                    gameView.setSelectedBuildingType("Farm");
                    handleBuildAction(null);
                    break;
                case "Quarry":
                    gameView.setSelectedBuildingType("Quarry");
                    handleBuildAction(null);
                    break;
                case "LumberMill":
                    gameView.setSelectedBuildingType("LumberMill");
                    handleBuildAction(null);
                    break;
                case "CementPlant":
                    gameView.setSelectedBuildingType("CementPlant");
                    handleBuildAction(null);
                    break;
                case "SteelMill":
                    gameView.setSelectedBuildingType("SteelMill");
                    handleBuildAction(null);
                    break;
                case "ToolFactory":
                    gameView.setSelectedBuildingType("ToolFactory");
                    handleBuildAction(null);
                    break;
                default:
                    System.out.println("Unknown button: " + gameView.getSelectedButton());
                    break;
            }
        }
    }
}