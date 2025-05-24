package com.nutrifacil.test;

import com.nutrifacil.model.Alergia;
import com.nutrifacil.model.TipoDieta;
import com.nutrifacil.model.Usuario;
import com.nutrifacil.service.ServicoRecomendacaoAlimentos;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TesteRecomendacaoAlimentos {
    private Usuario usuario;
    private ServicoRecomendacaoAlimentos servicoAlimentos;
    private Map<String, List<String>> recomendacoes;

    public TesteRecomendacaoAlimentos() {
        this.servicoAlimentos = new ServicoRecomendacaoAlimentos();
        this.usuario = new Usuario(
            "Teste",
            30,
            70.0,
            170.0,
            "M",
            TipoDieta.MEDITERRANEA,
            "Teste"
        );
    }

    @Dado("que o usuário seleciona a dieta {string}")
    public void queOUsuarioSelecionaADieta(String dieta) {
        TipoDieta tipoDieta = TipoDieta.valueOf(dieta.toUpperCase());
        usuario.setTipoDieta(tipoDieta);
    }

    @Dado("que o usuário tem alergia a {string}")
    public void queOUsuarioTemAlergiaA(String alergia) {
        Alergia alergiaEnum = Alergia.valueOf(alergia.toUpperCase());
        usuario.adicionarAlergia(alergiaEnum);
    }

    @Quando("o sistema gera as recomendações")
    public void oSistemaGeraAsRecomendacoes() {
        recomendacoes = servicoAlimentos.getRecomendacoesDieta(usuario);
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