%{
#include <stdio.h>
#include "y.tab.h"
#include <string.h>
%}

%% 
[0-9]+? {yylval.number=atoi(yytext); return NUMBER;}
[a-zA-Z]+ {yylval.string=strdup(yytext); return IDENT;}
= return IGUAL;
"+" return MAIS;
";" return TERM;
%%


