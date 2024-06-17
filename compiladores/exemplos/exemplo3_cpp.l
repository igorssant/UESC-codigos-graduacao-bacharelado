%{
#include <stdio.h>
#include "y.tab.h"
int nwords=0;
%}


%% 
"-"?[0-9]+("."[0-9]+)? return NUMBER;
mostrar printf("Numero de palavras %d", nwords);
[a-zA-Z]+ {nwords++; return IDENT;}
%%

