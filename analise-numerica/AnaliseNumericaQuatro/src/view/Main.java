/**
 * @author igorsssantana
 */
package view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import model.MetodoSecante;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;
import utils.LidarComArquivo;

public class Main{
    /* Objeto com escopo de classe */
    public static MetodoSecante objetoSecante;
    
    /**
     * Metodo funcao.
     * Recebe como parametros
     * modelo da funcao como String
     * e X para aplicar a funcao.
     * Retorna um double, a saida para x.
     * @param modeloFuncao
     * @param xK
     * @return 
     */
    public static double funcao(String modeloFuncao, double xK){
        Function funcao = new Function(modeloFuncao);
        Argument argumento = new Argument("x = " + Double.toString(xK));
        Expression instancia = new Expression("f(x)", funcao, argumento);
        return instancia.calculate();
    }
    
    /**
     * Metodo metodoDaSecante.
     * Recebe como parametros
     * modelo da funcao como String
     * e o numero maximo de interacoes.
     * Nao tem retorno.
     * @param funcao
     * @param maximoDeIteracoes 
     */
    public static void metodoDaSecante(String funcao, int maximoDeIteracoes){
        int contador = 0;
        
        do{
            objetoSecante.setFxK(
                funcao(
                        funcao,
                        objetoSecante.getxK()
                )
            );
            objetoSecante.setFxKMais1(
                funcao(
                        funcao,
                        objetoSecante.getxKMais1()
                )
            );
            objetoSecante.gerarNovoXKMaisUm();
            objetoSecante.gerarErro();
            
            if(objetoSecante.getErro() < objetoSecante.getTolerancia()){
                break;
            }

            contador++;
        } while( objetoSecante.getxKMais1() >= objetoSecante.getTolerancia() && contador < maximoDeIteracoes );
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, NumberFormatException{
        ArrayList<String> conteudoLido;
        String funcao = "";
        int maximoDeIteracoes = 10;
        double xK = 0.0,
                xKMais1 = 0.0,
                tolerancia = 0.0;
        
        try{
            conteudoLido = new ArrayList<>(LidarComArquivo.lerDeArquivo());
            funcao = conteudoLido.get(0);
            xK = Double.parseDouble(conteudoLido.get(1));
            xKMais1 = Double.parseDouble(conteudoLido.get(2));
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
        
        objetoSecante = new MetodoSecante(xK, xKMais1, tolerancia);
        metodoDaSecante(funcao, maximoDeIteracoes);
        
        try{
            LidarComArquivo.escreverEmArquivo(objetoSecante.getxKMais1());
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
    } // fim de metodo Main
} // fim de classe Main