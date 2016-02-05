 package multithreading08_producer_consumer_notify;

import java.util.Scanner;

public class Processor 
{
	
	
	public void produce() throws InterruptedException
	{
		synchronized(this)
		{
			System.out.println("Producer thread running");
			wait(); // calling wait() stops thread execution until notify function is called on another thread, however wait() temporarily releases the lock
			System.out.println("Resumed.");
		}
		
		
	}
	
	public void consume() throws InterruptedException
	{
		Thread.sleep(2000);
		
		synchronized(this)
		{
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Press return key to continue...");
			scanner.nextLine();
			System.out.println("Return key pressed.");
			notify();
			Thread.sleep(5000);
			
		}
		
		
	}
}
