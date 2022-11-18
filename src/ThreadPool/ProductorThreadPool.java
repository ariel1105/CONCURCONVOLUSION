package ThreadPool;

import Buffer.Buffer;

import java.awt.image.WritableRaster;

public class ProductorThreadPool extends Thread{

    private Buffer buffer;

    private double[][] filtro;

    private WritableRaster origen;

    public ProductorThreadPool(Buffer buffer, double[][] filtro, WritableRaster origen){
        this.buffer = buffer;
        this.filtro = filtro;
        this.origen = origen;
    }

    @Override
    public void run(){
        for(int y = 1; y < origen.getHeight()-1; y++){
            for(int x = 1; x < origen.getWidth() - 1; x++){
                Task task = new Task(x,y,origen, filtro);
                this.buffer.write(task);
            }
        }

        /*for(int i = 0; i < this.tasks; i++){
            this.buffer.write(new DummyTask());
        }*/
        /*try {
            this.pool.stop();
            notify();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
    }


}
