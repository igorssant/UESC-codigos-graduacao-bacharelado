compilar() {
	mpijavac -cp "/usr/local/lib/mpi.jar" ./src/VetorDistancia.java ./src/No.java
	mv ./src/VetorDistancia.class ~/Documents/trabalho/vetorDistancias
	mv ./src/No.class ~/Documents/trabalho/vetorDistancias
}

executar() {
	mpirun -np 8 java VetorDistancia $1
}

mover_para_pasta_out() {
	mv ./VetorDistancia.class ./out/
	mv ./No.class ./out/
}

main() {
	compilar 0
	executar $1
	mover_para_pasta_out 0
}

main $1
