%{
int yylex(void);
void yyerror(char* s);

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <limits.h>
#define TAMANHO_IDENTIFICADOR 7
#define CHAR_ERRO "N/A"

typedef struct node {
    char tipo_variavel[TAMANHO_IDENTIFICADOR];
    char* nome_variavel;
    int valor_inteiro;
    char* valor_string;
    struct node* ptr_proximo_no;
} node_t;

typedef struct lista {
    node_t* ptr_primeiro_lista;
    struct lista* ptr_proxima_lista;
} lista_t;

typedef struct escopo {
    lista_t* ptr_topo;
} escopo_t;

// COMANDOS DA LISTA
void inserir_inteiro(char*, char*, int);
void inserir_string(char*, char*, char*);
lista_t* criar_lista();
void deletar_lista(lista_t*);

// COMANDOS DA PILHA
int vazia(escopo_t*);
escopo_t* criar_pilha();
void deletar_pilha(escopo_t*);
void push(lista_t*);
lista_t* pop();

// REGRAS DE NEGOCIO
lista_t* retornar_escopo(escopo_t*, char*);
int variavel_no_escopo_atual(lista_t*, char*);
void imprimir_variavel(char*);
void atualizar_variavel_numero(lista_t*, char*, int);
void atualizar_variavel_cadeia(lista_t*, char*, char*);
int retornar_valor_inteiro_de_variavel(escopo_t*, char*);
char* retornar_valor_string_de_variavel(escopo_t*, char*);
char* concatenar(const char*, const char*);
char* retornar_tipo_da_variavel(escopo_t*, char*);

// VARIAVEIS QUE SALVARAO A LISTA_ATUAL E A PILHA_ATUAL
lista_t* lista_atual = NULL;
escopo_t* escopo_atual = NULL;
%}

%token INTEIRO IDENTIFICADOR BLOCO STRING TIPONUM TIPOCAD PRINT NOVO FIM IGUAL SOMA MULTIPLICACAO ABRPAREN FECPAREN MENOS TERMINADOR
%left MENOS
%union {
    int numero;
    char* cadeia;
    char* identificador;
}

%%
comandos: comandos PRINT IDENTIFICADOR TERMINADOR {
        imprimir_variavel($3.identificador);
    } |
    comandos NOVO BLOCO {
        push(lista_atual);
    } |
    comandos FIM BLOCO{ 
        lista_atual = pop();

        // SE O PROGRAMA ESTIVER PRESTES A ENCERRAR, MATAR A PILHA
        if(vazia(escopo_atual)) {
            deletar_pilha(escopo_atual);
        }
    } |
    comandos criacao |
    comandos atribuicao
    |
    ;

criacao: TIPONUM IDENTIFICADOR TERMINADOR {
        if(vazia(escopo_atual)) {
            printf("Erro. Nao ha nenhum bloco aberto.\n");
        } else if(variavel_no_escopo_atual(lista_atual, $2.identificador)) {
            printf("Erro. Variavel ja foi declarada.\n");
        } else {
            inserir_inteiro("NUMERO", $2.identificador, 0);
        }
    } |
    TIPONUM IDENTIFICADOR IGUAL INTEIRO TERMINADOR {
        if(vazia(escopo_atual)) {
            printf("Erro. Nao ha nenhum bloco aberto.\n");
        } else if(variavel_no_escopo_atual(lista_atual, $2.identificador)) {
            printf("Erro. Variavel ja foi declarada.\n");
        } else {
            inserir_inteiro("NUMERO", $2.identificador, $4.numero);
        }
    } |
    TIPONUM IDENTIFICADOR IGUAL IDENTIFICADOR TERMINADOR {
        int valor = retornar_valor_inteiro_de_variavel(escopo_atual, $4.identificador);
        
        // SO IRA ATUALIZAR SE NAO HOUVER ERRO NENHUM
        if(valor != INT_MIN) {
            if(variavel_no_escopo_atual(lista_atual, $2.identificador)) {
                printf("Erro. Variavel ja foi declarada.\n");
            } else {
                inserir_inteiro("NUMERO", $2.identificador, $4.numero);
            }
        }
    } |
    TIPOCAD IDENTIFICADOR TERMINADOR {
        if(vazia(escopo_atual)) {
            printf("Erro. Nao ha nenhum bloco aberto.\n");
        } else if(variavel_no_escopo_atual(lista_atual, $2.identificador)) {
            printf("Erro. Variavel ja foi declarada.\n");
        } else {
            inserir_string("CADEIA", $2.identificador, "");
        }
    } |
    TIPOCAD IDENTIFICADOR IGUAL STRING TERMINADOR {
        if(vazia(escopo_atual)) {
            printf("Erro. Nao ha nenhum bloco aberto.\n");
        } else if(variavel_no_escopo_atual(lista_atual, $2.identificador)) {
            printf("Erro. Variavel ja foi declarada.\n");
        } else {
            inserir_string("CADEIA", $2.identificador, $4.cadeia);
        }
    } |
    TIPOCAD IDENTIFICADOR IGUAL IDENTIFICADOR TERMINADOR {
        char* valor = strdup(retornar_valor_string_de_variavel(escopo_atual, $4.identificador));

        // SO IRA ATUALIZAR SE NAO HOUVER ERRO NENHUM
        if(!strcmp(valor, CHAR_ERRO)) {
            if(variavel_no_escopo_atual(lista_atual, $2.identificador)) {
                printf("Erro. Variavel ja foi declarada.\n");
            } else {
                inserir_string("CADEIA", $2.identificador, $4.cadeia);
            }
        }
    } |
    soma |
    subtracao |
    multiplicacao |
    concatenar
    ;

atribuicao: IDENTIFICADOR IGUAL INTEIRO TERMINADOR {
        atualizar_variavel_numero(lista_atual, $1.identificador, $3.numero);
    } |
    IDENTIFICADOR IGUAL STRING TERMINADOR {
        atualizar_variavel_cadeia(lista_atual, $1.identificador, $3.cadeia);
    } |
    IDENTIFICADOR IGUAL IDENTIFICADOR TERMINADOR {
        char* tipo_variavel = strdup(retornar_tipo_da_variavel(escopo_atual, $3.identificador));
        char* valor_cadeia;
        int valor_numero;

        if(!strcmp(tipo_variavel, "NUMERO")) {
            valor_numero = retornar_valor_inteiro_de_variavel(escopo_atual, $3.identificador);

            if (valor_numero != INT_MIN) {
                atualizar_variavel_numero(lista_atual, $1.identificador, valor_numero);
            }
        } else {
            valor_cadeia = strdup(retornar_valor_string_de_variavel(escopo_atual, $3.identificador));

            if(strcmp(valor_cadeia, CHAR_ERRO)) {
                atualizar_variavel_cadeia(lista_atual, $1.identificador, valor_cadeia);
            }
        }
    } |
    soma |
    subtracao |
    multiplicacao |
    concatenar
    ;

soma: IDENTIFICADOR IGUAL INTEIRO SOMA INTEIRO TERMINADOR {
        printf("%s = %d", $1.identificador, ($3.numero + $5.numero));
    } |
    IDENTIFICADOR IGUAL INTEIRO SOMA IDENTIFICADOR TERMINADOR {
        printf("%s = %d + %s", $1.identificador, $3.numero, $5.identificador);
    } |
    IDENTIFICADOR IGUAL IDENTIFICADOR SOMA INTEIRO TERMINADOR {
        printf("%s = %s + %d", $1.identificador, $3.identificador, $5.numero);
    } |
    IDENTIFICADOR IGUAL IDENTIFICADOR SOMA IDENTIFICADOR TERMINADOR {
        printf("%s = %s + %s", $1.identificador, $3.identificador, $5.identificador);
    } |
    TIPONUM IDENTIFICADOR IGUAL INTEIRO SOMA INTEIRO TERMINADOR {
        printf("%s = %d", $2.identificador, ($4.numero + $6.numero));
    } |
    TIPONUM IDENTIFICADOR IGUAL INTEIRO SOMA IDENTIFICADOR TERMINADOR {
        printf("%s = %d + %s", $2.identificador, $4.numero, $6.identificador);
    } |
    TIPONUM IDENTIFICADOR IGUAL IDENTIFICADOR SOMA INTEIRO TERMINADOR {
        printf("%s = %s + %d", $2.identificador, $4.identificador, $6.numero);
    } |
    TIPONUM IDENTIFICADOR IGUAL IDENTIFICADOR SOMA IDENTIFICADOR TERMINADOR {
        printf("%s = %s + %s", $2.identificador, $4.identificador, $6.identificador);
    }
    ;

subtracao: IDENTIFICADOR IGUAL INTEIRO MENOS INTEIRO TERMINADOR {
        printf("%s = %d", $1.identificador, ($3.numero - $5.numero));
    } |
    IDENTIFICADOR IGUAL INTEIRO MENOS IDENTIFICADOR TERMINADOR {
        printf("%s = %d - %s", $1.identificador, $3.numero, $5.identificador);
    } |
    IDENTIFICADOR IGUAL IDENTIFICADOR MENOS INTEIRO TERMINADOR {
        printf("%s = %s - %d", $1.identificador, $3.identificador, $5.numero);
    } |
    IDENTIFICADOR IGUAL IDENTIFICADOR MENOS IDENTIFICADOR TERMINADOR {
        printf("%s = %s - %s", $1.identificador, $3.identificador, $5.identificador);
    } |
    TIPONUM IDENTIFICADOR IGUAL INTEIRO MENOS INTEIRO TERMINADOR {
        printf("%s = %d", $2.identificador, ($4.numero - $6.numero));
    } |
    TIPONUM IDENTIFICADOR IGUAL INTEIRO MENOS IDENTIFICADOR TERMINADOR {
        printf("%s = %d - %s", $2.identificador, $4.numero, $6.identificador);
    } |
    TIPONUM IDENTIFICADOR IGUAL IDENTIFICADOR MENOS INTEIRO TERMINADOR {
        printf("%s = %s - %d", $2.identificador, $4.identificador, $6.numero);
    } |
    TIPONUM IDENTIFICADOR IGUAL IDENTIFICADOR MENOS IDENTIFICADOR TERMINADOR {
        printf("%s = %s - %s", $2.identificador, $4.identificador, $6.identificador);
    }
    ;

multiplicacao: IDENTIFICADOR IGUAL INTEIRO MULTIPLICACAO INTEIRO TERMINADOR {
        printf("%s = %d", $1.identificador, ($3.numero * $5.numero));
    } |
    TIPONUM IDENTIFICADOR IGUAL INTEIRO MULTIPLICACAO INTEIRO TERMINADOR {
        printf("%s = %d", $2.identificador, ($4.numero * $6.numero));
    }
    ;

concatenar: IDENTIFICADOR IGUAL STRING SOMA STRING TERMINADOR {
        printf("%s = %s%s", $1.identificador, $3.cadeia, $5.cadeia);
    } |
    TIPOCAD IDENTIFICADOR IGUAL STRING SOMA STRING TERMINADOR {
        printf("%s = %s%s", $2.identificador, $4.cadeia, $6.cadeia);
    }
    ;
%%

extern FILE *yyin;

int main() {
    escopo_atual = criar_pilha();
    lista_atual = criar_lista();

	do {
		yyparse(); 
	} while(!feof(yyin));
}

void yyerror(char *s) {
   fprintf(stderr, "erro: %s\n", s);
}

void inserir_inteiro(char* tipo, char* lexema, int valor_inteiro) {
    // CRIANDO NOVO ELO PARA A LISTA
	node_t* ptr_novo_elo = (node_t*) malloc(sizeof(node_t));

    // SALVANDO OS VALORES NO ELO
    strcpy(ptr_novo_elo->tipo_variavel, tipo);
	ptr_novo_elo->nome_variavel = strdup(lexema);
	ptr_novo_elo->valor_inteiro = valor_inteiro;

    // ATUALIZANDO O PONTEIRO DE INICIO DA LISTA
	ptr_novo_elo->ptr_proximo_no = lista_atual->ptr_primeiro_lista;
    lista_atual->ptr_primeiro_lista = ptr_novo_elo;

	return;
}

void inserir_string(char* tipo, char* lexema, char* valor_string) {
    // CRIANDO NOVO ELO PARA A LISTA
	node_t* ptr_novo_elo = (node_t*) malloc(sizeof(node_t));

    // SALVANDO OS VALORES NO ELO
    strcpy(ptr_novo_elo->tipo_variavel, tipo);
	ptr_novo_elo->nome_variavel = strdup(lexema);
	ptr_novo_elo->valor_string = strdup(valor_string);

    // ATUALIZANDO O PONTEIRO DE INICIO DA LISTA
	ptr_novo_elo->ptr_proximo_no = lista_atual->ptr_primeiro_lista;

	return;
}

lista_t* criar_lista() {
    lista_t* nova_lista = (lista_t*) malloc(sizeof(lista_t));
    
    nova_lista->ptr_proxima_lista = NULL;
    nova_lista->ptr_primeiro_lista = NULL;
    
    return nova_lista;
}

void deletar_lista(lista_t* lista) {
    node_t* no = lista->ptr_primeiro_lista;

    // IRA PARAR QUANDO A LISTA ESTIVER VAZIA
    while(no != NULL) {
        // ATUALIZANDO O PONTEIRO DO INICIO DA LISTA
        lista->ptr_primeiro_lista = no->ptr_proximo_no;

        // LIBERANDO O NO DA MEMORIA
        free(no);

        // SALVANDO O PROXIMO NO NA VARIAVEL
        no = lista->ptr_primeiro_lista;
    }
    
    // LIMPANDO A LISTA DA MEMORIA
    free(lista);
    return;
}

int vazia(escopo_t* escopo){
    // VERIFICANDO SE A PILHA POSSUI ALGO EMPILHADO
    if(escopo->ptr_topo == NULL) {
        return 1;
    }

    return 0;
}

escopo_t* criar_pilha() {
    escopo_t* pilha = (escopo_t*) malloc(sizeof(escopo_t));
    
    pilha->ptr_topo = NULL;
    
    return pilha;
}

void deletar_pilha(escopo_t* pilha) {
    lista_t* lista = pilha->ptr_topo;

    // IRA PARAR QUANDO A PILHA ESTIVER VAZIA
    while(!vazia(pilha)) {
        // ATUALIZANDO O PONTEIRO DO TOPO DA PILHA
        pilha->ptr_topo = lista->ptr_proxima_lista;

        // DELETANDO A LISTA
        deletar_lista(lista);

        // ATUALIZANDO O PONTEIRO PARA OUTRA LISTA
        lista = pilha->ptr_topo;
    }
    
    // LIMPANDO A PILHA DA MEMORIA
    free(pilha);
    return;
}

void push(lista_t* lista_atual) {
    // INSERINDO UMA NOVA LISTA NO TOPO DA PILHA
    lista_atual->ptr_proxima_lista = escopo_atual->ptr_topo;   

    // ATUALIZANDO O PONTEIRO
    escopo_atual->ptr_topo = lista_atual;

    return;
}

lista_t* pop() {
    lista_t* temp = NULL;

    // VERIFICANDO SE A PILHA ESTA VAZIA
    if(vazia(escopo_atual)) {
        printf("Erro. Pilha vazia.\n");
        return NULL;
    }

    // SALVANDO A LISTA QUE ESTA NO TOPO DA PILHA
    temp = escopo_atual->ptr_topo;

    // REMOVENDO O TOPO DA PILHA E EXCLUINDO DA MEMORIA
    escopo_atual->ptr_topo = escopo_atual->ptr_topo->ptr_proxima_lista;
    free(temp);

    return temp;
}

lista_t* retornar_escopo(escopo_t* escopo, char* nome_variavel) {
    escopo_t* novo_escopo = (escopo_t*) malloc(sizeof(escopo_t));
    lista_t* nova_lista = NULL;

    novo_escopo->ptr_topo = escopo->ptr_topo->ptr_proxima_lista;

    // SE CHEGAR A BASE DA PILHA E NAO TIVER MAIS NADA
    if(novo_escopo->ptr_topo == NULL) {
        return NULL;
    }
    
    // VERIFICA SE NESTE ESCOPO TEM A VARIAVEL
    if(variavel_no_escopo_atual(novo_escopo->ptr_topo, nome_variavel)) {
        nova_lista = novo_escopo->ptr_topo;
    } else {
        nova_lista = retornar_escopo(novo_escopo, nome_variavel);
    }

    free(novo_escopo);
    return nova_lista;
}

int variavel_no_escopo_atual(lista_t* lista, char* nome_variavel) {
    node_t* no = lista->ptr_primeiro_lista;

    // LACO IRA PARA DE EXECUTAR SE no FOR NULO
    while(no != NULL) {
        // COMPARANDO AS STRINGS
        if(!strcmp(no->nome_variavel, nome_variavel)) {
            return 1; // SE EXISTIR, RETORNAR VERDADE
        }

        no = no->ptr_proximo_no;
    }

    // SE CHGOU ATE AQUI, ENTAO A VARIAVEL NAO EXISTE NO ESCOPO ATUAL
    return 0;
}

void imprimir_variavel(char* nome_variavel) {
    node_t* no = NULL;
    lista_t* temp = NULL;

    // VERIFICANDO SE no ESTA NO ESCOPO ATUAL
    if(variavel_no_escopo_atual(lista_atual, nome_variavel)) {
        no = lista_atual->ptr_primeiro_lista;
    } else { // SE NAO ESTIVER, PEGUE DA PILHA
        temp = retornar_escopo(escopo_atual, nome_variavel);
        
        // SO ATRIBUIR AO no SE REALEMNTE EXISTIR
        if(temp != NULL) {
            no = temp->ptr_primeiro_lista;
        }
    }

    // SE no NAO FOI ATUALIZANDO, REPORTAR ERRO E ABORTAR FUNCAO
    if(no == NULL) {
        printf("Erro: variavel não declarada.\n");
        return;
    }

    // ITERANDO PELA LISTA PARA PEGAR O no CORRETO
    while(no != NULL) {
        // VERIFICANDO SE AS VARIAVEIS BATEM
        if(!strcmp(no->nome_variavel, nome_variavel)) {
            // VERIFICANDO O TIPO PARA A IMPRESSAO
            if(!strcmp(no->tipo_variavel, "NUMERO")) {
                printf("%d\n", no->valor_inteiro);
            } else {
                printf("%s\n", no->valor_string);
            }
        }

        no = no->ptr_proximo_no;
    }
    
    return;
}

void atualizar_variavel_numero(lista_t* lista, char* nome_variavel, int novo_valor) {
    node_t* no = NULL;
    lista_t* temp = NULL;

    // VERIFICANDO SE no ESTA NO ESCOPO ATUAL
    if(variavel_no_escopo_atual(lista, nome_variavel)) {
        no = lista->ptr_primeiro_lista;
    } else { // SE NAO ESTIVER, PEGUE DA PILHA
        temp = retornar_escopo(escopo_atual, nome_variavel);
        no = temp->ptr_primeiro_lista;
        temp = NULL;
    }

    // SE no NAO FOI ATUALIZANDO, REPORTAR ERRO E ABORTAR FUNCAO
    if(no == NULL) {
        printf("Erro: variavel não declarada.\n");
        return;
    }

    // ITERANDO PELA LISTA PARA PEGAR O no CORRETO
    while(no != NULL) {
        if(!strcmp(no->nome_variavel, nome_variavel)) {
            no->valor_inteiro = novo_valor;
        }

        no = no->ptr_proximo_no;
    }

    return;
}

void atualizar_variavel_cadeia(lista_t* lista, char* nome_variavel, char* novo_valor) {
    node_t* no = NULL;
    lista_t* temp = NULL;

    // VERIFICANDO SE no ESTA NO ESCOPO ATUAL
    if(variavel_no_escopo_atual(lista, nome_variavel)) {
        no = lista->ptr_primeiro_lista;
    } else { // SE NAO ESTIVER, PEGUE DA PILHA
        temp = retornar_escopo(escopo_atual, nome_variavel);
        no = temp->ptr_primeiro_lista;
        temp = NULL;
    }

    // SE no NAO FOI ATUALIZANDO, REPORTAR ERRO E ABORTAR FUNCAO
    if(no == NULL) {
        printf("Erro: variavel não declarada.\n");
        return;
    }
    
    // ITERANDO PELA LISTA PARA PEGAR O no CORRETO
    while(no != NULL) {
        if(!strcmp(no->nome_variavel, nome_variavel)) {
            no->valor_string = strdup(novo_valor);
        }

        no = no->ptr_proximo_no;
    }

    return;
}

int retornar_valor_inteiro_de_variavel(escopo_t* escopo, char* nome_variavel) {
    node_t* no = NULL;
    lista_t* temp = escopo->ptr_topo;

    // VERIFICA SE VARIAVEL ESTA NO ESCOPO ATUAL
    if(variavel_no_escopo_atual(temp, nome_variavel)) {
        no = temp->ptr_primeiro_lista;
    } else { // SE NAO EXISTIR, BUSCAR NA PILHA
        temp = retornar_escopo(escopo, nome_variavel);

        // SO ATRIBUIR AO no SE REALEMNTE EXISTIR
        if(temp != NULL) {
            no = temp->ptr_primeiro_lista;
        }
    }

    // SE no NAO FOI ATUALIZANDO, REPORTAR ERRO E ABORTAR FUNCAO
    if(no == NULL) {
        printf("Erro: variavel nao existe.\n");
        return INT_MIN;
    }

    // ITERANDO PELA LISTA PARA PEGAR O no CORRETO
    while(no != NULL) {
        if(!strcmp(no->nome_variavel, nome_variavel)) {
            // IRA SALVAR SO E SOMENTE SE FOR DO TIPO NUMERO
            if(!strcmp(no->tipo_variavel, "NUMERO")) {
                return no->valor_inteiro;
            }
        }
    }

    // SE CHEGOU ATE AQUI, ENTAO HA ERRO
    printf("Erro: tipos incompativeis.\n");
    return INT_MIN;
}

char* retornar_valor_string_de_variavel(escopo_t* escopo, char* nome_variavel) {
    node_t* no = NULL;
    lista_t* temp = escopo->ptr_topo;

    // VERIFICA SE VARIAVEL ESTA NO ESCOPO ATUAL
    if(variavel_no_escopo_atual(temp, nome_variavel)) {
        no = temp->ptr_primeiro_lista;
    } else { // SE NAO EXISTIR, BUSCAR NA PILHA
        temp = retornar_escopo(escopo, nome_variavel);

        // SO ATRIBUIR AO no SE REALEMNTE EXISTIR
        if(temp != NULL) {
            no = temp->ptr_primeiro_lista;
        }
    }

    // SE no NAO FOI ATUALIZANDO, REPORTAR ERRO E ABORTAR FUNCAO
    if(no == NULL) {
        printf("Erro: variavel nao existe.\n");
        return CHAR_ERRO;
    }

    // ITERANDO PELA LISTA PARA PEGAR O no CORRETO
    while(no != NULL) {
        if(!strcmp(no->nome_variavel, nome_variavel)) {
            // IRA SALVAR SO E SOMENTE SE FOR DO TIPO NUMERO
            if(!strcmp(no->tipo_variavel, "CADEIA")) {
                return no->valor_string;
            }
        }
    }

    // SE CHEGOU ATE AQUI, ENTAO HA ERRO
    printf("Erro: tipos incompativeis.\n");
    return CHAR_ERRO;
}

char* concatenar(const char* const string1, const char* const string2) {
    char* resultado;
    char* aux;
    
    // COPIANDO PRIMEIRA PARTE DA STRING
    resultado = strdup(string1);
    resultado[strlen(resultado) - 1] = '\0';

    // COPIANDO SEGUNDA PARTE DA STRING
    strncpy(aux, string2 + 1, strlen(string2) - 1);
    strcat(resultado, aux);

    // LIMPANDO aux DA MEMORIA
    aux == NULL;

    return resultado;
}

char* retornar_tipo_da_variavel(escopo_t* escopo, char* nome_variavel) {
    char* tipo;
    node_t* no = NULL;
    lista_t* temp = escopo->ptr_topo;

    // VERIFICA SE VARIAVEL ESTA NO ESCOPO ATUAL
    if(variavel_no_escopo_atual(temp, nome_variavel)) {
        no = temp->ptr_primeiro_lista;
    } else { // SE NAO EXISTIR, BUSCAR NA PILHA
        temp = retornar_escopo(escopo, nome_variavel);

        // SO ATRIBUIR AO no SE REALEMNTE EXISTIR
        if(temp != NULL) {
            no = temp->ptr_primeiro_lista;
        }
    }

    // SE no NAO FOI ATUALIZANDO, REPORTAR ERRO E ABORTAR FUNCAO
    if(no == NULL) {
        printf("Erro: variavel nao existe.\n");
        return CHAR_ERRO;
    }

    // ITERANDO PELA LISTA PARA PEGAR O no CORRETO
    while(no != NULL) {
        if(!strcmp(no->nome_variavel, nome_variavel)) {
            tipo = strdup(no->tipo_variavel);
            break;
        }
    }

    return tipo;
}
