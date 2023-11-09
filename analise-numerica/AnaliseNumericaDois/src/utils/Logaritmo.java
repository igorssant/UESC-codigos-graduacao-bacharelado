package utils;
/**
 * @author igorsssantana
 */
import java.lang.Math;

public class Logaritmo{
    /**
     * Esse metodo recebe a base e o operando.
     * Retorna o resultado do logaritmo na base passada como argumento.
     * @param base
     * @param operando
     * @return resultado
     */
    public static double logBaseQualquer(double base, double operando){
        double resultado = Math.log(operando) / Math.log(base);
        return resultado;
    }
}