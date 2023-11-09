package model;

/**
 * @author igorsssantana
 */
public class TabelaHermite{
    private int quantidadePontos;
    private double[][] tabela;

    /**
     * Construtor de classe vazio
     */
    public TabelaHermite(){}

    /**
     * Contrutor principal da classe
     * recebe a quantidade de pontos
     * @param quantidadePontos 
     */
    public TabelaHermite(int quantidadePontos){
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
    public TabelaHermite(int quantidadePontos, double[][] tabela){
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
        this.tabela[0][0] = 0.8862269254;
        this.tabela[0][1] = 0.8862269254;
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
        this.tabela[0][0] = 0.2954089751;
        this.tabela[0][1] = 1.181635900;
        this.tabela[0][2] = 0.2954089751;
        //--------------------
        this.tabela[1][0] = -1.224744871;
        this.tabela[1][1] = 0f;
        this.tabela[1][2] = 1.224744871;
        
        /*return this.tabela;*/
    }
    
    private void tabelaQuatroPontos(){
        this.tabela = new double[2][4];
        
        /*
         *  LINHA 0 == PESOS
         *  LINHA 1 == ARGUMENTOS
        */
        this.tabela[0][0] = 0.08131283544;
        this.tabela[0][1] = 0.8049140900;
        this.tabela[0][2] = 0.8049140900;
        this.tabela[0][3] = 0.08131283544;
        //--------------------
        this.tabela[1][0] = -1.650680123;
        this.tabela[1][1] = -0.5246476323;
        this.tabela[1][2] = 0.5246476323;
        this.tabela[1][3] = 1.650680123;
        
        /*return this.tabela;*/
    }
    
    private void tabelaCincoPontos(){
        this.tabela = new double[2][5];
        
        /*
         *  LINHA 0 == PESOS
         *  LINHA 1 == ARGUMENTOS
        */
        this.tabela[0][0] = 0.01995324205;
        this.tabela[0][1] = 0.3936193231;
        this.tabela[0][2] = 0.6453087204;
        this.tabela[0][3] = 0.3936193231;
        this.tabela[0][4] = 0.01995324205;
        //--------------------
        this.tabela[1][0] = -2.020182870;
        this.tabela[1][1] = -0.9585724646;
        this.tabela[1][2] = 0f;
        this.tabela[1][3] = 0.9585724646;
        this.tabela[1][4] = 2.020182870;
        
        /*return this.tabela;*/
    }
    
    private void tabelaSeisPontos(){
        this.tabela = new double[2][6];
        
        /*
         *  LINHA 0 == PESOS
         *  LINHA 1 == ARGUMENTOS
        */
        this.tabela[0][0] = 0.004530009905;
        this.tabela[0][1] = 0.1570673203;
        this.tabela[0][2] = 0.7246295952;
        this.tabela[0][3] = 0.7246295952;
        this.tabela[0][4] = 0.1570673203;
        this.tabela[0][5] = 0.004530009905;
        //--------------------
        this.tabela[1][0] = -2.350604973;
        this.tabela[1][1] = -1.335849074;
        this.tabela[1][2] = -0.4360774119;
        this.tabela[1][3] = 0.4360774119;
        this.tabela[1][4] = 1.335849074;
        this.tabela[1][5] = 2.350604973;
        
        /*return this.tabela;*/
    }
    
    private void tabelaSetePontos(){
        this.tabela = new double[2][7];
        
        /*
         *  LINHA 0 == PESOS
         *  LINHA 1 == ARGUMENTOS
        */
        this.tabela[0][0] = 0.0009717812450;
        this.tabela[0][1] = 0.05451558281;
        this.tabela[0][2] = 0.4256072526;
        this.tabela[0][3] = 0.8102646175;
        this.tabela[0][4] = 0.4256072526;
        this.tabela[0][5] = 0.05451558281;
        this.tabela[0][6] = 0.0009717812450;
        //--------------------
        this.tabela[1][0] = -2.651961356;
        this.tabela[1][1] = -1.673551628;
        this.tabela[1][2] = -0.8162878828;
        this.tabela[1][3] = 0f;
        this.tabela[1][4] = 0.8162878828;
        this.tabela[1][5] = 1.673551628;
        this.tabela[1][6] = 2.651961356;
        
        /*return this.tabela;*/
    }
    
    private void tabelaOitoPontos(){
        this.tabela = new double[2][8];
        
        /*
         *  LINHA 0 == PESOS
         *  LINHA 1 == ARGUMENTOS
        */
        this.tabela[0][0] = 0.0001996040722;
        this.tabela[0][1] = 0.01707798300;
        this.tabela[0][2] = 0.2078023258;
        this.tabela[0][3] = 0.6611470125;
        this.tabela[0][4] = 0.6611470125;
        this.tabela[0][5] = 0.2078023258;
        this.tabela[0][6] = 0.01707798300;
        this.tabela[0][7] = 0.0001996040722;
        //--------------------
        this.tabela[1][0] = -2.930637420;
        this.tabela[1][1] = -1.981656756;
        this.tabela[1][2] = -1.157193712;
        this.tabela[1][3] = -0.3811869902;
        this.tabela[1][4] = 0.3811869902;
        this.tabela[1][5] = 1.157193712;
        this.tabela[1][6] = 1.981656756;
        this.tabela[1][7] = 2.930637420;
        
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