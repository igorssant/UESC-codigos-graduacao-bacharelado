%{
int yylex(void);
void yyerror(char* s);
#include <stdlib.h>
%}

%token NUMBER IDENT

%%
list:        {printf("R1");}
  | list any {printf("R2");}
;
any: NUMBER	{printf("R3");}
   | IDENT 	{printf("R4");}
    ;
%%


extern FILE *yyin;
int main() {
	do { 
		yyparse(); 
	} while (!feof(yyin));
}

void yyerror(char *s) {
   fprintf(stderr, "%s\n", s);
}


