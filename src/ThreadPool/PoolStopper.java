package ThreadPool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PoolStopper extends Thread {
    public static WorkerCounter workerCounter;
    public File outputFile;
    public BufferedImage bi_salida;

    public PoolStopper(WorkerCounter workerCounter , BufferedImage bi_salida , File outputFile) {

        this.workerCounter = workerCounter;
        this.bi_salida = bi_salida;
        this.outputFile = outputFile;
    }

    public void run() {
        try {
            workerCounter.stop();
            ImageIO.write(bi_salida, "jpg", outputFile);
            long tiempoFinal = System.currentTimeMillis();
            System.out.println("Tiempo:" +" " + (tiempoFinal - UserPool.contadorInicial));

        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
