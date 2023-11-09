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
public class SistemaDeOrdemSuperior{
    /* ESCOPO DE CLASSE */
    private static Derivada ordemSuperior;
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, NumberFormatException{
        ArrayList<String> conteudoDoArquivo;
        String [] funcao = null;
        int quantidade = 0;
        double [] y = null;
        double xi = 0.0,
                xf = 0.0,
                h = 0.0,
                intervaloSaida = 0.0;
        
        try{
            conteudoDoArquivo = new ArrayList<>(LidarComArquivo.lerDeArquivo());
            quantidade = Integer.parseInt(conteudoDoArquivo.get(0));            
            funcao = new String[quantidade];
            y = new double[quantidade];
            
            for(int i = 0; i < quantidade; i++){
                funcao[i] = conteudoDoArquivo.get(1 + i);
            }
            
            for(int j = 0; j < quantidade; j++){
                y[j] = Double.parseDouble(conteudoDoArquivo.get(1 + quantidade + j));
            }
            
            xi = Double.parseDouble(conteudoDoArquivo.get((2 * quantidade) + 1));
            xf = Double.parseDouble(conteudoDoArquivo.get((2 * quantidade) + 2));
            h = Double.parseDouble(conteudoDoArquivo.get((2 * quantidade) + 3));
            
            conteudoDoArquivo.clear(); /* IMPEDE PILHA DE ESTOURAR */
            intervaloSaida = xf;
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
        
        catch(IndexOutOfBoundsException e){
            System.out.println("1- O ponteiro estourou na pilha!\nO vetor/matriz/lista não possui esse número de casas!");
        }
        
        catch(Exception e){
            System.out.println("1- Ocorreu um erro inesperado!\nPor favor tente novamente.");
        }
        
        ordemSuperior = new Derivada(y, xi, xf, h, intervaloSaida);
        ordemSuperior = CalcularEuler.metodoDeEuler(funcao, ordemSuperior);
        
        try{
            LidarComArquivo.escreverEmArquivo(ordemSuperior.getValoresDeY());
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
        
        catch(IndexOutOfBoundsException e){
            System.out.println("2- O ponteiro estourou na pilha!\nO vetor/matriz/lista não possui esse número de casas!");
        }

        catch(Exception e){
            System.out.println("2- Ocorreu um erro inesperado!\nPor favor tente novamente.");
        }
        
        System.out.println("Finalizado com sucesso!!!");
    }
}