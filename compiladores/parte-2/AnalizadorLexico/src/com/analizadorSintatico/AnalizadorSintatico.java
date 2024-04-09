package com.analizadorSintatico;

import com.analizadorSintatico.automato.Automato;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

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
        HashMap<String, Integer> somatorioDosTokensReconhecidos = new HashMap<>();
        ArrayList<ArrayList<String>> listaDeTokensReconhecidos = new ArrayList<>();

        try(FileInputStream bufferLeitura = new FileInputStream(arquivo)) {
            Character caractereAtual = null;
            int contador = 0;

            while(bufferLeitura.available() > -1) {
                /* VERIFICA SE A LEITURA DE NOVOS CARACTERES ESTA ATIVADA */
                if(automato.lerNovoCaractere()) {
                    caractereAtual = (char) bufferLeitura.read();
                }

                automato.lerCaractere(caractereAtual.toString());

                /* VERIFICA SE ALCANCOU 'EOF' NO ARQUIVO .cic */
                if(automato.getEstadoAtual() == -1) {
                    break;
                }

                /* VERIFICANDO SE O ESTADO ATUAL EH DE ACEITACAO */
                if(automato.estadoEhAceitacao()) {
                    imprimeArquivoTranspilado(automato);
                    listaDeTokensReconhecidos.add(new ArrayList<>(4));
                    listaDeTokensReconhecidos.get(contador).add(automato.getLinhaAtual().toString());
                    listaDeTokensReconhecidos.get(contador).add(automato.getColunaAtual().toString());
                    listaDeTokensReconhecidos.get(contador).add(automato.getNomeDoToken());

                    /* FAZENDO A VERIFICACAO DOS LEXEMAS
                     * SE HOUVER VARIACAO, SALVE NA LISTA
                     * SENAO, APENAS DEIXE UM ESPACO EM BRANCO
                     */
                    if(automato.getTokenPossuiValor()){
                        listaDeTokensReconhecidos.get(contador).add(automato.getValorDoToken());
                    } else {                                                    /* DEIXANDO O ESPACO EM BRANCO */
                        listaDeTokensReconhecidos.get(contador).add(" ");
                    }

                    contador++;

                    /* VERIFICANDO SE O TOKEN GERADO JA EXISTE NO HASHMAP */
                    if(!somatorioDosTokensReconhecidos.containsKey(automato.getNomeDoToken())) {
                        somatorioDosTokensReconhecidos.put(automato.getNomeDoToken(), 1);
                    } else { /* SE JA EXISTIR, INCREMENTA O CONTADOR */
                        somatorioDosTokensReconhecidos.put(
                            automato.getNomeDoToken(),
                            (somatorioDosTokensReconhecidos.get(automato.getNomeDoToken()) + 1)
                        );
                    }

                    automato.esquecerTokenAtual();
                    automato.voltarAoEstadoInicial();
                }
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        } finally {
            imprimirListaDeTokensReconhecidos(listaDeTokensReconhecidos);
            imprimirSomatorioDeTokesReconhecidos(somatorioDosTokensReconhecidos);
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
     * Metodo utilitario para imprimir
     * todos os tokens reconhecidos e
     * sua quantidade de ocorrencias
     * @param somatorioDosTokensReconhecidos HashMap (String, Integer)
     */
    private static void imprimirSomatorioDeTokesReconhecidos(HashMap<String, Integer> somatorioDosTokensReconhecidos) {
        String caminho = "src/saida/somatorioDeTokensReconhecidos.txt";
        File arquivoDoSomatorio = new File(caminho);

        try(PrintWriter bufferDeEscrita = new PrintWriter(arquivoDoSomatorio)) {
            bufferDeEscrita.println("+-----------------------+-----------------------+");
            bufferDeEscrita.printf("|%20s\t|%20s\t|\n", "TOKEN", "USOS");
            bufferDeEscrita.println("+-----------------------+-----------------------+");
            somatorioDosTokensReconhecidos.forEach((token, numeroDeAparicoes) -> {
                if(!token.equals(" ") && !token.equals("\n") && !token.isBlank()) {
                    bufferDeEscrita.printf("|%20s\t|%20s\t|\n", token, numeroDeAparicoes);
                }
            });
            bufferDeEscrita.println("+-----------------------+-----------------------+");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo utilitario para imprimir
     * todos os tokens, bem como sua
     * linha, sua coluna e seu lexema (se houver)
     * @param listaDeTokensReconhecidos ArrayList(ArrayList(String))
     */
    private static void imprimirListaDeTokensReconhecidos(ArrayList<ArrayList<String>> listaDeTokensReconhecidos) {
        String caminho = "src/saida/indiceTokensReconhecidos.txt";
        File arquivoDeIndice = new File(caminho);

        try(PrintWriter bufferDeEscrita = new PrintWriter(arquivoDeIndice)) {
            bufferDeEscrita.println("+-----------------------+-----------------------+-----------------------+-----------------------+");
            bufferDeEscrita.printf("|%20s\t|%20s\t|%20s\t|%20s\t|\n", "LINHA", "COLUNA", "TOKEN", "USOS");
            bufferDeEscrita.println("+-----------------------+-----------------------+-----------------------+-----------------------+");
            listaDeTokensReconhecidos.forEach((linha) -> {
                if(!linha.get(3).equals("\n")) {
                    bufferDeEscrita.printf("|%20s\t|%20s\t|%20s\t|%20s\t|\n", linha.get(0), linha.get(1), linha.get(2), linha.get(3));
                }
            });
            bufferDeEscrita.println("+-----------------------+-----------------------+-----------------------+-----------------------+");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
