import java.util.concurrent.*;
public class Excercise4
{
    public static void main(String [] args)
    {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for(int i=0;i<5;i++)
        {
            exec.execute(new Task2());
        }
        exec.shutdown();
        exec = Executors.newCachedThreadPool();
         for(int i=0;i<5;i++)
        {
            exec.execute(new Task2());
        }
        exec.shutdown();
    }
} 

