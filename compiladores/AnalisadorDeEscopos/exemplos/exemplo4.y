%{
int yylex(void);
void yyerror(char* s);
#include <stdlib.h>
%}


%token NUMBER IDENT MAIS MENOS IGUAL
%left MENOS

%%
exp: // vazio
     | exp termo   {printf("%d", $2);}
     ;
termo: NUMBER {$$ = $1}
     | NUMBER MAIS NUMBER {$$ = $1+$3}
     | NUMBER MENOS NUMBER {$$ = $1-$3}
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

