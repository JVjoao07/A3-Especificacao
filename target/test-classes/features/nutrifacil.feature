# language: pt

Funcionalidade: Cálculo de Taxa Metabólica Basal (TMB)
  Como um usuário do NutriFácil
  Eu quero calcular minha taxa metabólica basal
  Para saber quantas calorias meu corpo gasta em repouso

  Cenário: Cálculo de TMB para usuário masculino
    Dado que o usuário tem as seguintes informações:
      | nome  | idade | peso | altura | sexo |
      | João  | 30    | 75.5 | 175    | M    |
    Quando o sistema calcula a TMB
    Então o resultado deve ser aproximadamente 1725.63 kcal/dia

  Cenário: Cálculo de TMB para usuário feminino
    Dado que o usuário tem as seguintes informações:
      | nome  | idade | peso | altura | sexo |
      | Maria | 28    | 62.0 | 165    | F    |
    Quando o sistema calcula a TMB
    Então o resultado deve ser aproximadamente 1384.38 kcal/dia

Funcionalidade: Cálculo de Índice de Massa Corporal (IMC)
  Como um usuário do NutriFácil
  Eu quero calcular meu IMC
  Para saber minha classificação de peso

  Cenário: Usuário com IMC normal
    Dado que o usuário tem peso "65" kg
    E altura "170" cm
    Quando o sistema calcula o IMC
    Então o resultado deve ser "22.49"
    E a classificação deve ser "Peso normal"

  Cenário: Usuário com sobrepeso
    Dado que o usuário tem peso "85" kg
    E altura "170" cm
    Quando o sistema calcula o IMC
    Então o resultado deve ser "29.41"
    E a classificação deve ser "Sobrepeso"

Funcionalidade: Cálculo de Consumo de Água
  Como um usuário do NutriFácil
  Eu quero saber minha necessidade diária de água
  Para manter-me hidratado adequadamente

  Cenário: Cálculo de consumo de água para usuário de 70kg
    Dado que o usuário tem peso "70" kg
    Quando o sistema calcula o consumo de água recomendado
    Então o resultado deve ser "2450" ml por dia

Funcionalidade: Recomendação de Alimentos
  Como um usuário do NutriFácil
  Eu quero receber recomendações de alimentos
  Para seguir minha dieta adequadamente

  Cenário: Recomendações para dieta Mediterrânea
    Dado que o usuário seleciona a dieta "Mediterrânea"
    Quando o sistema gera as recomendações
    Então a lista deve incluir "Azeite de oliva"
    E deve incluir "Peixes"
    E deve incluir "Grãos integrais"

  Cenário: Recomendações para dieta Vegetariana
    Dado que o usuário seleciona a dieta "Vegetariana"
    Quando o sistema gera as recomendações
    Então a lista deve incluir "Tofu"
    E deve incluir "Quinoa"
    E deve incluir "Legumes"

Funcionalidade: Filtragem de Alimentos por Alergias
  Como um usuário alérgico
  Eu quero que o sistema filtre alimentos que não posso consumir
  Para evitar reações alérgicas

  Cenário: Usuário com alergia a lactose
    Dado que o usuário tem alergia a "Lactose"
    E seleciona a dieta "Mediterrânea"
    Quando o sistema gera as recomendações
    Então a lista não deve incluir "Queijo"
    E não deve incluir "Iogurte"

  Cenário: Usuário com múltiplas alergias
    Dado que o usuário tem alergia a "Lactose"
    E tem alergia a "Glúten"
    E seleciona a dieta "Baixo Carboidrato"
    Quando o sistema gera as recomendações
    Então a lista não deve incluir "Queijo"
    E não deve incluir "Pão"
    E deve incluir "Carnes magras"
    E deve incluir "Verduras" 