package com.nutrifacil;

import com.nutrifacil.model.Alergia;
import com.nutrifacil.model.TipoDieta;
import com.nutrifacil.model.Usuario;
import com.nutrifacil.service.ServicoNutricional;
import com.nutrifacil.service.CalculadoraMetabolicas;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Aplicacao {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CalculadoraMetabolicas calculadoraMetabolicas = new CalculadoraMetabolicas();
    private static final ServicoNutricional servicoNutricional = new ServicoNutricional();

    public static void main(String[] args) {
        System.out.println("=== Bem-vindo ao NutriFácil ===");
        
        Usuario usuario = coletarInformacoesUsuario();
        exibirMenu(usuario);
        
        scanner.close();
    }

    private static Usuario coletarInformacoesUsuario() {
        System.out.println("\nPor favor, insira suas informações:");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Idade: ");
        int idade = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Peso (kg): ");
        double peso = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Altura (cm): ");
        double altura = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Sexo (M/F): ");
        String sexo = scanner.nextLine().toUpperCase();
        
        System.out.println("\nTipos de dieta disponíveis:");
        for (TipoDieta tipo : TipoDieta.values()) {
            System.out.println("- " + tipo);
        }
        System.out.print("Escolha sua dieta: ");
        TipoDieta tipoDieta = TipoDieta.valueOf(scanner.nextLine().toUpperCase());
        
        System.out.print("Objetivo (ex: perder peso, ganhar massa, etc): ");
        String objetivo = scanner.nextLine();
        
        Usuario usuario = new Usuario(nome, idade, peso, altura, sexo, tipoDieta, objetivo);
        
        System.out.println("\nAlergias disponíveis:");
        for (Alergia alergia : Alergia.values()) {
            System.out.println("- " + alergia);
        }
        
        while (true) {
            System.out.print("\nDigite uma alergia (ou 'fim' para terminar): ");
            String alergiaStr = scanner.nextLine();
            if (alergiaStr.equalsIgnoreCase("fim")) break;
            
            try {
                Alergia alergia = Alergia.valueOf(alergiaStr.toUpperCase());
                usuario.adicionarAlergia(alergia);
                System.out.println("Alergia adicionada: " + alergia);
            } catch (IllegalArgumentException e) {
                System.out.println("Alergia inválida. Por favor, escolha uma das opções listadas.");
            }
        }
        
        return usuario;
    }

    private static void exibirMenu(Usuario usuario) {
        while (true) {
            System.out.println("\n=== Menu NutriFácil ===");
            System.out.println("1. Exibir informações do usuário");
            System.out.println("2. Calcular Taxa Metabólica Basal (TMB)");
            System.out.println("3. Calcular Índice de Massa Corporal (IMC)");
            System.out.println("4. Calcular consumo de água recomendado");
            System.out.println("5. Obter recomendações alimentares");
            System.out.println("6. Sair");
            System.out.print("\nEscolha uma opção: ");
            
            int opcao = Integer.parseInt(scanner.nextLine());
            
            switch (opcao) {
                case 1:
                    exibirInformacoesUsuario(usuario);
                    break;
                case 2:
                    calcularTMB(usuario);
                    break;
                case 3:
                    calcularIMC(usuario);
                    break;
                case 4:
                    calcularConsumoAgua(usuario);
                    break;
                case 5:
                    exibirRecomendacoesAlimentares(usuario);
                    break;
                case 6:
                    System.out.println("\nObrigado por usar o NutriFácil!");
                    return;
                default:
                    System.out.println("\nOpção inválida. Por favor, escolha uma opção de 1 a 6.");
            }
        }
    }

    private static void exibirInformacoesUsuario(Usuario usuario) {
        System.out.println("\n=== Informações do Usuário ===");
        System.out.println(usuario);
        System.out.println("\nAlergias:");
        for (Alergia alergia : usuario.getAlergias()) {
            System.out.println("- " + alergia);
        }
    }

    private static void calcularTMB(Usuario usuario) {
        double tmb = calculadoraMetabolicas.calcularTMB(usuario);
        System.out.printf("\nSua Taxa Metabólica Basal (TMB) é: %.2f kcal/dia%n", tmb);
    }

    private static void calcularIMC(Usuario usuario) {
        double imc = calculadoraMetabolicas.calcularIMC(usuario);
        String categoria = calculadoraMetabolicas.getCategoriaIMC(imc);
        System.out.printf("\nSeu Índice de Massa Corporal (IMC) é: %.2f%n", imc);
        System.out.println("Classificação: " + categoria);
    }

    private static void calcularConsumoAgua(Usuario usuario) {
        double consumoAgua = calculadoraMetabolicas.calcularConsumoAgua(usuario);
        System.out.printf("\nSeu consumo diário de água recomendado é: %.0f ml%n", consumoAgua);
    }

    private static void exibirRecomendacoesAlimentares(Usuario usuario) {
        Map<String, List<String>> recomendacoes = servicoNutricional.getRecomendacoesDieta(usuario);
        
        System.out.println("\n=== Recomendações Alimentares ===");
        
        System.out.println("\nAlimentos Recomendados:");
        for (String alimento : recomendacoes.get("Alimentos Recomendados")) {
            System.out.println("- " + alimento);
        }
        
        System.out.println("\nAlimentos a Evitar:");
        for (String alimento : recomendacoes.get("Alimentos a Evitar")) {
            System.out.println("- " + alimento);
        }
    }
} 