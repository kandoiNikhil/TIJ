import java.util.concurrent.*;
class A implements Runnable {
    private volatile boolean flag = false;
   public synchronized void run() {
        System.out.println("Task going into wait");
            try {
                flag = true;
                wait();
                System.out.println("Done Waiting");
            }
            catch(InterruptedException e) {
                System.out.println("Task interrupted");
            }
       }
}
class B implements Runnable {
    A a;
    B(A a1) {
         a = a1;
    }
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        }
        catch(InterruptedException e) {
            System.out.println("Thread Interrupted during sleep");
        }
        System.out.println("Notifying All");
        synchronized(a)
        {
          a.notifyAll();
        }
    }
}
public class Excercise21 {
    public static void main(String [] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        A a = new A();
        B b = new B(a);
        exec.execute(a);
        exec.execute(b);
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();

    }
}
