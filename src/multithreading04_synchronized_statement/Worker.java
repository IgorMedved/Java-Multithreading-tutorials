package multithreading04_synchronized_statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker
{
	private Random random = new Random();
	private Object lock1 = new Object(); // Any object can be used for locking code execution to one thread. Object class is the simplest
	private Object lock2 = new Object(); // second lock
	

	private List<Integer> list1 = new ArrayList<>();
	private List<Integer> list2 = new ArrayList<>();

	public void stageOne()
	{
		// this code adds values to list 1
		// adding values to list 1 is completely independent from adding values to list2 in stageTwo() and thus it is safe
		// to run stageOne() and stageTwo() on two different threads simultaneously
		// using two different locks ensures that the code runs faster while still preventing the following synchronized
		// statement from being run by two or more different threads simultaneously
		synchronized (lock1)
		{
			try
			{
				Thread.sleep(1);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list1.add(random.nextInt(100));
		}
	}

	public void stageTwo()
	{
		// this code adds values to list2
		// lock2 prevents the program from running the following synchronized statement on 2 or more different
		// threads simultaneously
		synchronized (lock2)
		{

			try
			{
				Thread.sleep(1);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list2.add(random.nextInt(100));
		}
	}

	public void process()
	{
		for (int i = 0; i < 1000; i++)
		{
			stageOne(); 
			stageTwo();
		}
	}

	public void main()
	{
		System.out.println("Starting ...");

		long start = System.currentTimeMillis();

		Thread t1 = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				process();
			}
		});

		t1.start();

		Thread t2 = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				process();
			}
		});

		t2.start();

		try
		{
			t1.join();
			t2.join();
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		
		// The use of synchronized statements instead of synchronized methods ensures that the time it takes to run
		// the program is cut almost in half, because it takes advantage that adding values to list1 is independent of 
		// adding values to list2. It still protects two threads from adding values to list1 at the same time.
		
		System.out.println("Time taken: " + (end - start));
		System.out.println("List1 size " + list1.size() + "; list2 size "
				+ list2.size());
	}

}
