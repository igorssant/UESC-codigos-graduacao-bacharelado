package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.Derivada;

/**
 * @author igorsssantana
 */
public class CalcularEuler{
    /**
     * Recebe um dicionario vazio,
     * cabeçalho para o dicionario: String
     * valores para popular o dicionario: Double
     * retorna o dicionario populado
     * @param dicionario
     * @param variaveis
     * @param xi
     * @param valoresYi
     * @return dict
     */
    public static Map<String, Double> popularDicionario(Map dicionario, String [] variaveis, double xi, double[] valoresYi){
        dicionario.put(variaveis[0], xi);
        
        for(int i = 0; i < valoresYi.length; i++){
            dicionario.put(variaveis[i+1], valoresYi[i]);
        }
        
        return dicionario;
    }
    
    /**
     * metodo para calcular a
     * primeira derivada nos pontos
     * @param funcao
     * @param euler
     * @return objeto Derivada
     */
    public static Derivada metodoDeEuler(String[] funcao, Derivada euler){
        double[] yi = euler.getY(),
                novoYi = null;
        String [] variaveisDaEquacao = null;
        Map<String, Double> dicionario = new HashMap<>();
        ArrayList<Double> valoresDeX = new ArrayList<>();
        
        novoYi = yi;
        
        while(true){
            for(int i = 0; i < funcao.length; i++){
                /* PEGAR AS VARIAVEIS QUE ESTAO NA EQUACAO ATUAL */
                variaveisDaEquacao = Funcao.variaveisAtuais(funcao[i]);
                
                /* FAZ UM DICIONARIO PARA APLICAR OS RESPECTIVOS VALORES */
                dicionario = popularDicionario(dicionario, variaveisDaEquacao, euler.getXi(), euler.getY());
                
                if(dicionario.containsKey("y") && dicionario.containsKey("z")){
                    novoYi[i] += Funcao.funcao(
                            funcao[i],
                            dicionario.get("y"),
                            dicionario.get("z")
                    ) * euler.getH();
                }
                
                else if(dicionario.containsKey("y")){
                    novoYi[i] += Funcao.funcao(
                            funcao[i],
                            dicionario.get("y")
                    ) * euler.getH();
                }
            }
            
            valoresDeX.add(euler.getXi());
            euler.setValoresDeY(novoYi);
            euler.setXi(euler.getXi() + euler.getH());
            
            if(euler.getXi() > euler.getXf()){
                break;
            }
        }
        
        euler.setValoresDeX(valoresDeX.stream().mapToDouble(d -> d).toArray());
        
        return euler;
    }
}