package com.gomoku.interfaces;

import com.jogada.Jogada;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceServidorRMI extends Remote {
    Short[][] receberTabuleiroAtualizado() throws RemoteException;
    Short incluirJogador() throws RemoteException;
    void enviarJogada(Jogada jogada) throws RemoteException;
    Short vencedor() throws RemoteException;
    Short turnoDeQuem() throws RemoteException;
    Boolean todosJogadoresConectadosComSucesso() throws RemoteException;
}
