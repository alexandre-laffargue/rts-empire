package com.rtsgame.models;

import com.rtsgame.models.buildings.Building;

public class Map {
    private static int width;
    private static  int height;


    private Tile[][] grid;

    private static Map instance;

    private Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Tile[width][height];
        initializeTiles();
    }

    public static Map getInstance(int width, int height) {
        if (instance == null) {
            instance = new Map(width, height);
        }
        return instance;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Tile getTile(int x, int y) {
        return grid[x][y];
    }

    private void initializeTiles() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new Tile(TerrainType.GRASS);
            }
        }
    }

    public void placeBuilding(Building building, int x, int y) {
        if (isValidPosition(x, y) && isSpaceAvailable(building, x, y)) {
            grid[x][y].setBuilding(building);
            for (int i = 0; i < building.getDimensions().getWidth(); i++) {
                for (int j = 0; j < building.getDimensions().getHeight(); j++) {    
                    grid[x + i][y + j].setArea("building");
                }
            }
        } else {
            System.out.println("Cannot place building at " + x + ", " + y);
        }
    }

    public void removeBuilding(Building building) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (grid[x][y].getBuilding() == building) {
                    grid[x][y].setBuilding(null);
                    for (int i = 0; i < building.getDimensions().getWidth(); i++) {
                        for (int j = 0; j < building.getDimensions().getHeight(); j++) {
                            grid[x + i][y + j].setArea("vide");
                        }
                    }
                }
            }
        }
    }

    // Vérifier si la position est dans les limites de la carte
    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    // Vérifier si l'espace est disponible pour placer le bâtiment
    public boolean isSpaceAvailable(Building building, int x, int y) {
        for (int i = 0; i < building.getDimensions().getWidth(); i++) {
            for (int j = 0; j < building.getDimensions().getHeight(); j++) {
                if (!isValidPosition(x + i, y + j) || grid[x + i][y + j].getArea() == "building") {
                    return false;
                }
            }
        }
        return true;
    }

}