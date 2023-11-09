package view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import model.Integral;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;
import utils.LidarComArquivo;

/**
 * @author igorsssantana
 */
public class MultiploTrapezoide{
    /* ESCOPO DE CLASSE */
    private static Integral multiTrapezio;
    
    /**
     * Recebe uma funcao em String
     * e um ponto x
     * retorna f(x)
     * @param stringFuncao
     * @param x
     * @return fx = funcao(funcao, derivadanumerica.getPontosX((int) (posicaoDoPonto + derivadanumerica.getH())));
     */
    public static double funcao(String stringFuncao, double x){
        Function funcao = new Function(stringFuncao);
        Argument argumento = new Argument("x = " + Double.toString(x));
        Expression instancia = new Expression("f(x)", funcao, argumento);
        return instancia.calculate();
    }
    
    /**
     * @param funcao 
     */
    public static void integralMultiplaTrapezio(String funcao){
        double somatorio = funcao(
                funcao,
                multiTrapezio.getPontos(0)
        );
        
        for(int i = 1; i < multiTrapezio.getIntervalo(); i++){
            somatorio += 2 * funcao(
                    funcao,
                    multiTrapezio.getPontos(i)
            );
        }
        
        somatorio += funcao(
                funcao,
                multiTrapezio.getPontos(multiTrapezio.getPontos().length-1)
        );
        multiTrapezio.setIntegralCalculada(somatorio);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, NumberFormatException{
        ArrayList<String> conteudoDoArquivo;
        String funcao = "";
        int intervalos = 0;
        double h = 0f;
        double [] pontos = null;
        
        try{
            conteudoDoArquivo = new ArrayList<>(LidarComArquivo.lerDeArquivo());
            funcao = conteudoDoArquivo.get(0);
            h = Double.parseDouble(conteudoDoArquivo.get(1));
            intervalos = Integer.parseInt(conteudoDoArquivo.get(2));
            pontos = new double[intervalos+1];
            
            for(int i = 0; i< intervalos+1; i++){
                pontos[i] = Double.parseDouble(conteudoDoArquivo.get(i+3));
            }
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
        
        multiTrapezio = new Integral(pontos, h, intervalos);
        integralMultiplaTrapezio(funcao);
        
        try{
            LidarComArquivo.escreverEmArquivo(multiTrapezio.getIntegralCalculada());
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