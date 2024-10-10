import java.util.Arrays;
import java.util.Random;

public class Main {
    public static Integer[][] gerarMatriz(final Integer linhas, final Integer colunas) {
        Integer[][] matriz = new Integer[linhas][colunas];
        Random gerador = new Random();

        for(int i = 0; i < linhas; i++) {
            for(int j = 0; j < colunas; j++) {
                matriz[i][j] = gerador.nextInt(0, 10);
            }
        }

        return matriz;
    }

    public static void imprimirMatriz(final Integer[][] matriz) {
        for(int i = 0; i < matriz.length; i++) {
            System.out.println(Arrays.toString(matriz[i]));
        }
    }

   public static void imprimirDuasMatrizes(final Integer[][] matrizA, final Integer[][] matrizB) {
       System.out.println("MATRIZ 1:");
       imprimirMatriz(matrizA);
       System.out.println("\nMATRIZ 2:");
       imprimirMatriz(matrizB);
   }

   public static Integer[][] multiplicaDuasMatrizes(final Integer[][] matrizA, final Integer[][] matrizB) {
        Integer[][] matrizResultante = new Integer[matrizA.length][matrizB.length];

        matrizResultante[0][0] = matrizA[0][0] * matrizB[0][0] + matrizA[1][0] * matrizB[0][1] + matrizA[2][0] * matrizB[0][2] + matrizA[3][0] * matrizB[0][3];
        matrizResultante[0][1] = matrizA[0][0] * matrizB[1][0] + matrizA[1][0] * matrizB[1][1] + matrizA[2][0] * matrizB[1][2] + matrizA[3][0] * matrizB[1][3];
        matrizResultante[0][2] = matrizA[0][0] * matrizB[2][0] + matrizA[1][0] * matrizB[2][1] + matrizA[2][0] * matrizB[2][2] + matrizA[3][0] * matrizB[1][3];
        matrizResultante[0][3] = matrizA[0][0] * matrizB[3][0] + matrizA[1][0] * matrizB[3][1] + matrizA[2][0] * matrizB[3][2] + matrizA[3][0] * matrizB[1][3];
        // --- --- ---
        matrizResultante[1][0] = matrizA[0][1] * matrizB[0][0] + matrizA[1][1] * matrizB[0][1] + matrizA[1][1] * matrizB[0][2] + matrizA[3][1] * matrizB[0][3];
        matrizResultante[1][1] = matrizA[0][1] * matrizB[1][0] + matrizA[1][1] * matrizB[1][1] + matrizA[2][1] * matrizB[1][2] + matrizA[3][1] * matrizB[1][3];
        matrizResultante[1][2] = matrizA[0][1] * matrizB[2][0] + matrizA[1][1] * matrizB[2][1] + matrizA[2][1] * matrizB[2][2] + matrizA[3][1] * matrizB[1][3];
        matrizResultante[1][3] = matrizA[0][1] * matrizB[3][0] + matrizA[1][1] * matrizB[3][1] + matrizA[2][1] * matrizB[3][2] + matrizA[3][1] * matrizB[1][3];
        // --- --- ---
        matrizResultante[2][0] = matrizA[0][2] * matrizB[0][0] + matrizA[1][2] * matrizB[0][1] + matrizA[2][2] * matrizB[0][2] + matrizA[3][2] * matrizB[0][3];
        matrizResultante[2][1] = matrizA[0][2] * matrizB[1][0] + matrizA[1][2] * matrizB[1][1] + matrizA[2][2] * matrizB[1][2] + matrizA[3][2] * matrizB[1][3];
        matrizResultante[2][2] = matrizA[0][2] * matrizB[2][0] + matrizA[1][2] * matrizB[2][1] + matrizA[2][2] * matrizB[2][2] + matrizA[3][2] * matrizB[1][3];
        matrizResultante[2][3] = matrizA[0][2] * matrizB[3][0] + matrizA[1][2] * matrizB[3][1] + matrizA[2][2] * matrizB[3][2] + matrizA[3][2] * matrizB[1][3];
        // --- --- ---
        matrizResultante[3][0] = matrizA[0][3] * matrizB[0][0] + matrizA[1][3] * matrizB[0][1] + matrizA[1][3] * matrizB[0][2] + matrizA[3][3] * matrizB[0][3];
        matrizResultante[3][1] = matrizA[0][3] * matrizB[1][0] + matrizA[1][3] * matrizB[1][1] + matrizA[2][3] * matrizB[1][2] + matrizA[3][3] * matrizB[1][3];
        matrizResultante[3][2] = matrizA[0][3] * matrizB[2][0] + matrizA[1][3] * matrizB[2][1] + matrizA[2][3] * matrizB[2][2] + matrizA[3][3] * matrizB[1][3];
        matrizResultante[3][3] = matrizA[0][3] * matrizB[3][0] + matrizA[1][3] * matrizB[3][1] + matrizA[2][3] * matrizB[3][2] + matrizA[3][3] * matrizB[1][3];

        return matrizResultante;
    }

    public static void main(String[] args) {
        final Integer linhas = 4,
            colunas = 4;
        Integer[][] matrizA = gerarMatriz(linhas, colunas),
            matrizB = gerarMatriz(linhas, colunas),
            matrizResultante = null;

        imprimirDuasMatrizes(matrizA, matrizB);
        matrizResultante = multiplicaDuasMatrizes(matrizA, matrizB);
        System.out.println("\nMATRIZ RESULTANTE:");
        imprimirMatriz(matrizResultante);
    }
}
