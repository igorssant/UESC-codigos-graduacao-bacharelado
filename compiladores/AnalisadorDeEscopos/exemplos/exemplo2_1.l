%{
#include <stdio.h>
int nwords=0;
%}

%%
"-"?[0-9]+("."[0-9]+)? {printf("NUMBER");}
mostrar {printf("%d\n", nwords);}
[a-z][a-z0-9_]* { printf("comprimento de %s é %d\n", yytext, yyleng); nwords += 1; }
%%


