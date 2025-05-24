package com.nutrifacil;

import com.nutrifacil.model.Alergia;
import com.nutrifacil.model.TipoDieta;
import com.nutrifacil.model.Usuario;
import com.nutrifacil.service.ServicoNutricional;
import com.nutrifacil.service.CalculadoraMetabolicas;

import java.util.List;
import java.util.Map;

public class Aplicacao {
    public static void main(String[] args) {
        // Criando um usuário de exemplo
        Usuario usuario = new Usuario(
            "João Silva",
            30,
            75.5, // peso em kg
            175.0, // altura em cm
            "M",
            TipoDieta.MEDITERRANEA,
            "Manter peso e melhorar saúde"
        );

        // Adicionando algumas alergias
        usuario.adicionarAlergia(Alergia.LACTOSE);
        usuario.adicionarAlergia(Alergia.GLUTEN);

        // Inicializando os serviços
        CalculadoraMetabolicas calculadoraMetabolicas = new CalculadoraMetabolicas();
        ServicoNutricional servicoNutricional = new ServicoNutricional();

        // Calculando métricas de saúde
        double tmb = calculadoraMetabolicas.calcularTMB(usuario);
        double imc = calculadoraMetabolicas.calcularIMC(usuario);
        String categoriaIMC = calculadoraMetabolicas.getCategoriaIMC(imc);
        double consumoAgua = calculadoraMetabolicas.calcularConsumoAgua(usuario);

        // Obtendo recomendações alimentares
        Map<String, List<String>> recomendacoesDieta = servicoNutricional.getRecomendacoesDieta(usuario);

        // Exibindo resultados
        System.out.println("=== NutriFácil - Relatório de Saúde ===");
        System.out.println("\nInformações do Usuário:");
        System.out.println(usuario);
        
        System.out.println("\nMétricas de Saúde:");
        System.out.printf("Taxa Metabólica Basal (TMB): %.2f kcal/dia%n", tmb);
        System.out.printf("Índice de Massa Corporal (IMC): %.2f - %s%n", imc, categoriaIMC);
        System.out.printf("Consumo Diário de Água Recomendado: %.0f ml%n", consumoAgua);

        System.out.println("\nRecomendações Alimentares:");
        System.out.println("\nAlimentos Recomendados:");
        recomendacoesDieta.get("Alimentos Recomendados").forEach(alimento -> 
            System.out.println("- " + alimento));

        System.out.println("\nAlimentos a Evitar:");
        recomendacoesDieta.get("Alimentos a Evitar").forEach(alimento -> 
            System.out.println("- " + alimento));

        // Exemplo com outro usuário
        System.out.println("\n=== Exemplo com Outro Usuário ===");
        Usuario usuario2 = new Usuario(
            "Maria Santos",
            28,
            62.0,
            165.0,
            "F",
            TipoDieta.VEGETARIANA,
            "Perder peso"
        );
        usuario2.adicionarAlergia(Alergia.SOJA);

        System.out.println("\nInformações do Usuário:");
        System.out.println(usuario2);

        System.out.println("\nMétricas de Saúde:");
        System.out.printf("Taxa Metabólica Basal (TMB): %.2f kcal/dia%n", 
            calculadoraMetabolicas.calcularTMB(usuario2));
        System.out.printf("Índice de Massa Corporal (IMC): %.2f - %s%n", 
            calculadoraMetabolicas.calcularIMC(usuario2),
            calculadoraMetabolicas.getCategoriaIMC(calculadoraMetabolicas.calcularIMC(usuario2)));
        System.out.printf("Consumo Diário de Água Recomendado: %.0f ml%n", 
            calculadoraMetabolicas.calcularConsumoAgua(usuario2));

        System.out.println("\nRecomendações Alimentares:");
        Map<String, List<String>> recomendacoes2 = servicoNutricional.getRecomendacoesDieta(usuario2);
        System.out.println("\nAlimentos Recomendados:");
        recomendacoes2.get("Alimentos Recomendados").forEach(alimento -> 
            System.out.println("- " + alimento));
        System.out.println("\nAlimentos a Evitar:");
        recomendacoes2.get("Alimentos a Evitar").forEach(alimento -> 
            System.out.println("- " + alimento));
    }
} 