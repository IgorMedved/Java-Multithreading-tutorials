package multithreading01_a_thread_class;

// This code shows how to run a thread by extending Thread class
public class App
{

	
	public static void main(String[] args)
	{
		Runner runner1 = new Runner();
		// make sure that start() method of Runner class not the run(), as otherwise the code will start in main thread!!!
		runner1.start();
		
		Runner runner2 = new Runner();
		runner2.start();

	}

}
