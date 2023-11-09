package utils;

/**
 * @author igorsssantana
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LidarComArquivo{
    /*
    * As duas constantes abaixo devem conter o caminho absoluto
    * do projeto java em seu computador.
    * Senao, nao sera possivel lidar com entrada e saida
    * de dados.
    */
    final private static String caminhoLeitura = "/home/igorsssantana/Documents/trabalho/NetBeansProjects/regressaoPolinomialContinua/src/entradaDeDados.txt";
    final private static String caminhoEscrita = "/home/igorsssantana/Documents/trabalho/NetBeansProjects/regressaoPolinomialContinua/src/saidaDeDados.txt";
    private static File arquivo = null;
    
    /**
     * Esse metodo nao recebe nenhum parametro.
     * Esse metodo retorna o conteudo contido no arquivo.
     * @return linhaQueFoiLida
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static ArrayList<String> lerDeArquivo() throws FileNotFoundException, IOException{
        Scanner leituraDeArquivo;
        ArrayList<String> linhaQueFoiLida;
        
        arquivo = new File(caminhoLeitura);
        leituraDeArquivo = new Scanner(arquivo);
        linhaQueFoiLida = new ArrayList<>();
        
        while(leituraDeArquivo.hasNextLine()){
            linhaQueFoiLida.add(leituraDeArquivo.nextLine());
        }
        
        leituraDeArquivo.close();
        arquivo = null;
        return linhaQueFoiLida;
    }
    
    /**
     * Esse metodo recebe o conteudo que sera escrito
     * no arquivo. Em formato de String.
     * Nao possui retorno.
     * @param conteudo
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void escreverEmArquivo(String conteudo) throws FileNotFoundException, IOException{
        FileWriter escritaEmArquivo;
        
        arquivo = new File(caminhoEscrita);
        escritaEmArquivo = new FileWriter(arquivo);
        
        escritaEmArquivo.write(conteudo);
        
        escritaEmArquivo.close();
        arquivo = null;
    }
    
    /**
     * Esse metodo recebe o conteudo que sera escrito
     * no arquivo. Em formato de Double.
     * Nao possui retorno.
     * @param conteudo
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void escreverEmArquivo(Double conteudo) throws FileNotFoundException, IOException{
        FileWriter escritaEmArquivo;
        
        arquivo = new File(caminhoEscrita);
        escritaEmArquivo = new FileWriter(arquivo);
        
        escritaEmArquivo.write(Double.toString(conteudo));
        
        escritaEmArquivo.close();
        arquivo = null;
    }
    
    /**
     * Esse metodo recebe o conteudo que sera escrito
     * no arquivo. Em formato de Integer.
     * Nao possui retorno.
     * @param conteudo
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void escreverEmArquivo(int conteudo) throws FileNotFoundException, IOException{
        FileWriter escritaEmArquivo;
        
        arquivo = new File(caminhoEscrita);
        escritaEmArquivo = new FileWriter(arquivo);
        
        escritaEmArquivo.write(Integer.toString(conteudo));
        
        escritaEmArquivo.close();
        arquivo = null;
    }
    
    /**
     * Esse metodo recebe um
     * vetor de Double
     * @param conteudo
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void escreverEmArquivo(double[] conteudo) throws FileNotFoundException, IOException{
        FileWriter escritaEmArquivo;
        
        arquivo = new File(caminhoEscrita);
        escritaEmArquivo = new FileWriter(arquivo);
        
        for(int i = 0; i < conteudo.length; i++){
            escritaEmArquivo.write(Double.toString(conteudo[i]));
            escritaEmArquivo.write(" ");
        }
        
        escritaEmArquivo.close();
        arquivo = null;
    }
    
    /**
     * Esse metodo recebe uma
     * Matriz[M][N] de Double
     * @param conteudo
     * @param linhas
     * @param colunas
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void escreverEmArquivo(double[][] conteudo, int linhas, int colunas) throws FileNotFoundException, IOException{
        FileWriter escritaEmArquivo;
        
        arquivo = new File(caminhoEscrita);
        escritaEmArquivo = new FileWriter(arquivo);
        
        for(int i = 0; i < linhas; i++){
            for(int j = 0; j < colunas; j++){
                escritaEmArquivo.write(Double.toString(conteudo[i][j]));
                
                if(j != colunas - 1)
                    escritaEmArquivo.write(" ");
            }
            escritaEmArquivo.write("\n");
        }
        
        escritaEmArquivo.close();
        arquivo = null;
    }
}