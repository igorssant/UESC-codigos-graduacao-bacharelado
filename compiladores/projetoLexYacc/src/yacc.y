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
lista_t* push();
lista_t* pop();

// REGRAS DE NEGOCIO
lista_t* retornar_escopo(escopo_t*, char*);
int variavel_no_escopo_atual(lista_t*, char*);
void imprimir_variavel(char*);
void atualizar_variavel_numero(lista_t*, char*, int);
void atualizar_variavel_cadeia(lista_t*, char*, char*);
int retornar_valor_inteiro_de_variavel(lista_t*, char*);
char* retornar_valor_string_de_variavel(lista_t*, char*);
char* concatenar_strings(const char*, const char*);
char* retornar_tipo_da_variavel(lista_t*, char*);

// VARIAVEIS QUE SALVARAO A LISTA_ATUAL E A PILHA_ATUAL
lista_t* lista_atual = NULL;
escopo_t* escopo_atual = NULL;
%}

%token INTEIRO IDENTIFICADOR BLOCO STRING TIPONUM TIPOCAD PRINT NOVO FIM IGUAL SOMA ABRPAREN FECPAREN MENOS TERMINADOR
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
        lista_atual = push();
    } |
    comandos FIM BLOCO{ 
        lista_atual = pop();

        if(lista_atual == NULL) {
            lista_atual = criar_lista();
        }
        
        // SE O PROGRAMA ESTIVER PRESTES A ENCERRAR, MATAR A PILHA
        if(vazia(escopo_atual)) {
            deletar_pilha(escopo_atual);
        }
    } |
    comandos criacao |
    comandos atribuicao |
    comandos soma |
    comandos subtracao |
    comandos concatenar
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
        int valor = retornar_valor_inteiro_de_variavel(lista_atual, $4.identificador);
        
        // SO IRA ATUALIZAR SE NAO HOUVER ERRO NENHUM
        if(valor != INT_MIN) {
            if(variavel_no_escopo_atual(lista_atual, $2.identificador)) {
                printf("Erro. Variavel ja foi declarada.\n");
            } else {
                inserir_inteiro("NUMERO", $2.identificador, valor);
            }
        }
    } |
    TIPOCAD IDENTIFICADOR TERMINADOR {
        if(vazia(escopo_atual)) {
            printf("Erro. Nao ha nenhum bloco aberto.\n");
        } else if(variavel_no_escopo_atual(lista_atual, $2.identificador)) {
            printf("Erro. Variavel ja foi declarada.\n");
        } else {
            inserir_string("CADEIA", $2.identificador, "\0");
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
        char* valor = strdup(retornar_valor_string_de_variavel(lista_atual, $4.identificador));

        // SO IRA ATUALIZAR SE NAO HOUVER ERRO NENHUM
        if(strcmp(valor, CHAR_ERRO)) {
            if(variavel_no_escopo_atual(lista_atual, $2.identificador)) {
                printf("Erro. Variavel ja foi declarada.\n");
            } else {
                inserir_string("CADEIA", $2.identificador, valor);
            }
        }
    }
    ;

atribuicao: IDENTIFICADOR IGUAL INTEIRO TERMINADOR {
        atualizar_variavel_numero(lista_atual, $1.identificador, $3.numero);
    } |
    IDENTIFICADOR IGUAL STRING TERMINADOR {
        atualizar_variavel_cadeia(lista_atual, $1.identificador, $3.cadeia);
    } |
    IDENTIFICADOR IGUAL IDENTIFICADOR TERMINADOR {
        char* tipo_variavel_esquerda = NULL,
            *tipo_variavel_direita = NULL;
        char* valor_cadeia = NULL;
        int valor_numero = 0;

        tipo_variavel_esquerda = strdup(retornar_tipo_da_variavel(lista_atual, $1.identificador));
        tipo_variavel_direita = strdup(retornar_tipo_da_variavel(lista_atual, $3.identificador));

        // VERIFICA SE TIPO EH NUMERO E SE AMBOS TIPOS SAO IGUAIS ENTRE SI
        if(
            (!strcmp(tipo_variavel_esquerda, "NUMERO")) &&
            (!strcmp(tipo_variavel_esquerda, tipo_variavel_direita))
        ) {
            valor_numero = retornar_valor_inteiro_de_variavel(lista_atual, $3.identificador);

            // VERIFICA SE HOUVE ALGUM ERRO NA HORA DE RETORNAR O DADO
            if(valor_numero != INT_MIN) {
                atualizar_variavel_numero(lista_atual, $1.identificador, valor_numero);
            }
        } else if( // TIPO CADEIA ENTRA AQUI ABAIXO
            (!strcmp(tipo_variavel_esquerda, "CADEIA")) &&
            (!strcmp(tipo_variavel_esquerda, tipo_variavel_direita))
        ) {
            valor_cadeia = strdup(retornar_valor_string_de_variavel(lista_atual, $3.identificador));

            // VERIFICA SE HOUVE ALGUM ERRO NA HORA DE RETORNAR O DADO
            if(strcmp(valor_cadeia, CHAR_ERRO)) {
                atualizar_variavel_cadeia(lista_atual, $1.identificador, valor_cadeia);
            }
        } else {
            printf("Erro. Tipos incompativeis\n");
        }
    }
    ;

soma: IDENTIFICADOR IGUAL INTEIRO SOMA INTEIRO TERMINADOR {
        atualizar_variavel_numero(lista_atual, $1.identificador, ($3.numero + $5.numero));
    } |
    IDENTIFICADOR IGUAL INTEIRO SOMA IDENTIFICADOR TERMINADOR {
        int valor = retornar_valor_inteiro_de_variavel(lista_atual, $5.identificador);
        
        // SO IRA ATUALIZAR SE NAO HOUVER ERRO NENHUM
        if(valor != INT_MIN) {
            atualizar_variavel_numero(lista_atual, $1.identificador, ($3.numero + valor));
        }
    } |
    IDENTIFICADOR IGUAL IDENTIFICADOR SOMA INTEIRO TERMINADOR {
        int valor = retornar_valor_inteiro_de_variavel(lista_atual, $3.identificador);
        
        // SO IRA ATUALIZAR SE NAO HOUVER ERRO NENHUM
        if(valor != INT_MIN) {
            atualizar_variavel_numero(lista_atual, $1.identificador, ($5.numero + valor));
        }
    } |
    IDENTIFICADOR IGUAL IDENTIFICADOR SOMA IDENTIFICADOR TERMINADOR {
        int valor1 = retornar_valor_inteiro_de_variavel(lista_atual, $3.identificador),
            valor2 = retornar_valor_inteiro_de_variavel(lista_atual, $5.identificador);
        
        // SO IRA ATUALIZAR SE NAO HOUVER ERRO NENHUM
        if(valor1 != INT_MIN && valor2 != INT_MIN) {
            atualizar_variavel_numero(lista_atual, $1.identificador, (valor1 + valor2));
        }
    } |
    TIPONUM IDENTIFICADOR IGUAL INTEIRO SOMA INTEIRO TERMINADOR {
        if(vazia(escopo_atual)) {
            printf("Erro. Nao ha nenhum bloco aberto.\n");
        } else if(variavel_no_escopo_atual(lista_atual, $2.identificador)) {
            printf("Erro. Variavel ja foi declarada.\n");
        } else {
            inserir_inteiro("NUMERO", $2.identificador, ($4.numero + $6.numero));
        }
    } |
    TIPONUM IDENTIFICADOR IGUAL INTEIRO SOMA IDENTIFICADOR TERMINADOR {
        int valor = retornar_valor_inteiro_de_variavel(lista_atual, $6.identificador);

        // SO IRA ATUALIZAR SE NAO HOUVER ERRO NENHUM
        if(valor != INT_MIN) {
            if(variavel_no_escopo_atual(lista_atual, $2.identificador)) {
                printf("Erro. Variavel ja foi declarada.\n");
            } else {
                inserir_inteiro("NUMERO", $2.identificador, ($4.numero + valor));
            }
        }
    } |
    TIPONUM IDENTIFICADOR IGUAL IDENTIFICADOR SOMA INTEIRO TERMINADOR {
        int valor = retornar_valor_inteiro_de_variavel(lista_atual, $4.identificador);
        
        // SO IRA ATUALIZAR SE NAO HOUVER ERRO NENHUM
        if(valor != INT_MIN) {
            if(variavel_no_escopo_atual(lista_atual, $2.identificador)) {
                printf("Erro. Variavel ja foi declarada.\n");
            } else {
                inserir_inteiro("NUMERO", $2.identificador, ($6.numero + valor));
            }
        }
    } |
    TIPONUM IDENTIFICADOR IGUAL IDENTIFICADOR SOMA IDENTIFICADOR TERMINADOR {
        int valor1 = retornar_valor_inteiro_de_variavel(lista_atual, $4.identificador),
            valor2 = retornar_valor_inteiro_de_variavel(lista_atual, $6.identificador);

        // SO IRA ATUALIZAR SE NAO HOUVER ERRO NENHUM
        if((valor1 != INT_MIN) && (valor2 != INT_MIN)) {
            if(variavel_no_escopo_atual(lista_atual, $2.identificador)) {
                printf("Erro. Variavel ja foi declarada.\n");
            } else {
                atualizar_variavel_numero(lista_atual, $2.identificador, (valor1 + valor2));
            }
        }
    }
    ;

subtracao: IDENTIFICADOR IGUAL INTEIRO MENOS INTEIRO TERMINADOR {
        atualizar_variavel_numero(lista_atual, $1.identificador, ($3.numero - $5.numero));
    } |
    IDENTIFICADOR IGUAL INTEIRO MENOS IDENTIFICADOR TERMINADOR {
        int valor = retornar_valor_inteiro_de_variavel(lista_atual, $5.identificador);
        
        // SO IRA ATUALIZAR SE NAO HOUVER ERRO NENHUM
        if(valor != INT_MIN) {
            atualizar_variavel_numero(lista_atual, $1.identificador, ($3.numero - valor));
        }
    } |
    IDENTIFICADOR IGUAL IDENTIFICADOR MENOS INTEIRO TERMINADOR {
        int valor = retornar_valor_inteiro_de_variavel(lista_atual, $3.identificador);
        
        // SO IRA ATUALIZAR SE NAO HOUVER ERRO NENHUM
        if(valor != INT_MIN) {
            atualizar_variavel_numero(lista_atual, $1.identificador, ($5.numero - valor));
        }
    } |
    IDENTIFICADOR IGUAL IDENTIFICADOR MENOS IDENTIFICADOR TERMINADOR {
        int valor1 = retornar_valor_inteiro_de_variavel(lista_atual, $3.identificador),
            valor2 = retornar_valor_inteiro_de_variavel(lista_atual, $5.identificador);
        
        // SO IRA ATUALIZAR SE NAO HOUVER ERRO NENHUM
        if((valor1 != INT_MIN) && (valor2 != INT_MIN)) {
            atualizar_variavel_numero(lista_atual, $1.identificador, (valor1 - valor2));
        }
    } |
    TIPONUM IDENTIFICADOR IGUAL INTEIRO MENOS INTEIRO TERMINADOR {
        if(vazia(escopo_atual)) {
            printf("Erro. Nao ha nenhum bloco aberto.\n");
        } else if(variavel_no_escopo_atual(lista_atual, $2.identificador)) {
            printf("Erro. Variavel ja foi declarada.\n");
        } else {
            inserir_inteiro("NUMERO", $2.identificador, ($4.numero - $6.numero));
        }
    } |
    TIPONUM IDENTIFICADOR IGUAL INTEIRO MENOS IDENTIFICADOR TERMINADOR {
        int valor = retornar_valor_inteiro_de_variavel(lista_atual, $6.identificador);
        
        // SO IRA ATUALIZAR SE NAO HOUVER ERRO NENHUM
        if(valor != INT_MIN) {
            if(variavel_no_escopo_atual(lista_atual, $2.identificador)) {
                printf("Erro. Variavel ja foi declarada.\n");
            } else {
                inserir_inteiro("NUMERO", $2.identificador, ($4.numero - valor));
            }
        }
    } |
    TIPONUM IDENTIFICADOR IGUAL IDENTIFICADOR MENOS INTEIRO TERMINADOR {
        int valor = retornar_valor_inteiro_de_variavel(lista_atual, $4.identificador);
        
        // SO IRA ATUALIZAR SE NAO HOUVER ERRO NENHUM
        if(valor != INT_MIN) {
            if(variavel_no_escopo_atual(lista_atual, $2.identificador)) {
                printf("Erro. Variavel ja foi declarada.\n");
            } else {
                inserir_inteiro("NUMERO", $2.identificador, ($6.numero - valor));
            }
        }
    } |
    TIPONUM IDENTIFICADOR IGUAL IDENTIFICADOR MENOS IDENTIFICADOR TERMINADOR {
        int valor1 = retornar_valor_inteiro_de_variavel(lista_atual, $4.identificador),
            valor2 = retornar_valor_inteiro_de_variavel(lista_atual, $6.identificador);

        // SO IRA ATUALIZAR SE NAO HOUVER ERRO NENHUM
        if((valor1 != INT_MIN) && (valor2 != INT_MIN)) {
            if(variavel_no_escopo_atual(lista_atual, $2.identificador)) {
                printf("Erro. Variavel ja foi declarada.\n");
            } else {
                atualizar_variavel_numero(lista_atual, $2.identificador, (valor1 - valor2));
            }
        }
    }
    ;

concatenar: IDENTIFICADOR IGUAL STRING SOMA STRING TERMINADOR {
        char* string_concatenada = strdup(concatenar_strings($3.cadeia, $5.cadeia));
        
        if(!variavel_no_escopo_atual(lista_atual, $1.identificador)) {
            printf("Erro. Variavel nao foi declarada.\n");
        } else {
            atualizar_variavel_cadeia(lista_atual, $1.identificador, string_concatenada);
        }
    } |
    IDENTIFICADOR IGUAL IDENTIFICADOR SOMA STRING TERMINADOR {
        char* string_concatenada = NULL,
            *novo_valor_string = NULL,
            *tipo_variavel_esquerda = strdup(retornar_tipo_da_variavel(lista_atual, $1.identificador)),
            *tipo_variavel_direita = strdup(retornar_tipo_da_variavel(lista_atual, $3.identificador));
        
        // VERIFICANDO SE AS VARIAVEIS REALMENTE EXISTEM
        if(strcmp(tipo_variavel_esquerda, CHAR_ERRO) && strcmp(tipo_variavel_direita, CHAR_ERRO)) {
            // VERIFICA SE OS TIPOS SAO IGUAIS E SAO `CADEIA`
            if(
                (!strcmp(tipo_variavel_esquerda, tipo_variavel_direita)) &&
                (!strcmp(tipo_variavel_esquerda, "CADEIA"))
            ) {
                novo_valor_string = strdup(retornar_valor_string_de_variavel(lista_atual, $3.identificador));
                string_concatenada = strdup(concatenar_strings(novo_valor_string, $5.cadeia));
                atualizar_variavel_cadeia(lista_atual, $1.identificador, string_concatenada);
            }
        }
    } |
    IDENTIFICADOR IGUAL STRING SOMA IDENTIFICADOR TERMINADOR {
        char* string_concatenada = NULL,
            *novo_valor_string = NULL,
            *tipo_variavel_esquerda = strdup(retornar_tipo_da_variavel(lista_atual, $1.identificador)),
            *tipo_variavel_direita = strdup(retornar_tipo_da_variavel(lista_atual, $5.identificador));
        
        // VERIFICANDO SE AS VARIAVEIS REALMENTE EXISTEM
        if(strcmp(tipo_variavel_esquerda, CHAR_ERRO) && strcmp(tipo_variavel_direita, CHAR_ERRO)) {
            // VERIFICA SE OS TIPOS SAO IGUAIS E SAO `CADEIA`
            if(
                (!strcmp(tipo_variavel_esquerda, tipo_variavel_direita)) &&
                (!strcmp(tipo_variavel_esquerda, "CADEIA"))
            ) {
                novo_valor_string = strdup(retornar_valor_string_de_variavel(lista_atual, $5.identificador));
                string_concatenada = strdup(concatenar_strings($3.cadeia, novo_valor_string));
                atualizar_variavel_cadeia(lista_atual, $1.identificador, string_concatenada);
            }
        }
    } |
    TIPOCAD IDENTIFICADOR IGUAL STRING SOMA STRING TERMINADOR {
        char* string_concatenada = strdup(concatenar_strings($4.cadeia, $6.cadeia));

        if(vazia(escopo_atual)) {
            printf("Erro. Nao ha nenhum bloco aberto.\n");
        } else if(variavel_no_escopo_atual(lista_atual, $2.identificador)) {
            printf("Erro. Variavel ja foi declarada.\n");
        } else {
            inserir_string("CADEIA", $2.identificador, string_concatenada);
        }
    } |
    TIPOCAD IDENTIFICADOR IGUAL IDENTIFICADOR SOMA STRING TERMINADOR {
        char* string_concatenada = NULL,
            *novo_valor_string = NULL,
            *tipo_variavel_esquerda = strdup(retornar_tipo_da_variavel(lista_atual, $2.identificador)),
            *tipo_variavel_direita = strdup(retornar_tipo_da_variavel(lista_atual, $4.identificador));
        
        // VERIFICANDO SE AS VARIAVEIS REALMENTE EXISTEM
        if(strcmp(tipo_variavel_esquerda, CHAR_ERRO) && strcmp(tipo_variavel_direita, CHAR_ERRO)) {
            // VERIFICA SE OS TIPOS SAO IGUAIS E SAO `CADEIA`
            if(
                (!strcmp(tipo_variavel_esquerda, tipo_variavel_direita)) &&
                (!strcmp(tipo_variavel_esquerda, "CADEIA"))
            ) {
                novo_valor_string = strdup(retornar_valor_string_de_variavel(lista_atual, $4.identificador));
                string_concatenada = strdup(concatenar_strings(novo_valor_string, $6.cadeia));
                atualizar_variavel_cadeia(lista_atual, $2.identificador, string_concatenada);
            }
        }
    } |
    TIPOCAD IDENTIFICADOR IGUAL STRING SOMA IDENTIFICADOR TERMINADOR {
        char* string_concatenada = NULL,
            *novo_valor_string = NULL,
            *tipo_variavel_esquerda = strdup(retornar_tipo_da_variavel(lista_atual, $2.identificador)),
            *tipo_variavel_direita = strdup(retornar_tipo_da_variavel(lista_atual, $6.identificador));
        
        // VERIFICANDO SE AS VARIAVEIS REALMENTE EXISTEM
        if(strcmp(tipo_variavel_esquerda, CHAR_ERRO) && strcmp(tipo_variavel_direita, CHAR_ERRO)) {
            // VERIFICA SE OS TIPOS SAO IGUAIS E SAO `CADEIA`
            if(
                (!strcmp(tipo_variavel_esquerda, tipo_variavel_direita)) &&
                (!strcmp(tipo_variavel_esquerda, "CADEIA"))
            ) {
                novo_valor_string = strdup(retornar_valor_string_de_variavel(lista_atual, $6.identificador));
                string_concatenada = strdup(concatenar_strings($4.cadeia, novo_valor_string));
                atualizar_variavel_cadeia(lista_atual, $2.identificador, string_concatenada);
            }
        }
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
    lista_atual->ptr_primeiro_lista = ptr_novo_elo;

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

lista_t* push() {
    // REINICIANDO A LISTA
    lista_t* lista = NULL;
    lista = criar_lista();

    // INSERINDO UMA NOVA LISTA NO TOPO DA PILHA
    lista->ptr_proxima_lista = escopo_atual->ptr_topo;   

    // ATUALIZANDO OS PONTEIROS
    escopo_atual->ptr_topo = lista;

    return lista;
}

lista_t* pop() {
    lista_t* temp = NULL;

    // VERIFICANDO SE A PILHA ESTA VAZIA
    if(vazia(escopo_atual)) {
        printf("Erro. Pilha vazia.\n");
        return temp;
    }

    // SALVANDO A LISTA QUE ESTA NO TOPO DA PILHA
    temp = escopo_atual->ptr_topo;

    // REMOVENDO O TOPO DA PILHA E EXCLUINDO DA MEMORIA
    escopo_atual->ptr_topo = escopo_atual->ptr_topo->ptr_proxima_lista;
    free(temp);

    return escopo_atual->ptr_topo;
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

            break;
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
            break;
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
    }

    // SE no NAO FOI ATUALIZANDO, REPORTAR ERRO E ABORTAR FUNCAO
    if(no == NULL) {
        printf("Erro: variavel não declarada.\n");
        return;
    }

    // ITERANDO PELA LISTA PARA PEGAR O no CORRETO
    while(no != NULL) {
        if(!strcmp(no->nome_variavel, nome_variavel)) {
            strcpy(no->valor_string, novo_valor);
            break;
        }

        no = no->ptr_proximo_no;
    }

    return;
}

int retornar_valor_inteiro_de_variavel(lista_t* lista, char* nome_variavel) {
    node_t* no = NULL;
    lista_t* temp = NULL;

    // VERIFICA SE VARIAVEL ESTA NO ESCOPO ATUAL
    if(variavel_no_escopo_atual(lista, nome_variavel)) {
        no = lista->ptr_primeiro_lista;
    } else { // SE NAO EXISTIR, BUSCAR NA PILHA
        temp = retornar_escopo(escopo_atual, nome_variavel);

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

        no = no->ptr_proximo_no;
    }

    // SE CHEGOU ATE AQUI, ENTAO HA ERRO
    printf("Erro: tipos incompativeis.\n");
    return INT_MIN;
}

char* retornar_valor_string_de_variavel(lista_t* lista, char* nome_variavel) {
    node_t* no = NULL;
    lista_t* temp = NULL;

    // VERIFICA SE VARIAVEL ESTA NO ESCOPO ATUAL
    if(variavel_no_escopo_atual(lista, nome_variavel)) {
        no = lista->ptr_primeiro_lista;
    } else { // SE NAO EXISTIR, BUSCAR NA PILHA
        temp = retornar_escopo(escopo_atual, nome_variavel);

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

        no = no->ptr_proximo_no;
    }

    // SE CHEGOU ATE AQUI, ENTAO HA ERRO
    printf("Erro: tipos incompativeis.\n");
    return CHAR_ERRO;
}

char* concatenar_strings(const char* string1, const char* string2) {
    char* resultado = NULL;
    char* aux = NULL;
    
    // COPIANDO PRIMEIRA PARTE DA STRING
    strcpy(resultado, string1);
    resultado[strlen(resultado) - 1] = '\0';

    // COPIANDO SEGUNDA PARTE DA STRING
    strncpy(aux, (string2 + 1), (strlen(string2) - 1));
    strcat(resultado, aux);

    return resultado;
}

char* retornar_tipo_da_variavel(lista_t* lista, char* nome_variavel) {
    node_t* no = NULL;
    lista_t* temp = NULL;
    char* tipo = NULL;

    // VERIFICA SE VARIAVEL ESTA NO ESCOPO ATUAL
    if(variavel_no_escopo_atual(lista, nome_variavel)) {
        no = lista->ptr_primeiro_lista;
    } else { // SE NAO EXISTIR, BUSCAR NA PILHA
        temp = retornar_escopo(escopo_atual, nome_variavel);

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

        no = no->ptr_proximo_no;
    }

    return tipo;
}
