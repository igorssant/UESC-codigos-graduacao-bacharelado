package com.analizadorSintatico.erro;

public class UnexpectedTokenException extends RuntimeException {
    /**
     * Construtor para ativar a excessão
     * e retorná-la ao usuário
     * @param linha integer
     * @param coluna Integer
     * @param conteudoDaLinha String
     */
    public UnexpectedTokenException(Integer linha, Integer coluna, String conteudoDaLinha) {
        super("Token inesperado foi lido:");
        System.err.println(conteudoDaLinha);
        System.out.println("Linha: " + linha + "; Coluna: " + coluna);

        for (int i = 0; i < (coluna - 1); i++) {
            System.out.print("-");
        }

        System.out.print("/\\");
    }

    /**
     * Construtor para ativar a excessão
     * e retorná-la ao usuário
     * @param message String
     */
    public UnexpectedTokenException(String message) {
        super(message);
    }

    /**
     * Construtor para ativar a excessão
     * e retorná-la ao usuário
     * @param message String
     * @param cause Throwable
     */
    public UnexpectedTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Construtor para ativar a excessão
     * e retorná-la ao usuário
     * @param cause Throwable
     */
    public UnexpectedTokenException(Throwable cause) {
        super(cause);
    }

    /**
     * Construtor para ativar a excessão
     * e retorná-la ao usuário
     * @param message String
     * @param cause Throwable
     * @param enableSuppression Boolean
     * @param writableStackTrace Boolean
     */
    public UnexpectedTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
