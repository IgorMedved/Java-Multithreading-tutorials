package multithreading13_futures_callable_interface;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Callable;
import java.util.Random;
import java.io.IOException;


// This code shows how to use Callable interface instead of Runner interface for passing to Thread constructor
// Callable interface is very similar to Runnable interface except that Callable is parametrized to return a value
// Futures can get the result from asynchronous computation. It can also cancel the thread work if necessary
public class App
{

     public static void main(String []args)
     {
        ExecutorService executor = Executors.newCachedThreadPool();
        
        // future will contain result of the call() execution after the work is completed
        Future <Integer> future = executor.submit(new Callable<Integer>(){
            
            @Override
            public Integer call() throws Exception
            {
                Random random = new Random();
                
                int duration = random.nextInt(4000);
                
                if (duration > 2000)
                {
                    throw new IOException ("Sleeping for too long.");
                }
                
                System.out.println("Starting ...");
                
                try
                {
                    Thread.sleep(duration);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                
                System.out.println("Finished");
                
                return duration;
            }
            
        });
        
        executor.shutdown();
        
        try
        {
            System.out.println ("The execution time: " + future.get());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
     }        
        
}