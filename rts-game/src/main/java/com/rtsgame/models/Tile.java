package com.rtsgame.models;

import com.rtsgame.models.buildings.Building;

public class Tile {
    private TerrainType terrainType;
    private Building building;
    private String area;

    public Tile(TerrainType terrainType) {
        this.terrainType = terrainType;
        this.building = null;
        area = "vide";
    }

    public TerrainType getTerrainType() {
        return terrainType;
    }

    public void setTerrainType(TerrainType terrainType) {
        this.terrainType = terrainType;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

}