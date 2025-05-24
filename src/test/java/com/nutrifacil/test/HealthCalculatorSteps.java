package com.nutrifacil.test;

import com.nutrifacil.model.DietType;
import com.nutrifacil.model.User;
import com.nutrifacil.service.HealthCalculator;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.cucumber.datatable.DataTable;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthCalculatorSteps {
    private User user;
    private HealthCalculator healthCalculator;
    private double resultado;

    public HealthCalculatorSteps() {
        this.healthCalculator = new HealthCalculator();
    }

    @Dado("que o usuário tem as seguintes informações:")
    public void queOUsuarioTemAsSeguintesInformacoes(DataTable dataTable) {
        Map<String, String> dados = dataTable.asMaps().get(0);
        user = new User(
            dados.get("nome"),
            Integer.parseInt(dados.get("idade")),
            Double.parseDouble(dados.get("peso")),
            Double.parseDouble(dados.get("altura")),
            dados.get("sexo"),
            DietType.MEDITERRANEA, // valor padrão para os testes
            "Teste"
        );
    }

    @Dado("que o usuário tem peso {string} kg")
    public void queOUsuarioTemPeso(String peso) {
        user = new User(
            "Teste",
            30,
            Double.parseDouble(peso),
            170.0,
            "M",
            DietType.MEDITERRANEA,
            "Teste"
        );
    }

    @Dado("altura {string} cm")
    public void altura(String altura) {
        user.setHeight(Double.parseDouble(altura));
    }

    @Quando("o sistema calcula a TMB")
    public void oSistemaCalculaATMB() {
        resultado = healthCalculator.calculateTMB(user);
    }

    @Quando("o sistema calcula o IMC")
    public void oSistemaCalculaOIMC() {
        resultado = healthCalculator.calculateIMC(user);
    }

    @Quando("o sistema calcula o consumo de água recomendado")
    public void oSistemaCalculaOConsumoDeAguaRecomendado() {
        resultado = healthCalculator.calculateWaterConsumption(user);
    }

    @Então("o resultado deve ser aproximadamente {double} kcal/dia")
    public void oResultadoDeveSerAproximadamente(double esperado) {
        assertEquals(esperado, resultado, 0.01);
    }

    @Então("o resultado deve ser {string}")
    public void oResultadoDeveSer(String esperado) {
        assertEquals(Double.parseDouble(esperado), resultado, 0.01);
    }

    @Então("a classificação deve ser {string}")
    public void aClassificacaoDeveSer(String classificacao) {
        assertEquals(classificacao, healthCalculator.getIMCCategory(resultado));
    }

    @Então("o resultado deve ser {string} ml por dia")
    public void oResultadoDeveSerMlPorDia(String esperado) {
        assertEquals(Double.parseDouble(esperado), resultado);
    }
} 