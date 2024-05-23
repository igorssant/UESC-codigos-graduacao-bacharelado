package com.analisadorDeEscopos;

import com.analisadorDeEscopos.controller.Automato;
import com.analisadorDeEscopos.utils.escritaEmArquivo.EscritaEmArquivoTranspilado;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AnalisadorDeEscopos {
    /**
     * Método transpilarArquivoOriginal.
     * Recebe como parâmetros o caminho
     * para o arquivo de entrada, como String
     * e o caminho para o arquivo intermediário
     * transpilado, como String.
     * O método executa o automato token a token
     * no arquivo de entrada e faz a transpilação
     * para o arquivo transpilado.
     * @param arquivoDeEntrada String
     * @param arquivoTranspilado String
     */
    public static void transpilarArquivoOriginal(String arquivoDeEntrada, String arquivoTranspilado) {
        Automato automato = new Automato();
        String caractereAtual = "";

        System.out.println("Inicializando a transpilação do arquivo...");

        /*
         * abrindo buffer de leitura.
         * O buffer será fechado automaticamente
         * após finalização do bloco try-catch
         */
        try(FileInputStream bufferLeitura = new FileInputStream(arquivoDeEntrada)) {
            FileWriter bufferEscrita = new FileWriter(arquivoTranspilado);

            while(bufferLeitura.available() > -1) {
                if(automato.getLerNovoCaractere()) {
                    caractereAtual = String.valueOf((char) bufferLeitura.read());
                }

                automato.lerCaractere(caractereAtual);

                if(automato.getEstadoAtual() == -1) {
                    break;
                }

                if(automato.getEstadoEhFinal()) {
                    EscritaEmArquivoTranspilado.escreverTokenEmArquivo(
                        automato.getNomeToken(),
                        automato.getValorToken(),
                        bufferEscrita
                    );
                    automato.retornarAoEstadoInicial();
                    automato.esquecerTokenAtual();
                }
            }

            /* fechando buffer de escrita em arquivo */
            bufferEscrita.close();
        } catch(FileNotFoundException e) {
            throw new RuntimeException("Arquivo não existe ou caminho passado incorreto:\n" + e);
        } catch(IOException e) {
            throw new RuntimeException("Não foi possível ler o arquivo:\n" + e);
        } finally {
            System.out.println("Finalizando a transpilação do arquivo...");
        }
    }

    public static void analisadorDeEscopos(String arquivoTranspilado) {
        System.out.println("Inicializando o Analisador de Escopos...");

        /*
         * abrindo buffer de leitura.
         * O buffer será fechado automaticamente
         * após finalização do bloco try-catch
         */
        try(Scanner leitura = new Scanner(arquivoTranspilado)) {
            while(leitura.hasNext()) {
                String linha = leitura.nextLine();

            }
        } catch(Exception e) {
            throw new RuntimeException("Alguma exceção não tratada foi lançada: " + e);
        } finally {
            System.out.println("Finalizando o Analisador de Escopos...");
        }
    }

    /**
     * Método principal da classe.
     * Inicia e finaliza a execução
     * do processo como um todo.
     * Não passar nenhum argumento !!!
     * @param args String
     */
    public static void main(String[] args) {
        String arquivoDeEntrada = "src/data/entrada/exemplo1.txt",
            arquivoTranspilado = "src/data/transpilado/arquivoTranspilado.bon";

        transpilarArquivoOriginal(arquivoDeEntrada, arquivoTranspilado);
        analisadorDeEscopos(arquivoTranspilado);
    }
}
