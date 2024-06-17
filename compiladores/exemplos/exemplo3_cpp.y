%{
extern "C"   
    {   
            int yyparse(void);   
            int yylex(void);   
            void yyerror(char* s);
            int yywrap()   
            {   
                    return 1;   
            }   
  
    }
#include <stdlib.h>
#include <iostream>
#include <cstring>
using namespace std;
%}

%token NUMBER IDENT

%%
list:        {cout << "R1";}
  | list any {cout << "R2";}
;
any: NUMBER	{cout << "R3";}
   | IDENT 	{cout << "R4";}
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


