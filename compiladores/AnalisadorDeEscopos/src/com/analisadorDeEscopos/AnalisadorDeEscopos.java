package com.analisadorDeEscopos;

import com.analisadorDeEscopos.controller.Automato;
import com.analisadorDeEscopos.utils.escopo.Escopo;
import com.analisadorDeEscopos.utils.escritaEmArquivo.EscritaEmArquivoDeSaida;
import com.analisadorDeEscopos.utils.escritaEmArquivo.EscritaEmArquivoTranspilado;
import java.io.*;

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
    public static void transpilarArquivoOriginal(final String arquivoDeEntrada, final String arquivoTranspilado) {
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

    /**
     * Método analisadorDeEscopos.
     * Recebe como parâmetros o caminho
     * para o arquivo transpilado, como String
     * e o caminho para o arquivo de saída
     * como String.
     * O método utiliza de outro método,
     * com.analisadorDeEscopos.utils.escopo.Escopo,
     * para auxiliar a manipulação do programa -
     * variáveis, comandos, blocos, etc - separados
     * por linha do código transpilado que foi lido.
     * @param arquivoTranspilado String
     * @param arquivoDeSaida String
     */
    public static void analisadorDeEscopos(final String arquivoTranspilado, final String arquivoDeSaida) {
        final Escopo escopo = new Escopo();

        System.out.println("Inicializando o Analisador de Escopos...");

        /*
         * abrindo buffer de leitura.
         * O buffer será fechado automaticamente
         * após finalização do bloco try-catch
         */
        try(BufferedReader bufferLeitura = new BufferedReader(new FileReader(arquivoTranspilado))) {
            BufferedWriter bufferEscrita = new BufferedWriter(new FileWriter(arquivoDeSaida));
            String linha;

            while((linha = bufferLeitura.readLine()) != null) {
                /*
                 * Verificação de linhas em branco.
                 * Se a linha não possuir nenhuma
                 * informação, apenas passe para
                 * a próxima iteração (sem realizar
                 * nenhuma outra instrução).
                 */
                if(linha.isBlank() || linha.isEmpty()) {
                    escopo.incrementarLinha();
                    continue;
                }

                escopo.pilhaDeEscopo(linha);

                /*
                * verificando se há algo para imprimir.
                * se não houver, parte para a próxima linha
                */
                if(escopo.getPossuiValorParaImpressao()) {
                    EscritaEmArquivoDeSaida.escreverLinhaEmArquivo(
                        escopo.getValorParaImprimir(),
                        bufferEscrita
                    );
                    escopo.valorParaImpressaoJaFoiUsado();
                }
            }

            /* fechando buffer de escrita em arquivo */
            bufferEscrita.close();
        } catch(FileNotFoundException e) {
            throw new RuntimeException("Arquivo não existe ou caminho passado incorreto:\n" + e);
        } catch(StringIndexOutOfBoundsException e) {
            throw new RuntimeException("Erro, String vazia ou corrompida: " + e);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível escrever no arquivo:\n" + e);
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
        final String arquivoDeEntrada = "src/data/entrada/exemplo1.txt",
            arquivoTranspilado = "src/data/transpilado/arquivoTranspilado.bon",
            arquivoDeSaida = "src/data/saida/saida1.txt";

        transpilarArquivoOriginal(arquivoDeEntrada, arquivoTranspilado);
        analisadorDeEscopos(arquivoTranspilado, arquivoDeSaida);
    }
}
