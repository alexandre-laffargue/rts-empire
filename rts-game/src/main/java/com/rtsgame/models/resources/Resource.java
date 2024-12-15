package com.rtsgame.models.resources;

public class Resource {
    private final int amount;
    private final ResourceType type;

    public Resource(ResourceType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public ResourceType getType() {
        return type;
    }
    
    public String toString() {
        return type + ": " + amount;
    }

    public Resource addAmount(int amountToAdd) {
        return new Resource(this.type, this.amount + amountToAdd);
    }

    public Resource removeAmount(int amountToRemove) {
        return new Resource(this.type, Math.max(0, this.amount - amountToRemove));
    }
}