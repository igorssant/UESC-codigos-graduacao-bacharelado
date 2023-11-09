package controller;


import model.Integral;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

/**
 * @author igorsssantana
 */
public class IntegralMultiplaTrapezio{
    /**
     * Recebe uma funcao em String
     * e um ponto x
     * retorna f(x)
     * @param stringFuncao
     * @param x
     * @return fx = funcao(funcao, derivadanumerica.getPontosX((int) (posicaoDoPonto + derivadanumerica.getH())));
     */
    public static double funcao(String stringFuncao, double x){
        Function funcao = new Function(stringFuncao);
        Argument argumento = new Argument("x = " + Double.toString(x));
        Expression instancia = new Expression("f(x)", funcao, argumento);
        return instancia.calculate();
    }
    
    /**
     * @param funcao 
     */
    public static double integralMultiplaTrapezio(String funcao, Integral multiTrapezio){
        double somatorio = 0f,
                x = multiTrapezio.getPontos(0);
        
        multiTrapezio.gerarNovoH();
        somatorio += funcao(funcao, x);
        
        for(int i = 1; i < multiTrapezio.getIntervalo(); i++){
            x += multiTrapezio.getH();
            somatorio += 2 * funcao(funcao, x);
        }
        
        somatorio += funcao(funcao, multiTrapezio.getPontos(1));
        return ((somatorio / (2 * multiTrapezio.getIntervalo())) * (multiTrapezio.getPontos(1) - multiTrapezio.getPontos(0)));
    }
}