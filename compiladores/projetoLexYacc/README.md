# Trabalho de Compiladores - Lex e Yacc
- Quarto crédito da disciplina DEC000058 - Compiladores
- Semestre 2024.1

## Índice
- [Trabalho de Compiladores - Lex e Yacc](#trabalho-de-compiladores---lex-e-yacc)
  - [Índice](#índice)
  - [Descrição do Trabalho](#descrição-do-trabalho)
    - [Modificações Posteriores](#modificações-posteriores)
  - [Facetas da Análise](#facetas-da-análise)
  - [Explicação de funções](#explicação-de-funções)
  - [Instalação do Projeto](#instalação-do-projeto)
  - [Como Executar](#como-executar)
  - [Referências](#referências)

## Descrição do Trabalho
- Verificação de tipos e escopo com expressão de adição
Observem que podem reutilizar mesmas rotinas como:
- Realizar o mesmo trabalho anterior, porém usando lex e yacc (verificação de tipo e escopo)
Adicional ao trabalho anterior: expressão de adição simples
- id = termo+termo;
- id = constante;
- Onde termo pode ser um id ou uma constante numérica ou cadeia.
- Observem que podem reutilizar mesmas rotinas como:
  - inserir e remover escopo da pilha
  - verificar tipo das variáveis
- Sua execução deve:
  1. mostrar o resultado do print, para o identificador
  2. mostrar erros: tipos de dados incompatíveis e variáveis não declaradas. Não será dado exemplos (casos de testes) com outros erros. Então a entrada estará sempre correta lexicalmente e sintaticamente
- **Entregar**: arquivos (.l e .y) + video(captura de tela) mostrando a execução para os casos de teste, entradas e saídas.
- Para realizar essa tarefa, você deverá implementar uma tabela de símbolos, como no exemplo 5. Deverá atualizá-la sempre que os ids mudarem de valor
- Um id não pode mudar de tipo. Um id ganha em uma atribuição, a depender o tipo da operação (ou dado) do lado direto da
atribuição.
  - o operador + pode ser aplicado para 2 dados numéricos. Onde é realizado a adição desses valores.
  - o operador + pode ser aplicado para 2 dados do tipo cadeia. Onde é realizado a concatenação dessas cadeias

### Modificações Posteriores
Para o último trabalho, modifiquei os casos de teste para simplificar:
- adicionei `;` ao fim de atribuições e declarações.
- valores numericos apenas inteiros não-negativos.

## Facetas da Análise

## Explicação de funções

## Instalação do Projeto
- Baixe diretamente o projeto do GitHub em formado `.zip`.
- Ou utilize o comando:
```sh
git clone 
```

## Como Executar
Após instalar o projeto do GitHub diretamente, ou utilizando o comando `git clone`

## Referências
- https://developer.ibm.com/tutorials/au-lexyacc/
- https://www2.cs.arizona.edu/classes/cs453/fall14/DOCS/tutorial-large.pdf
- https://www.oreilly.com/library/view/lex-yacc/9781565920002/ch01.html