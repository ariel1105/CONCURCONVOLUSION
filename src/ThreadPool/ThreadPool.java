package ThreadPool;

import Buffer.Buffer;

import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

public class ThreadPool {

    private Integer tasks;

    private Buffer buffer;

    private WritableRaster origen;

    public static WritableRaster destino;

    private double[][]filtro;


    private List<FilterWorker> workerList = new ArrayList<FilterWorker>();

    public ThreadPool(Integer capacidad, Integer workers, WritableRaster origen, WritableRaster destino, double[][]filtro){
        this.buffer = new Buffer(capacidad);
        this.origen = origen;
        this.destino = destino;
        this.tasks = (origen.getHeight()-2)*(origen.getWidth()-2);
        this.filtro = filtro;
        for(int i = 0; i<workers; i++){
            FilterWorker worker = new FilterWorker(this.buffer, this);
            workerList.add(worker);
            worker.start();
        }

    }


    public WritableRaster getDestino() {
        return destino;
    }

    public synchronized void disminuirTareas(){
        this.tasks--;
        notify();
        //System.out.println("cantidad de tareas: " + this.tasks);
    }

    public synchronized void launch(){
        ProductorThreadPool productor = new ProductorThreadPool(this.buffer, this.filtro, this.origen);
        productor.start();
    }

    public double[][] getMatriz() {
        return this.filtro;
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
