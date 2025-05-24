package com.nutrifacil;

import com.nutrifacil.model.Alergia;
import com.nutrifacil.model.TipoDieta;
import com.nutrifacil.model.Usuario;
import com.nutrifacil.service.ServicoRecomendacaoAlimentos;
import com.nutrifacil.service.CalculadoraSaude;

import java.util.List;
import java.util.Map;

public class Principal {
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
        CalculadoraSaude calculadoraSaude = new CalculadoraSaude();
        ServicoRecomendacaoAlimentos servicoAlimentos = new ServicoRecomendacaoAlimentos();

        // Calculando métricas de saúde
        double tmb = calculadoraSaude.calcularTMB(usuario);
        double imc = calculadoraSaude.calcularIMC(usuario);
        String categoriaIMC = calculadoraSaude.getCategoriaIMC(imc);
        double consumoAgua = calculadoraSaude.calcularConsumoAgua(usuario);

        // Obtendo recomendações alimentares
        Map<String, List<String>> recomendacoesDieta = servicoAlimentos.getRecomendacoesDieta(usuario);

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
            calculadoraSaude.calcularTMB(usuario2));
        System.out.printf("Índice de Massa Corporal (IMC): %.2f - %s%n", 
            calculadoraSaude.calcularIMC(usuario2),
            calculadoraSaude.getCategoriaIMC(calculadoraSaude.calcularIMC(usuario2)));
        System.out.printf("Consumo Diário de Água Recomendado: %.0f ml%n", 
            calculadoraSaude.calcularConsumoAgua(usuario2));

        System.out.println("\nRecomendações Alimentares:");
        Map<String, List<String>> recomendacoes2 = servicoAlimentos.getRecomendacoesDieta(usuario2);
        System.out.println("\nAlimentos Recomendados:");
        recomendacoes2.get("Alimentos Recomendados").forEach(alimento -> 
            System.out.println("- " + alimento));
        System.out.println("\nAlimentos a Evitar:");
        recomendacoes2.get("Alimentos a Evitar").forEach(alimento -> 
            System.out.println("- " + alimento));
    }
} 