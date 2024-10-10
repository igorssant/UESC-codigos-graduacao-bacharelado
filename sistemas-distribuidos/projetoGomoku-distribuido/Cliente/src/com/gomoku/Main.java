package com.gomoku;

import com.gomoku.controller.TabuleiroController;
import com.gomoku.interfaces.InterfaceServidorRMI;
import com.gomoku.utils.Utils;
import com.jogada.Jogada;
import com.gomoku.view.Tela;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static TabuleiroController tabuleiroController;
    private static Tela tela;
    private static Scanner scanner;

    public static void main(String[] args) {
        Integer porta = 30000;
        String link = "rmi://localhost:",
            nomeDaClasse = "/ServidorRMI";

        try {
            Boolean meuTurno = false;
            InterfaceServidorRMI servidorRMI = (InterfaceServidorRMI) Naming.lookup(link.concat(porta + nomeDaClasse));
            scanner = new Scanner(System.in);
            Short jogador = conectarPartida(servidorRMI);
            tabuleiroController = new TabuleiroController();
            tela = Tela.getInstancia();

            if(jogador == 1) {
                enviarJogada(servidorRMI, jogador);
            }

            do {
                meuTurno = Objects.equals(servidorRMI.turnoDeQuem(), jogador);

                if(!meuTurno) {
                    continue;
                }

                tabuleiroController.setTabuleiro(receberTabuleiroAtualizado(servidorRMI));
                tela.atualizarTela(jogador, tabuleiroController.getTabuleiro());
                enviarJogada(servidorRMI, jogador);
            } while(servidorRMI.vencedor() == 0);

            tabuleiroController.setTabuleiro(receberTabuleiroAtualizado(servidorRMI));
            tela.atualizarTela(jogador, tabuleiroController.getTabuleiro());

            if(servidorRMI.vencedor() == 3) {
                tela.imprimirImpate();
            } else {
                tela.imprimirVitoria(servidorRMI.vencedor());
            }
        } catch(NotBoundException e) {
            System.err.println("O servidor não está no ar.");
        } catch(MalformedURLException e) {
            System.err.println("Erro na URL do servidor!\nEstá mal formada.");
        } catch(RemoteException e) {
            System.err.println("Houve um erro na transferência de dados.");
        } catch(Exception e) {
            System.err.println("Ocorreu um erro inesperado.");
        }
    }
    
    private static Jogada receberJogadaDoJogador(Short jogador) {
        Short[] linhaColuna = Utils.recuperarLinhaColunaDeFrase(
            Utils.pegarJogadaDaTela(scanner)
        );

        return new Jogada(linhaColuna[0], linhaColuna[1], jogador);
    }

    private static Short conectarPartida(InterfaceServidorRMI servidorRMI) throws RemoteException {
        return servidorRMI.incluirJogador();
    }

    private static void enviarJogada(InterfaceServidorRMI servidorRMI, Short jogador) throws RemoteException  {
        Jogada jogada = receberJogadaDoJogador(jogador);
        tabuleiroController.atualizarTabuleiro(jogada.linha(), jogada.coluna(), jogador);
        servidorRMI.enviarJogada(jogada);
        tela.limparJogadaAntiga();
        tela.atualizarTela((short) (3 - jogador), tabuleiroController.getTabuleiro());
    }

    private static Short[][] receberTabuleiroAtualizado(InterfaceServidorRMI servidorRMI) throws RemoteException {
        tela.limparJogadaAntiga();
        return servidorRMI.receberTabuleiroAtualizado();
    }
}
