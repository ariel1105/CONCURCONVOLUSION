package Buffer;

public class Productor extends Thread{

    private final Buffer buffer;

    public Productor(Buffer buffer){
        this.buffer = buffer;
    }

    /*public void run(){
        while(true){
            buffer.write(i);
        }
    }*/
}
