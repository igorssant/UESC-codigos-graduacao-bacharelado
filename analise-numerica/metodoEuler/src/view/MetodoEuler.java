package view;

import controller.CalcularEuler;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import model.Derivada;
import utils.LidarComArquivo;

/**
 * @author igorsssantana
 */
public class MetodoEuler{
    /* ESCOPO DE CLASSE */
    private static Derivada euler;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, NumberFormatException{
        ArrayList<String> conteudoDoArquivo;
        String funcao = "";
        double y = 0.0,
                xi = 0.0,
                xf = 0.0,
                h = 0.0,
                intervaloSaida = 0.0;
        
        try{
            conteudoDoArquivo = new ArrayList<>(LidarComArquivo.lerDeArquivo());
            funcao = conteudoDoArquivo.get(0);
            y = Double.parseDouble(conteudoDoArquivo.get(1));
            xi = Double.parseDouble(conteudoDoArquivo.get(2));
            xf = Double.parseDouble(conteudoDoArquivo.get(3));
            h = Double.parseDouble(conteudoDoArquivo.get(4));
            
            if(conteudoDoArquivo.size() > 5){
                intervaloSaida = Double.parseDouble(conteudoDoArquivo.get(5));
            }
            
            else{
                intervaloSaida = xf;
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
        
        euler = new Derivada(y, xi, xf, h, intervaloSaida);
        euler = CalcularEuler.metodoDeEuler(funcao, euler);
        
        try{
            LidarComArquivo.escreverEmArquivo(euler.getValoresDeX(), euler.getValoresDeY());
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