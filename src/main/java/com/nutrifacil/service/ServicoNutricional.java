package com.nutrifacil.service;

import com.nutrifacil.model.Alergia;
import com.nutrifacil.model.TipoDieta;
import com.nutrifacil.model.Usuario;

import java.util.*;

public class ServicoNutricional {
    private final Map<TipoDieta, List<String>> alimentosPorDieta;
    private final Map<String, Set<Alergia>> alergiasPorAlimento;

    public ServicoNutricional() {
        this.alimentosPorDieta = inicializarAlimentosPorDieta();
        this.alergiasPorAlimento = inicializarAlergiasPorAlimento();
    }

    private Map<TipoDieta, List<String>> inicializarAlimentosPorDieta() {
        Map<TipoDieta, List<String>> alimentos = new EnumMap<>(TipoDieta.class);
        
        // Dieta Mediterrânea
        alimentos.put(TipoDieta.MEDITERRANEA, Arrays.asList(
            "Azeite de oliva", "Peixes", "Frutas frescas", "Vegetais", "Grãos integrais",
            "Legumes", "Nozes", "Iogurte", "Queijo", "Vinho tinto (moderadamente)"
        ));

        // Dieta Baixo Carboidrato
        alimentos.put(TipoDieta.BAIXO_CARBOIDRATO, Arrays.asList(
            "Carnes magras", "Ovos", "Peixes", "Queijos", "Verduras",
            "Abacate", "Azeite", "Manteiga", "Nozes", "Sementes"
        ));

        // Dieta Cetogênica
        alimentos.put(TipoDieta.CETOGENICA, Arrays.asList(
            "Carnes", "Peixes gordos", "Ovos", "Manteiga", "Azeite",
            "Abacate", "Queijos", "Nozes", "Sementes", "Verduras de baixo carboidrato"
        ));

        // Dieta Vegetariana
        alimentos.put(TipoDieta.VEGETARIANA, Arrays.asList(
            "Legumes", "Grãos integrais", "Tofu", "Tempeh", "Quinoa",
            "Frutas", "Vegetais", "Nozes", "Sementes", "Laticínios"
        ));

        return alimentos;
    }

    private Map<String, Set<Alergia>> inicializarAlergiasPorAlimento() {
        Map<String, Set<Alergia>> alergias = new HashMap<>();
        
        // Exemplos de alimentos e suas alergias associadas
        alergias.put("Queijo", new HashSet<>(Arrays.asList(Alergia.LACTOSE)));
        alergias.put("Pão", new HashSet<>(Arrays.asList(Alergia.GLUTEN)));
        alergias.put("Amendoim", new HashSet<>(Arrays.asList(Alergia.AMENDOIM)));
        alergias.put("Ovos", new HashSet<>(Arrays.asList(Alergia.OVOS)));
        alergias.put("Tofu", new HashSet<>(Arrays.asList(Alergia.SOJA)));
        alergias.put("Camarão", new HashSet<>(Arrays.asList(Alergia.FRUTOS_DO_MAR)));
        alergias.put("Nozes", new HashSet<>(Arrays.asList(Alergia.NOZES)));
        
        return alergias;
    }

    public List<String> getAlimentosRecomendados(Usuario usuario) {
        List<String> alimentosRecomendados = new ArrayList<>(alimentosPorDieta.get(usuario.getTipoDieta()));
        Set<Alergia> alergiasUsuario = usuario.getAlergias();

        // Remove alimentos que contêm alergênicos do usuário
        alimentosRecomendados.removeIf(alimento -> {
            Set<Alergia> alergiasAlimento = alergiasPorAlimento.getOrDefault(alimento, Collections.emptySet());
            return !Collections.disjoint(alergiasAlimento, alergiasUsuario);
        });

        return alimentosRecomendados;
    }

    public Map<String, List<String>> getRecomendacoesDieta(Usuario usuario) {
        Map<String, List<String>> recomendacoes = new HashMap<>();
        recomendacoes.put("Alimentos Recomendados", getAlimentosRecomendados(usuario));
        recomendacoes.put("Alimentos a Evitar", getAlimentosEvitar(usuario));
        return recomendacoes;
    }

    private List<String> getAlimentosEvitar(Usuario usuario) {
        Set<Alergia> alergiasUsuario = usuario.getAlergias();
        List<String> alimentosEvitar = new ArrayList<>();

        for (Map.Entry<String, Set<Alergia>> entry : alergiasPorAlimento.entrySet()) {
            if (!Collections.disjoint(entry.getValue(), alergiasUsuario)) {
                alimentosEvitar.add(entry.getKey());
            }
        }

        return alimentosEvitar;
    }
} 