import java.util.concurrent.*;
class NonTask
{
    public void hold() throws InterruptedException
    {
       TimeUnit.SECONDS.sleep(10);
    
    }   
}
class Task18 implements Runnable
{
    public void run()
    {
        try{
        NonTask n = new NonTask();
        n.hold();
        //TimeUnit.SECONDS.sleep(10);
        }catch(InterruptedException e)
        {
            System.out.println("Interrupted Exception --> " + e);
        }
        System.out.println("Exiting the thread");
    }
}
public class Excercise18
{

    public static void main(String [] args) throws Exception
    {
       ExecutorService exec = Executors.newCachedThreadPool();
       Future<?> future = exec.submit( new Task18());
       System.out.println("Interupting Thread");
       future.cancel(true); 
       System.out.println("Exiting main");
       exec.shutdown();
    }
}
