#!/bin/bash

flex ./src/lex.l
mv ./lex.yy.c ./test/
gcc ./test/lex.yy.c -o lex -lfl
mv ./lex ./test/out/
./test/out/lex
