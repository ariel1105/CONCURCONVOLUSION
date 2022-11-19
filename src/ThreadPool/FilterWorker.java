package ThreadPool;

import Buffer.*;


public class FilterWorker extends Thread{

    private Buffer buffer;

    private ThreadPool pool;


    public FilterWorker(Buffer buffer, ThreadPool threadPool){
        this.buffer = buffer;
        this.pool = threadPool;
    }


    @Override
    public void run(){
        while (true){
            Runnable task = this.buffer.read();
            task.run();
        }

    }
}
