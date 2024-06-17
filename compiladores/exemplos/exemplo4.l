%{
#include <stdio.h>
#include "y.tab.h"
extern int yylval;
%}


%% 
"-"?[0-9]+? {printf("NUMBER"); yylval=atoi(yytext); return NUMBER;}
[a-zA-Z]+ return IDENT;
= return IGUAL;
"+" return MAIS;
"-" return MENOS;
%%

