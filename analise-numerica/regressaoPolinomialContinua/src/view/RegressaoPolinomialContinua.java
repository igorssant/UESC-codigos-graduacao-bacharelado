package view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import utils.LidarComArquivo;
import controller.IntegralMultiplaSimp38;
/* import controller.IntegralMultiplaTrapezio; */
import model.RegressaoPolinomial;
import model.TresOitavos;
/* import model.Integral; */

/**
 * @author igorsssantana
 */
public class RegressaoPolinomialContinua{
    /* ESCOPO DE CLASSE */
    private static TresOitavos integral;
    private static RegressaoPolinomial resultado;
    
    /**
     * 
     * @param funcao
     * @param ordem
     */
    public static void parteUmDaRegressao(String funcao, int ordem){
        /*
         * (1, 1) ==> b^1 / 1 - a^1 / 1
         * 
         * (1, x) ==> b^2 / 2 - a^2 / 2
         * (x, 1) ==> mesmo que acima
         *
         * (x, x) ==> x^2 ==> b^3 / 3 - a^3 / 3
         *
         * (x^2, x) ==> x^3 ==> b^4 / 4 - a^4 / 4
         * (x, x^2) ==> mesmo que acima
         *
         * (x^m, x^m) ==> x^(m+m) ==> b^(2m+1) / (2m+1) - a^(2m+1) / (2m+1)
        */
        String[] vetorDeFuncoes = new String[ordem-1];
        double [] vetorY = new double[ordem-1];
        double [][] matrizResultante = new double[ordem-1][ordem-1];
        
        for(int i = 0; i< ordem-1; i++){
            for(int j = 0; j < ordem-1; j++){
                /* inicializando a matriz com 1 */
                matrizResultante[i][j] = 1f;
            }
            
            /* x^i => x^0, x^1... x^(ordem-2) */
            vetorDeFuncoes[i] = (new StringBuilder()).append("x^").append(i).toString();
            /* inicializando o vetor com 1 */
            vetorY[i] = 1f;
        }
        
        for(int i = 0; i < ordem-1; i++){
            matrizResultante[0][i] = IntegralMultiplaSimp38.integralMultiTresOitavos("f(x) = " + vetorDeFuncoes[i], integral);
            vetorY[i] = IntegralMultiplaSimp38.integralMultiTresOitavos(
                    (new StringBuilder()).append(funcao).append(" * (").append(vetorDeFuncoes[i] + ")").toString(),
                    integral
            );
        }
        
        for(int i = 0; i < ordem-1; i++){
            for(int j = 0; j < ordem-1; j++){
                if(i > j){
                    matrizResultante[i][j] = matrizResultante[j][i];
                }
                
                else{
                    matrizResultante[i][j] = IntegralMultiplaSimp38.integralMultiTresOitavos(
                            (new StringBuilder()).append("f(x) = ").append("(" + vetorDeFuncoes[i]).append(") * (").append(vetorDeFuncoes[j] + ")").toString(),
                            integral
                    );
                }
            }
        }

        resultado = new RegressaoPolinomial(matrizResultante, vetorY);
    }
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, NumberFormatException{
        ArrayList<String> conteudoDoArquivo;
        String funcao = "";
        int ordem = 0;
        double[] pontos = new double[2];
        
        try{
            conteudoDoArquivo = new ArrayList<>(LidarComArquivo.lerDeArquivo());
            funcao = conteudoDoArquivo.get(0);
            ordem = Integer.parseInt(conteudoDoArquivo.get(1));
            
            for(int i = 0; i < 2; i++){
                pontos[i] = Double.parseDouble(conteudoDoArquivo.get(i+2));
            }
            
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
        
        integral = new TresOitavos(pontos[0], pontos[1]);
        integral.setIntervalo(3);
        parteUmDaRegressao(funcao, ordem);
        
        try{
            LidarComArquivo.escreverEmArquivo(resultado.matrizExpandida(ordem), ordem, ordem-1);
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