package com.analizadorSintatico.erro;

public class UnexpectedTokenException extends RuntimeException {
    public UnexpectedTokenException(Integer linha, Integer coluna, String conteudoDaLinha) {
        super("Token inesperado foi lido:");
        System.err.println(conteudoDaLinha);
        System.out.println("Linha: " + linha + "; Coluna: " + coluna);

        for (int i = 0; i < (coluna - 1); i++) {
            System.out.print("-");
        }

        System.out.print("/\\");
    }

    public UnexpectedTokenException(String message) {
        super(message);
    }

    public UnexpectedTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedTokenException(Throwable cause) {
        super(cause);
    }

    public UnexpectedTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
