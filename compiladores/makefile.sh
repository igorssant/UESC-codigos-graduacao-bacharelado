#!/usr/bin/bash

flex ./src/lex.l
mv ./lex.yy.c ./src/
yacc -d ./src/yacc.y
mv ./y.tab.c ./src/
mv ./y.tab.h ./src/
gcc -c ./src/lex.yy.c ./src/y.tab.c
mv ./lex.yy.o ./src/
mv ./y.tab.o ./src/
gcc -o executavel ./src/lex.yy.o ./src/y.tab.o -lfl
mv ./executavel ./out/
./out/executavel
