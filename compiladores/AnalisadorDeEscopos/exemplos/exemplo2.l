
%{
#include <stdio.h>
int nwords=0;
%}

%%
"-"?[0-9]+("."[0-9]+)? printf("NUMBER");
[a-z][a-z0-9_]* { printf("ident: %s\n", yytext); nwords += 1; }
%%

