public class CriptografarComplexo extends CriptoDecorator{
    public CriptografarComplexo(ICriptografar base){
        super(base);
    }

    @Override
    public void criptografar(){
        super.criptografar();
        System.out.println(" Complexa");
    }
}
