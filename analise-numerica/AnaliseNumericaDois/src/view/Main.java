/**
 * @author igorsssantana
 */
package view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import model.PosicaoFalsa;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;
import utils.LidarComArquivo;

public class Main{
    /* Objeto do escopo da classe */
    public static PosicaoFalsa posicaofalsa;
    
    /**
     * Metodo para calcular f(entrada)
     * Redebe dois parametros: funcao e entrada para a funcao.
     * A funcao deve ser String.
     * A entrada deve ser Double.
     * Retorna f(entrada).
     * @param modeloFuncao
     * @param modeloArgumento
     * @return 
     */
    public static double funcao(String modeloFuncao, Double modeloArgumento){
        Function funcao = new Function(modeloFuncao);
        Argument argumento = new Argument("x = " + Double.toString(modeloArgumento));
        Expression instancia = new Expression("f(x)", funcao, argumento);
        return instancia.calculate();
    }

    /**
     * Trata o objeto PosicaoFalsa.
     * Salva a raiz tolerada em PosicaoC.
     * @param limiteDeIteracoes
     */
    public static void posicaoFalsa(String funcao, int limiteDeIteracoes){
        int contador = 0;
        posicaofalsa.gerarPosicaoC();
        /* O laco termina quando o valor da posicao C for menor da tolerancia aceitavel */
        while( Math.abs(posicaofalsa.getPosicaoC()) > posicaofalsa.getTolerancia() && contador < (1 + limiteDeIteracoes) ){
            posicaofalsa.gerarPosicaoC();
            posicaofalsa.setFc(
                    funcao(
                            funcao,
                            posicaofalsa.getPosicaoC()
                    )
            );

            if(posicaofalsa.getFa() * posicaofalsa.getFc() < 0){
                posicaofalsa.setLimiteB(posicaofalsa.getPosicaoC());
                posicaofalsa.setFb(posicaofalsa.getFc());
            }
            
            else{
                posicaofalsa.setLimiteA(posicaofalsa.getPosicaoC());
                posicaofalsa.setFa(posicaofalsa.getFc());
            }

            contador++;
            // System.out.println("Valor de f(c) = " + posicaofalsa.getFc() + "\nResultado final = " + posicaofalsa.getPosicaoC());
        }
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, NumberFormatException{
        ArrayList<String> conteudoDoArquivo;
        int limiteDeIteracoes = 10;
        String funcao = "",
                auxiliar = "";
        double limiteA = 0.0,
                limiteB = 0.0,
                tolerancia = 0.0,
                fa = 0.0,
                fb = 0.0;
        
        try{
            conteudoDoArquivo = new ArrayList<>(LidarComArquivo.lerDeArquivo());
            funcao = conteudoDoArquivo.get(0);
            limiteA = Double.parseDouble(conteudoDoArquivo.get(1));
            limiteB = Double.parseDouble(conteudoDoArquivo.get(2));
            tolerancia = Double.parseDouble(conteudoDoArquivo.get(3));
            /* so executa abaixo se a lista retornada tiver tamanho */
            if(conteudoDoArquivo.size() > 4){
                limiteDeIteracoes = Integer.parseInt(conteudoDoArquivo.get(4));
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
        
        fa = funcao(funcao, limiteA);
        fb = funcao(funcao, limiteB);
        posicaofalsa = new PosicaoFalsa(limiteA, limiteB, tolerancia, fa, fb);
        posicaoFalsa(funcao, limiteDeIteracoes);
        
        try{
            LidarComArquivo.escreverEmArquivo(posicaofalsa.getPosicaoC());
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
    } // Fim do metodo main
} // Fim da classe Main