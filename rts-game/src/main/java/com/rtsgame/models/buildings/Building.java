package com.rtsgame.models.buildings;

import com.rtsgame.models.resources.Resource;


public interface Building {
    String getName();
    int getInhabitants();
    int getWorkers();
    Resource[] getConstructionCost();
    int getConstructionTime();
    Dimensions getDimensions();
    Resource[] getResourceConsumption();
    Resource[] getResourceProduction();
    void build();
    void addWorker(int numWorkers);
    int getRemainingConstructionTime();
    void setRemainingConstructionTime(int remainingConstructionTime);

}
