// Read a text file and add the line number at the beginning of each line, including empty lines.

import java.util.*;
import java.io.*;

public class Ex1009 {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("09.in"));
		PrintWriter out = new PrintWriter(new File("09.out"));
		
		int count = 1; // initialize
		while(in.hasNext()) {
			String line = in.nextLine();
			out.println(count+": "+line);
			count++;
		}
		
		in.close();
		out.close();
	}

}
