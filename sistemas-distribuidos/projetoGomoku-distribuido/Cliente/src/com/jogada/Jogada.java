package com.jogada;

import java.io.Serializable;

public record Jogada(Short linha, Short coluna, Short jogador) implements Serializable {
    private static final Long serialVersionUID = 30000l;
}
