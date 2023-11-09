package ProjetoLP3.lib.Gerenciador;

import ProjetoLP3.lib.Rio.Rio;
import java.util.ArrayList;

public class GerenciadorDeFluxo {
    
    private ArrayList<Rio> rios;
    private float consumoTotal;
    
    public GerenciadorDeFluxo () {} // Contrutor vazio
    
    public GerenciadorDeFluxo (Rio rio, float consumoTotal) {
        
        rios = new ArrayList<Rio> ();
        this.rios.add (rio);
        this.consumoTotal = consumoTotal;
    }
    
    public void adicionarRio (Rio rio) {
        
        this.rios.add (rio);
    }
    
    public void removerRio (Rio rio) {
        
        this.rios.remove (rio);
    }
    
        public float consumoTotal () {
        
        return this.consumoTotal;
    }
}