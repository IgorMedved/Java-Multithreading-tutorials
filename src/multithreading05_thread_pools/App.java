package multithreading05_thread_pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


// This code shows the usage of thread pools
// The advantage of thread pool over regular thread is that the threads can be reused reducing the overhead of creating a 
// new thread from scratch.
// The disadvantage of threads from thread pools is less control over the execution of individual threads in thread pool
// The individual threads cannot be interrupted individually or joined only controlled as a whole through shutdownNow() function
public class App
{

	
	public static void main(String[] args)
	{
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		for (int i = 0; i < 10; i++)
		{
			executor.submit(new Processor (i));
		}
		
		executor.shutdown();
		
		try
		{
			executor.awaitTermination(5, TimeUnit.MINUTES); // there is no join function for individual threads
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("All tasks completed!");
	}

}

class Processor implements Runnable
{
	private int id;
	
	public Processor(int id)
	{
		this.id = id;
	}
	
	@Override
	public void run()
	{
		System.out.println("Starting task: " + id);
		
		try
		{
			Thread.sleep(5000);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed task: " + id);
		
	}
}
