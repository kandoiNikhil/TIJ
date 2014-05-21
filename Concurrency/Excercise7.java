public class Excercise7
{
    public static void main(String [] args)
    {
        for(int i=0;i<5;i++)
        {
        Thread thread = new Thread( new Task7() );
        thread.setDaemon(true);
        thread.start();
        }
    }
}



