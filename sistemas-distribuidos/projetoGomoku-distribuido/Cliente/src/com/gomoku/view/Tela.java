package com.gomoku.view;

import com.gomoku.controller.TabuleiroController;

public class Tela {
    private static Tela instancia;
    private final Short dimensao;

    private Tela() {
        this.dimensao = TabuleiroController.getNumeroDeIntersecoes();
        imprimirInicioJogo();
    }

    public static Tela getInstancia() {
        if(instancia == null) {
            instancia = new Tela();
        }

        return instancia;
    }

    public void limparJogadaAntiga() {
        for(short i = 0; i < 100; i++) {
            System.out.print("\n");
        }
    }

    public void atualizarTela(final Short jogador, final Short[][] tabuleiro) {
        String fechamentoDeLinha = "]\n";
        Character peca = (jogador == 1)? 'O' : 'X';

        System.out.println("Vez das peças " + peca + " - jogador " + jogador);

        for(short i = 0; i < this.dimensao; i++) {
            System.out.print("[");
            imprimirEspacos();

            for(short j = 0; j < this.dimensao; j++) {
                if(tabuleiro[i][j] == 1) {
                    fechamentoDeLinha = "O" + getEspacamentoUniforme();
                } else if(tabuleiro[i][j] == 2) {
                    fechamentoDeLinha = "X" + getEspacamentoUniforme();
                } else {
                    fechamentoDeLinha = " " + getEspacamentoUniforme();
                }

                if((1 + j) == this.dimensao) {
                    fechamentoDeLinha = fechamentoDeLinha + "]\n";
                } else {
                    fechamentoDeLinha = fechamentoDeLinha + "|" + getEspacamentoUniforme();
                }

                System.out.print(fechamentoDeLinha);
            }
        }
    }

    public void imprimirVitoria(Short jogador) {
        Character peca = (jogador == 1)? 'O' : 'X';

        System.out.println("JOGADOR " + jogador + " VENCEU.\nAS PEÇAS " + peca + " GANHARAM!");
    }

    public void imprimirImpate() {
        System.out.println("EMPATE !!!");
    }

    private void imprimirInicioJogo() {
        String fechamentoDeLinha = "]\n";

        System.out.println("Vez das peças O - Jogador 1");

        for(short i = 0; i < this.dimensao; i++) {
            System.out.print("[");
            imprimirEspacos();

            for(short j = 0; j < this.dimensao; j++) {
                if((1 + j) == this.dimensao) {
                    fechamentoDeLinha = getEspacamentoUniforme() + " ]\n";

                } else {
                    fechamentoDeLinha = getEspacamentoUniforme() + " |" + getEspacamentoUniforme();
                }

                System.out.print(fechamentoDeLinha);
            }
        }

        System.out.println("\nDigite linha e coluna separados por um espaço em branco para jogar:");
        System.out.println("Exemplo: '10 14' para jogar na linha 10, coluna 14");
    }

    private void imprimirEspacos() {
        // 2 ESPACOS |__|
        System.out.print("  ");
    }

    private String getEspacamentoUniforme() {
        // 2 ESPACOS |__|
        return "  ";
    }
}
