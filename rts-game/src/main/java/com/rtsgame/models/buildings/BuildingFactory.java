package com.rtsgame.models.buildings;

import com.rtsgame.models.resources.Resource;
import com.rtsgame.models.resources.ResourceType;

public class BuildingFactory {

    public static Building createBuilding(String type) {
        switch (type) {
            case "WoodenCabin":
                return createWoodenCabin();
            case "House":
                return createHouse();
            case "ApartmentBuilding":
                return createApartmentBuilding();
            case "Farm":
                return createFarm();
            case "Quarry":
                return createQuarry();
            case "LumberMill":
                return createLumberMill();
            case "CementPlant":
                return createCementPlant();
            case "SteelMill":
                return createSteelMill();
            case "ToolFactory":
                return createToolFactory();
            default:
                throw new IllegalArgumentException("Invalid building type: " + type);
        }
    }

    private static Building createWoodenCabin() {
        Resource[] cost = {new Resource(ResourceType.WOOD, 1)};
        Resource[] production = {new Resource(ResourceType.WOOD, 2), new Resource(ResourceType.FOOD, 2)};
        return new SimpleBuilding("WoodenCabin", new Dimensions(1, 1), 2, 2, cost, new Resource[0], production, 2);
    }

    private static Building createHouse() {
        Resource[] cost = {new Resource(ResourceType.WOOD, 2), new Resource(ResourceType.STONE, 2)};
        return new SimpleBuilding("House", new Dimensions(2, 2), 4, 0, cost, new Resource[0], new Resource[0], 4);
    }

    private static Building createApartmentBuilding() {
        Resource[] cost = {new Resource(ResourceType.WOOD, 50), new Resource(ResourceType.STONE, 50)};
        return new SimpleBuilding("ApartmentBuilding", new Dimensions(3, 2), 60, 0, cost, new Resource[0], new Resource[0], 6);
    }

    private static Building createFarm() {
        Resource[] cost = {new Resource(ResourceType.WOOD, 5), new Resource(ResourceType.STONE, 5)};
        Resource[] production = {new Resource(ResourceType.FOOD, 10)};
        return new SimpleBuilding("Farm", new Dimensions(3, 3), 5, 3, cost, new Resource[0], production, 2);
    }

    private static Building createQuarry() {
        Resource[] cost = {new Resource(ResourceType.WOOD, 50)};
        Resource[] production = {new Resource(ResourceType.STONE, 4), new Resource(ResourceType.IRON, 4), new Resource(ResourceType.COAL, 4)};
        return new SimpleBuilding("Quarry", new Dimensions(2, 2), 2, 30, cost, new Resource[0], production, 2);
    }

    private static Building createLumberMill() {
        Resource[] cost = {new Resource(ResourceType.WOOD, 50), new Resource(ResourceType.STONE, 50)};
        Resource[] consumption = {new Resource(ResourceType.WOOD, 4)};
        Resource[] production = {new Resource(ResourceType.LUMBER, 4)};
        return new SimpleBuilding("LumberMill", new Dimensions(3, 3), 0, 10, cost, consumption, production, 4);
    }

    private static Building createCementPlant() {
        Resource[] cost = {new Resource(ResourceType.WOOD, 50), new Resource(ResourceType.STONE, 50)};
        Resource[] consumption = {new Resource(ResourceType.STONE, 4), new Resource(ResourceType.COAL, 4)};
        Resource[] production = {new Resource(ResourceType.CEMENT, 4)};
        return new SimpleBuilding("CementPlant", new Dimensions(4, 3), 0, 10, cost, consumption, production, 4);
    }

    private static Building createSteelMill() {
        Resource[] cost = {new Resource(ResourceType.WOOD, 100), new Resource(ResourceType.STONE, 50)};
        Resource[] consumption = {new Resource(ResourceType.IRON, 4), new Resource(ResourceType.COAL, 2)};
        Resource[] production = {new Resource(ResourceType.STEEL, 4)};
        return new SimpleBuilding("SteelMill", new Dimensions(4, 3), 0, 40, cost, consumption, production, 6);
    }

    private static Building createToolFactory() {
        Resource[] cost = {new Resource(ResourceType.WOOD, 50), new Resource(ResourceType.STONE, 50)};
        Resource[] consumption = {new Resource(ResourceType.STEEL, 4), new Resource(ResourceType.COAL, 4)};
        Resource[] production = {new Resource(ResourceType.TOOLS, 4)};
        return new SimpleBuilding("ToolFactory", new Dimensions(4, 3), 0, 12, cost, consumption, production, 8);
    }

    public static String[] getBuildingTypes() {
        return new String[] {
            "WoodenCabin",
            "House",
            "ApartmentBuilding",
            "Farm",
            "Quarry",
            "LumberMill",
            "CementPlant",
            "SteelMill",
            "ToolFactory"
        };
    }
}