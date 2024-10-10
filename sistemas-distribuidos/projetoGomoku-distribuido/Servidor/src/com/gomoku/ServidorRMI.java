package com.gomoku;

import com.gomoku.controller.Jogo;
import com.gomoku.interfaces.InterfaceServidorRMI;
import com.jogada.Jogada;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class ServidorRMI extends UnicastRemoteObject implements InterfaceServidorRMI {
    private static Jogo jogo;

    public ServidorRMI() throws RemoteException {
        super(0);
        jogo = new Jogo();
    }

    @Override
    public Short[][] receberTabuleiroAtualizado() throws RemoteException {
        return jogo.getTabuleiroAtualizado();
    }

    @Override
    public Short incluirJogador() throws RemoteException {
        Short quantidadeJogadores;

        jogo.adicionarJogadorEmPartida();
        quantidadeJogadores = jogo.getNumeroDeJogadoresAtual();
        System.out.println("Novo jogador adicionado\t[ " + quantidadeJogadores + " ].");
        return quantidadeJogadores;
    }

    @Override
    public void enviarJogada(Jogada jogada) throws RemoteException {
        System.out.println("==================================== JOGADA ====================================");
        System.out.println("Jogador [ " + jogada.jogador() + " ]\tLinha [ " + jogada.linha() + " ]\tColuna [ " + jogada.coluna() + " ]");
        jogo.receberJogada(jogada.linha(), jogada.coluna(), jogada.jogador());
    }

    @Override
    public Short vencedor() throws RemoteException {
        return jogo.verificaVitoria();
    }

    @Override
    public Short turnoDeQuem() {
        return jogo.getTurnoDoJogador();
    }

    @Override
    public Boolean todosJogadoresConectadosComSucesso() throws RemoteException {
        return jogo.getNumeroDeJogadoresAtual().equals((short) 2);
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        Integer porta = 30000;
        String link = "rmi://localhost:",
            nomeDaClasse = "/ServidorRMI";

        try {
            LocateRegistry.createRegistry(porta);
            confirmaServidorIniciado();
        } catch(RemoteException e) {
            confirmaRegistroJaCriado();
        }

        ServidorRMI servidorRMI = new ServidorRMI();
        Naming.rebind(
            link.concat(porta + nomeDaClasse),
            servidorRMI
        );
        confirmaRegistroCriado();
        System.out.println("Servidor esperando conexão dos clientes.");
    }

    private static void confirmaServidorIniciado() {
        System.out.println("Servidor Inicializado com sucesso.");
    }

    private static void confirmaRegistroCriado() {
        System.out.println("Registro RMI criado com sucesso.");
    }

    private static void confirmaRegistroJaCriado() {
        System.err.println("Registro já existe.");
    }
}
