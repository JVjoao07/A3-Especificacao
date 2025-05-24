package com.nutrifacil.test;

import com.nutrifacil.model.Allergy;
import com.nutrifacil.model.DietType;
import com.nutrifacil.model.User;
import com.nutrifacil.service.FoodRecommendationService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FoodRecommendationSteps {
    private User user;
    private FoodRecommendationService foodService;
    private Map<String, List<String>> recomendacoes;

    public FoodRecommendationSteps() {
        this.foodService = new FoodRecommendationService();
        this.user = new User(
            "Teste",
            30,
            70.0,
            170.0,
            "M",
            DietType.MEDITERRANEA,
            "Teste"
        );
    }

    @Dado("que o usuário seleciona a dieta {string}")
    public void queOUsuarioSelecionaADieta(String dieta) {
        DietType dietType = DietType.valueOf(dieta.toUpperCase());
        user.setDietType(dietType);
    }

    @Dado("que o usuário tem alergia a {string}")
    public void queOUsuarioTemAlergiaA(String alergia) {
        Allergy allergy = Allergy.valueOf(alergia.toUpperCase());
        user.addAllergy(allergy);
    }

    @Quando("o sistema gera as recomendações")
    public void oSistemaGeraAsRecomendacoes() {
        recomendacoes = foodService.getDietRecommendations(user);
    }

    @Então("a lista deve incluir {string}")
    public void aListaDeveIncluir(String alimento) {
        List<String> alimentosRecomendados = recomendacoes.get("Alimentos Recomendados");
        assertTrue(alimentosRecomendados.contains(alimento),
            "A lista de alimentos recomendados deveria incluir: " + alimento);
    }

    @Então("a lista não deve incluir {string}")
    public void aListaNaoDeveIncluir(String alimento) {
        List<String> alimentosRecomendados = recomendacoes.get("Alimentos Recomendados");
        assertFalse(alimentosRecomendados.contains(alimento),
            "A lista de alimentos recomendados não deveria incluir: " + alimento);
    }
} 