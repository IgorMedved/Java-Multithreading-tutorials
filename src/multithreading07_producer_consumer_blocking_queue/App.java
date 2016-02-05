package multithreading07_producer_consumer_blocking_queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// This code shows the use of ArrayBlockingQueue.
// and producer consumer pattern. The produce produces the product on one thread while the consumer consumes it when available
// The limited size of blocking queue simulates the storing constraints the produce faces. Only 10 values can be stored in this 
// example
public class App
{

	
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
	
	
	public static void main(String[] args) throws InterruptedException
	{
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run()
			{
				try
				{
					producer();
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run()
			{
				try
				{
					consumer();
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}
	
	private static void producer () throws InterruptedException
	{
		Random random = new Random();
		
		while (true)
		{
			
			queue.put(random.nextInt(100)); // if the  queue is full the thread will automatically wait for the time when there is
											// some space, and the program will not crash 
			Thread.sleep(1000);
		}
	}
	
	private static void consumer() throws InterruptedException
	{
		Random random = new Random();
		
		while (true)
		{
			Thread.sleep(100);
			
			if(random.nextInt(10)==0)
			{
				Integer value = queue.take(); // if there is no values to take from queue the tread will wait until some values
											  // are added
				
				System.out.println("Value taken " + value + "; queue size " + queue.size());
			}
		}
	}

}
