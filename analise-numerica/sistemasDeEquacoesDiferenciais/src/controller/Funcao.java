package controller;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

/**
 * @author igorsssantana
 */
public class Funcao{
    /**
     * Recebe uma funcao em String
     * e um ponto x
     * retorna f(x)
     * @param stringFuncao
     * @param y
     * @return f(x)
     */
    public static double funcao(String stringFuncao, double y){
        Function funcao = new Function(stringFuncao);
        Argument argumentoX = new Argument("y = " + Double.toString(y));
        Expression instancia = new Expression("f(y)", funcao, argumentoX);
        return instancia.calculate();
    }
    
    /**
     * Recebe uma funcao em String,
     * ponto x e ponto y
     * retorna f(x, y)
     * @param stringFuncao
     * @param z
     * @param y
     * @return f(x,y)
     */
    public static double funcao(String stringFuncao, double y, double z){
        Function funcao = new Function(stringFuncao);
        Argument argumentoX = new Argument("y = " + Double.toString(y));
        Argument argumentoY = new Argument("z = " + Double.toString(z));
        Expression instancia = new Expression("f(y, z)", funcao, argumentoX, argumentoY);
        return instancia.calculate();
    }
    
    /**
     * Recebe uma funcao em String,
     * ponto x, ponto y1 e ponto y2
     * retorna f(x, y1, y2)
     * @param stringFuncao
     * @param x
     * @param y
     * @param z
     * @return f(x, y1, y2)
     */
    public static double funcao(String stringFuncao, double x, double y, double z){
        Function funcao = new Function(stringFuncao);
        Argument argumentoX = new Argument("x = " + Double.toString(x));
        Argument argumentoY1 = new Argument("y = " + Double.toString(y));
        Argument argumentoY2 = new Argument("z = " + Double.toString(z));
        Expression instancia = new Expression("f(x, y, z)", funcao, argumentoX, argumentoY1, argumentoY2);
        return instancia.calculate();
    }
    
    /**
     * Recebe uma funcao String
     * um argumento (x, y, z) etc
     * e o valor double para substituir
     * @param funcao
     * @param argumento
     * @param valorNovo
     * @return funcao modificada
     */
    public static String modificaFuncao(String funcao, String argumento, double valorNovo){
        String mantem = funcao.substring(0, 10);
        funcao = funcao.replace("f(x, y) = ", "");
        funcao = funcao.replace(argumento, "(" +Double.toString(valorNovo) + ")");
        mantem = mantem.concat(funcao);
        return mantem;
    }
    
    /**
     * Recebe uma funcao String
     * um argumento (x, y1, y2) etc
     * e o valor double para substituir
     * @param funcao
     * @param argumento1
     * @param argumento2
     * @param valorNovo1
     * @param valorNovo2
     * @return funcao modificada
     */
    public static String modificaFuncao(String funcao, String argumento1, String argumento2, double valorNovo1, double valorNovo2){
        String mantem = funcao.substring(0, 15);
        funcao = funcao.replace("f(x, y1, y2) = ", "");
        funcao = funcao.replace(argumento1, "(" +Double.toString(valorNovo1) + ")");
        funcao = funcao.replace(argumento2, "(" +Double.toString(valorNovo2) + ")");
        mantem = mantem.concat(funcao);
        return mantem;
    }
    
    /**
     * Recebe uma funcao String
     * um argumento (x, y1, y2) etc
     * e o valor double para substituir
     * @param funcao
     * @param argumento1
     * @param argumento2
     * @param argumento3
     * @param valorNovo1
     * @param valorNovo2
     * @param valorNovo3
     * @return funcao modificada
     */
    public static String modificaFuncao(String funcao, String argumento1, String argumento2, String argumento3, double valorNovo1, double valorNovo2, double valorNovo3){
        String mantem = funcao.substring(0, 15);
        funcao = funcao.replace("f(x, y1, y2) = ", "");
        funcao = funcao.replace(argumento1, "(" +Double.toString(valorNovo1) + ")");
        funcao = funcao.replace(argumento2, "(" +Double.toString(valorNovo2) + ")");
        funcao = funcao.replace(argumento3, "(" +Double.toString(valorNovo3) + ")");
        mantem = mantem.concat(funcao);
        return mantem;
    }
    
    /**
     * 
     * @param funcaoAtual
     * @return 
     */
    public static String [] variaveisAtuais(String funcaoAtual){
        String [] variaveis = new String[3],
                primitivas = new String[]{"x", "y", "z"};
        String mantem = funcaoAtual.substring(0, 10);
        
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