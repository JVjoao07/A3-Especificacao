package com.nutrifacil.service;

import com.nutrifacil.model.User;

public class HealthCalculator {
    
    public double calculateTMB(User user) {
        double tmb;
        if (user.getGender().equalsIgnoreCase("M")) {
            tmb = (10 * user.getWeight()) + (6.25 * user.getHeight()) - (5 * user.getAge()) + 5;
        } else {
            tmb = (10 * user.getWeight()) + (6.25 * user.getHeight()) - (5 * user.getAge()) - 161;
        }
        return Math.round(tmb * 100.0) / 100.0; // Arredonda para 2 casas decimais
    }

    public double calculateIMC(User user) {
        double heightInMeters = user.getHeight() / 100.0;
        double imc = user.getWeight() / (heightInMeters * heightInMeters);
        return Math.round(imc * 100.0) / 100.0;
    }

    public String getIMCCategory(double imc) {
        if (imc < 18.5) return "Abaixo do peso";
        if (imc < 25) return "Peso normal";
        if (imc < 30) return "Sobrepeso";
        if (imc < 35) return "Obesidade Grau I";
        if (imc < 40) return "Obesidade Grau II";
        return "Obesidade Grau III";
    }

    public double calculateWaterConsumption(User user) {
        return Math.round(user.getWeight() * 35.0); // 35ml por kg de peso
    }
} 