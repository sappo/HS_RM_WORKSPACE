import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.io.Console;

/**
 * Simple Hello world class.
 */
public class Hello {
	/**
	 *	Asks for the users name and prints a greeting.
	 * 	@param args all arguments are ignored
	 */
	public static void main(String[] args) throws Exception{
		Console console = System.console();
        
		if (console == null) {
            System.out.println("Couldn't get Console object!");
        } else {
			//Prints umlauts characters correctly			
			console.printf("Wie heiﬂt du?\t");
			
			//Reads user input from console
			String name = console.readLine();
            
            console.printf("Hallo, " + name + "!\n");
		}
	}
}