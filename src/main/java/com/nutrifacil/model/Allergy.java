package com.nutrifacil.model;

public enum Allergy {
    LACTOSE("Lactose"),
    GLUTEN("Glúten"),
    AMENDOIM("Amendoim"),
    OVOS("Ovos"),
    SOJA("Soja"),
    FRUTOS_DO_MAR("Frutos do Mar"),
    NOZES("Nozes");

    private final String description;

    Allergy(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
} 