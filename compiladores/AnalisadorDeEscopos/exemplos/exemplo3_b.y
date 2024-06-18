%{
int yylex(void);
void yyerror(char* s);
#include <stdlib.h>
%}

%token NUMBER IDENT IGUAL

%%
s: 
  | s at  {printf("R0");}
;
at: IDENT IGUAL any {printf("R1");}
;
any: NUMBER	{printf("R2");}
   | IDENT 	{printf("R3");}
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

