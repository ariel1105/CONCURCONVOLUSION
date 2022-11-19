package ThreadPool;

public class UserPool extends Thread {
    static final long contadorInicial = System.currentTimeMillis();
    ThreadPool pool;

    public UserPool(ThreadPool pool) {
        this.pool = pool;
    }

    public void run() {
        this.pool.launch();
    }
}
