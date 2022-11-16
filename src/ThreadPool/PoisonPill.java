package ThreadPool;

public class PoisonPill implements Runnable {

    public PoisonPill(){

    }
    @Override
    public void run() {
        try {
            System.out.println("Corto");
            throw new PoisonException();
        } catch (PoisonException e) {
            throw new RuntimeException(e);
        }
    }
}
