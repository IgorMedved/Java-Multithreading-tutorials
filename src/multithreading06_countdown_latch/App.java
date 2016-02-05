package multithreading06_countdown_latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// This code shows the use of countdown latches. Countdown latches allow for more flexible control of how the threads in thread
// pools are run. The code will not proceed until the countdown latch reaches 0; The flexible use of countdown latches
// can bring functionality similar to Thread.join()
public class App
{

	public static void main(String[] args)
	{
		CountDownLatch latch = new CountDownLatch(4);
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		for (int i= 0; i <4; i ++)
		{
			executor.submit(new Processor(latch));
		}
		
		try
		{
			latch.await(); //waits until the latch counted down to zero this is more flexible then executor.awaitTermination();
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
	private CountDownLatch latch;
	
	public Processor (CountDownLatch latch)
	{
		this.latch = latch;
	}
	
	public void run()
	{
		System.out.println("Started...");
		
		try
		{
			Thread.sleep(3000);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		latch.countDown();
		
		
	}
}
