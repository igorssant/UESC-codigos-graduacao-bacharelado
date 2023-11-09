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
public class TresOitavos{
    /* ESCOPO DE CLASSE */
    private static Integral tresOitavos;
    
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
    public static void calculaIntegralTresOitavos(String funcao){
        if(tresOitavos.getH() == 0f){
            tresOitavos.calculaNovoH();
        }
        
        tresOitavos.setIntegralCalculada(
            (3f / 8f) * tresOitavos.getH()
            * (
                funcao(funcao, tresOitavos.getPontos(0))
                + 3 * (funcao(funcao, tresOitavos.getPontos(1)))
                + 3 * (funcao(funcao, tresOitavos.getPontos(2)))
                + funcao(funcao, tresOitavos.getPontos(3))
            )
        );
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, NumberFormatException{
        ArrayList<String> conteudoDoArquivo;
        String funcao = "";
        double h = 0f;
        double [] pontos = new double[4];
        
        try{
            conteudoDoArquivo = new ArrayList<>(LidarComArquivo.lerDeArquivo());
            funcao = conteudoDoArquivo.get(0);
            
            for(int i = 0; i < 4; i++){
                pontos[i] = Double.parseDouble(conteudoDoArquivo.get(i+1));
            }
            
            if(conteudoDoArquivo.size() > 5){
                h = Double.parseDouble(conteudoDoArquivo.get(5));
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
        
        if(h != 0f){
            tresOitavos = new Integral(pontos, h);
        }
        
        else{
            tresOitavos = new Integral(pontos);
        }
        
        calculaIntegralTresOitavos(funcao);
        
        try{
            LidarComArquivo.escreverEmArquivo(tresOitavos.getIntegralCalculada());
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