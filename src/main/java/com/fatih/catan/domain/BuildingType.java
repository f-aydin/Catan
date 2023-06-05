package com.fatih.catan.domain;

public enum BuildingType {
    SETTLEMENT (1),
    CITY (2);

    private final int amountOfResourcesToAdd;

    BuildingType(int amountOfResourcesToAdd) {
        this.amountOfResourcesToAdd = amountOfResourcesToAdd;
    }

    public int getAmountOfResourcesToAdd() {
        return amountOfResourcesToAdd;
    }
}