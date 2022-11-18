package Buffer;

public class Buffer {

    private Runnable[] data;
    private int n;
    private int begin = 0;
    private int end = 0;

    public Buffer(int tamano){
        this.n = tamano;
        this.data = new Runnable[this.n + 1];
    }

    public synchronized void write(Runnable o){
        while(isFull()){
            try {
                wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        data[begin] = o;
        begin = next(begin);
        notifyAll();
    }

    public synchronized Runnable read() {
        while(isEmpty()){
            try{
                wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        Runnable result = data[end];
        end = next(end);
        notifyAll();
        return  result;
    }

    public boolean isEmpty() {return begin == end;}

    private boolean isFull() {return next(begin)==end;}

    private int next(int i){return (i+1)%(this.n+1);}

}
