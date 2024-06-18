%{
int yylex(void);
void yyerror(char* s);
#include <stdlib.h>
%}


%token NUMBER IDENT MAIS MENOS IGUAL
%left MENOS

%%
s: //vazio
   | s exp   {printf("%d", $2);}
     ;
exp: termo MENOS termo {$$ = $1-$3}
   | termo MAIS termo {$$ = $1+$3}
   ;
termo: NUMBER {$$ = $1}
     | MENOS NUMBER {$$ = 0-$2}
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

