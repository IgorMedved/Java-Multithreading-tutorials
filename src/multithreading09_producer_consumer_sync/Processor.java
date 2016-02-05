package multithreading09_producer_consumer_sync;

import java.util.LinkedList;
import java.util.Random;

public class Processor
{
	private LinkedList<Integer> list1 = new LinkedList<>();
	private final static int LIMIT = 10;

	private Object lock = new Object();

	public void produce() throws InterruptedException
	{
		int value = 0;

		while (true)
		{
			synchronized (lock)
			{
				while (list1.size()== LIMIT)
				{
					lock.wait(); // lock.wait() stops current thread execution and releases the lock on  synchronized statement
				}
				list1.add(value++);
				lock.notify();// lock.notify() lets the other thread know that it can continue execution once it can obtain
							  // a lock
			}
		}

	}

	public void consume() throws InterruptedException
	{
		Random random = new Random();
		
		while (true)
		{
			synchronized (lock)
			{
				while(list1.size()== 0)
				{
					lock.wait();
				}
				
				System.out.print("List size is: " + list1.size());
				int value = list1.removeFirst();
				System.out.println("; value is " + value);
				lock.notify();
			}
			
			Thread.sleep(random.nextInt(2000));
		}
	}

}
