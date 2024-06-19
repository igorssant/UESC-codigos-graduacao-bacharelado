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
O programa:
- nomes de bloco precisam iniciar e finalizar com o caractere *underscore* (`_`) e podem conter apenas letras minúsculas ou números;
  - `BLOCO _nome_bloco_`.
- nomes de variáveis devem iniciar com letras minúsculas, podem conter qualquer tamanho, podem receber números e underscores em sua formação, mas não aceitam letras maiúsculas nem caracteres especiais;
- exige um token finalizador (`;`) para atribuição, criação e impressão;
  - `NUMERO a = 10;`;
  - `CADEIA x;`;
  - `a = 50;`;
  - `PRINT x;`.
- não aceita o token finalizador para criação e exclusão de bloco;
  - `BLOCO _nome_bloco_`;
  - `FIM _nome_bloco_`
- aceita como entrada para variáveis numéricas, somente valores inteiros positivos;
- não aceita nenhum tipo de acento ou caractere especial para variáveis do tipo *cadeia*;
- não aceita criacão de variáveis fora de blocos;
- **NÃO** aceita criação de uma lista váriaveis numa mesma linha, como no exemplo abaixo:
  - `NUMERO a = 5, b, c = 10;`.
- aceita somente a criação de uma variável por linha;
- aceita somente uma atribuição de valor a um variável por linha, como no exemplo abaixo:
  - `a = 10;`.
- após o fechamento do bloco principal (ou seja, o primeiro bloco declarado) é possível criar novos blocos para criar/instânciar novas variáveis;
- aceita soma;
  - `NUMERO b = 10 + 20;`;
  - `NUMERO a = b + 10;`;
  - `NUMERO c = 50 + d;`;
  - `a = 5 + 5;`;
  - `b = c + 1;`;
  - `c = a + b;`;
  - `d = 5 + a;`.
- aceita subtração;
  - `NUMERO b = 10 - 20;`;
  - `NUMERO a = b - 10;`;
  - `NUMERO c = 50 - d;`;
  - `a = 5 - 5;`;
  - `b = c - 1;`;
  - `c = a - b;`;
  - `d = 5 - a;`.
- aceita concatenação;
  - `CADEIA x = "ola " + "mundo";`;
  - `CADEIA y = x + " !!!";`;
  - `CADEIA z = "> " + y;`;
  - `x = "hello" + " world";`;
  - `z = x + " !";`;
  - `y = "nao " + x;`.
- não aceita multiplicação;
- não aceita divisão.

## Explicação de funções
<section>

```c
void inserir_inteiro(char*, char*, int);
```
A função `inserir_inteiro()`:
- recebe como parâmetros:
  -  o tipo da variável `:: char*`;
  -  o nome da variável `:: char*`;
  -  o valor inteiro da variável (positivo) `:: int`.
- cria um novo nó e insere ao início da lista encadeada que se encontra no topo da pilha;
- não possui retorno.
</section>
<hr>
<section>

```c
void inserir_string(char*, char*, char*);
```
A função `inserir_string()`:
- recebe como parâmetros:
  -  o tipo da variável `:: char*`;
  -  o nome da variável `:: char*`;
  -  o valor em cadeia de caracteres da variável `:: char*`.
- cria um novo nó e insere ao início da lista encadeada que se encontra no topo da pilha;
- não possui retorno.
</section>
<hr>
<section>

```c
lista_t* criar_lista();
```
A função `criar_lista()`:
- não recebe parâmetros;
- cria uma nova lista encadeada;
- aloca memória e salva a lista;
- por fim, retorna a lista alocada;
- retorna ponteiro para uma nova lista encadeada `:: lista_t*`.
</section>
<hr>
<section>

```c
void deletar_lista(lista_t*);
```
A função `deletar_lista()`:
- recebe como parâmetros;
  - um ponteiro para um lista encadeada qualquer `:: lista_t*`.
- desaloca completamente a lista da memória;
- não possui retorno.
</section>
<hr>
<section>

```c
int vazia(escopo_t*);
```
A função `vazia()`:
- recebe como parâmetros:
  - um ponteiro para uma pilha `:: escopo_t*`;
- verifica se o ponteiro para o topo da pilha aponta para `NULL`;
  - Se verdade, retorna 1;
  - Se falso, retorna 0.
- retorna valor inteiro representando verdadeiro(1) ou falso(0) `:: int`.
</section>
<hr>
<section>

```c
escopo_t* criar_pilha();
```
A função `criar_pilha()`:
- não recebe parâmetros;
- cria uma nova pilha;
- aloca na memória e retorna um ponteiro para a pilha criada;
- retorna ponteiro para uma nova pilha `:: escopo_t*`.
</section>
<hr>
<section>

```c
void deletar_pilha(escopo_t*);
```
A função `deletar_pilha()`:
- recebe como parâmetros:
  - um ponteiro para uma pilha `:: escopo_t*`.
- desaloca completamente a pilha da memória;
- não possui retorno.
</section>
<hr>
<section>

```c
void push(lista_t*);
```
A função `push()`:
- recebe como parâmetros:
  - um ponteiro para uma lista encadeada `:: lista_t*`.
- cria uma nova lista encadeada;
- atualiza os ponteiros envolvidos;
- salva a lista no topo da pilha;
- não possui retorno.
</section>
<hr>
<section>

```c
lista_t* pop();
```
A função `pop()`:
- não recebe parâmetros;
- remove uma lista encadeada do topo da pilha;
- atualiza os ponteiro envolvidos;
- desaloca a lista removida;
- retorna a lista que está no topo da pilha;
- retorna ponteiro para lista encadeada `:: lista_t*`.
</section>
<hr>
<section>

```c
lista_t* retornar_escopo(escopo_t*, char*);
```
A função `retornar_escopo()`:
- recebe como parâmetros:
  - um ponteiro para uma lista encadeada `:: lista_t`;
  - o nome de uma variável qualquer `:: char*`.
- itera pela pilha global em busca de uma lista que possua a variável em questão;
- percorre de lista em lista até que ache o nó que possui chave `nome_variavel` igual ao valor passado  como parâmetro;
  - se achar, retorna a lista correta `:: lista_t*`;
  - se não achar, retorna o valor `NULL`.
- não realiza nenhuma mudança na pilha global nem nas listas encadeadas salvas;
- retorna ponteiro para lista encadeada `:: lista_t*`.
</section>
<hr>
<section>

```c
int variavel_no_escopo_atual(lista_t*, char*);
```
A função `variavel_no_escopo_atual()`:
- recebe como parâmetros:
  - um ponteiro para uma lista encadeada `:: lista_t`;
  - o nome de uma variável qualquer `:: char*`.
- itera pela lista recebida até que ache o nó que possui chave `nome_variavel` igual ao valor passado como parâmetro;
  - se achar, retorna verdade(1);
  - se não achar, retorna falso(0).
- não realiza nenhuma mudança na lista encadeada passada como parâmetro;
- retorna valor inteiro representando verdadeiro(1) ou falso(0) `:: int`.
</section>
<hr>
<section>

```c
void imprimir_variavel(char*);
```
A função `variavel_no_escopo_atual()`:
- recebe como parâmetros:
  - o nome de uma variável qualquer `:: char*`.
- itera pela pilha global, de lista em lista;
- ao percorrer as listas, procura de nó em nó se o mesmo possui chave `nome_variavel` igual ao valor passado como parâmetro;
- ao percorrer completamente uma lista, se não achar o nó correto, desce para a próxima lista salva na pilha;
  - se achar, imprime o valor da variável no console;
  - se não achar, imprime: `Erro. Variável não declarada.`.
- não realiza nenhuma mudança na pilha global, ou nas listas encadeadas salvas;
- não possui retorno.
</section>
<hr>
<section>

```c
void atualizar_variavel_numero(lista_t*, char*, int);
```
A função `atualizar_variavel_numero()`:
- recebe como parâmetros:
  - um ponteiro para uma lista encadeada `:: lista_t*`;
  - o nome de uma variável qualquer `:: char*`;
  - um valor inteiro qualquer `:: int`.
- itera pela pilha global, de lista em lista;
- ao percorrer as listas, procura de nó em nó se o mesmo possui chave `nome_variavel` igual ao valor passado como parâmetro;
- ao percorrer completamente uma lista, se não achar o nó correto, desce para a próxima lista salva na pilha;
  - se achar, atualiza o valor inteiro da variável no nó;
  - se não achar, imprime: `Erro. Variável não declarada.`.
- não realiza nenhuma mudança na pilha global, ou nas listas encadeadas salvas. Somente altera o nó;
- não possui retorno.
</section>
<hr>
<section>

```c
void atualizar_variavel_cadeia(lista_t*, char*, char*);
```
A função `atualizar_variavel_cadeia()`:
- recebe como parâmetros:
  - um ponteiro para uma lista encadeada `:: lista_t*`;
  - o nome de uma variável qualquer `:: char*`;
  - uma lista de caracteres qualquer `:: char*`.
- itera pela pilha global, de lista em lista;
- ao percorrer as listas, procura de nó em nó se o mesmo possui chave `nome_variavel` igual ao valor passado como parâmetro;
- ao percorrer completamente uma lista, se não achar o nó correto, desce para a próxima lista salva na pilha;
  - se achar, atualiza o valor *string* da variável no nó;
  - se não achar, imprime: `Erro. Variável não declarada.`.
- não realiza nenhuma mudança na pilha global, ou nas listas encadeadas salvas. Somente altera o nó;
- não possui retorno.
</section>
<hr>
<section>

```c
int retornar_valor_inteiro_de_variavel(lista_t*, char*);
```
A função `retornar_valor_inteiro_de_variavel()`:
- recebe como parâmetros:
  - um ponteiro para uma lista encadeada `:: lista_t*`;
  - o nome de uma variável qualquer `:: char*`.
- itera pela lista de nó em nó;
- ao percorrer os nós, procura se algum dos mesmos possui chave `nome_variavel` igual ao valor passado como parâmetro;
- ao percorrer completamente uma lista, se não achar o nó correto, acessa a pilha global e itera pelos níveis da pilha, lista a lista, procurando o nó;
  - se achar, retorna o valor inteiro da variável desejada;
  - se não achar, imprime: `Erro. Variável não declarada.`.
- não realiza nenhuma mudança na pilha global, ou nas listas encadeadas salvas;
- retorna valor inteiro do nó procurado `:: int` ou `NULL` se não existir.
</section>
<hr>
<section>

```c
char* retornar_valor_string_de_variavel(lista_t*, char*);
```
A função `retornar_valor_string_de_variavel()`:
- recebe como parâmetros:
  - um ponteiro para uma lista encadeada `:: lista_t*`;
  - o nome de uma variável qualquer `:: char*`.
- itera pela lista de nó em nó;
- ao percorrer os nós, procura se algum dos mesmos possui chave `nome_variavel` igual ao valor passado como parâmetro;
- ao percorrer completamente uma lista, se não achar o nó correto, acessa a pilha global e itera pelos níveis da pilha, lista a lista, procurando o nó;
  - se achar, retorna o valor *string* da variável desejada;
  - se não achar, imprime: `Erro. Variável não declarada.`.
- não realiza nenhuma mudança na pilha global, ou nas listas encadeadas salvas;
- retorna valor *string* do nó procurado `:: char*` ou `NULL` se não existir.
</section>
<hr>
<section>

```c
char* concatenar_strings(const char*, const char*);
```
A função `concatenar_strings()`:
- recebe como parâmetros:
  - uma cadeia de caracteres qualquer `:: char*`;
  - uma cadeia de caracteres qualquer `:: char*`.
- cria uma nova cadeia de caracteres internamente;
- salva o valor da concatenação das duas cadeias de caracteres de entrada na variável criada;
- retorna a variável criada com o valor concatenado;
- retorna frase resultante da concatenação `:: char*`.
</section>
<hr>
<section>

```c
char* retornar_tipo_da_variavel(lista_t*, char*);
```
A função `retornar_tipo_da_variavel()`:
- recebe como parâmetros:
  - um ponteiro para uma lista encadeada `:: lista_t*`;
  - o nome de uma variável qualquer `:: char*`.
- itera pela lista de nó em nó;
- ao percorrer os nós, procura se algum dos mesmos possui chave `nome_variavel` igual ao valor passado como parâmetro;
- ao percorrer completamente uma lista, se não achar o nó correto, acessa a pilha global e itera pelos níveis da pilha, lista a lista, procurando o nó;
  - se achar, retorna o tipo da veriável procurada;
  - se não achar, imprime: `Erro. Variável não declarada.`.
- não realiza nenhuma mudança na pilha global, ou nas listas encadeadas salvas;
- retorna tipo da variável procurada `:: char*` ou `NULL` se não existir.
</section>

## Instalação do Projeto
- Baixe diretamente o projeto do GitHub em formato `.zip`.
- Infelizmente não é possível clonar o projeto utilizando o comando `git clone`.

## Como Executar
Após instalar o projeto do GitHub, vá até a pasta principal do projeto (onde estão localizados os arquivos `README.md`, `makefile.sh` e `testar_lex.sh`).
Abra esta mesma pasta em um terminal e digite o comando abaixo:

```sh
sh makefile.sh
```

O comando acima irá executar toda a etapa de compilação dos arquivos relacionados ao lex e o yacc, além de executar o projeto imediatamente, caso não haja nenhum erro na etapa de compilação.

Lembrando, os arquivos compilados, os executáveis e os arquivos objetos já estão, de fato, incluídos no projeto, mas é indicado recompilar todos os arquivos novamente para adequar-se melhor à sua máquina.

## Referências
- https://developer.ibm.com/tutorials/au-lexyacc/
- https://www2.cs.arizona.edu/classes/cs453/fall14/DOCS/tutorial-large.pdf
- https://www.oreilly.com/library/view/lex-yacc/9781565920002/ch01.html
