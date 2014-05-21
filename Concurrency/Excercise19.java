import java.util.*;
import java.util.concurrent.*;
class Count
{
    private int count = 0;
    private Random rand = new Random(47);
    public synchronized int increment()
    {
        int temp = count;
        if(rand.nextBoolean())
            Thread.yield();
        return (count = ++temp);
    }
    public synchronized int value()
    {
        return count;
    }
}
class Enterance implements Runnable
{
    private static Count count = new Count();
    private static List<Enterance> enterances = new ArrayList<Enterance>();
    private int number = 0;
    private final int id;
    private static volatile boolean canceled = false;
    public static void cancel()
    {
        canceled = true;
    }
    public Enterance(int id)
    {
        this.id = id;
        enterances.add(this);
    }
    public void run()
    {
        boolean temp = true;
        while(temp){
            synchronized(this)
            {
                ++number;
            }
            System.out.println(this + " Total: " + count.increment() );
            try
            {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            catch(InterruptedException e)
            {
                System.out.println("Sleep interrupted");
                temp = !temp;
            }
        }
        System.out.println("Stopping " + this);
    }
public synchronized int getValue()
{
    return number;
}
public static int getTotalCount()
{
    return count.value();
}
public String toString()
{
    return "Enterance " + id + " : " + getValue();
}
public static int sumEnterances()
{
    int sum = 0;
    for(Enterance enterance:enterances)
        sum+=enterance.getValue();
    return sum;
}
}
public class Excercise19
{
    public static void main(String [] args) throws Exception
    {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i=0;i<5;i++)
        {
            exec.execute(new Enterance(i));
        }
        TimeUnit.SECONDS.sleep(1);
//        Enterance.cancel();
        
        exec.shutdownNow();
        if(! exec.awaitTermination(250, TimeUnit.MILLISECONDS))
            System.out.println("Some Tasks were not terminated");
        System.out.println("Total : " + Enterance.getTotalCount());
        System.out.println("Sum of Enterances: " + Enterance.sumEnterances());
    }
}


