package com.analizadorSintatico.automato;

import com.analizadorSintatico.estado.Estado;
import static com.analizadorSintatico.expressaoRegular.ExpressaoRegular.listaDeRegex;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Automato {
    private Estado estado;
    private String token;

    public Automato() {
        this.token = "";
        this.estado = new Estado();
    }

    public Automato(String caractereAtual) {
        this.token = "";
        this.estado = new Estado(caractereAtual);
    }

    public Integer getUltimoEstado() {
        return Estado.ultimoEstado;
    }

    public String getToken() {
        return this.token;
    }

    public Integer getEstadoAtual() {
        return this.estado.getEstadoAtual();
    }

    public void lerCaractere(String caractereAtual) {
        Integer estadoAtual = this.estado.getEstadoAtual();
        Pattern regexParaHexadecimal = Pattern.compile("[A-F]");
        Matcher matcherParaHexadecimal = regexParaHexadecimal.matcher(caractereAtual),
            matcher = null;

        switch(estadoAtual) {
            case 0:
                ArrayList<Pattern> listaDeRegexPermitidos = (ArrayList<Pattern>) listaDeRegex.clone();
                listaDeRegexPermitidos.remove(21);
                listaDeRegexPermitidos.remove(20);
                listaDeRegexPermitidos.remove(4);
                listaDeRegexPermitidos.remove(3);

                if((matcher = listaDeRegexPermitidos.get(0).matcher(caractereAtual)).matches()) { /* LEU UM NUMERO */
                    mudarDeEstado(caractereAtual, 3);
                } else if ((matcher = listaDeRegexPermitidos.get(1).matcher(caractereAtual)).matches()) { /* LEU UM PONTO */
                    mudarDeEstado(caractereAtual, 35);
                } else if ((matcher = listaDeRegexPermitidos.get(2).matcher(caractereAtual)).matches()) { /* LEU ASPAS */
                    mudarDeEstado(caractereAtual, 1);
                } else if ((matcher = listaDeRegexPermitidos.get(3).matcher(caractereAtual)).matches()) { /* LEU LETRA MINUSCULA */
                    mudarDeEstado(caractereAtual, 36);
                } else if ((matcher = listaDeRegexPermitidos.get(4).matcher(caractereAtual)).matches()) { /* LEU LETRA MAIUSCULA */
                    if(matcherParaHexadecimal.matches()) {
                        mudarDeEstado(caractereAtual, 14);
                    } else {
                        throw new RuntimeException("UNEXPECTED TOKEN");
                    }
                } else if ((matcher = listaDeRegexPermitidos.get(5).matcher(caractereAtual)).matches()) { /* LEU TRALHA '#' */
                    mudarDeEstado(caractereAtual, 18);
                } else if ((matcher = listaDeRegexPermitidos.get(6).matcher(caractereAtual)).matches()) { /* LEU MENOR QUE */
                    mudarDeEstado(caractereAtual, 20);
                } else if ((matcher = listaDeRegexPermitidos.get(7).matcher(caractereAtual)).matches()) { /* LEU MAIOR QUE */
                    mudarDeEstado(caractereAtual, 44);
                } else if ((matcher = listaDeRegexPermitidos.get(8).matcher(caractereAtual)).matches()) { /* LEU SINAL DE MENOS */
                    mudarDeEstado(caractereAtual, 58);
                    lerCaractere(caractereAtual);
                } else if ((matcher = listaDeRegexPermitidos.get(9).matcher(caractereAtual)).matches()) { /* LEU SINAL DE MAIS */
                    mudarDeEstado(caractereAtual, 57);
                    lerCaractere(caractereAtual);
                } else if ((matcher = listaDeRegexPermitidos.get(10).matcher(caractereAtual)).matches()) { /* LEU ASTERISCO */
                    mudarDeEstado(caractereAtual, 59);
                    lerCaractere(caractereAtual);
                } else if ((matcher = listaDeRegexPermitidos.get(11).matcher(caractereAtual)).matches()) { /* LEU SINAL DE PORCENTAGEM */
                    mudarDeEstado(caractereAtual, 60);
                    lerCaractere(caractereAtual);
                } else if ((matcher = listaDeRegexPermitidos.get(12).matcher(caractereAtual)).matches()) { /* LEU SINAL DE IGUAL */
                    mudarDeEstado(caractereAtual, 49);
                } else if ((matcher = listaDeRegexPermitidos.get(13).matcher(caractereAtual)).matches()) { /* LEU SIMBOLO PIPE '|' */
                    mudarDeEstado(caractereAtual, 61);
                    lerCaractere(caractereAtual);
                } else if ((matcher = listaDeRegexPermitidos.get(14).matcher(caractereAtual)).matches()) { /* LEU 'E' COMERCIAL */
                    mudarDeEstado(caractereAtual, 62);
                    lerCaractere(caractereAtual);
                } else if ((matcher = listaDeRegexPermitidos.get(15).matcher(caractereAtual)).matches()) { /* LEU ACENTO TIL */
                    mudarDeEstado(caractereAtual, 56);
                    lerCaractere(caractereAtual);
                } else if ((matcher = listaDeRegexPermitidos.get(16).matcher(caractereAtual)).matches()) { /* LEU PONTO E VIRGULA */
                    mudarDeEstado(caractereAtual, 75);
                    lerCaractere(caractereAtual);
                } else if ((matcher = listaDeRegexPermitidos.get(17).matcher(caractereAtual)).matches()) { /* LEU DOIS PONTOS */
                    mudarDeEstado(caractereAtual, 73);
                    lerCaractere(caractereAtual);
                } else if ((matcher = listaDeRegexPermitidos.get(18).matcher(caractereAtual)).matches()) { /* LEU ABRE PARENTESE */
                    mudarDeEstado(caractereAtual, 77);
                    lerCaractere(caractereAtual);
                } else if ((matcher = listaDeRegexPermitidos.get(19).matcher(caractereAtual)).matches()) { /* LEU FECHA PARENTESE */
                    mudarDeEstado(caractereAtual, 79);
                    lerCaractere(caractereAtual);
                } else {
                    System.err.println("Erro não esperado.\nEstado 0 aceitou um token não esperado: " + caractereAtual);
                    System.exit(-1);
                }

                break;

            case 1:
                break;

            case 2:
                break;

            case 3:
                break;

            case 4:
                break;

            case 5:
                break;

            case 6:
                break;

            case 7:
                break;

            case 8:
                break;

            case 9:
                break;

            case 10:
                break;

            case 11:
                break;

            case 12:
                break;

            case 13:
                break;

            case 14:
                System.out.println("passei pelo estado 14");
                break;

            case 15:
                break;

            case 16:
                break;

            case 17:
                break;

            case 18:
                break;

            case 19:
                break;

            case 20:
                break;

            case 21:
                break;

            case 22:
                break;

            case 23:
                break;

            case 24:
                break;

            case 25:
                break;

            case 26:
                break;

            case 27:
                break;

            case 28:
                break;

            case 29:
                break;

            case 30:
                break;

            case 31:
                break;

            case 32:
                break;

            case 33:
                break;

            case 34:
                break;

            case 35:
                break;

            case 36:
                System.out.println("passei pelo estado 36");

                if((matcher = listaDeRegex.get(5).matcher(caractereAtual)).matches()) {
                    mudarDeEstado(caractereAtual, 38);
                } else if ((matcher = listaDeRegex.get(6).matcher(caractereAtual)).matches()) {
                    mudarDeEstado(caractereAtual, 37);
                } else {
                    System.err.println("Erro não esperado.\nEstado 0 aceitou um token não esperado: " + caractereAtual);
                    System.exit(-1);
                }

                break;

            case 37:
                System.out.println("passei pelo estado 37");

                if((matcher = listaDeRegex.get(5).matcher(caractereAtual)).matches()) {
                    mudarDeEstado(caractereAtual, 40);
                } else {
                    mudarDeEstado(caractereAtual, 41);
                    System.out.println("NO TERMINAL DE ACEITAÇÃO ALCANÇADO");
                }

                break;

            case 38:
                System.out.println("passei pelo estado 38");

                if((matcher = listaDeRegex.get(5).matcher(caractereAtual)).matches()) {
                    manterEstado(caractereAtual);
                } else {
                    mudarDeEstado(caractereAtual, 39);
                    System.out.println("NO TERMINAL DE ACEITAÇÃO ALCANÇADO");
                }

                break;

            case 39:
                break;

            case 40:
                System.out.println("passei pelo estado 40");

                if((matcher = listaDeRegex.get(6).matcher(caractereAtual)).matches()) {
                    mudarDeEstado(caractereAtual, 37);
                } else {
                    mudarDeEstado(caractereAtual, 41);
                    System.out.println("NO TERMINAL DE ACEITAÇÃO ALCANÇADO");
                }

                break;

            case 41:
                break;

            case 42:
                break;

            case 43:
                break;

            case 44:
                break;

            case 45:
                break;

            case 46:
                break;

            case 47:
                break;

            case 48:
                break;

            case 49:
                break;

            case 50:
                break;

            case 51:
                break;

            case 52:
                break;

            case 53:
                break;

            case 54:
                break;

            case 55:
                break;

            case 56:
                break;

            case 57:
                break;

            case 58:
                break;

            case 59:
                break;

            case 60:
                break;

            case 61:
                break;

            case 62:
                break;

            case 63:
                break;

            case 64:
                break;

            case 65:
                break;

            case 66:
                break;

            case 67:
                break;

            case 68:
                break;

            case 69:
                break;

            case 70:
                break;

            case 71:
                break;

            case 72:
                break;

            case 73:
                break;

            case 74:
                break;

            case 75:
                break;

            case 76:
                break;

            case 77:
                break;

            case 78:
                break;

            case 79:

                break;

            default:
                System.err.println("Não existe tal estado!\nEstado: q" + estadoAtual + ".");
                System.exit(-1);
        }
    }

    private void manterEstado(String caractereAtual) {
        this.estado.setCaractereAtual(caractereAtual);
    }

    private void mudarDeEstado(String caractereAtual, Integer novoEstado) {
        this.estado.setEstadoAtual(novoEstado);
        this.estado.setCaractereAtual(caractereAtual);
        this.token = this.token.concat(caractereAtual);
    }

    private void esquecerTokenAtual() {
        this.token = "";
    }
}
