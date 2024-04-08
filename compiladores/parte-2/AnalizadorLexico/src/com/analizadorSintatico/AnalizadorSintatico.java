package com.analizadorSintatico;

import com.analizadorSintatico.automato.Automato;
import java.io.*;

public class AnalizadorSintatico {
    /**
     * Metodo principal do
     * analisador sintatico
     * Ele recebe como entrada
     * um ponteiro para o
     * arquivo de entrada .cic
     * @param arquivo File
     */
    private static void analizadorSintatico(File arquivo) {
        Automato automato = new Automato();

        try(FileInputStream bufferLeitura = new FileInputStream(arquivo)) {
            Character caractereAtual = null;

            while(bufferLeitura.available() > -1) {
                if(automato.lerNovoCaractere()) {
                    caractereAtual = (char) bufferLeitura.read();
                    System.out.println(caractereAtual);
                }

                automato.lerCaractere(caractereAtual.toString());

                if(automato.estadoEhAceitacao()) {
                    imprimeArquivoTranspilado(automato);
                    automato.esquecerTokenAtual();
                    automato.voltarAoEstadoInicial();
                }
            }
        } catch(FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch(IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Finalizando execução do programa...");
        }
    }

    /**
     * Metodo usado para imprimir os tokens
     * no arquivo transpilado, ou seja,
     * o arquivo de saida
     * O metodo recebe como entrada
     * o automato que está lendo
     * o arquivo de entrada .cic
     * @param automato Automato
     */
    private static void imprimeArquivoTranspilado(Automato automato){
        String caminho = "src/saida/entradaModificada",
            extensao = ".bon";
        File arquivo = new File(caminho + extensao);

        try(FileWriter bufferEscrita = new FileWriter(arquivo, true)) {
            if(automato.getValorDoToken().equals("\n")) {
                bufferEscrita.write(automato.getValorDoToken());
            } else {
                abrirToken(bufferEscrita);

                if (automato.getTokenPossuiValor()) {
                    escreverTokenVariavel(automato.getNomeDoToken(), automato.getValorDoToken(), bufferEscrita);
                } else {
                    escreverTokenInvariavel(automato.getNomeDoToken(), bufferEscrita);
                }

                fechaToken(bufferEscrita);
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo usado para imprimir,
     * no arquivo de saida, os
     * tokens que possuem valores variaveis
     * @param chave String
     * @param valor String
     * @param bufferEscrita FileWriter
     * @throws IOException
     */
    private static void escreverTokenVariavel(String chave, String valor, FileWriter bufferEscrita) throws IOException {
        bufferEscrita.write(chave);
        separadorTokenVariavel(bufferEscrita);
        bufferEscrita.write(valor);
    }

    /**
     * Metodo usado para imprimir,
     * no arquivo de saida, os
     * tokens que <b>NÃO</b> possuem valores variaveis
     * @param chave String
     * @param bufferEscrita FileWriter
     * @throws IOException
     */
    private static void escreverTokenInvariavel(String chave, FileWriter bufferEscrita) throws IOException {
        bufferEscrita.write(chave);
    }

    /**
     * Metodo utilitario usado
     * para imprimir o caractere
     * '<' no arquivo de saida
     * @param bufferEscrita FileWriter
     * @throws IOException
     */
    private static void abrirToken(FileWriter bufferEscrita) throws IOException {
        bufferEscrita.write("<");
    }

    /**
     * Metodo utilitario usado
     * para imprimir o caractere
     * ',' no arquivo de saida
     * @param bufferEscrita FileWriter
     * @throws IOException
     */
    private static void separadorTokenVariavel(FileWriter bufferEscrita) throws IOException {
        bufferEscrita.write(", ");
    }

    /**
     * Metodo utilitario usado
     * para imprimir o caractere
     * '>' no arquivo de saida
     * @param bufferEscrita FileWriter
     * @throws IOException
     */
    private static void fechaToken(FileWriter bufferEscrita) throws IOException {
        bufferEscrita.write(">");
    }

    /**
     * Metodo inicial do
     * projeto
     * Ele vai iniciar todo
     * o processo do automato
     * @param args String[] | null
     */
    public static void main(String[] args) {
        File arquivo = new File("src/entrada/entrada.cic");

        if(!arquivo.exists()) {
            System.err.println("O caminho passado está incorreto ou não existe nenhum arquivo com este nome!");
            System.exit(-1);
        }

        if(!(arquivo.isFile() && arquivo.canRead())) {
            System.err.println("Não foi possível abrir o arquivo: " + arquivo.getName() + "\n");
            System.exit(-1);
        }

        System.out.println("Inicializando programa...");
        analizadorSintatico(arquivo);
    }
}
