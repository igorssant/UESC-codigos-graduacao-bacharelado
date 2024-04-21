package com.analizadorSintatico;

import com.analizadorSintatico.automato.Automato;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import static com.analizadorSintatico.escritaEmArquivo.ImprimeEmArquivoTranspilado.imprimeArquivoTranspilado;
import static com.analizadorSintatico.escritaEmArquivo.ImprimeListaDeTokensReconhecidos.imprimirListaDeTokensReconhecidos;
import static com.analizadorSintatico.escritaEmArquivo.ImprimeSomatorioDeTokens.imprimirSomatorioDeTokesReconhecidos;
import static com.analizadorSintatico.token.Token.mapaDeTokens;

public class AnalizadorSintatico {
    /**
     * Metodo principal do
     * analisador sintatico
     * Ele recebe como entrada
     * um ponteiro para o
     * arquivoDeEntrada de entrada .cic
     * @param arquivoDeEntrada File
     */
    private static void analizadorSintatico(
        File arquivoDeEntrada,
        File arquivoDeSaida,
        File arquivoDeIndices,
        File arquivoDeSomatorio
    ) {
        ArrayList<ArrayList<String>> listaDeTokensReconhecidos = new ArrayList<>();
        HashMap<String, Integer> somatorioDosTokensReconhecidos = new HashMap<>();
        Automato automato = new Automato(arquivoDeEntrada.getPath());

        try(FileInputStream bufferLeitura = new FileInputStream(arquivoDeEntrada)) {
            int posicaoAtualDoIndiceNaListaDeTokens = 0;
            Character caractereAtual = null;

            while(bufferLeitura.available() > -1) {
                /* VERIFICA SE A LEITURA DE NOVOS CARACTERES ESTA ATIVADA */
                if(automato.lerNovoCaractere()) {
                    caractereAtual = (char) bufferLeitura.read();
                }

                assert caractereAtual != null;
                automato.lerCaractere(caractereAtual.toString());

                /* VERIFICA SE ALCANCOU 'EOF' NO ARQUIVO .cic */
                if(automato.getEstadoAtual() == -1) {
                    break;
                }

                /* VERIFICANDO SE O ESTADO ATUAL EH DE ACEITACAO */
                if(automato.estadoEhAceitacao()) {
                    if(
                        !automato.getNomeDoToken().equals(mapaDeTokens.get("comentarioMonolinha"))
                        &&
                        !automato.getNomeDoToken().equals(mapaDeTokens.get("comentarioMultilinha"))
                    ) {
                        listaDeTokensReconhecidos.add(new ArrayList<>(4));
                        imprimeArquivoTranspilado(automato, arquivoDeSaida);
                        listaDeTokensReconhecidos
                        .get(posicaoAtualDoIndiceNaListaDeTokens)
                        .add(
                            automato
                            .getLinhaAtual()
                            .toString()
                        );
                        listaDeTokensReconhecidos
                        .get(posicaoAtualDoIndiceNaListaDeTokens)
                        .add(
                            automato
                            .getColunaAtual()
                            .toString()
                        );
                        listaDeTokensReconhecidos
                        .get(posicaoAtualDoIndiceNaListaDeTokens)
                        .add(
                            automato.getNomeDoToken()
                        );

                        /* FAZENDO A VERIFICACAO DOS LEXEMAS
                         * SE HOUVER VARIACAO, SALVE NA LISTA
                         * SENAO, APENAS DEIXE UM ESPACO EM BRANCO
                         */
                        if (automato.getTokenPossuiValor()) {
                            listaDeTokensReconhecidos
                            .get(posicaoAtualDoIndiceNaListaDeTokens)
                            .add(automato.getValorDoToken());
                        } else {    /* DEIXANDO O ESPACO EM BRANCO */
                            listaDeTokensReconhecidos
                            .get(posicaoAtualDoIndiceNaListaDeTokens)
                            .add(" ");
                        }

                        posicaoAtualDoIndiceNaListaDeTokens++;

                        /* VERIFICANDO SE O TOKEN GERADO JA EXISTE NO HASHMAP */
                        if (!somatorioDosTokensReconhecidos.containsKey(automato.getNomeDoToken())) {
                            somatorioDosTokensReconhecidos.put(automato.getNomeDoToken(), 1);
                        } else { /* SE JA EXISTIR, INCREMENTA O CONTADOR */
                            somatorioDosTokensReconhecidos.put(
                                automato.getNomeDoToken(),
                                (somatorioDosTokensReconhecidos.get(automato.getNomeDoToken()) + 1)
                            );
                        }
                    }

                    automato.esquecerTokenAtual();
                    automato.voltarAoEstadoInicial();
                }
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        } finally {
            imprimirListaDeTokensReconhecidos(listaDeTokensReconhecidos, arquivoDeSomatorio);
            imprimirSomatorioDeTokesReconhecidos(somatorioDosTokensReconhecidos, arquivoDeIndices);
            System.out.println("Finalizando execução do programa...");
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
        /*String pastaEntrada = "AnalizadorLexico/src/entrada/",
            pastaSaida = "AnalizadorLexico/src/saida/",
            nomeDoArquivoDeEntrada = "entrada1.cic",
            nomeDoArquivoTranspiladoDeSaida = "entradaModificada1.bon",
            nomeDoArquivoDeIndices = "indiceTokensReconhecidos1.txt",
            nomeDoArquivoDeSomatorios = "somatorioDeTokensReconhecidos1.txt";
        File arquivoDeEntrada = new File(pastaEntrada + nomeDoArquivoDeEntrada),
            arquivoDeSaida = new File(pastaSaida + nomeDoArquivoTranspiladoDeSaida),
            arquivoDeSomatorio = new File(pastaSaida + nomeDoArquivoDeSomatorios),
            arquivoDeIndices = new File(pastaSaida + nomeDoArquivoDeIndices);*/
        /*String pastaEntrada = "AnalizadorLexico/src/entrada/",
            pastaSaida = "AnalizadorLexico/src/saida/",
            nomeDoArquivoDeEntrada = "entrada2.cic",
            nomeDoArquivoTranspiladoDeSaida = "entradaModificada2.bon",
            nomeDoArquivoDeIndices = "indiceTokensReconhecidos2.txt",
            nomeDoArquivoDeSomatorios = "somatorioDeTokensReconhecidos2.txt";
        File arquivoDeEntrada = new File(pastaEntrada + nomeDoArquivoDeEntrada),
            arquivoDeSaida = new File(pastaSaida + nomeDoArquivoTranspiladoDeSaida),
            arquivoDeSomatorio = new File(pastaSaida + nomeDoArquivoDeSomatorios),
            arquivoDeIndices = new File(pastaSaida + nomeDoArquivoDeIndices);*/
        String pastaEntrada = "AnalizadorLexico/src/entrada/",
            pastaSaida = "AnalizadorLexico/src/saida/",
            nomeDoArquivoDeEntrada = "entrada3.cic",
            nomeDoArquivoTranspiladoDeSaida = "entradaModificada3.bon",
            nomeDoArquivoDeIndices = "indiceTokensReconhecidos3.txt",
            nomeDoArquivoDeSomatorios = "somatorioDeTokensReconhecidos3.txt";
        File arquivoDeEntrada = new File(pastaEntrada + nomeDoArquivoDeEntrada),
            arquivoDeSaida = new File(pastaSaida + nomeDoArquivoTranspiladoDeSaida),
            arquivoDeSomatorio = new File(pastaSaida + nomeDoArquivoDeSomatorios),
            arquivoDeIndices = new File(pastaSaida + nomeDoArquivoDeIndices);

        if(!arquivoDeEntrada.exists()) {
            System.err.println("O caminho passado está incorreto ou não existe nenhum arquivo com este nome!");
            System.exit(-1);
        }

        if(!(arquivoDeEntrada.isFile() && arquivoDeEntrada.canRead())) {
            System.err.println("Não foi possível abrir o arquivo: " + arquivoDeEntrada.getName() + "\n");
            System.exit(-1);
        }

        System.out.println("Inicializando programa...");
        analizadorSintatico(arquivoDeEntrada, arquivoDeSaida, arquivoDeIndices, arquivoDeSomatorio);
    }
}
