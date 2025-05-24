package com.nutrifacil.model;

import java.util.HashSet;
import java.util.Set;

public class Usuario {
    private String nome;
    private int idade;
    private double peso; // em kg
    private double altura; // em cm
    private String sexo; // "M" ou "F"
    private TipoDieta tipoDieta;
    private String objetivo;
    private Set<Alergia> alergias;

    public Usuario(String nome, int idade, double peso, double altura, String sexo, 
                TipoDieta tipoDieta, String objetivo) {
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
        this.sexo = sexo;
        this.tipoDieta = tipoDieta;
        this.objetivo = objetivo;
        this.alergias = new HashSet<>();
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public TipoDieta getTipoDieta() { return tipoDieta; }
    public void setTipoDieta(TipoDieta tipoDieta) { this.tipoDieta = tipoDieta; }

    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }

    public Set<Alergia> getAlergias() { return alergias; }

    public void adicionarAlergia(Alergia alergia) {
        this.alergias.add(alergia);
    }

    public void removerAlergia(Alergia alergia) {
        this.alergias.remove(alergia);
    }

    @Override
    public String toString() {
        return "Usuário: " + nome + "\n" +
               "Idade: " + idade + " anos\n" +
               "Peso: " + peso + " kg\n" +
               "Altura: " + altura + " cm\n" +
               "Sexo: " + sexo + "\n" +
               "Dieta: " + tipoDieta.getDescricao() + "\n" +
               "Objetivo: " + objetivo + "\n" +
               "Alergias: " + alergias.stream()
                   .map(Alergia::getDescricao)
                   .reduce((a, b) -> a + ", " + b)
                   .orElse("Nenhuma");
    }
} 