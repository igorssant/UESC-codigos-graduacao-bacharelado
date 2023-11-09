public class AutenticarComplexo extends AutenticarDecorator{
    public AutenticarComplexo(IAutenticar autentica){
        super(autentica);
    }

    public AutenticarComplexo(){}

    @Override
    public void autenticar(){
        super.autenticar();
        System.out.println(" Complexo");
    }
}
