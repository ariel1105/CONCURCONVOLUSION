import ThreadPool.ThreadPool;
public class Main {
    public static void main(String [] args) {
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


        ThreadPool pool = new ThreadPool(5, 4, 100);
        UserPool user = new UserPool(pool);
        PoolStopper stopper = new PoolStopper(pool);
        user.start();
        stopper.start();
    }
}