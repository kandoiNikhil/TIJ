public class Excercise2
{
    public static void main(String [] args)
    {
        Task2 t1 = new Task2(5);
        Task2 t2 = new Task2(7);
        Task2 t3 = new Task2(4);
        Task2 t4 = new Task2(10);
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        Thread thread3 = new Thread(t3);
        Thread thread4 = new Thread(t4);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        System.out.println("Main Thread Over");
    }
}

