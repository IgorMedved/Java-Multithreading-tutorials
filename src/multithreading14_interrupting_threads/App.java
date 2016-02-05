package multithreading14_interrupting_threads;

import java.util.Random;

public class App{

     public static void main(String []args) throws InterruptedException
     {
        System.out.println("Thread starting...");
        
        Thread t1 = new Thread (new Runnable()
        {
            public void run()
            {
                Random random = new Random();
                for (int i = 0; i < 1e8; i++)
                {
                    if(Thread.currentThread().isInterrupted())
                    {
                        System.out.println("Thread is executing for too long");
                        break;
                    }
                
                    Math.sin(random.nextDouble());
                }
            }
            
            
        });
        
        t1.start();
        Thread.sleep(1000);
        
        t1.interrupt(); // interrupt a thread if the backround thread is running for more than a second
        t1.join();
        System.out.println("Finished");
     }
}