package com.nutrifacil;

import com.nutrifacil.model.Allergy;
import com.nutrifacil.model.DietType;
import com.nutrifacil.model.User;
import com.nutrifacil.service.FoodRecommendationService;
import com.nutrifacil.service.HealthCalculator;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Criando um usuário de exemplo
        User user = new User(
            "João Silva",
            30,
            75.5, // peso em kg
            175.0, // altura em cm
            "M",
            DietType.MEDITERRANEA,
            "Manter peso e melhorar saúde"
        );

        // Adicionando algumas alergias
        user.addAllergy(Allergy.LACTOSE);
        user.addAllergy(Allergy.GLUTEN);

        // Inicializando os serviços
        HealthCalculator healthCalculator = new HealthCalculator();
        FoodRecommendationService foodService = new FoodRecommendationService();

        // Calculando métricas de saúde
        double tmb = healthCalculator.calculateTMB(user);
        double imc = healthCalculator.calculateIMC(user);
        String imcCategory = healthCalculator.getIMCCategory(imc);
        double waterConsumption = healthCalculator.calculateWaterConsumption(user);

        // Obtendo recomendações alimentares
        Map<String, List<String>> dietRecommendations = foodService.getDietRecommendations(user);

        // Exibindo resultados
        System.out.println("=== NutriFácil - Relatório de Saúde ===");
        System.out.println("\nInformações do Usuário:");
        System.out.println(user);
        
        System.out.println("\nMétricas de Saúde:");
        System.out.printf("Taxa Metabólica Basal (TMB): %.2f kcal/dia%n", tmb);
        System.out.printf("Índice de Massa Corporal (IMC): %.2f - %s%n", imc, imcCategory);
        System.out.printf("Consumo Diário de Água Recomendado: %.0f ml%n", waterConsumption);

        System.out.println("\nRecomendações Alimentares:");
        System.out.println("\nAlimentos Recomendados:");
        dietRecommendations.get("Alimentos Recomendados").forEach(food -> 
            System.out.println("- " + food));

        System.out.println("\nAlimentos a Evitar:");
        dietRecommendations.get("Alimentos a Evitar").forEach(food -> 
            System.out.println("- " + food));

        // Exemplo com outro usuário
        System.out.println("\n=== Exemplo com Outro Usuário ===");
        User user2 = new User(
            "Maria Santos",
            28,
            62.0,
            165.0,
            "F",
            DietType.VEGETARIANA,
            "Perder peso"
        );
        user2.addAllergy(Allergy.SOJA);

        System.out.println("\nInformações do Usuário:");
        System.out.println(user2);

        System.out.println("\nMétricas de Saúde:");
        System.out.printf("Taxa Metabólica Basal (TMB): %.2f kcal/dia%n", 
            healthCalculator.calculateTMB(user2));
        System.out.printf("Índice de Massa Corporal (IMC): %.2f - %s%n", 
            healthCalculator.calculateIMC(user2),
            healthCalculator.getIMCCategory(healthCalculator.calculateIMC(user2)));
        System.out.printf("Consumo Diário de Água Recomendado: %.0f ml%n", 
            healthCalculator.calculateWaterConsumption(user2));

        System.out.println("\nRecomendações Alimentares:");
        Map<String, List<String>> recommendations2 = foodService.getDietRecommendations(user2);
        System.out.println("\nAlimentos Recomendados:");
        recommendations2.get("Alimentos Recomendados").forEach(food -> 
            System.out.println("- " + food));
        System.out.println("\nAlimentos a Evitar:");
        recommendations2.get("Alimentos a Evitar").forEach(food -> 
            System.out.println("- " + food));
    }
} 