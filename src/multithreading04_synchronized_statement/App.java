package multithreading04_synchronized_statement;

/* 
 * This code shows example of using synchronized statement. Unlike with synchronized methods where the lock is obtained for 
 * the object to which the method belongs to. Using synchronized statements allows the developer to specify the object to
 * lock. This makes code run faster and, as it allows separate synchronized blocks within one object being run by different 
 * threads if they use different locks.
 * Yet care must be taken that the code within different synchronized blocks is truly independent and do not access same variables
 * for read and write
 * 
 */
public class App
{

	
	public static void main(String[] args)
	{
		new Worker().main();

	}

}
