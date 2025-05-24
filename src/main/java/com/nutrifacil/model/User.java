package com.nutrifacil.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String name;
    private int age;
    private double weight; // em kg
    private double height; // em cm
    private String gender; // "M" ou "F"
    private DietType dietType;
    private String objective;
    private Set<Allergy> allergies;

    public User(String name, int age, double weight, double height, String gender, 
                DietType dietType, String objective) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.dietType = dietType;
        this.objective = objective;
        this.allergies = new HashSet<>();
    }

    // Getters e Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public DietType getDietType() { return dietType; }
    public void setDietType(DietType dietType) { this.dietType = dietType; }

    public String getObjective() { return objective; }
    public void setObjective(String objective) { this.objective = objective; }

    public Set<Allergy> getAllergies() { return allergies; }

    public void addAllergy(Allergy allergy) {
        this.allergies.add(allergy);
    }

    public void removeAllergy(Allergy allergy) {
        this.allergies.remove(allergy);
    }

    @Override
    public String toString() {
        return "Usuário: " + name + "\n" +
               "Idade: " + age + " anos\n" +
               "Peso: " + weight + " kg\n" +
               "Altura: " + height + " cm\n" +
               "Gênero: " + gender + "\n" +
               "Dieta: " + dietType.getDescription() + "\n" +
               "Objetivo: " + objective + "\n" +
               "Alergias: " + allergies.stream()
                   .map(Allergy::getDescription)
                   .reduce((a, b) -> a + ", " + b)
                   .orElse("Nenhuma");
    }
} 