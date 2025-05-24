# NutriFácil

NutriFácil é um sistema Java que auxilia no cálculo de métricas de saúde e fornece recomendações alimentares personalizadas.

## Funcionalidades

- Cálculo de Taxa Metabólica Basal (TMB) usando a fórmula de Mifflin
- Cálculo de Índice de Massa Corporal (IMC)
- Cálculo de consumo diário de água recomendado
- Recomendações alimentares baseadas em diferentes tipos de dieta:
  - Mediterrânea
  - Baixo Carboidrato
  - Cetogênica
  - Vegetariana
- Filtragem de alimentos com base em alergias do usuário

## Requisitos

- Java 11 ou superior
- Maven 3.6 ou superior

## Como Executar

1. Clone o repositório
2. Navegue até o diretório do projeto
3. Execute o comando Maven para compilar:
   ```bash
   mvn clean install
   ```
4. Execute o programa:
   ```bash
   mvn exec:java -Dexec.mainClass="com.nutrifacil.Aplicacao"
   ```

## Estrutura do Projeto

```
src/main/java/com/nutrifacil/
├── model/
│   ├── Usuario.java
│   ├── TipoDieta.java
│   └── Alergia.java
├── service/
│   ├── CalculadoraMetabolicas.java
│   └── ServicoNutricional.java
└── Aplicacao.java
```

## Exemplo de Uso

O programa inclui exemplos de uso com dois usuários diferentes, demonstrando:
- Cálculo de métricas de saúde
- Recomendações alimentares personalizadas
- Filtragem de alimentos com base em alergias

## Contribuindo

Sinta-se à vontade para contribuir com o projeto através de pull requests ou reportando issues. 
