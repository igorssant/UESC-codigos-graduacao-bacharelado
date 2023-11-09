/**
 * @author igorsssantana
 */
package view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import model.MetodoNewtonRaphson;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;
import utils.LidarComArquivo;

public class Main{
    /* Objeto com escopo de classe */
    public static MetodoNewtonRaphson objetoNewton;
    
    /**
     * @param modeloFuncao
     * @param xAnterior
     * @return 
     */
    public static double funcao(String modeloFuncao, double xAnterior){
        Function funcao = new Function(modeloFuncao);
        Argument argumento = new Argument("x = " + Double.toString(xAnterior));
        Expression instancia = new Expression("f(x)", funcao, argumento);
        return instancia.calculate();
    }
    
    /**
     * @param modeloDerivada
     * @param xAnterior
     * @return 
     */
    public static double derivada(String modeloDerivada, double xAnterior){
        Function derivada = new Function(modeloDerivada);
        Argument argumento = new Argument("x = " + Double.toString(xAnterior));
        Expression instancia = new Expression("d(x)", derivada, argumento);
        return instancia.calculate();
    }
    
    public static void metodoNewtonRaphson(String funcao, String derivada, int maximoDeIteracoes){
        int contador = 0;
        
        do{
            objetoNewton.setFxAnterior(
                funcao(
                        funcao,
                        objetoNewton.getxAnterior()
                )
            );
            objetoNewton.setDerivadaFxAnterior(
                    derivada(
                            derivada,
                            objetoNewton.getxAnterior()
                    )
            );
            objetoNewton.gerarNovoXk();
            objetoNewton.gerarErro();
            
            if(objetoNewton.getErro() < 1.0){
                break;
            }

            contador++;
        } while( objetoNewton.getxAtual() >= objetoNewton.getTolerancia() && contador < maximoDeIteracoes );
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, NumberFormatException{
        ArrayList<String> conteudoLido;
        String funcao = "",
                derivada = "";
        int maximoDeIteracoes = 10;
        double  xAnterior = 0.0,
                tolerancia = 0.0;
        
        try{
            conteudoLido = new ArrayList<>(LidarComArquivo.lerDeArquivo());
            funcao = conteudoLido.get(0);
            derivada = conteudoLido.get(1);
            xAnterior = Double.parseDouble(conteudoLido.get(2));
            tolerancia = Double.parseDouble(conteudoLido.get(3));

            /* so executa abaixo se a lista retornada tiver tamanho */
            if(conteudoLido.size() > 4){
                maximoDeIteracoes = Integer.parseInt(conteudoLido.get(4));
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
        
        objetoNewton = new MetodoNewtonRaphson(xAnterior, tolerancia);
        metodoNewtonRaphson(funcao, derivada, maximoDeIteracoes);
        
        try{
            LidarComArquivo.escreverEmArquivo(objetoNewton.getxAtual());
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
    } // Fim de metodo main
} // Fim de classe Main