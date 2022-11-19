package ThreadPool;

import ThreadPool.ThreadPool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class PoolStopper extends Thread {
    ThreadPool pool;
    File outputFile;
    BufferedImage bi_salida;

    public PoolStopper(ThreadPool pool , BufferedImage bi_salida , File outputFile) {

        this.pool = pool;
        this.bi_salida = bi_salida;
        this.outputFile = outputFile;
    }

    public void run() {
        try {
            pool.stop();
            ImageIO.write(bi_salida, "jpg", outputFile);
            long tiempoFinal = System.currentTimeMillis();
            System.out.println("Tiempo:" +" " + (tiempoFinal - UserPool.contadorInicial));

        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
