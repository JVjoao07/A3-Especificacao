package com.nutrifacil.model;

public enum Alergia {
    LACTOSE("Lactose"),
    GLUTEN("Glúten"),
    AMENDOIM("Amendoim"),
    OVOS("Ovos"),
    SOJA("Soja"),
    FRUTOS_DO_MAR("Frutos do Mar"),
    NOZES("Nozes");

    private final String descricao;

    Alergia(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
} 