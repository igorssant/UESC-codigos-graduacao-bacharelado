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
     * ponto k
     * retorna f(k)
     * @param stringFuncao
     * @param k
     * @return f(k)
     */
    public static double funcao(String stringFuncao, double k){
        Function funcao = new Function(stringFuncao);
        Argument argumentoK = new Argument("k = " + Double.toString(k));
        Expression instancia = new Expression("f(k)", funcao, argumentoK);
        return instancia.calculate();
    }
    
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
    
    /**
     * Recebe uma funcao em String,
     * uma equação k, um ponto x
     * e ponto y
     * retorna f(x, y, k)
     * @param stringFuncao
     * @param x
     * @param y
     * @param k
     * @return f(x, y, k)
     */
    public static double funcao(String stringFuncao, double x, double y, double k){
        Function funcao = new Function(stringFuncao);
        Argument argumentoX = new Argument("x = " + Double.toString(x));
        Argument argumentoY = new Argument("y = " + Double.toString(y));
        Argument argumentoK = new Argument("k = " + Double.toString(k));
        Expression instancia = new Expression("f(x, y, k)", funcao, argumentoX, argumentoY, argumentoK);
        return instancia.calculate();
    }
    
    /**
     * 
     * @param funcaoAtual
     * @return 
     */
    public static String [] variaveisAtuais(String funcaoAtual){
        String [] variaveis = new String[3],
                primitivas = new String[]{"x", "y", "k"};
        String mantem = funcaoAtual.substring(0, 13);
        funcaoAtual.replace(mantem, "");
        mantem = null;
        
        for(int i = 0; i < variaveis.length; i++){
            if(funcaoAtual.contains(primitivas[2])){
                variaveis[2] = primitivas[2];
            }
            
            if(funcaoAtual.contains(primitivas[1])){
                variaveis[1] = primitivas[1];
            }
            
            if(funcaoAtual.contains(primitivas[0])){
                variaveis[0] = primitivas[0];
            }
        }
        
        return variaveis;
    }
}