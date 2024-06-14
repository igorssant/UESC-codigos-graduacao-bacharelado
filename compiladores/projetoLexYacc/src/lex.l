%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "y.tab.h"
%}

%%
[0-9]+                  { yylval.numero = atoi(yytext); return INTEIRO; }
[a-z]+(_?[a-zA-Z0-9]?)* { yylval.identificador = strdup(yytext); return IDENTIFICADOR; }
_[a-z]+[a-z0-9]*_       { return BLOCO; }
\"([^\n^\"])*\"         { yylval.cadeia = strdup(yytext); return STRING; }
"NUMERO"                { return TIPONUM; }
"CADEIA"                { return TIPOCAD; }
"PRINT"                 { return PRINT; }
"BLOCO"                 { return NOVO; }
"FIM"                   { return FIM; }
"="                     { return IGUAL; }
"+"                     { return SOMA; }
"("                     { return ABRPAREN; }
")"                     { return FECPAREN; }
"-"                     { return MENOS; }
";"                     { return TERMINADOR; }
%%
