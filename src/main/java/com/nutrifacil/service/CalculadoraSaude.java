package com.nutrifacil.service;

import com.nutrifacil.model.Usuario;

public class CalculadoraSaude {
    
    public double calcularTMB(Usuario usuario) {
        double tmb;
        if (usuario.getSexo().equalsIgnoreCase("M")) {
            tmb = (10 * usuario.getPeso()) + (6.25 * usuario.getAltura()) - (5 * usuario.getIdade()) + 5;
        } else {
            tmb = (10 * usuario.getPeso()) + (6.25 * usuario.getAltura()) - (5 * usuario.getIdade()) - 161;
        }
        return Math.round(tmb * 100.0) / 100.0; // Arredonda para 2 casas decimais
    }

    public double calcularIMC(Usuario usuario) {
        double alturaEmMetros = usuario.getAltura() / 100.0;
        double imc = usuario.getPeso() / (alturaEmMetros * alturaEmMetros);
        return Math.round(imc * 100.0) / 100.0;
    }

    public String getCategoriaIMC(double imc) {
        if (imc < 18.5) return "Abaixo do peso";
        if (imc < 25) return "Peso normal";
        if (imc < 30) return "Sobrepeso";
        if (imc < 35) return "Obesidade Grau I";
        if (imc < 40) return "Obesidade Grau II";
        return "Obesidade Grau III";
    }

    public double calcularConsumoAgua(Usuario usuario) {
        return Math.round(usuario.getPeso() * 35.0); // 35ml por kg de peso
    }
} 