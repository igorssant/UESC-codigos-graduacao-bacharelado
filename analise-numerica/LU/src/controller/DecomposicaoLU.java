package controller;

import static java.util.stream.IntStream.range;

/**
 * @author igorsssantana
 */
public class DecomposicaoLU{
    /**
     * @param vetorA
     * @param vetorB
     * @return produto escalar entre os vetores
     */
    public static double multiplicacaoPorEscalar(double[] vetorA, double[] vetorB) {
        return range(0, vetorA.length).mapToDouble(i -> vetorA[i] * vetorB[i]).sum();
    }

    /**
     * @param matrizA
     * @param matrizB
     * @return multiplicacao entre matrizes
     */
    public static double[][] multiplicacaoEntreMatrizes(double[][] matrizA, double[][] matrizB) {
        double[][] resultado = new double[matrizA.length][matrizB[0].length];
        double[] auxiliar = new double[matrizB.length];

        for(int j = 0; j < matrizB[0].length; j++){
            for(int k = 0; k < matrizB.length; k++){
                auxiliar[k] = matrizB[k][j];
            }
            
            for(int i = 0; i < matrizA.length; i++){
                resultado[i][j] = multiplicacaoPorEscalar(matrizA[i], auxiliar);
            }
        }
        
        return resultado;
    }
    
    /**
     * metodo para realizar
     * o pivoteamento de uma matriz
     * m X m (quadrada)
     * @param matriz
     * @return matriz pivoteada
     */
    public static double[][] pivoteamento(double[][] matriz){
        int tamanho = matriz.length;
        double maximo = 0f;
        double [] auxiliar = null;
        double[][] pivoteada = range(0, tamanho)
                .mapToObj(j -> range(0, tamanho)
                .mapToDouble(i -> i == j ? 1 : 0).toArray())
                .toArray(double[][]::new);

        for(int i = 0; i < tamanho; i++){
            maximo = matriz[i][i];
            int linha = i;
            
            for(int j = i; j < tamanho; j++){
                if(matriz[j][i] > maximo){
                    maximo = matriz[j][i];
                    linha = j;
                }
            }
            
            if(i != linha){
                auxiliar = pivoteada[i];
                pivoteada[i] = pivoteada[linha];
                pivoteada[linha] = auxiliar;
            }
        }
        
        return pivoteada;
    }
    
    /**
     * metodo de decomposicao LU
     * recebe uma matriz quadrada
     * retorna sua decomposiocao LU
     * @param matrizA
     * @return matriz decomposta LU
     */
    public static double[][][] lu(double[][] matrizA) {
        int tamanho = matrizA.length;
        double somatorio1 = 0f,
                somatorio2 = 0f;
        double [][] L = new double[tamanho][tamanho],
            U = new double[tamanho][tamanho],
            P = pivoteamento(matrizA),
            matrizA2 = multiplicacaoEntreMatrizes(P, matrizA);

        for(int j = 0; j < tamanho; j++){
            L[j][j] = 1;
            
            for (int i = 0; i < j + 1; i++){
                somatorio1 = 0f;
                
                for(int k = 0; k < i; k++){
                    somatorio1 += U[k][j] * L[i][k];
                }
                
                U[i][j] = matrizA2[i][j] - somatorio1;
            }
            
            for(int i = j; i < tamanho; i++){
                somatorio2 = 0f;
                
                for(int k = 0; k < j; k++){
                    somatorio2 += U[k][j] * L[i][k];
                }
                
                L[i][j] = (matrizA2[i][j] - somatorio2) / U[j][j];
            }
        }
        
        return new double[][][]{L, U, P};
    }
}