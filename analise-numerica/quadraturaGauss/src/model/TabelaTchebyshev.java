package model;

/**
 * @author igorsssantana
 */
public class TabelaTchebyshev{
    private int quantidadePontos;
    private double[][] tabela;

    /**
     * Construtor de classe vazio
     */
    public TabelaTchebyshev(){}

    /**
     * Contrutor principal da classe
     * recebe a quantidade de pontos
     * @param quantidadePontos 
     */
    public TabelaTchebyshev(int quantidadePontos){
        if(quantidadePontos > 1 &&  quantidadePontos < 9){
            this.quantidadePontos = quantidadePontos;
        }
        
        else{
            System.out.println("Uma quadratura deve possuir entre 2 e 6 pontos.\nFoi digitado um número abaixo ou acima desse intervalo!!!");
            System.exit(-1);
        }
        
        gerarNovaTabela();
    }

    /**
     * Construtor completo de classe
     * recebe a quantidade de pontos
     * e a tabela completa
     * @param quantidadePontos
     * @param tabela 
     */
    public TabelaTchebyshev(int quantidadePontos, double[][] tabela){
        if(quantidadePontos > 1 &&  quantidadePontos < 9){
            this.quantidadePontos = quantidadePontos;
            this.tabela = tabela;
        }
        
        else{
            System.out.println("Uma quadratura deve possuir entre 2 e 6 pontos.\nFoi digitado um número abaixo ou acima desse intervalo!!!");
            System.exit(-1);
        }
        
        gerarNovaTabela();
    }

    public int getQuantidadePontos(){
        return this.quantidadePontos;
    }

    public void setQuantidadePontos(int quantidadePontos){
        this.quantidadePontos = quantidadePontos;
    }

    public double[][] getTabela(){
        return this.tabela;
    }

    public void setTabela(double[][] tabela){
        this.tabela = tabela;
    }
    
    public double getPeso(int indice){
        return this.tabela[0][indice];
    }
    
    public double getArgumento(int indice){
        return this.tabela[1][indice];
    }
    
    private void tabelaDoisPontos(){
        this.tabela = new double[2][2];
        
        /*
         *  LINHA 0 == PESOSe
         *  LINHA 1 == ARGUMENTOS
        */
        this.tabela[0][0] = 1.570796326;
        this.tabela[0][1] = -1.570796326;
        //--------------------
        this.tabela[1][0] = 0.7071067811;
        this.tabela[1][1] = -0.7071067811;
        
        /*return this.tabela;*/
    }
    
    private void tabelaTresPontos(){
        this.tabela = new double[2][3];
        
        /*
         *  LINHA 0 == PESOS
         *  LINHA 1 == ARGUMENTOS
        */
        this.tabela[0][0] = 1.047197551;
        this.tabela[0][1] = 1.047197551;
        this.tabela[0][2] = 1.047197551;
        //--------------------
        this.tabela[1][0] = -0.8660254037;
        this.tabela[1][1] = 0f;
        this.tabela[1][2] = 0.8660254037;
        
        /*return this.tabela;*/
    }
    
    private void tabelaQuatroPontos(){
        this.tabela = new double[2][4];
        
        /*
         *  LINHA 0 == PESOS
         *  LINHA 1 == ARGUMENTOS
        */
        this.tabela[0][0] = 0.7853981633;
        this.tabela[0][1] = 0.7853981633;
        this.tabela[0][2] = 0.7853981633;
        this.tabela[0][3] = 0.7853981633;
        //--------------------
        this.tabela[1][0] = -0.9238795325;
        this.tabela[1][1] = -0.3826834323;
        this.tabela[1][2] = 0.3826834323;
        this.tabela[1][3] = 0.9238795325;
        
        /*return this.tabela;*/
    }
    
    private void tabelaCincoPontos(){
        this.tabela = new double[2][5];
        
        /*
         *  LINHA 0 == PESOS
         *  LINHA 1 == ARGUMENTOS
        */
        this.tabela[0][0] = 0.6283185307;
        this.tabela[0][1] = 0.6283185307;
        this.tabela[0][2] = 0.6283185307;
        this.tabela[0][3] = 0.6283185307;
        this.tabela[0][4] = 0.6283185307;
        //--------------------
        this.tabela[1][0] = -0.9510565162;
        this.tabela[1][1] = -0.5877852522;
        this.tabela[1][2] = 0f;
        this.tabela[1][3] = 0.5877852522;
        this.tabela[1][4] = 0.9510565162;
        
        /*return this.tabela;*/
    }
    
    private void tabelaSeisPontos(){
        this.tabela = new double[2][6];
        
        /*
         *  LINHA 0 == PESOS
         *  LINHA 1 == ARGUMENTOS
        */
        this.tabela[0][0] = 0.5235987755;
        this.tabela[0][1] = 0.5235987755;
        this.tabela[0][2] = 0.5235987755;
        this.tabela[0][3] = 0.5235987755;
        this.tabela[0][4] = 0.5235987755;
        this.tabela[0][5] = 0.5235987755;
        //--------------------
        this.tabela[1][0] = -0.9659258262;
        this.tabela[1][1] = -0.7071067811;
        this.tabela[1][2] = -0.2588190451;
        this.tabela[1][3] = 0.2588190451;
        this.tabela[1][4] = 0.7071067811;
        this.tabela[1][5] = 0.9659258262;
        
        /*return this.tabela;*/
    }
    
    private void tabelaSetePontos(){
        this.tabela = new double[2][7];
        
        /*
         *  LINHA 0 == PESOS
         *  LINHA 1 == ARGUMENTOS
        */
        this.tabela[0][0] = 0.4487989505;
        this.tabela[0][1] = 0.4487989505;
        this.tabela[0][2] = 0.4487989505;
        this.tabela[0][3] = 0.4487989505;
        this.tabela[0][4] = 0.4487989505;
        this.tabela[0][5] = 0.4487989505;
        this.tabela[0][6] = 0.4487989505;
        //--------------------
        this.tabela[1][0] = -0.9749279121;
        this.tabela[1][1] = -0.7818314824;
        this.tabela[1][2] = -0.4338837391;
        this.tabela[1][3] = 0f;
        this.tabela[1][4] = 0.4338837391;
        this.tabela[1][5] = 0.7818314824;
        this.tabela[1][6] = 0.9749279121;
        
        /*return this.tabela;*/
    }
    
    private void tabelaOitoPontos(){
        this.tabela = new double[2][8];
        
        /*
         *  LINHA 0 == PESOS
         *  LINHA 1 == ARGUMENTOS
        */
        this.tabela[0][0] = 0.3926990816;
        this.tabela[0][1] = 0.3926990816;
        this.tabela[0][2] = 0.3926990816;
        this.tabela[0][3] = 0.3926990812;
        this.tabela[0][4] = 0.3926990812;
        this.tabela[0][5] = 0.3926990812;
        this.tabela[0][6] = 0.3926990812;
        this.tabela[0][7] = 0.3926990812;
        //--------------------
        this.tabela[1][0] = -0.9807852804;
        this.tabela[1][1] = -0.8314696123;
        this.tabela[1][2] = -0.5555702330;
        this.tabela[1][3] = -0.1950903220;
        this.tabela[1][4] = 0.1950903220;
        this.tabela[1][5] = 0.5555702330;
        this.tabela[1][6] = 0.8314696123;
        this.tabela[1][7] = 0.9807852804;
        
        /*return this.tabela;*/
    }
    
    private void gerarNovaTabela(){
        switch(this.quantidadePontos){
            case 2:
                tabelaDoisPontos();
                break;
            
            case 3:
                tabelaTresPontos();
                break;
            
            case 4:
                tabelaQuatroPontos();
                break;
            
            case 5:
                tabelaCincoPontos();
                break;
            
            case 6:
                tabelaSeisPontos();
                break;
                
            case 7:
                tabelaSetePontos();
                break;
            
            case 8:
                tabelaOitoPontos();
                break;
            
            default:
                System.out.println("Ocorreu um erro inesperado.\nPor favor, tente novamente.");
        }
    }
}