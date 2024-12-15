package com.rtsgame.models;

import com.rtsgame.controllers.GameController;
import com.rtsgame.models.buildings.Building;
import com.rtsgame.models.buildings.BuildingFactory;
import com.rtsgame.models.resources.Resource;
import com.rtsgame.models.resources.ResourceType;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private List<Building> buildings;
    private Map map; // Carte du jeu
    private int currentDay; // Jour actuel
    private Resource[] resources; // Tableau pour stocker les ressources globales du joueur
    private GameController gameController; // Contrôleur du jeu

    private Thread gameLoopThread; 

    private static GameManager instance;


    private GameManager() {
        buildings = new ArrayList<>();
        map = Map.getInstance(30, 20);
        currentDay = 0;
        resources = initializeResources();
        gameController = null;

        // ajoute un woodencabin
        Building building = BuildingFactory.createBuilding("WoodenCabin");
        buildings.add(building);
        map.placeBuilding(building, 10, 10);
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    private Resource[] initializeResources() {
        return new Resource[] {
                new Resource(ResourceType.FOOD, 100),
                new Resource(ResourceType.WOOD, 50),
                new Resource(ResourceType.STONE, 0),
                new Resource(ResourceType.COAL, 0),
                new Resource(ResourceType.IRON, 0),
                new Resource(ResourceType.STEEL, 0),
                new Resource(ResourceType.CEMENT, 0),
                new Resource(ResourceType.LUMBER, 0),
                new Resource(ResourceType.TOOLS, 0)
        };
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void startGame() {
        gameLoopThread = new Thread(this::gameLoop);
        gameLoopThread.start();
    }

    public void endGame() {
        gameLoopThread.interrupt();
    }

    private void gameLoop() {
        try {
            while (!Thread.currentThread().isInterrupted()) {

                gameController.nextTurn();
                Thread.sleep(2000); // Pause de 2 secondes
            }
        } catch (InterruptedException e) {
            System.out.println("Game loop interrupted.");
        }
    }

    private Boolean isVictory() {
        for (Resource resource : resources) {
            if (resource.getType() == ResourceType.STEEL && resource.getAmount() >= 100) {
                return true;
            }
        }
        return false;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public void nextTurn() {
        System.out.println("Day " + currentDay);
        currentDay++;
        int totalFoodConsumption = 0;
        for (Building building : buildings) {
            //System.out.println("Building: " + building.getName());
            if (building.getRemainingConstructionTime() > 0) {
                //System.out.println("Construction time remaining: " + building.getRemainingConstructionTime());
                building.setRemainingConstructionTime(building.getRemainingConstructionTime() - 1);
                continue;
            }
            Resource[] production = building.getResourceProduction();
            if (hasResources (production)) {
                addResources(production);
                for (Resource resource : production) {
                    //System.out.println("Production: " + resource.getAmount() + " " + resource.getType());
                }
            }

            Resource[] consumption = building.getResourceConsumption();
            if (hasResources(consumption)) {
                consumeResources(consumption);
                for (Resource resource : consumption) {
                    //System.out.println("Consumption: " + resource.getAmount() + " " + resource.getType());
                }
            }
            totalFoodConsumption += building.getInhabitants();
        }

        // Consommation de nourriture par les habitants
        totalFoodConsumption *= 1;
        consumeFoodForInhabitants(totalFoodConsumption);
        //System.out.println("Total food consumption: " + totalFoodConsumption);  

        // Vérifier si le joueur a gagné
        if (isVictory()) {
            System.out.println("Victory!");
            endGame();
        }
    }

    // Vérifier si le tableau contient des ressources
    private boolean hasResources(Resource[] resources) {
        for (Resource resource : resources) {
            if (resource.getAmount() > 0) {
                return true;
            }
        }
        return false;
    }

    public void removeBuilding(Building building) {
        System.out.println("Removing building...");
        if (buildings.remove(building)) {
            map.removeBuilding(building); 
            //System.out.println(building.getName() + " removed.");
        } else {
            System.out.println("Building not found.");
        }
    }


    public void constructBuilding(String buildingType, int x, int y) {
        Building building = BuildingFactory.createBuilding(buildingType);
        if (hasEnoughResources(building.getConstructionCost())) {
            buildings.add(building);
            map.placeBuilding(building, x, y);
            consumeResources(building.getConstructionCost());
        } else {
            System.out.println("Not enough resources to build " + buildingType);
        }
    }


    public void upgradeBuilding(Building building) {
        // Vérifier si le joueur a suffisamment de ressources pour l'amélioration
      //  if (hasEnoughResources(building.getUpgradeCost())) {
            // Déduire le coût de l'amélioration des ressources du joueur
      //      consumeResources(building.getUpgradeCost());
            // Améliorer le bâtiment
     //       building.upgrade();
    ///    } else {
            // Gérer le cas où le joueur n'a pas assez de ressources
      //      System.out.println("Not enough resources to upgrade " + building.getName());
    //    }
    }

    // Vérifier si le joueur a suffisamment de ressources pour un coût donné
    private boolean hasEnoughResources(Resource[] cost) {
        for (Resource resourceCost : cost) {
            ResourceType type = resourceCost.getType();
            int amountNeeded = resourceCost.getAmount();
            int currentAmount = getResourceAmount(type);
            if (currentAmount < amountNeeded) {
                return false;
            }
        }
        return true;
    }

    // Obtenir la quantité d'une ressource spécifique
    private int getResourceAmount(ResourceType type) {
        for (Resource resource : resources) {
            if (resource.getType() == type) {
                return resource.getAmount();
            }
        }
        return 0;
    }

    // Ajouter des ressources aux ressources globales du joueur
    private void addResources(Resource[] resourcesToAdd) {
        for (Resource resourceToAdd : resourcesToAdd) {
            for (int i = 0; i < resources.length; i++) {
                if (resources[i].getType() == resourceToAdd.getType()) {
                    resources[i] = resources[i].addAmount(resourceToAdd.getAmount());
                    break;
                }
            }
        }
    }

    // Consommer des ressources (les déduire des ressources globales du joueur)
    private void consumeResources(Resource[] resourcesToConsume) {
        for (Resource resourceToConsume : resourcesToConsume) {
            for (int i = 0; i < resources.length; i++) {
                if (resources[i].getType() == resourceToConsume.getType()) {
                    resources[i] = resources[i].removeAmount(resourceToConsume.getAmount());
                    break;
                }
            }
        }
    }

    // Consommer la nourriture des habitants
    private void consumeFoodForInhabitants(int totalFoodConsumption) {
        for (int i = 0; i < resources.length; i++) {
            if (resources[i].getType() == ResourceType.FOOD) {
                resources[i] = resources[i].removeAmount(totalFoodConsumption);
                if (resources[i].getAmount() == 0) {
                    endGame();
                    System.out.println("Famine! Not enough food.");
                }
                break;
            }
        }
    }

    public Resource[] getResources() {
        return resources;
    }

    public Map getMap() {
        return map;
    }

    public String[] getAvailableBuildingTypes() {
        return BuildingFactory.getBuildingTypes();
    }

}