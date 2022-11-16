package ThreadPool;

public class DummyTask implements Runnable{

    public DummyTask(){

    }

    @Override
    public void run() {
        System.out.println("Tarea Completada");
    }
}
