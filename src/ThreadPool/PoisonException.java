package ThreadPool;

public class PoisonException extends Exception {

    public PoisonException(){
        super("Termino el thread");
    }
}
