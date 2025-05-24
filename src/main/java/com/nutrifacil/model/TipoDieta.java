package com.nutrifacil.model;

public enum TipoDieta {
    MEDITERRANEA("Mediterrânea"),
    BAIXO_CARBOIDRATO("Baixo Carboidrato"),
    CETOGENICA("Cetogênica"),
    VEGETARIANA("Vegetariana");

    private final String descricao;

    TipoDieta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
} 