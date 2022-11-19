package ThreadPool;

public class WorkerCounter {
    private int finishedWorkers = 0;
    private ThreadPool threadpool;

    public WorkerCounter(ThreadPool threadpool) {
        this.threadpool = threadpool;
    }

    public synchronized void stop() throws InterruptedException {
        while(this.finishedWorkers < this.threadpool.getWorkerList().size()){
            wait();
        }
        System.out.println("Terminó la convolución, revise la imagen de salida");
    }

    public synchronized void aumentarFinishedWorkers() throws InterruptedException {

        this.finishedWorkers ++ ;
        notify();
    }
}
