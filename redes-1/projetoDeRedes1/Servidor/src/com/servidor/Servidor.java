package com.servidor;

import com.jogada.Jogada;
import com.servidor.controller.DefensorController;
import com.servidor.controller.RevolverController;
import com.servidor.enums.Acoes;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Servidor {
    /**
     * Método que irá rodar toda a aplicação
     * do jogo em si.
     * Não possui retorno.
     * Recebe os seguintes parâmetros
     * @param receberObjetoDoAtacante ObjectInputStream
     * @param enviarObjetoParaAtacante ObjectOutputStream
     * @throws IOException Jogada inválida recebida
     * @throws ClassNotFoundException Erro na comunicação com o CLiente
     */
    public static void jogo(
        ObjectInputStream receberObjetoDoAtacante,
        ObjectOutputStream enviarObjetoParaAtacante
    ) throws IOException, ClassNotFoundException {
        final Random geradorDeNumerosAleatorios = new Random();
        final DefensorController defensorController = new DefensorController();
        final RevolverController revolverController = new RevolverController();
        Boolean primeiraRodada = true;
        Jogada jogadaDoAtacante = null,
            jogadaDoDefensor = null;

        do {
            /* CONDIÇÃO PARA ENCERRAR O LOOP */
            if(defensorController.perdeu()) {
                return;
            }

            jogadaDoAtacante = (Jogada) receberObjetoDoAtacante.readObject();

            /* VERIFICA SE AÇÃO RETORNADA É VÁLIDA */
            if(!Acoes.contemValor(jogadaDoAtacante.jogada())) {
                throw new IOException(
                    "Jogada inválida: " +
                    jogadaDoAtacante.jogada() +
                    "\nAs jogadas devem ser: 0 [ RECARREGAR ], 1 [ DEFENDER ] ou 2 [ RECARREGAR ]."
                );
            }

            /* JOGADOR RETORNOU A FLAG DE DERROTA */
            if(jogadaDoAtacante.jogada() == 3) {
                return;
            }

            /* DECIDIR JOGADA DO SERVIDOR */
            if(primeiraRodada) {
                primeiraRodada = false;
                jogadaDoDefensor = new Jogada((short) 2);
            } else if(revolverController.revolverVazio()) {
                jogadaDoDefensor = new Jogada((short) (1 + geradorDeNumerosAleatorios.nextInt(2)));
            } else {
                jogadaDoDefensor = new Jogada((short) geradorDeNumerosAleatorios.nextInt(2));
            }

            defensorController.realizarAcao(jogadaDoDefensor.jogada());

            if(defensorController.getUltimaAcao() == 0) {
                revolverController.disparar();
            } else if (defensorController.getUltimaAcao() == 2) {
                revolverController.adicionarBala();
            }

            /*
             * VERIFICA SE JOGADOR (ATACANTE) IRÁ DISPARAR
             * E
             * QUE O SERVIDOR (DEFENSOR) NÃO IRÁ DEFENDER
             */
            if(jogadaDoAtacante.jogada() == 0 && jogadaDoDefensor.jogada() != 1) {
                defensorController.sofreuDano();
            }

            /*
             * VERIFICA SE O DEFENSOR ESTÁ COM PONTOS DE VIDA ZERADOS
             * SE ESTIVER RETORNA A FLAG DE DERROTA
             */
            if(defensorController.perdeu()) {
                jogadaDoDefensor = new Jogada((short) 3);
                defensorController.realizarAcao(jogadaDoDefensor.jogada());
            }

            enviarObjetoParaAtacante.writeObject(jogadaDoDefensor);

            System.out.println(
                "[ Jogada do Cliente ]:\t" + jogadaDoAtacante.jogada() +
                "\n[ Jogada do Servidor ]:\t" + jogadaDoDefensor.jogada()
            );
        } while(true);
    }

    /**
     * Método principal do Servidor.
     * Inicia e finaliza a aplicação.
     * @param args NÃO DEVE SER USADO
     */
    public static void main(String[] args) {
        final Integer PORTA = 30000;
        final ServerSocket socketDoServidor;
        Socket socketDeConexao = null;
        ObjectOutputStream enviarObjetoParaAtacante = null;
        ObjectInputStream receberObjetoDoAtacante = null;

        try {
            /* ATRELANDO O SOCKET A UMA PORTA */
            socketDoServidor = new ServerSocket(PORTA);

            do {
                /* ESCOPO DE CONEXÃO */
                socketDeConexao = socketDoServidor.accept();
                System.out.println("Novo host foi conectado.\nInicializando um novo jogo.");

                /* CRIANDO OBJETOS DE COMUNICAÇÃO CLIENTE-SERVIDOR */
                receberObjetoDoAtacante = new ObjectInputStream(socketDeConexao.getInputStream());
                enviarObjetoParaAtacante = new ObjectOutputStream(socketDeConexao.getOutputStream());

                /* ESCOPO DE JOGO */
                jogo(receberObjetoDoAtacante, enviarObjetoParaAtacante);
            } while(!socketDoServidor.isClosed());
        } catch(IOException e) {
            throw new RuntimeException("Ocorreu algum erro de comunicação entre cliente e servidor.\n" + e);
        } catch(ClassNotFoundException e) {
            throw new RuntimeException("Classe não vinculada ao servidor.\n" + e);
        } finally {
            System.out.println("Encerrando servidor...");
        }
    }
}
