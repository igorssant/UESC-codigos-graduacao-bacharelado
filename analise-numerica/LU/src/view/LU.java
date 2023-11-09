package view;

import controller.DecomposicaoLU;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import model.MatrizLU;
import utils.LidarComArquivo;

/**
 * @author igorsssantana
 */
public class LU{
    /* ESCOPO DE CLASSE */
    private static MatrizLU matrizLU;
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, NumberFormatException{
        ArrayList<ArrayList<Double>> conteudoDoArquivo = null;
        double[][] matriz = null,
                L = null,
                U = null;
        double[][][] resultado = null;
        
        try{
            conteudoDoArquivo = new ArrayList<>(LidarComArquivo.lerMatrizArquivo());
            matriz = conteudoDoArquivo.stream()
                .map(l -> l.stream()
                        .mapToDouble(Double::doubleValue)
                        .toArray()
                )
                .toArray(double[][]::new);
            L = U = new double[matriz[0].length][matriz[0].length];
            
            conteudoDoArquivo.clear();
        }
        
        catch(FileNotFoundException e){
            System.out.println("Não foi possível encontrar o arquivo no caminho passado.\nPor favor digite o caminho correto.");
        }
        
        catch(IOException e){
            System.out.println("Não foi possível ler o conteúdo do arquivo.\nPor favor coloque as permissões corretas, ou o caminho correto.");
        }
        
        catch(NumberFormatException e){
            System.out.println("Ocorreu um erro durante a leitura do arquivo.\nErro de conversão de tipos.");
        }
        
        catch(Exception e){
            System.out.println("1- Ocorreu um erro inesperado!\nPor favor tente novamente.");
        }
        
        matrizLU = new MatrizLU(matriz);
        resultado = DecomposicaoLU.lu(matrizLU.getMatriz());
        
        for(int i = 0; i < resultado[0].length; i++){
            for(int j = 0; j < resultado[0].length; j++){
                L[i][j] = resultado[i][j][0];
                U[i][j] = resultado[i][j][1];
            }
        }
        
        try{
            LidarComArquivo.escreverEmArquivo(L, U);
        }
        
        catch(FileNotFoundException e){
            System.out.println("Não foi possível encontrar o arquivo no caminho passado.\nPor favor digite o caminho correto.");
        }
        
        catch(IOException e){
            System.out.println("Não foi possível escrever o conteúdo do arquivo.\nPor favor coloque as permissões corretas, ou o caminho correto.");
        }
        
        catch(NumberFormatException e){
            System.out.println("Ocorreu um erro durante a escrita do arquivo.\nErro de conversão de tipos.");
        }

        catch(Exception e){
            System.out.println("2- Ocorreu um erro inesperado!\nPor favor tente novamente.");
        }
        
        System.out.println("Finalizado com sucesso!!!");
    }
}