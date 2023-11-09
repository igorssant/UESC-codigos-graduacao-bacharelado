package view;

import controller.CalculaModelosMatematicos;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import model.Integral;
import model.TabelaHermite;
import model.TabelaLaguerre;
import model.TabelaLegendre;
import model.TabelaTchebyshev;
import utils.LidarComArquivo;

/**
 * @author igorsssantana
 */
public class QuadraturaGauss{
    /* ESCOPO DE CLASSE */
    private static Integral integral;
    private static TabelaHermite tabelaHermite;
    private static TabelaLaguerre tabelaLaguerre;
    private static TabelaLegendre tabelaLegendre;
    private static TabelaTchebyshev tabelaTchebyshev;
    
    public static void escolherModeloMatematico(String funcao, String modelo, int qtdPontos){
        switch(modelo){
            case "hermite":
                tabelaHermite = new TabelaHermite(qtdPontos);
                integral = CalculaModelosMatematicos.hermite(funcao, integral, tabelaHermite);
                break;
            
            case "laguerre":
                tabelaLaguerre = new TabelaLaguerre(qtdPontos);
                integral = CalculaModelosMatematicos.laguerre(funcao, integral, tabelaLaguerre);
                break;
            
            case "legendre":
                tabelaLegendre = new TabelaLegendre(qtdPontos);
                integral = CalculaModelosMatematicos.legendre(funcao, integral, tabelaLegendre);
                break;
            
            case "tchebyshev":
                tabelaTchebyshev = new TabelaTchebyshev(qtdPontos);
                integral = CalculaModelosMatematicos.tchebyshev(funcao, integral, tabelaTchebyshev);
                break;
                
            default:
                System.out.println("Erro!\nFunção escolhida não é reconhecida pelo sistema.\nTente novamente.");
                System.exit(-1);
        }
    }
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, NumberFormatException{
        ArrayList<String> conteudoDoArquivo;
        String funcao = "",
                modelo = "legendre";
        int qtdPontos = 0;
        double limiteInferior = 0f,
                limiteSuperior = 0f;
        
        try{
            conteudoDoArquivo = new ArrayList<>(LidarComArquivo.lerDeArquivo());
            funcao = conteudoDoArquivo.get(0);
            qtdPontos = Integer.parseInt(conteudoDoArquivo.get(1));
            limiteInferior = Double.parseDouble(conteudoDoArquivo.get(2));
            limiteSuperior = Double.parseDouble(conteudoDoArquivo.get(3));
            
            if(conteudoDoArquivo.size() > 4){
                modelo = conteudoDoArquivo.get(4).toLowerCase();
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
        
        integral = new Integral(limiteInferior, limiteSuperior);
        escolherModeloMatematico(funcao, modelo, qtdPontos);
        
        try{
            LidarComArquivo.escreverEmArquivo(integral.getIntegralCalculada());
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