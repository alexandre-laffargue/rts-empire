package com.rtsgame.views;

import com.rtsgame.controllers.GameController;
import com.rtsgame.models.GameManager;
import com.rtsgame.models.buildings.Building;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameView extends Application {
    private GameController gameController;
    private GameManager gameManager;
    private MapView mapView;
    private ResourcesBar resourcesBar;
    private String selectedBuildingType;
    private String selectedButton;
    private int selectedX;
    private int selectedY;

    public GameView(GameController gameController, GameManager gameManager) {
        this.gameController = gameController;
        this.gameManager = gameManager;
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        resourcesBar = new ResourcesBar();
        root.setRight(resourcesBar);

        mapView = new MapView(this);
        root.setCenter(mapView);

        createBuildingTypeButtons(root);

        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("RTS Game");
        primaryStage.show();

        updateMap();
    }

    private void createBuildingTypeButtons(BorderPane root) {
        BuildingButtons buildingButtons = new BuildingButtons(gameManager, this);
        root.setBottom(buildingButtons.getButtonBox());
    }

    public void updateMap() {
        mapView.updateMap(gameManager.getMap());
    }

    public void updateResources() {
        resourcesBar.updateResources(gameManager.getResources(), gameManager.getCurrentDay());
    }


    public void setSelectedBuildingType(String buildingType) {
        selectedBuildingType = buildingType;
    }

    public String getSelectedBuildingType() {
        return selectedBuildingType;
    }
    
    public void setSelectedButton(String button) {
        selectedButton = button;
    }

    public String getSelectedButton() {
        return selectedButton;
    }

    public Building getSelectedBuilding() {
        if (selectedX != -1 && selectedY != -1) {
            return gameManager.getMap().getTile(selectedX, selectedY).getBuilding();
        } else {
            return null;
        }
    }



    // ### Getters and Setters ###


    public void setSelectedX(int x) {
        selectedX = x;
    }

    public int getSelectedX() {
        return selectedX;
    }

    public void setSelectedY(int y) {
        selectedY = y;
    }

    public int getSelectedY() {
        return selectedY;
    }
    
    public GameManager getGameManager() {
        return gameManager;
    }

    public GameController getGameController() {
        return gameController;
    }

    
}