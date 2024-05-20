package com.cliente;

import com.cliente.controller.AtacanteController;
import com.cliente.controller.RevolverController;
import com.jogada.Jogada;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    /**
     * Método usado para, entre várias aspas,
     * limpar a tela do console. Mas só
     * funciona em telas pequenas.
     */
    private static void limparTelaConsole() {
        for(Integer i = 0; i < 100; i++) {
            System.out.print("\n");
        }
    }

    /**
     * Método usado para escrever na tela
     * o prompt de ação do jogador.
     */
    private static void pedirAcaoAoJogador() {
        System.out.println(
            "Não é possível disparar o revólver vazio.\n" +
            "[ LEMBRETE ] Um revólver guarda, no máximo, 6 balas.\n" +
            "Digite sua ação:"
        );
        System.out.printf(
            "[%11s ] -> %2d\n" +
            "[%11s ] -> %2d\n" +
            "[%11s ] -> %2d\n" +
            "[%11s ] -> %2d\n",
            "ATACAR", 0,
            "COBERTURA", 1,
            "RECARREGAR", 2,
            "RENDER-SE", 3
        );
    }

    /**
     * Método imprimeArtesDasAcoes. Seu funcionamento
     * é abrir os arquivos que contém as artes e
     * imprimí-las a depender das jogadas do Servidor
     * e do Cliente.
     * @param acaoDoAtacante Short
     * @param acaoDoDefensor Short
     * @throws IOException Não achou o arquivo especificado || Não consegue ler o arquivo
     */
    private static void imprimeArteDasAcoes(Short acaoDoAtacante, Short acaoDoDefensor) throws IOException {
        FileReader arquivoDisparo = new FileReader("src/artes/acoes/disparar.asciiart"),
            arquivoDefesa = new FileReader("src/artes/acoes/tomarCobertura.asciiart"
        ), arquivoRecarregar = new FileReader("src/artes/acoes/recarregar.asciiart");
        BufferedReader leituraDoAtacante,
            leituraDoDefensor;

        leituraDoAtacante = switch(acaoDoAtacante) {
            case 0 -> new BufferedReader(arquivoDisparo);
            case 1 -> new BufferedReader(arquivoDefesa);
            case 2 -> new BufferedReader(arquivoRecarregar);
            default -> null;
        };

        leituraDoDefensor = switch(acaoDoDefensor) {
            case 0 -> new BufferedReader(arquivoDisparo);
            case 1 -> new BufferedReader(arquivoDefesa);
            case 2 -> new BufferedReader(arquivoRecarregar);
            default -> null;
        };

        /* SE ALGUM DELES FOR VAZIO, ENTÃO O JOGO JÁ ACABOU */
        if(leituraDoAtacante == null || leituraDoDefensor == null) {
            return;
        }

        /* AS LINHAS DOS ARQUIVOS */
        String parteAtacante = leituraDoAtacante.readLine(),
            parteDefensor = leituraDoDefensor.readLine();

        if(acaoDoAtacante.equals(acaoDoDefensor)) {
            while(parteAtacante != null) {
                System.out.print(
                    parteAtacante +
                    "\t\t" +
                    parteAtacante +
                    "\n"
                );
                parteAtacante = leituraDoAtacante.readLine();
            }
        } else {
            while(parteAtacante != null) {
                System.out.print(
                    parteAtacante +
                    "\t\t" +
                    parteDefensor +
                    "\n"
                );
                parteAtacante = leituraDoAtacante.readLine();
                parteDefensor = leituraDoDefensor.readLine();
            }
        }

        /* FECHANDO O QUE PRECISA SER FECHADO */
        leituraDoAtacante.close();
        leituraDoDefensor.close();
        arquivoDisparo.close();
        arquivoDefesa.close();
        arquivoRecarregar.close();
    }

    /**
     * Método imprimeVitoriaOuDerrota. Um método utilitário
     * que recebe um valor booleano para decidir o que será
     * impresso na tela:
     * true -> VITORIA;
     * false -> DERROTA;
     * @param vitoriaOuDerrota Boolean
     * @throws IOException Não achou o arquivo especificado || Não consegue ler o arquivo
     */
    private static void imprimeVitoriaOuDerrota(Boolean vitoriaOuDerrota) throws IOException {
        final FileReader arquivo;
        final BufferedReader leituraNoArquivo;
        String linhaLida = null;

        /* IF TERNARIO PARA VERIFICAR QUAL ARQUIVO  SERÁ LIDO */
        arquivo = vitoriaOuDerrota?
            new FileReader("src/artes/resultados/vencer.asciiart") :
            new FileReader("src/artes/resultados/perder.asciiart");
        leituraNoArquivo = new BufferedReader(arquivo);
        linhaLida = leituraNoArquivo.readLine();

        while(linhaLida != null) {
            System.out.println(linhaLida);
            linhaLida = leituraNoArquivo.readLine();
        }

        /* FECHANDO O QUE PRECISA SER FECHADO */
        leituraNoArquivo.close();
        arquivo.close();
    }

    /**
     * Método JOGO. Irá rodar toda a parte lógica do
     * jogo. Tratar erros. Fazer chamada a outros métodos.
     * Enviar/receber mensagens para/do servidor. Etc.
     * @param leituraAssincrona BufferedReader
     * @param receberDoServidor ObjectInputStream
     * @param enviarObjetoParaServidor ObjectOutputStream
     * @throws IOException Jogador digitou uma opção incorreta
     * @throws ClassNotFoundException Erro na comunicação com o Servidor
     */
    public static void jogo(
        final BufferedReader leituraAssincrona,
        final ObjectInputStream receberDoServidor,
        final ObjectOutputStream enviarObjetoParaServidor
    ) throws IOException, ClassNotFoundException {
        final AtacanteController atacanteController = new AtacanteController();
        final RevolverController revolverController = new RevolverController();
        Jogada jogadaDoAtacante,
            jogadaDoDefensor;

        /*
         * INICIALIZANDO COM A AÇÃO `ATIRAR` PARA
         * TRATAMENTO MAIS SIMPLIFICADO NO FUTURO.
         */
        atacanteController.realizarAcao((short) 0);
        jogadaDoDefensor = new Jogada((short) 0);

        do {
            /* CONDIÇÕES PARA ENCERRAR O LOOP */
            if(atacanteController.perdeu()) { /* PERDER */
                enviarObjetoParaServidor.writeObject(new Jogada((short) 3));
                imprimeVitoriaOuDerrota(false);
                return;
            }

            if(jogadaDoDefensor.jogada() == 3) {  /* VENCER */
                imprimeVitoriaOuDerrota(true);
                return;
            }

            pedirAcaoAoJogador();
            System.out.printf(
                "Quantidade de balas: [%2d ]\n",
                revolverController.getQuantidadeDeBalasAtual()
            );
            jogadaDoAtacante = new Jogada(Short.parseShort(leituraAssincrona.readLine()));
            atacanteController.realizarAcao(jogadaDoAtacante.jogada());

            if(atacanteController.getUltimaAcao() == 0) {
                revolverController.disparar();
            } else if(atacanteController.getUltimaAcao() == 2) {
                revolverController.adicionarBala();
            }

            enviarObjetoParaServidor.writeObject(jogadaDoAtacante);
            jogadaDoDefensor = (Jogada) receberDoServidor.readObject();

            /*
             * VERIFICA SE O SERVIDOR (DEFENSOR) IRÁ DISPARAR
             * E
             * SE O JOGADOR (ATACANTE) NÃO IRÁ DEFENDER
             */
            if(jogadaDoAtacante.jogada() != 1 && jogadaDoDefensor.jogada() == 0) {
                atacanteController.sofreuDano();
            }

            /*
             * MÉTODOS ABAIXO IMPRIMEM AS AÇÕES NA TELA
             */
            limparTelaConsole();
            imprimeArteDasAcoes(jogadaDoAtacante.jogada(), jogadaDoDefensor.jogada());

        } while(true);
    }

    /**
     * Método principal da aplicação Cliente.
     * Inicia e finaliza toda a aplicação.
     * @param args NÃO DEVE SER UTILIZADO
     */
    public static void main(String[] args) {
        final Scanner leituraSincrona = new Scanner(System.in);
        final BufferedReader leituraAssincrona = new BufferedReader(new InputStreamReader(System.in));
        final Integer numeroDaPorta;
        final String host;
        final Socket socketDoCliente;
        final ObjectInputStream receberDoServidor;
        final ObjectOutputStream enviarObjetoParaServidor;

        try {
            System.out.println("Digite o IP do servidor:");
            host = leituraSincrona.nextLine();

            System.out.println("Digite o número da porta onde o servidor está escutando:");
            numeroDaPorta = leituraSincrona.nextInt();

            /* Conectando ao Socket do servidor */
            socketDoCliente = new Socket(host, numeroDaPorta);

            /* CRIANDO OBJETOS DE COMUNICAÇÃO CLIENTE-SERVIDOR */
            enviarObjetoParaServidor = new ObjectOutputStream(socketDoCliente.getOutputStream());
            receberDoServidor = new ObjectInputStream(socketDoCliente.getInputStream());

            /* ESCOPO DE JOGO */
            jogo(leituraAssincrona, receberDoServidor, enviarObjetoParaServidor);

            /* ENCERRAR E FECHAR TODAS AS CONEXÕES */
            enviarObjetoParaServidor.close();
            receberDoServidor.close();
            socketDoCliente.close();
            leituraAssincrona.close();
        } catch(IOException e) {
            throw new RuntimeException(e);
        } catch(ClassNotFoundException e) {
            throw new RuntimeException("Essa classe não foi implementada pelo cliente.\n" + e);
        } finally {
            leituraSincrona.close();
            System.out.println("Encerrando conexão...");
        }
    }
}
