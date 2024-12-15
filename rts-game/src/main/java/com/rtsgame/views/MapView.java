package com.rtsgame.views;

import com.rtsgame.models.buildings.Building;
import com.rtsgame.models.Map;
import com.rtsgame.models.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MapView extends GridPane {
    private GameView gameView;

    public MapView(GameView gameView) {
        this.gameView = gameView;
    }

    private String getBuildingImagePath(Building building) {
        if (building.getRemainingConstructionTime() > 0) {
            return "Construction.png";
        }
        switch (building.getName()) {
            case "WoodenCabin": return "WoodenCabin.png";
            case "House": return "House.png";
            case "ApartmentBuilding": return "ApartmentBuilding.png";
            case "Farm": return "Farm.png";
            case "Quarry": return "Quarry.png";
            case "LumberMill": return "LumberMill.png";
            case "CementPlant": return "CementPlant.png";
            case "SteelMill": return "SteelMill.png";
            case "ToolFactory": return "ToolFactory.png";
            default: return ""; 
        }
    }
    public void updateMap(Map map) {
        getChildren().clear();
        Pane mapPane = new Pane();
        mapPane.setPrefSize(map.getWidth() * 32, map.getHeight() * 32);
    
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Tile tile = map.getTile(x, y);
                Pane tilePane = new Pane();
                tilePane.setOnMouseClicked(e -> handleTileClick(e));
                if (!map.getTile(x, y).getArea().equals("building")) {
                    switch (tile.getTerrainType()) {
                        case GRASS:
                            addTileImage(tilePane, "grass.png", 32, 32);
                            break;
                        case WATER:
                            tilePane.setStyle("-fx-background-color: blue;");
                            break;
                        case MOUNTAIN:
                            tilePane.setStyle("-fx-background-color: gray;");
                            break;
                    }
                    tilePane.setLayoutX(x * 32);
                    tilePane.setLayoutY(y * 32);
                    mapPane.getChildren().add(tilePane);
                } 
                Building building = tile.getBuilding();
                if (building != null) {
                    Pane buildingPane = new Pane();
                    addTileImage(
                        buildingPane,
                        getBuildingImagePath(building),
                        building.getDimensions().getWidth() * 32,
                        building.getDimensions().getHeight() * 32
                    );
                    buildingPane.setOnMouseClicked(e -> handleBuildingClick(e,building));

                    buildingPane.setLayoutX(x * 32);
                    buildingPane.setLayoutY(y * 32);
    
                    mapPane.getChildren().add(buildingPane);
                }
            }
        }
        getChildren().add(mapPane);
    }

    
    private void handleTileClick(MouseEvent event) {
        double mouseX = event.getSceneX();
        double mouseY = event.getSceneY();
    
        // Calculer la tuile cliquée
        int selectedX = (int) (mouseX / 32);
        int selectedY = (int) (mouseY / 32);
    
        gameView.setSelectedX(selectedX);
        gameView.setSelectedY(selectedY);
    
        Building building = gameView.getGameManager().getMap().getTile(selectedX, selectedY).getBuilding();
        gameView.getGameController().setSelectedTile(selectedX, selectedY, building);
    }

    private void handleBuildingClick(MouseEvent event, Building building) {
        double mouseX = event.getSceneX();
        double mouseY = event.getSceneY();
    
        int selectedX = (int) (mouseX / 32);
        int selectedY = (int) (mouseY / 32);
    
        gameView.setSelectedX(selectedX);
        gameView.setSelectedY(selectedY);

        gameView.getGameController().setSelectedTile(selectedX, selectedY, building);

    }

    private void addTileImage(Pane tilePane, String imagePath, int width, int height) {
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/images/" + imagePath)));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        tilePane.getChildren().add(imageView);
    }
}