package com.nutrifacil.service;

import com.nutrifacil.model.Allergy;
import com.nutrifacil.model.DietType;
import com.nutrifacil.model.User;

import java.util.*;

public class FoodRecommendationService {
    private final Map<DietType, List<String>> dietFoods;
    private final Map<String, Set<Allergy>> foodAllergies;

    public FoodRecommendationService() {
        this.dietFoods = initializeDietFoods();
        this.foodAllergies = initializeFoodAllergies();
    }

    private Map<DietType, List<String>> initializeDietFoods() {
        Map<DietType, List<String>> foods = new EnumMap<>(DietType.class);
        
        // Dieta Mediterrânea
        foods.put(DietType.MEDITERRANEA, Arrays.asList(
            "Azeite de oliva", "Peixes", "Frutas frescas", "Vegetais", "Grãos integrais",
            "Legumes", "Nozes", "Iogurte", "Queijo", "Vinho tinto (moderadamente)"
        ));

        // Dieta Low Carb
        foods.put(DietType.LOW_CARB, Arrays.asList(
            "Carnes magras", "Ovos", "Peixes", "Queijos", "Verduras",
            "Abacate", "Azeite", "Manteiga", "Nozes", "Sementes"
        ));

        // Dieta Cetogênica
        foods.put(DietType.CETOGENICA, Arrays.asList(
            "Carnes", "Peixes gordos", "Ovos", "Manteiga", "Azeite",
            "Abacate", "Queijos", "Nozes", "Sementes", "Verduras de baixo carboidrato"
        ));

        // Dieta Vegetariana
        foods.put(DietType.VEGETARIANA, Arrays.asList(
            "Legumes", "Grãos integrais", "Tofu", "Tempeh", "Quinoa",
            "Frutas", "Vegetais", "Nozes", "Sementes", "Laticínios"
        ));

        return foods;
    }

    private Map<String, Set<Allergy>> initializeFoodAllergies() {
        Map<String, Set<Allergy>> allergies = new HashMap<>();
        
        // Exemplos de alimentos e suas alergias associadas
        allergies.put("Queijo", new HashSet<>(Arrays.asList(Allergy.LACTOSE)));
        allergies.put("Pão", new HashSet<>(Arrays.asList(Allergy.GLUTEN)));
        allergies.put("Amendoim", new HashSet<>(Arrays.asList(Allergy.AMENDOIM)));
        allergies.put("Ovos", new HashSet<>(Arrays.asList(Allergy.OVOS)));
        allergies.put("Tofu", new HashSet<>(Arrays.asList(Allergy.SOJA)));
        allergies.put("Camarão", new HashSet<>(Arrays.asList(Allergy.FRUTOS_DO_MAR)));
        allergies.put("Nozes", new HashSet<>(Arrays.asList(Allergy.NOZES)));
        
        return allergies;
    }

    public List<String> getRecommendedFoods(User user) {
        List<String> recommendedFoods = new ArrayList<>(dietFoods.get(user.getDietType()));
        Set<Allergy> userAllergies = user.getAllergies();

        // Remove alimentos que contêm alergênicos do usuário
        recommendedFoods.removeIf(food -> {
            Set<Allergy> foodAllergens = foodAllergies.getOrDefault(food, Collections.emptySet());
            return !Collections.disjoint(foodAllergens, userAllergies);
        });

        return recommendedFoods;
    }

    public Map<String, List<String>> getDietRecommendations(User user) {
        Map<String, List<String>> recommendations = new HashMap<>();
        recommendations.put("Alimentos Recomendados", getRecommendedFoods(user));
        recommendations.put("Alimentos a Evitar", getFoodsToAvoid(user));
        return recommendations;
    }

    private List<String> getFoodsToAvoid(User user) {
        Set<Allergy> userAllergies = user.getAllergies();
        List<String> foodsToAvoid = new ArrayList<>();

        for (Map.Entry<String, Set<Allergy>> entry : foodAllergies.entrySet()) {
            if (!Collections.disjoint(entry.getValue(), userAllergies)) {
                foodsToAvoid.add(entry.getKey());
            }
        }

        return foodsToAvoid;
    }
} 