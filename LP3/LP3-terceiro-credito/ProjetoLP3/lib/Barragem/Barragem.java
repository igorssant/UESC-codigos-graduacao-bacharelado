package ProjetoLP3.lib.Barragem;

public interface Barragem {
    
    public void abrirComportas (int numero);
    public void fecharComportas (int numero);
    public void adicionarComporta (int numero);
    public void removeComporta (int numero);
    public float getNivelLago ();
    public void setNivelLago (float quantidade);
    public float getVazao ();
    public void setVazao (float vazao);
    public float getConsumo ();
    public void setConsumo (float consumo);
    
}