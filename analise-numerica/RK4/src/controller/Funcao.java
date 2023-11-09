package controller;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

/**
 * @author igorsssantana
 */
public class Funcao{
    /**
     * Recebe uma funcao em String,
     * ponto x e ponto y
     * retorna f(x, y)
     * @param stringFuncao
     * @param x
     * @param y
     * @return f(x,y)
     */
    public static double funcao(String stringFuncao, double x, double y){
        Function funcao = new Function(stringFuncao);
        Argument argumentoX = new Argument("x = " + Double.toString(x));
        Argument argumentoY = new Argument("y = " + Double.toString(y));
        Expression instancia = new Expression("f(x, y)", funcao, argumentoX, argumentoY);
        return instancia.calculate();
    }
}