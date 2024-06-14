%{
int yylex(void);
void yyerror(char* s);

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct node {
    char tipo_variavel[7];
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
void atualizar_variavel_numero(lista_t*, char*);
void atualizar_variavel_cadeia(lista_t*, char*);

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
        //imprimir_variavel($3);
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
        printf("SEM VALOR -> %s", $2.identificador);
    } |
    TIPONUM IDENTIFICADOR IGUAL INTEIRO TERMINADOR {
        printf("%s = %d", $2.identificador, $4.numero);
    } |
    TIPONUM IDENTIFICADOR IGUAL IDENTIFICADOR TERMINADOR {
        printf("%s = %s", $2.identificador, $4.identificador);
    } |
    TIPOCAD IDENTIFICADOR TERMINADOR {
        printf("SEM VALOR -> %s", $2.identificador);
    } |
    TIPOCAD IDENTIFICADOR IGUAL STRING TERMINADOR {
        printf("%s = %s", $2.identificador, $4.cadeia);
    } |
    TIPOCAD IDENTIFICADOR IGUAL IDENTIFICADOR TERMINADOR {
        printf("%s = %s", $2.identificador, $4.identificador);
    } |
    soma |
    subtracao |
    multiplicacao |
    concatenar
    ;

atribuicao: IDENTIFICADOR IGUAL INTEIRO TERMINADOR {
        printf("%s = %d", $1.identificador, $3.numero);
    } |
    IDENTIFICADOR IGUAL STRING TERMINADOR {
        printf("%s = %s", $1.identificador, $3.cadeia);
    } |
    IDENTIFICADOR IGUAL IDENTIFICADOR TERMINADOR {
        printf("%s = %s", $1.identificador, $3.identificador);
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
	strcpy(ptr_novo_elo->nome_variavel, lexema);
	ptr_novo_elo->valor_inteiro = valor_inteiro;

    // ATUALIZANDO O PONTEIRO DE INICIO DA LISTA
	ptr_novo_elo->ptr_proximo_no = lista_atual->ptr_primeiro_lista;

	return;
}

void inserir_string(char* tipo, char* lexema, char* valor_string) {
    // CRIANDO NOVO ELO PARA A LISTA
	node_t* ptr_novo_elo = (node_t*) malloc(sizeof(node_t));

    // SALVANDO OS VALORES NO ELO
    strcpy(ptr_novo_elo->tipo_variavel, tipo);
	strcpy(ptr_novo_elo->nome_variavel, lexema);
	strcpy(ptr_novo_elo->valor_string, valor_string);

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
