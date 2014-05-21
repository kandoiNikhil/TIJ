import java.util.concurrent.*;
public class Excercise6
{
    public static void main(String [] args)
    {
    // Just taking the input as 5
    ExecutorService exec = Executors.newFixedThreadPool(5);
    for(int i=0;i<5;i++)
    {
        exec.execute( new Task6() );
    }
    exec.shutdown();
    }
}
