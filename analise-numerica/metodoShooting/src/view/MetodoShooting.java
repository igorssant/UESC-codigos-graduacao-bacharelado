package view;

import controller.Shooting;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import model.Derivada;
import utils.LidarComArquivo;

/**
 * @author igorsssantana
 */
public class MetodoShooting{
    /* ESCOPO DE CLASSE */
    private static Derivada shooting;
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, NumberFormatException{
        ArrayList<String> conteudoDoArquivo = null;
        String funcao = "";
        int maximoDeIteracoes = 10;
        double xi = 0f,
                xf = 0f,
                yi = 0f,
                h = 0f,
                tolerancia = 0.00005,
                alfa = 0f,
                beta = 0f,
                yf = 0f;
        
        try{
            conteudoDoArquivo = new ArrayList<>(LidarComArquivo.lerDeArquivo());
            funcao = conteudoDoArquivo.get(0);
            yi = Double.parseDouble(conteudoDoArquivo.get(1));
            xi = Double.parseDouble(conteudoDoArquivo.get(2));
            xf = Double.parseDouble(conteudoDoArquivo.get(3));
            h = Double.parseDouble(conteudoDoArquivo.get(4));
            alfa = Double.parseDouble(conteudoDoArquivo.get(5));
            beta = Double.parseDouble(conteudoDoArquivo.get(6));
            tolerancia = Double.parseDouble(conteudoDoArquivo.get(7));
            
            if(conteudoDoArquivo.size() > 8){
                maximoDeIteracoes = Integer.parseInt(conteudoDoArquivo.get(8));
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
        
        shooting = new Derivada(yf, xi, xf, h, tolerancia, alfa, beta);
        yf = Shooting.shooting(funcao, shooting, maximoDeIteracoes);
        
        try{
            LidarComArquivo.escreverEmArquivo(yf);
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