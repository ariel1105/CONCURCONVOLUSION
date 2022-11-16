package ThreadPool;

import Buffer.Buffer;

public class ThreadWorker extends Thread{

    private Buffer buffer;

    private ThreadPool pool;


    public ThreadWorker(Buffer buffer, ThreadPool threadPool){
        this.buffer = buffer;
        this.pool = threadPool;
    }

    public void run(){
        while (true){
            Runnable task = (Runnable) this.buffer.read();
            task.run();
            this.pool.disminuirTareas();
        }
    }
}
