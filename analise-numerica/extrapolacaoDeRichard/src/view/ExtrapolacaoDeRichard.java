package view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import model.Integral;
import controller.IntegralMultiplaTrapezio;
import static java.lang.Double.NaN;
import utils.LidarComArquivo;

/**
 * @author igorsssantana
 */
public class ExtrapolacaoDeRichard{
    /* ESCOPO DE CLASSE */
    private static Integral multiTrapezio;
    
    public static double integracaoRhomberg(String funcao, int maximoIteracoes, double erroEsperado){
        double[][] integral = new double[10][10];
        double erroAbsoluto = 0f;
        int n = 1,
            j = 0,
            iterador = 0;
        
        multiTrapezio.setIntervalo(1);
        multiTrapezio.gerarNovoH();
        integral[0][0] = IntegralMultiplaTrapezio.integralMultiplaTrapezio(funcao, multiTrapezio);
        
        while(true){
            iterador++;
            n = (int) Math.pow(2, iterador);
            multiTrapezio.setIntervalo(n);
            integral[iterador][0] = IntegralMultiplaTrapezio.integralMultiplaTrapezio(funcao, multiTrapezio);
            
            for(int k = 1; k < iterador; k++){
                j = iterador - k;
                integral[j][k] = (Math.pow(4, k) * integral[j+1][k-1] - integral[j][k-1]) / (Math.pow(4, k) - 1);
            }
            
            erroAbsoluto = Math.abs((integral[0][iterador+1] - integral[0][iterador]) / integral[0][iterador+1]) * 100;
            
            if(iterador > (maximoIteracoes-1) || erroAbsoluto < (0.01 + erroEsperado)){
                break;
            }
        }
        
        return integral[1][maximoIteracoes-1];
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, NumberFormatException{
        ArrayList<String> conteudoDoArquivo;
        String funcao = "";
        int maximoDeIteracoes = 0;
        double integralFinal = NaN,
                erroEsperado = 0f;
        double [] pontos = null;
        
        try{
            conteudoDoArquivo = new ArrayList<>(LidarComArquivo.lerDeArquivo());
            funcao = conteudoDoArquivo.get(0);
            pontos = new double[2];
            
            for(int i = 0; i< 2; i++){
                pontos[i] = Double.parseDouble(conteudoDoArquivo.get(i+1));
            }
            
            maximoDeIteracoes = Integer.parseInt(conteudoDoArquivo.get(3));
            erroEsperado = Double.parseDouble(conteudoDoArquivo.get(4));
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
        
        multiTrapezio = new Integral(pontos);
        integralFinal = integracaoRhomberg(funcao, maximoDeIteracoes, erroEsperado);
        
        try{
            LidarComArquivo.escreverEmArquivo(integralFinal);
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