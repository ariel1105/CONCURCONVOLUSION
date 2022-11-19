package ThreadPool;

import Buffer.Buffer;

import java.awt.image.WritableRaster;

public class ProductorThreadPool extends Thread{

    private WritableRaster destino;
    private  int workers;
    private Buffer buffer;

    private double[][] filtro;

    private WritableRaster origen;

    public ProductorThreadPool(Buffer buffer, double[][] filtro, WritableRaster origen, WritableRaster destino, int workers){
        this.buffer = buffer;
        this.filtro = filtro;
        this.origen = origen;
        this.workers = workers;
        this.destino = destino;
    }

    @Override
    public void run(){
        for(int y = 1; y < origen.getHeight()-1; y++){
            for(int x = 1; x < origen.getWidth() - 1; x++){
                Task task = new Task(x,y,origen, destino , filtro);
                this.buffer.write(task);
            }

        }

        for(int i = 0; i < workers; i++){
            PoisonPill pill = new PoisonPill();
            this.buffer.write(pill);
        }

    }


}
