package com.jogada;

import java.io.Serializable;

public record Jogada(Short jogada) implements Serializable {
    private static final Long serialVersionUID = 30000l;
}
