package com.analizadorSintatico.erro;

import com.analizadorSintatico.erro.implementacao.Erro;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class ErrosNoCodigoFonte {
    private ArrayList<Erro> listaDeErros;

    /**
     * Construtor de classe vazio
     * Intancia o ArrayList de erros
     * com um valor inicial de 5
     */
    public ErrosNoCodigoFonte() {
        this.listaDeErros = new ArrayList<>(5);
    }

    /**
     * Contrutor de classe completo
     * recebe uma lista contendo todos
     * os erros do codigo fonte
     * @param listaDeErros ArrayList(Erro)
     */
    public ErrosNoCodigoFonte(ArrayList<Erro> listaDeErros) {
        this.listaDeErros = listaDeErros;
    }

    /**
     * Metodo getter que
     * retorna um ArrayList
     * contendo todos os erros
     * do codigo fonte
     * @return listaDeErros ArrayList(Erro)
     */
    public ArrayList<Erro> getListaDeErros() {
        return listaDeErros;
    }

    /**
     * Metodo setter que
     * recebe um ArrayList
     * contendo todos os erros
     * do codigo fonte
     * @param listaDeErros ArrayList(Erro)
     */
    public void setListaDeErros(ArrayList<Erro> listaDeErros) {
        this.listaDeErros = listaDeErros;
    }

    /**
     * Metodo para insercao de um novo erro
     * O novo erro é adicionado ao final da lista
     * Os paraametros sao:
     * @param coluna Integer
     * @param linha Integer
     * @param causa String
     */
    public void adicionarErro(Integer coluna, Integer linha, String causa) {
        this.listaDeErros.add(new Erro(coluna, linha, causa));
    }

    /**
     * Metodo de atualizacao de um
     * erro especifico
     * O usuario deve passar como
     * arumentos:
     * @param coluna Integer
     * @param linha Integer
     * @param causa String
     * @param indice Integer
     */
    public void atualizarErro(Integer coluna, Integer linha, String causa, Integer indice) {
        if(this.listaDeErros.size() > indice) { /* O INDICE EH VALIDO ??? */
            this.listaDeErros.add(indice, new Erro(coluna, linha, causa));
        } else {                                /* O INDICE NAO EH VALIDO !!! */
            adicionarErro(coluna, linha, causa);
        }
    }

    /**
     * Metodo para remover
     * um unico erro da lista,
     * por seu indice inteiro
     * @param indice Integer
     */
    public void removerErro(Integer indice) {
        this.listaDeErros.remove(indice);
    }

    /**
     * Metodo para limpar a lista
     * Ele remove todo e qualquer
     * elemento contido na lista
     */
    public void limparListaDeErros() {
        this.listaDeErros.clear();
    }

    public void gerarRelatorioDeErros() throws FileNotFoundException {
        if(this.listaDeErros.isEmpty()) {
            return;
        }

        String caminhoDeEscrita = "src/saida/errosEncontrados.txt",
            caminhoDeLeitura = "src/entrada/entrada1.cic";
        File arquivoDeErros = new File(caminhoDeEscrita);

        FileReader arquivoOrginal = new FileReader(caminhoDeLeitura);

        try(PrintWriter bufferDeEscrita = new PrintWriter(arquivoDeErros)) {
            Integer linhaAtual = 0;
            String linhaLida = null;

            try(BufferedReader bufferDeLeitura = new BufferedReader(arquivoOrginal)) {
                /* CODIGO IRA EXECUTAR ATE LER TODAS AS LINHAS DO ARQUIVO ORIGINAL .cic */
                while((linhaLida = bufferDeLeitura.readLine()) != null) {
                    bufferDeEscrita.printf("[%3s]\t", (1 + linhaAtual));
                    bufferDeEscrita.printf("%s\n", linhaLida);
                    listaDeErros.forEach((erro) -> {
                        /* VERIFICA SE A LINHA ATUAL POSSUI ERROS */
                        if(Objects.equals(erro.getLinha(), linhaAtual)) {
                            for(int i = 0; i < (erro.getColuna() - 1); i++) {
                                bufferDeEscrita.printf("-");
                            }

                            bufferDeEscrita.printf("/\\\n");
                            bufferDeEscrita.printf("Erro linha %3d coluna %3d: %s\n", erro.getLinha(), erro.getColuna(), erro.getCausa());
                        }
                    });
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
