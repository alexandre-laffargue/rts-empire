package com.rtsgame.models.buildings;

import com.rtsgame.models.resources.Resource;

public abstract class BuildingDecorator implements Building {
    protected Building building;

    public BuildingDecorator(Building building) {
        this.building = building;
    }

    @Override
    public String getName() {
        return building.getName();
    }

    public int getInhabitants() {
        return building.getInhabitants();
    }

    @Override
    public int getWorkers() {
        return building.getWorkers();
    }

    @Override
    public Resource[] getConstructionCost() {
        return building.getConstructionCost();
    }

    @Override
    public int getConstructionTime() {
        return building.getConstructionTime();
    }

    @Override
    public Dimensions getDimensions() {
        return building.getDimensions();
    }

    @Override
    public Resource[] getResourceConsumption() {
        return building.getResourceConsumption();
    }

    @Override
    public Resource[] getResourceProduction() {
        return building.getResourceProduction();
    }

    @Override
    public void build() {
        building.build();
    }

    @Override
    public void addWorker(int numWorkers) {
        building.addWorker(numWorkers);
    }

    @Override
    public int getRemainingConstructionTime() {
        return building.getRemainingConstructionTime();
    }

    @Override
    public void setRemainingConstructionTime(int remainingConstructionTime) {
        building.setRemainingConstructionTime(remainingConstructionTime);
    }

    
}