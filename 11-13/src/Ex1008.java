/*
 * Read a text file and report the number of lines in the file. Ignore the empty lines.
 */
import java.util.*;
import java.io.*;
public class Ex1008 {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("foo.txt"));
		
		// count # of non-empty lines
		int count = 0; // initialize
		while(in.hasNext()) {
			String line = in.nextLine();
			if(!line.isEmpty()) {
				count++;
			}
			
		}
		System.out.println(count);
		in.close();
	}

}
