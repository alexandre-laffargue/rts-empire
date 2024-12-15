package com.rtsgame.models.buildings;

import com.rtsgame.models.resources.Resource;


public class SimpleBuilding implements Building {
    private String type; // "House", "Farm", "Quarry", etc.
    private int inhabitants;
    private int workers;
    private Resource[] resourceConstruction;
    private int constructionTime;
    private Dimensions tileSize;
    private Resource[] resourceConsumption;
    private Resource[] resourceProduction;
    private int maxWorkers;
    private int remainingConstructionTime;


    public SimpleBuilding(String type, Dimensions tileSize, int inhabitants, int workers,
                          Resource[] resourceConstruction, Resource[] resourceConsumption,
                          Resource[] resourceProduction, int constructionTime) {
        this.type = type;
        this.tileSize = tileSize;
        this.inhabitants = inhabitants;
        this.maxWorkers = workers;
        this.resourceConstruction = resourceConstruction;
        this.resourceConsumption = resourceConsumption;
        this.resourceProduction = resourceProduction;
        this.constructionTime = constructionTime;
        this.workers = inhabitants;
        remainingConstructionTime = constructionTime;
    }

    @Override
    public String getName() {
        return type;
    }

    @Override
    public int getInhabitants() {
        return inhabitants;
    }

    @Override
    public int getWorkers() {
        return workers;
    }

    @Override
    public Resource[] getConstructionCost() {
        return resourceConstruction;
    }

    @Override
    public int getConstructionTime() {
        return constructionTime;
    }

    @Override
    public Dimensions getDimensions() {
        return tileSize;
    }

    @Override
    public Resource[] getResourceConsumption() {
        return resourceConsumption;
    }

    @Override
    public Resource[] getResourceProduction() {
        return resourceProduction;
    }


    @Override
    public void build() {
        System.out.println("Building a simple " + type + "...");
    }

    @Override
    public void addWorker(int numWorkers) {
        int newWorkers = this.workers + numWorkers;
        if (newWorkers > this.maxWorkers) {
            this.workers = this.maxWorkers;
        } else if (newWorkers < 0) {
            this.workers = 0;
        } else {
            this.workers = newWorkers;
        }   
    }

    public int getRemainingConstructionTime() {
        return remainingConstructionTime;
    }

    public void setRemainingConstructionTime(int remainingConstructionTime) {
        this.remainingConstructionTime = remainingConstructionTime;
    }

}