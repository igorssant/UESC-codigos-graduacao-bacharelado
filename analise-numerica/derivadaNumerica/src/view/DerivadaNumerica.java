package view;

import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Double.NaN;
import java.util.ArrayList;
import model.Derivada;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;
import utils.LidarComArquivo;

/**
 * @author igorsssantana
 */
public class DerivadaNumerica{
    /* ESCOPO DE CLASSE */
    private static Derivada derivadanumerica;
    
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
    
    public static void primeiraDerivada(String funcao){
        derivadanumerica.setDerivadaPrimeira(
                -funcao(funcao, derivadanumerica.getPontoParaDerivar() + (2 * derivadanumerica.getH()))
                +8 * funcao(funcao, derivadanumerica.getPontoParaDerivar() + derivadanumerica.getH())
                -8 * funcao(funcao, derivadanumerica.getPontoParaDerivar() - derivadanumerica.getH())
                -funcao(funcao, derivadanumerica.getPontoParaDerivar() - (2 * derivadanumerica.getH()))
        );
        
        derivadanumerica.setDerivadaPrimeira(
                derivadanumerica.getDerivadaPrimeira() / (12f * derivadanumerica.getH())
        );
    }
    
    public static void segundaDerivada(String funcao){
        derivadanumerica.setDerivadaSegunda(
                -funcao(funcao, derivadanumerica.getPontoParaDerivar() + (2 * derivadanumerica.getH()))
                +16 * funcao(funcao, derivadanumerica.getPontoParaDerivar() + derivadanumerica.getH())
                -30 * funcao(funcao, derivadanumerica.getPontoParaDerivar())
                -16 * funcao(funcao, derivadanumerica.getPontoParaDerivar() - derivadanumerica.getH())
                -funcao(funcao, derivadanumerica.getPontoParaDerivar() - (2 * derivadanumerica.getH()))
        );
        
        derivadanumerica.setDerivadaSegunda(
                derivadanumerica.getDerivadaSegunda() / (12f * Math.pow(derivadanumerica.getH(), 2))
        );
    }
    
    public static void derivadaNumerica(String funcao, int selecaoDerivada){
        switch(selecaoDerivada){
            case 0:
                primeiraDerivada(funcao);
                break;
                
            case 1:
                segundaDerivada(funcao);
                break;
                
            case 2:
                primeiraDerivada(funcao);
                segundaDerivada(funcao);
                break;
            
            default:
                System.out.println("Não foi possível realizar a derivação.\nOpção incorreta encontrada no arquivo!");
                derivadanumerica.setDerivadaPrimeira(NaN);
                derivadanumerica.setDerivadaSegunda(NaN);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, NumberFormatException{
        ArrayList<String> conteudoDoArquivo;
        String funcao = "";
        int selecaoDerivada = 0;
        double pontoParaDerivar = 0.0,
                h = 0.0;
        
        try{
            conteudoDoArquivo = new ArrayList<>(LidarComArquivo.lerDeArquivo());
            funcao = conteudoDoArquivo.get(0);
            pontoParaDerivar = Double.parseDouble(conteudoDoArquivo.get(1));
            h = Double.parseDouble(conteudoDoArquivo.get(2));
            
            if(conteudoDoArquivo.size() > 3){
                selecaoDerivada = Integer.parseInt(conteudoDoArquivo.get(3));
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
        
        derivadanumerica= new Derivada(pontoParaDerivar, h);
        derivadaNumerica(funcao, selecaoDerivada);
        
        try{
            switch(selecaoDerivada){
                case 0:
                    LidarComArquivo.escreverEmArquivo(derivadanumerica.getDerivadaPrimeira());
                    break;
                    
                case 1:
                    LidarComArquivo.escreverEmArquivo(derivadanumerica.getDerivadaSegunda());
                    break;
                    
                case 2:
                    LidarComArquivo.escreverEmArquivo(derivadanumerica.getDerivadaPrimeira() + "\n" + derivadanumerica.getDerivadaSegunda());
                    break;
                    
                default:
                    System.out.println("Ocorreu um erro inesperado.\nA opção digitada está incorreta!");
            }
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