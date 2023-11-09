package ProjetoLP3.lib.Rio;

import java.util.ArrayList;

import ProjetoLP3.lib.Barragem.Barragem;

public class Rio {
    
    private float volume;
    private final float volumeMaximo;
    private ArrayList<Barragem> barragens;
    
    public Rio (float volumeMaximo) {
        
        this.barragens = new ArrayList<Barragem> ();
        this.volumeMaximo = volumeMaximo;
        
    }

    public void Chover (float quantidade) {
        
        this.volume += quantidade;
    }

    public float Nivel () {
        
        return (this.volume / this.volumeMaximo) * 100;
    }

    public float volumeAtual () {
        
        return this.volume;
    }

    public float volumeMaximo () {
        
        return this.volumeMaximo;
    }

    public  ArrayList<Barragem> listarBarragens () {
        
        return this.barragens;
    }

    public void addBarragem (Barragem barragem) {
        
        this.barragens.add(barragem);
    }
    
    public void addBarragem (int posicao, Barragem barragem) {
        
        this.barragens.add(posicao, barragem);
    }

    public void removeBarragem (int posicao) {
        
        this.barragens.remove(posicao);
    }

    public void removeBarragem (Barragem barragem) {
        
        this.barragens.remove(barragem);
    }
}