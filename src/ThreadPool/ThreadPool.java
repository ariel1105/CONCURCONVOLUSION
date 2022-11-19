package ThreadPool;

import Buffer.Buffer;

import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

public class ThreadPool {

    private Buffer buffer;

    private WritableRaster origen;

    public WritableRaster destino;

    private double[][]filtro;

    public List<FilterWorker> workerList = new ArrayList<FilterWorker>();

    public List<FilterWorker> getWorkerList() {
        return workerList;
    }

    public ThreadPool(Integer capacidad, Integer workers, WritableRaster origen, WritableRaster destino, double[][]filtro){
        this.buffer = new Buffer(capacidad);
        this.origen = origen;
        this.destino = destino;
        this.filtro = filtro;
        for(int i = 0; i<workers; i++){
            FilterWorker worker = new FilterWorker(this.buffer, this);
            workerList.add(worker);
            worker.start();
        }

    }


    public synchronized void launch(){
        ProductorThreadPool productor = new ProductorThreadPool(this.buffer, this.filtro, this.origen, this.destino , this.workerList.size());
        productor.start();
    }



}
