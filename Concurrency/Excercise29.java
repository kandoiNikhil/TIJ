import java.util.concurrent.*;
enum Status { DRY , BUTTERED , JELLIED }
class BreadPiece {
    private Status status = Status.DRY;
    private final int id;
    public BreadPiece(int id) {
        this.id = id ;
    }
    public void butter() {
        this.status = Status.BUTTERED;
    }
    public void jelly() {
        this.status = Status.JELLIED;
    }
    public Status getStatus() {
        return this.status;
    }
    public String toString() {
        return "Bread Piece "+ id + " " + this.status;
    }
}
class Sandwich {
    private BreadPiece bread1,bread2;
    Sandwich(BreadPiece bread1, BreadPiece bread2) {
        this.bread1 = bread1 ;
        this.bread2 = bread2 ;
    }
        public String toString() {
            return "Sandwich -> " + bread1 + bread2;
    }

    public boolean isPerfect() {
       if ( this.bread1.getStatus() == Status.BUTTERED && this.bread2.getStatus() == Status.JELLIED )
            return true;
       if ( this.bread2.getStatus() == Status.BUTTERED && this.bread1.getStatus() == Status.JELLIED )
            return true;
       return false;
    }
}
class SandwichQueue extends LinkedBlockingQueue<Sandwich> {
}

class BreadQueue extends LinkedBlockingQueue<BreadPiece> {
}
class BreadProducer implements Runnable {
    private BreadQueue dryQueue;
    private int count= 0;
    BreadProducer(BreadQueue breadQueue) {
        this.dryQueue = breadQueue;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                BreadPiece bread = new BreadPiece(count++);
                dryQueue.put(bread);
                TimeUnit.SECONDS.sleep(1);
            }
        }
        catch(InterruptedException e) {
            System.out.println("Producer interrupted ");
        }
        System.out.println("Producer off");
    }
}
class Butterer implements Runnable {
    private BreadQueue dryQueue , butteredQueue ;
    public Butterer(BreadQueue dry,BreadQueue buttered) {
        dryQueue = dry ;
        butteredQueue = buttered ;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                BreadPiece bread = dryQueue.take();
                TimeUnit.SECONDS.sleep(2);
                bread.butter();
                System.out.println(bread);
                butteredQueue.put(bread);
            }
        }
        catch(InterruptedException e ) {
            System.out.println(" Butterer interrupted");
        }
        System.out.println("Butterer off");
    }
}
class Jeller implements Runnable {
    private BreadQueue dryQueue , jelliedQueue ;
    public Jeller(BreadQueue dry,BreadQueue jellied) {
        dryQueue = dry;
        jelliedQueue = jellied ;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                BreadPiece bread = dryQueue.take();
                TimeUnit.SECONDS.sleep(3);
                bread.jelly();
                System.out.println(bread);
                jelliedQueue.put(bread);
            }
        }
        catch(InterruptedException e) {
            System.out.println(" Jeller Interrupted" );
        }
        System.out.println(" Jeller Off " );
    }
}
class Assembler implements Runnable {
    private BreadQueue butteredQueue,jelliedQueue;
    private SandwichQueue assembledQueue;

    Assembler(BreadQueue buttered ,BreadQueue jellied ,SandwichQueue assembled){
        butteredQueue = buttered;
        jelliedQueue = jellied;
        assembledQueue = assembled ;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
               BreadPiece bread1 = butteredQueue.take();
               BreadPiece bread2 = jelliedQueue.take();
               Sandwich sandwich = new Sandwich(bread1,bread2);
               TimeUnit.SECONDS.sleep(1);
               System.out.println(sandwich);
               assembledQueue.put(sandwich);
            }
        }
        catch(InterruptedException e) {
            System.out.println(" Assembler Interrupted ");
        }
        System.out.println(" Assemler off ");
    }
}
class Eater implements Runnable {
    private SandwichQueue assembledQueue;
    Eater(SandwichQueue assembledQueue) {
        this.assembledQueue = assembledQueue;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                Sandwich sandwich = assembledQueue.take();
                if(sandwich.isPerfect())
                    System.out.println(" oh.. perfecto ");
                else
                    System.out.println(" implerefect sandwich " ) ;
            }
        }
        catch(InterruptedException e) {
            System.out.println("Eater interrupted" );
        }
    }
}
public class Excercise29 {
    public static void main(String [] args) throws Exception {
        BreadQueue dryQueue,butteredQueue,jelliedQueue;
        SandwichQueue assembledQueue;
        dryQueue = new BreadQueue();
        butteredQueue = new BreadQueue();
        jelliedQueue = new BreadQueue();
        assembledQueue = new SandwichQueue();
        BreadProducer producer = new BreadProducer(dryQueue);
        Butterer butterer = new Butterer(dryQueue , butteredQueue);
        Jeller jeller = new Jeller(dryQueue, jelliedQueue);
        Assembler assembler = new Assembler(butteredQueue , jelliedQueue , assembledQueue);
        Eater eater = new Eater(assembledQueue);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(producer);
        exec.execute(butterer);
        exec.execute(jeller);
        exec.execute(assembler);
        exec.execute(eater);  
        TimeUnit.SECONDS.sleep(30);
        exec.shutdownNow(); 
    }
}
