package controller;

import model.TresOitavos;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

/**
 * @author igorsssantana
 */
public class IntegralMultiplaSimp38{    
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
    
    public static double integralMultiTresOitavos(String funcao, TresOitavos integral){
        double auxiliar = 0f;
        
        integral.calculaNovoH();
        integral.setIntegralCalculada(
            funcao(
                funcao,
                integral.getLimiteInferior()
            ) + funcao(
                funcao,
                integral.getLimiteSuperior()
            )
        );
        
        
        for(int i = 1; i < integral.getIntervalo(); i++){
            auxiliar = integral.getLimiteInferior() + (integral.getH() * i);
            
            if(i % 3 == 0){
                integral.setIntegralCalculada(
                    integral.getIntegralCalculada()
                    + (2 * funcao(
                        funcao,
                        auxiliar
                    ))
                );
            }
            
            else{
                integral.setIntegralCalculada(
                    integral.getIntegralCalculada()
                    + (3 * funcao(
                        funcao,
                        auxiliar
                    ))
                );
            }
        }
        
        integral.setIntegralCalculada(
            integral.getIntegralCalculada() * ((3 * integral.getH()) / 8)
        );
        
        return integral.getIntegralCalculada();
    }
}