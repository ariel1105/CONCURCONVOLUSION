import ThreadPool.ThreadPool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String [] args) throws IOException {
        /*class UserPool extends Thread {

            ThreadPool pool;

            public UserPool(ThreadPool pool) {
                this.pool = pool;
            }

            public void run() {
                pool.launch();
            }
        }
        class PoolStopper extends Thread {
            ThreadPool pool;

            public PoolStopper(ThreadPool pool) {
                this.pool = pool;
            }

            public void run() {
                try {
                    pool.stop();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        ThreadPool pool = new ThreadPool(5, 4, 100);
        UserPool user = new UserPool(pool);
        PoolStopper stopper = new PoolStopper(pool);
        user.start();
        stopper.start();
    }*/

/*
        int[][] matriz = {{1,2,3},{4,5,6},{7,8,9}};
        matriz[0][0] = 1; matriz[0][1] = 2; matriz[0][2] = 3;
        matriz[1][0] = 4; matriz[1][1] = 5; matriz[1][2] = 6;
        matriz[2][0] = 7; matriz[2][1] = 8; matriz[2][2] = 9;
*/


        /*for(int y = 1; y < alto-1; y++){
            for(int x = 1; x < ancho-1; x++){
                //generando rojo

                double a11Rojo = raster.getSample(x-1,y+1,0);
                double a12Rojo = raster.getSample(x-1,y,0);
                double a13Rojo = raster.getSample(x-1,y-1,0);
                double a21Rojo = raster.getSample(x,y+1,0);
                double a22Rojo = raster.getSample(x,y,0);
                double a23Rojo = raster.getSample(x,y-1,0);
                double a31Rojo = raster.getSample(x+1,y+1,0);
                double a32Rojo = raster.getSample(x+1,y,0);
                double a33Rojo = raster.getSample(x+1,y-1,0);

                double rojo = a11Rojo * matriz[0][0] + a12Rojo * matriz[0][1] + a13Rojo * matriz[0][2]
                            + a21Rojo * matriz[1][0] + a22Rojo * matriz[1][1] + a23Rojo * matriz[1][2]
                            + a31Rojo * matriz[2][0] + a32Rojo * matriz[2][1] + a33Rojo * matriz[2][2];

                if(x==1 && y==1){
                    System.out.println(rojo);
                }
                if (rojo > 255){rojo = 255;}
                if (rojo < 0){rojo = 0;}
                destino.setSample(x,y,0, rojo);

                //genero  verde

                double a11Verde = raster.getSample(x-1,y+1,1);
                double a12Verde = raster.getSample(x-1,y,1);
                double a13Verde = raster.getSample(x-1,y-1,1);
                double a21Verde = raster.getSample(x,y+1,1);
                double a22Verde = raster.getSample(x,y,1);
                double a23Verde = raster.getSample(x,y-1,1);
                double a31Verde = raster.getSample(x+1,y+1,1);
                double a32Verde = raster.getSample(x+1,y,1);
                double a33Verde = raster.getSample(x+1,y-1,1);

                double verde = a11Verde * matriz[0][0] + a12Verde * matriz[0][1] + a13Verde * matriz[0][2]
                             + a21Verde * matriz[1][0] + a22Verde * matriz[1][1] + a23Verde * matriz[1][2]
                             + a31Verde * matriz[2][0] + a32Verde * matriz[2][1] + a33Verde * matriz[2][2];

                if (verde > 255){verde = 255;}
                if (verde < 0){verde = 0;}
                destino.setSample(x,y,1, verde);
                //genero azul


                double a11Azul = raster.getSample(x-1,y+1,2);
                double a12Azul = raster.getSample(x-1,y,2);
                double a13Azul = raster.getSample(x-1,y-1,2);
                double a21Azul = raster.getSample(x,y+1,2);
                double a22Azul = raster.getSample(x,y,2);
                double a23Azul = raster.getSample(x,y-1,2);
                double a31Azul = raster.getSample(x+1,y+1,2);
                double a32Azul = raster.getSample(x+1,y,2);
                double a33Azul = raster.getSample(x+1,y-1,2);

                double azul = a11Azul * matriz[0][0] + a12Azul * matriz[0][1] + a13Azul * matriz[0][2]
                            + a21Azul * matriz[1][0] + a22Azul * matriz[1][1] + a23Azul * matriz[1][2]
                            + a31Azul * matriz[2][0] + a32Azul * matriz[2][1] + a33Azul * matriz[2][2];
                if (azul > 255){azul = 255;}
                if (azul < 0){azul = 0;}
                destino.setSample(x,y,2,azul);
            }
        }


*/
        //blur
        //double[][] matriz = {{1/9d,1/9d,1/9d},{1/9d,1/9d,1/9d},{1/9d,1/9d,1/9d}};

        //k-sharpen
        double[][] matriz = {{0,-1,0},{-1,5,-1},{0,-1,0}};

        //sobel horizontal
        //double[][] matriz = {{1d,0,-1d},{2d,0,-2d},{1d,0,-1d}};

        //sobel vertical
        //double[][] matriz = {{1d,2d,1d},{0,0,0},{-1d,-2d,-1d}};

        class UserPool extends Thread {

            ThreadPool pool;

            public UserPool(ThreadPool pool) {
                this.pool = pool;
            }

            public void run() {
                pool.launch();
            }
        }
        class PoolStopper extends Thread {
            ThreadPool pool;

            public PoolStopper(ThreadPool pool) {
                this.pool = pool;
            }

            public void run() {
                try {
                    pool.stop();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        File image = new File("C:\\Users\\Usuario\\Desktop\\tp concurrente\\TP_Concurrente\\src\\prueba.jpg");
        BufferedImage bi = ImageIO.read(image);
        WritableRaster origen = bi.getRaster();

        int ancho = origen.getWidth();
        int alto = origen.getHeight();
        int canales = origen.getNumBands();

        WritableRaster destino = origen.createCompatibleWritableRaster(ancho, alto);
        destino.setPixels(0,0, ancho, alto, new double[ancho*alto*canales]);

        ThreadPool pool = new ThreadPool(8,8, origen, destino, matriz);

        UserPool user = new UserPool(pool);
        PoolStopper stopper = new PoolStopper(pool);

        user.start();
        stopper.start();

        BufferedImage bi_salida = new BufferedImage(bi.getColorModel(), pool.destino,bi.isAlphaPremultiplied(),null);
        File outputFile = new File("salida.jpg");
        ImageIO.write(bi_salida, "jpg", outputFile);
    }
}