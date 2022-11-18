package ThreadPool;


import java.awt.image.WritableRaster;

import static ThreadPool.ThreadPool.destino;

public class Task implements Runnable{

    int x;
    int y;

    private WritableRaster origen;

    private double[][] matriz;

    public Task(int x, int y, WritableRaster origen, double[][] matriz) {
        this.x = x;
        this.y = y;
        this.origen = origen;
        this.matriz = matriz;

    }


    @Override
    public void run() {
        //generando rojo

        double a11Rojo = origen.getSample(x-1,y+1,0);
        double a12Rojo = origen.getSample(x-1,y,0);
        double a13Rojo = origen.getSample(x-1,y-1,0);
        double a21Rojo = origen.getSample(x,y+1,0);
        double a22Rojo = origen.getSample(x,y,0);
        double a23Rojo = origen.getSample(x,y-1,0);
        double a31Rojo = origen.getSample(x+1,y+1,0);
        double a32Rojo = origen.getSample(x+1,y,0);
        double a33Rojo = origen.getSample(x+1,y-1,0);

        double rojo = a11Rojo * matriz[0][0] + a12Rojo * matriz[0][1] + a13Rojo * matriz[0][2]
                    + a21Rojo * matriz[1][0] + a22Rojo * matriz[1][1] + a23Rojo * matriz[1][2]
                    + a31Rojo * matriz[2][0] + a32Rojo * matriz[2][1] + a33Rojo * matriz[2][2];


        if (rojo > 255){rojo = 255;}
        if (rojo < 0){rojo = 0;}
        destino.setSample(x,y,0, rojo);

        //genero  verde

        double a11Verde = origen.getSample(x-1,y+1,1);
        double a12Verde = origen.getSample(x-1,y,1);
        double a13Verde = origen.getSample(x-1,y-1,1);
        double a21Verde = origen.getSample(x,y+1,1);
        double a22Verde = origen.getSample(x,y,1);
        double a23Verde = origen.getSample(x,y-1,1);
        double a31Verde = origen.getSample(x+1,y+1,1);
        double a32Verde = origen.getSample(x+1,y,1);
        double a33Verde = origen.getSample(x+1,y-1,1);

        double verde = a11Verde * matriz[0][0] + a12Verde * matriz[0][1] + a13Verde * matriz[0][2]
                     + a21Verde * matriz[1][0] + a22Verde * matriz[1][1] + a23Verde * matriz[1][2]
                     + a31Verde * matriz[2][0] + a32Verde * matriz[2][1] + a33Verde * matriz[2][2];

        if (verde > 255){verde = 255;}
        if (verde < 0){verde = 0;}
        destino.setSample(x,y,1, verde);
        //genero azul


        double a11Azul = origen.getSample(x-1,y+1,2);
        double a12Azul = origen.getSample(x-1,y,2);
        double a13Azul = origen.getSample(x-1,y-1,2);
        double a21Azul = origen.getSample(x,y+1,2);
        double a22Azul = origen.getSample(x,y,2);
        double a23Azul = origen.getSample(x,y-1,2);
        double a31Azul = origen.getSample(x+1,y+1,2);
        double a32Azul = origen.getSample(x+1,y,2);
        double a33Azul = origen.getSample(x+1,y-1,2);

        double azul = a11Azul * matriz[0][0] + a12Azul * matriz[0][1] + a13Azul * matriz[0][2]
                    + a21Azul * matriz[1][0] + a22Azul * matriz[1][1] + a23Azul * matriz[1][2]
                    + a31Azul * matriz[2][0] + a32Azul * matriz[2][1] + a33Azul * matriz[2][2];
        if (azul > 255){azul = 255;}
        if (azul < 0){azul = 0;}
        destino.setSample(x,y,2,azul);

    }
}
