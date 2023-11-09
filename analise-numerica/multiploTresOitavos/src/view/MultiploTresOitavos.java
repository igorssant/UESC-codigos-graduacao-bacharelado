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
public class MultiploTresOitavos{
    private static Integral multiTresOitavos;
    
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
    
    public static void integralMultiTresOitavos(String funcao){
        double auxiliar = 0f;
        
        multiTresOitavos.calculaNovoH();
        multiTresOitavos.setIntegralCalculada(
            funcao(
                funcao,
                multiTresOitavos.getLimiteInferior()
            ) + funcao(
                funcao,
                multiTresOitavos.getLimiteSuperior()
            )
        );
        
        
        for(int i = 1; i < multiTresOitavos.getIntervalo(); i++){
            auxiliar = multiTresOitavos.getLimiteInferior() + (multiTresOitavos.getH() * i);
            
            if(i % 3 == 0){
                multiTresOitavos.setIntegralCalculada(
                    multiTresOitavos.getIntegralCalculada()
                    + (2 * funcao(
                        funcao,
                        auxiliar
                    ))
                );
            }
            
            else{
                multiTresOitavos.setIntegralCalculada(
                    multiTresOitavos.getIntegralCalculada()
                    + (3 * funcao(
                        funcao,
                        auxiliar
                    ))
                );
            }
        }
        
        multiTresOitavos.setIntegralCalculada(
            multiTresOitavos.getIntegralCalculada() * ((3 * multiTresOitavos.getH()) / 8)
        );
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, NumberFormatException{
        ArrayList<String> conteudoDoArquivo;
        String funcao = "";
        int qtdSubintervalos = 0;
        double x0 = 0f,
                xi = 0f;
        
        try{
            conteudoDoArquivo = new ArrayList<>(LidarComArquivo.lerDeArquivo());
            funcao = conteudoDoArquivo.get(0);
            x0 = Double.parseDouble(conteudoDoArquivo.get(1));
            xi = Double.parseDouble(conteudoDoArquivo.get(2));
            qtdSubintervalos = Integer.parseInt(conteudoDoArquivo.get(3));
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
        
        multiTresOitavos = new Integral(x0, xi, qtdSubintervalos);
        integralMultiTresOitavos(funcao);
        
        try{
            LidarComArquivo.escreverEmArquivo(multiTresOitavos.getIntegralCalculada());
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