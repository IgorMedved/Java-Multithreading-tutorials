package multithreading12_semaphores;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App{

     public static void main(String []args) throws InterruptedException
     {
        /*Semaphore semaphore = new Semaphore(2);
        
        semaphore.acquire();
        
        
        System.out.println("Then number of available permists is: " + semaphore.availablePermits());*/
        Connection.getInstance().connect();
        
        ExecutorService executor = Executors.newCachedThreadPool();
        
        for (int i = 0; i < 200; i++)
        {
            executor.submit(new Runnable(){
                @Override
                public void run()
                {
                    Connection.getInstance().connect();
                    
                }
            });
        }
        
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
     }
}
