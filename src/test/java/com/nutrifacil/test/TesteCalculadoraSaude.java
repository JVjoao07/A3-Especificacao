package com.nutrifacil.test;

import com.nutrifacil.model.TipoDieta;
import com.nutrifacil.model.Usuario;
import com.nutrifacil.service.CalculadoraSaude;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.cucumber.datatable.DataTable;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteCalculadoraSaude {
    private Usuario usuario;
    private CalculadoraSaude calculadoraSaude;
    private double resultado;

    public TesteCalculadoraSaude() {
        this.calculadoraSaude = new CalculadoraSaude();
    }

    @Dado("que o usuário tem as seguintes informações:")
    public void queOUsuarioTemAsSeguintesInformacoes(DataTable dataTable) {
        Map<String, String> dados = dataTable.asMaps().get(0);
        usuario = new Usuario(
            dados.get("nome"),
            Integer.parseInt(dados.get("idade")),
            Double.parseDouble(dados.get("peso")),
            Double.parseDouble(dados.get("altura")),
            dados.get("sexo"),
            TipoDieta.MEDITERRANEA, // valor padrão para os testes
            "Teste"
        );
    }

    @Dado("que o usuário tem peso {string} kg")
    public void queOUsuarioTemPeso(String peso) {
        usuario = new Usuario(
            "Teste",
            30,
            Double.parseDouble(peso),
            170.0,
            "M",
            TipoDieta.MEDITERRANEA,
            "Teste"
        );
    }

    @Dado("altura {string} cm")
    public void altura(String altura) {
        usuario.setAltura(Double.parseDouble(altura));
    }

    @Quando("o sistema calcula a TMB")
    public void oSistemaCalculaATMB() {
        resultado = calculadoraSaude.calcularTMB(usuario);
    }

    @Quando("o sistema calcula o IMC")
    public void oSistemaCalculaOIMC() {
        resultado = calculadoraSaude.calcularIMC(usuario);
    }

    @Quando("o sistema calcula o consumo de água recomendado")
    public void oSistemaCalculaOConsumoDeAguaRecomendado() {
        resultado = calculadoraSaude.calcularConsumoAgua(usuario);
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
        assertEquals(classificacao, calculadoraSaude.getCategoriaIMC(resultado));
    }

    @Então("o resultado deve ser {string} ml por dia")
    public void oResultadoDeveSerMlPorDia(String esperado) {
        assertEquals(Double.parseDouble(esperado), resultado);
    }
} 