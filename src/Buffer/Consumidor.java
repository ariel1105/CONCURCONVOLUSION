package Buffer;

public class Consumidor extends Thread{

    private final Buffer buffer;

    public Consumidor(Buffer buffer){
        this.buffer = buffer;
    }

    public void run(){
        while (true){
            Object o = buffer.read();
            System.out.println("Leido " + o.toString());
        }
    }
}
