import ThreadPool.ThreadPool;
import ThreadPool.Filtro;
import ThreadPool.UserPool;
import ThreadPool.PoolStopper;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String [] args) throws IOException {

        System.out.println ("Empezamos el programa");

        System.out.println ("Por favor introduzca el tamaño del Buffer:");

        String tamañoBuffer = "";

        Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner

        tamañoBuffer = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner

        System.out.println ("El Tamaño del Buffer es: \"" + tamañoBuffer +"\"");

        System.out.println ("Por favor introduzca la cantidad de Threads a iniciar:");

        String cantidadThreads = "";

        cantidadThreads = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner

        System.out.println ("La cantidad de Threads es: \"" + cantidadThreads +"\"");

        System.out.println ("Por favor introduzca el filtro que desea aplicar:");

        String filtro = "";

        filtro = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner

        System.out.println ("El filtro seleccionado es: \"" + filtro +"\"");


        File image = new File("C:\\Users\\Administrator\\Desktop\\Facu\\TP_Concurrente\\src\\prueba.jpg");
        BufferedImage bi = ImageIO.read(image);
        WritableRaster origen = bi.getRaster();

        int ancho = origen.getWidth();
        int alto = origen.getHeight();
        int canales = origen.getNumBands();


        WritableRaster destino = origen.createCompatibleWritableRaster(ancho, alto);
        destino.setPixels(0,0, ancho, alto, new double[ancho*alto*canales]);

        BufferedImage bi_salida = new BufferedImage(bi.getColorModel(), destino,bi.isAlphaPremultiplied(),null);
        File outputFile = new File("salida.jpg");

        ThreadPool pool = new ThreadPool(Integer.parseInt(tamañoBuffer),Integer.parseInt(cantidadThreads), origen, destino, Filtro.getByName(filtro));
        UserPool user = new UserPool(pool);
        PoolStopper stopper = new PoolStopper(pool , bi_salida , outputFile);

        user.start();
        stopper.start();


    }
}