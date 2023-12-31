/*
 * 
 */
import java.util.*;
import java.io.*;

public class WordProcess {
	public static void main(String[] args) throws Exception {
		PrintWriter out = new PrintWriter(new File("word.out"));
		Scanner in = new Scanner(new File("word.in"));
		int n = in.nextInt(); // # of words
		int k = in.nextInt(); // max # of chars in a line
		
		int charsLeft=k; // characters left in current line
		while(in.hasNext()) { // for each word
			String str = in.next();
			int len = str.length();
			// if charsLeft==k (aka 1st word in line) just add the word
			if(charsLeft==k) {
				out.print(str);
				charsLeft-=len;
//				System.out.println("1");
			}
			// else if can fit the str len, then subtract len
			//   from charsLeft
			else if(charsLeft>=len) {
				charsLeft-=(len);
				out.print(" "+str);
//				System.out.println(2);
			}
			// otherwise create a new line, add the word, and set charsLeft
			//   to k-len
			else {
//				System.out.println(charsLeft+" "+len);
				out.println();
				out.print(str);
				charsLeft=k-len;
//				System.out.println(3);
			}
		}
		out.close();
		in.close();
	}
}
