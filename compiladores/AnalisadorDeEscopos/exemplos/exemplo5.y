%{
int yylex(void);
void yyerror(char* s);
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct node {
	char id[10]; 
	int value;
	struct node *next;
} node_t;

node_t *head = NULL;
node_t* insert (node_t* l, char *lex, int value);
node_t* show (node_t* l);
%}

%token NUMBER IDENT MAIS MENOS IGUAL TERM
%left MENOS
%union 
{
	int number;
    char *string;
}

%%
list: list exp
      |
      ;
exp: IDENT IGUAL NUMBER TERM {
	head = insert(head, $1.string, $3.number); 
	show(head);
} 
;
%%

extern FILE *yyin;

int main() {
	do { 
		yyparse(); 
	} while (!feof(yyin));
}

void yyerror(char *s) {
   fprintf(stderr, "erro: %s\n", s);
}

node_t* insert (node_t* l, char *lex, int value)
{
	node_t* novo = (node_t*) malloc(sizeof(l));
	strcpy(novo->id, lex);
	novo->value = value;
	novo->next=l;
	return novo;
}
node_t* show (node_t* l)
{
	node_t* p;
	for (p = l; p != NULL; p = p->next)
		printf("%s %d \n", p->id, p->value);
};




