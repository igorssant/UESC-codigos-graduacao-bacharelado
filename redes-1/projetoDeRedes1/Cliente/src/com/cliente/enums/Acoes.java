package com.cliente.enums;

import java.util.Objects;

public enum Acoes {
    ATACAR((short) 0),
    DEFENDER((short) 1),
    RECARREGAR((short) 2);

    private final Short valor;

    Acoes(Short valor) {
        this.valor = valor;
    }

    public static Boolean contemValor(Short valor) {
        for(Acoes acao : values()) {
            if(Objects.equals(acao.valor, valor)) {
                return true;
            }
        }

        return false;
    }
}
