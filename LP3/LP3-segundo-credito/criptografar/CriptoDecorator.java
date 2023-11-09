public class CriptoDecorator implements ICriptografar{
    private ICriptografar base;

    CriptoDecorator(ICriptografar base){
        this.base = base;
    }

    @Override
    public void criptografar(){
        this.base.criptografar();    
    }
}
