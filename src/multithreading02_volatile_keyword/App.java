package multithreading02_volatile_keyword;
import java.util.Scanner;


/* The basic problem with parallel thread execution is coordination of memory access for reads and writes
 * Volatile keyword ensures that after one thread modifies a value of volatile variable all other threads see an updated value
 * during read operations
 * It is safe to use volatile only when the present state of the variable is independent of its past states. And when it is not
 * the correct program logic execution is not guaranteed. Some examples of unsafe operations.
 * volatile int a;
 * a++, a= a+5; // here the value of a on the left is dependent on value of a on the right.
 * The following code is also unsafe as variable a depends on its own value through c
 * volitile int a, c;
 * 
 * a= c+3;
 * c= 3*a;
 * 
 * Volatile can be safe for using with variables that change only once (shutdown flag)
 * or for variables whose state is independent from their former state i.e(reading a temperature sensor)
 * However a lot of care should be applied!
 */
public class App
{

	
	public static void main(String[] args)
	{
		Processor proc1 = new Processor();
        proc1.start();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Press enter to stop ...");
        
        sc.nextLine();
        proc1.shutdown();// change value of shutdown flag in proc 1 and exit the while loop dependent on it
      
		     
		         
		        
	}
}

 








