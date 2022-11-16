package ThreadPool;

import Buffer.Buffer;

public class ProductorThreadPool extends Thread{


    private Buffer buffer;

    private ThreadPool pool;

    private Integer tasks;

    public ProductorThreadPool(ThreadPool pool, Buffer buffer, Integer tasks){
        this.buffer = buffer;
        this.pool = pool;
        this.tasks = tasks;
    }

    @Override
    public void run(){
        for(int i = 0; i < this.tasks; i++){
            this.buffer.write(new DummyTask());
        }
        /*try {
            this.pool.stop();
            notify();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
    }


}
