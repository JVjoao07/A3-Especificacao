package com.nutrifacil.model;

public enum DietType {
    MEDITERRANEA("Mediterrânea"),
    LOW_CARB("Low Carb"),
    CETOGENICA("Cetogênica"),
    VEGETARIANA("Vegetariana");

    private final String description;

    DietType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
} 