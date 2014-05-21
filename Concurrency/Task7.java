import java.util.*;
import java.util.concurrent.*;
public class Task7 implements Runnable
{
    public Task7()
    {
        System.out.println("Daemon Thread starts");
    }

    public void run()
    {
        int rand  = (int) (Math.random()*10);
       try{
          TimeUnit.MILLISECONDS.sleep(rand*1000);
         System.out.println("This thread '" + Thread.currentThread() +"'  ran for "+rand+" seconds");
       }
      catch(Exception e)
      {
         e.printStackTrace();
      }
    }
}

