package ThreadPool;


public class PoisonPill implements Runnable {
    public PoisonPill(){

    }
    @Override
    public void run() {
        try {
            PoolStopper.workerCounter.aumentarFinishedWorkers();
            throw new PoisonException();
        } catch (PoisonException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
