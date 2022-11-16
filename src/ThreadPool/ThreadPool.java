package ThreadPool;

import Buffer.Buffer;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {

    private Integer tasks;

    private Buffer buffer;


    private List<ThreadWorker> workerList = new ArrayList<ThreadWorker>();

    public ThreadPool(Integer capacidad, Integer workers, Integer tasks){
        this.buffer = new Buffer(capacidad);
        this.tasks = tasks;
        for(int i = 0; i<workers; i++){
            ThreadWorker worker = new ThreadWorker(this.buffer, this);
            workerList.add(worker);
            worker.start();
        }
    }

    public synchronized void disminuirTareas(){
        this.tasks--;
        notify();
        System.out.println("cantidad de tareas: " + this.tasks);
    }

    public synchronized void launch(){
        ProductorThreadPool productor = new ProductorThreadPool(this, this.buffer, this.tasks);
        productor.start();
    }

    public synchronized void stop() throws InterruptedException {
        while(this.tasks != 0){
            wait();
        }
        for(int i = 0; i < this.workerList.size(); i++){
            PoisonPill pill = new PoisonPill();
            this.buffer.write(pill);
        }
    }

}
