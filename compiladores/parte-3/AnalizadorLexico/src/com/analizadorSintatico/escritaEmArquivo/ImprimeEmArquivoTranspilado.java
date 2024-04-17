package com.analizadorSintatico.escritaEmArquivo;

import com.analizadorSintatico.automato.Automato;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ImprimeEmArquivoTranspilado {
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
     * Metodo usado para imprimir os tokens
     * no arquivo transpilado, ou seja,
     * o arquivo de saida
     * O metodo recebe como entrada
     * o automato que está lendo
     * o arquivo de entrada .cic
     * @param automato Automato
     */
    public static void imprimeArquivoTranspilado(Automato automato, File arquivo){
        try(FileWriter bufferEscrita = new FileWriter(arquivo, true)) {
            if(automato.getValorDoToken().equals("\n")) {
                bufferEscrita.write(automato.getValorDoToken());
            } else {
                abrirToken(bufferEscrita);

                if(automato.getTokenPossuiValor()) {
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
}
