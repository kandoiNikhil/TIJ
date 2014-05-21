import java.util.*;
import java.util.concurrent.*;
public class Task6 implements Runnable
{
    public void run()
    {
        int rand = (int) (Math.random()*10 );
        try{
        TimeUnit.MILLISECONDS.sleep(rand*1000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("Thread exiting after " + rand +" seconds.");
    }
}

