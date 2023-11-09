package controller;

import java.util.ArrayList;
import model.Integral;
import model.TabelaHermite;
import model.TabelaLaguerre;
import model.TabelaLegendre;
import model.TabelaTchebyshev;

/**
 * @author igorsssantana
 */
public class CalculaModelosMatematicos{
    /**
     * Retorna a integral calculada
     * recebe como parametro
     * a funcao original, um Objeto Integral
     * e um Objeto TabelaHermite
     * @param funcao
     * @param integral
     * @param tabelaHermite
     * @return 
     */
    public static Integral hermite(String funcao, Integral integral, TabelaHermite tabelaHermite){
        String novoX = MudancaVariaveis.novoX(integral.getLimiteInferior(), integral.getLimiteSuperior());
        
        funcao = MudancaVariaveis.contatenaFuncaoString(funcao, novoX);
        funcao = MudancaVariaveis.novoDx(funcao, integral.getLimiteInferior(), integral.getLimiteSuperior());
        integral.setIntegralCalculada(0f);
        
        for(int i = 0; i < tabelaHermite.getQuantidadePontos(); i++){
            integral.setIntegralCalculada(
                    integral.getIntegralCalculada() +
                    tabelaHermite.getPeso(i) * Funcao.funcao(
                            funcao,
                            tabelaHermite.getArgumento(i)
                    )
            );
        }
        
        return integral;
    }
    
    /**
     * Retorna a integral calculada
     * recebe como parametro
     * a funcao original, um Objeto Integral
     * e um Objeto TabelaLaguerre
     * @param funcao
     * @param integral
     * @param tabelaLaguerre
     * @return 
     */
    public static Integral laguerre(String funcao, Integral integral, TabelaLaguerre tabelaLaguerre){
        String novoX = MudancaVariaveis.novoX(integral.getLimiteInferior(), integral.getLimiteSuperior());
        
        funcao = MudancaVariaveis.contatenaFuncaoString(funcao, novoX);
        funcao = MudancaVariaveis.novoDx(funcao, integral.getLimiteInferior(), integral.getLimiteSuperior());
        integral.setIntegralCalculada(0f);
        
        for(int i = 0; i < tabelaLaguerre.getQuantidadePontos(); i++){
            integral.setIntegralCalculada(
                    integral.getIntegralCalculada() +
                    tabelaLaguerre.getPeso(i) * Funcao.funcao(
                            funcao,
                            tabelaLaguerre.getArgumento(i)
                    )
            );
        }
        
        return integral;
    }
    
    /**
     * Retorna a integral calculada
     * recebe como parametro
     * a funcao original, um Objeto Integral
     * e um Objeto TabelaLagrange
     * @param funcao
     * @param integral
     * @param tabelaLegendre
     * @return  integral
     */
    public static Integral legendre(String funcao, Integral integral, TabelaLegendre tabelaLegendre){
        String novoX = MudancaVariaveis.novoX(integral.getLimiteInferior(), integral.getLimiteSuperior());
        
        funcao = MudancaVariaveis.contatenaFuncaoString(funcao, novoX);
        funcao = MudancaVariaveis.novoDx(funcao, integral.getLimiteInferior(), integral.getLimiteSuperior());
        integral.setIntegralCalculada(0f);
        
        for(int i = 0; i < tabelaLegendre.getQuantidadePontos(); i++){
            integral.setIntegralCalculada(
                    integral.getIntegralCalculada() +
                    tabelaLegendre.getPeso(i) * Funcao.funcao(
                            funcao,
                            tabelaLegendre.getArgumento(i)
                    )
            );
        }
        
        return integral;
    }
    
    /**
     * Retorna a integral calculada
     * recebe como parametro
     * a funcao original, um Objeto Integral
     * e um Objeto TabelaTchebyshev
     * @param funcao
     * @param integral
     * @param tabelaTchebychev
     * @return  integral
     */
    public static Integral tchebyshev(String funcao, Integral integral, TabelaTchebyshev tabelaTchebychev){
        String novoX = MudancaVariaveis.novoX(integral.getLimiteInferior(), integral.getLimiteSuperior());
        ArrayList<String> lista = null;
        
        lista = MudancaVariaveis.retiraTchebyshev(funcao);
        
        //System.out.println(lista);
        
        funcao = MudancaVariaveis.contatenaFuncaoString(lista.get(1), novoX);
        funcao = MudancaVariaveis.novoDx(funcao, integral.getLimiteInferior(), integral.getLimiteSuperior());
        funcao = (new StringBuilder()).append(funcao).append(" * ").append(lista.get(0)).toString();
        
        lista.clear();
        integral.setIntegralCalculada(0f);
        
        System.out.println(funcao);
        
        for(int i = 0; i < tabelaTchebychev.getQuantidadePontos(); i++){
            integral.setIntegralCalculada(
                    integral.getIntegralCalculada() +
                    tabelaTchebychev.getPeso(i) * Funcao.funcao(
                            funcao,
                            tabelaTchebychev.getArgumento(i)
                    )
            );
        }        

        return integral;
    }
}