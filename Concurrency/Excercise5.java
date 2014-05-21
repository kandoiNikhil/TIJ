import java.util.concurrent.*;
import java.util.*;
public class Excercise5
{
    public static void main(String [] args)
    {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();
        for(int i=1;i<6;i++)
        {
            results.add(exec.submit( new Task5(5)));
        }
        for(Future<Integer> fs : results)
        {
            try
            {
                if(fs.isDone())
                {
                    System.out.println(fs.get());
                }
                else
                {
                    continue;
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                exec.shutdown();
            }
        }
    }
}

            

