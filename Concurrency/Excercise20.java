import java.util.concurrent.*;
class LiftOff implements Runnable
{
    protected int countDown = 10;
    private static int taskCount =0 ;
    private final int id = taskCount++;
    public LiftOff(){}
    public LiftOff(int countDown)
    {
        this.countDown = countDown;
    }
    public String status()
    {
        return "#"+id+"("+(countDown>0?countDown:"Liftoff!!!")+")";
    }
    public void run()
    {
        while(countDown-- >0)
        {
            System.out.println(status());
            Thread.yield();
        }
    }
}
public class Excercise20
{
    public static void main(String [] args)
    {
       ExecutorService exec = Executors.newCachedThreadPool();
       ArrayList<Future<?>> ff = new ArrayList<Future<?>>();
       for(int i=0;i<5;i++)
       {
            Future<?> f = exec.submit(new LiftOff());
       }
      exec.shutdown();
    }
} 
