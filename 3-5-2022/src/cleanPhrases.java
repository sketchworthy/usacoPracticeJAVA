/*
 * clean the phrases of enters
 */
import java.util.*;
import java.io.*;
public class cleanPhrases {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("phrases1.txt"));
		PrintWriter out = new PrintWriter(new File("cleanPhrases"));
		while (in.hasNext()) {
			String line = in.nextLine().trim(); // trim() gets rid of whitespace
			if(!line.isEmpty()&&line.length()>3) {
				out.println(line);
			}
		}
		out.close();
		in.close();
	}

}
