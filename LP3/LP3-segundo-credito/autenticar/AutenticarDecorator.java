public abstract class AutenticarDecorator implements IAutenticar{
    private IAutenticar autentica;

    public AutenticarDecorator(){}

    public AutenticarDecorator(IAutenticar autentica){
        this.autentica = autentica;
    }

    @Override
    public void autenticar(){
        autentica.autenticar();
    }

  /*  public void setAutentica(IAutenticar autentica)
    {
        this.autentica = autentica;
    }

    public IAutenticar getAutentica()
    {
        return this.autentica;
    }*/
}
