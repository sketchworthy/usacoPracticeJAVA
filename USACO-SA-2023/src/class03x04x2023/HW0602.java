package class03x04x2023;
/* TODO REVIEW
 * given 2 strs s and t, see if its possible to transform s into t by
 * using the following operation any amount of times: choose a substr of
 * s and sort it in ascending order
 * 
 * idea (motivated by class sol & leetcode solution as i had no idea how to 
 * do this on my own): Notice that being able to sort any substrs in ascending 
 * order lets us move a digit left until it hits a number smaller than itself, 
 * or right until it hits a number bigger than itself. Since only digits 0-9 
 * are possible, we can have an arraylist of arraydeques keeping track of 
 * the indices for each digit types, and process to see if the strs are
 * makeable that way
 * to compare and see if s2 is makeable from a list of queues representing s1,
 * notice that for first digit d in s2, if s1's queues earliest index of d
 * is at a smaller index than d's index, then 
 * 
 * difficulty: HARD HARD HARD. MB its bc i didn't have teacher to hold my hand,
 * but finding/understanding the idea was hard and implementing it required
 * a deeper understanding that was also hard to get
 */
import java.util.*;
import java.io.*;

public class HW0602 {
	public static void main(String[] args) throws Exception {
//		BufferedReader in = new BufferedReader(new FileReader("01.in"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		char[] s1 = in.readLine().toCharArray();
		char[] s2 = in.readLine().toCharArray();
		ArrayList<ArrayDeque<Integer>> idx = new ArrayList<>(10); // capacity 10
		for(int j=0;j<10;j++) { // list of queues of digit indices
			ArrayDeque<Integer>q = new ArrayDeque();
			idx.add(q);
		}
		for(int j=0;j<s1.length;j++) {
			idx.get(s1[j]-'0').add(j); // add index to the specific digit queue
		}
		
		boolean canMake=true; // true if s2 can be made from s1, else false
		// for each digit d in s2, see if
		Outer:
		for(char c:s2) {
			int d = c-'0';
			if(idx.get(d).isEmpty()) {
				canMake=false;
				break;
			}
			// check if there are smaller digits in front of d in s1
			//  ^ this works bc since we poll every d after each iteration,
			//    d is essentially the first digit, so we wanna see if we can
			//    move it to the front
			for(int i=0;i<d;i++) {
				if(!idx.get(i).isEmpty()&&idx.get(i).peek()<idx.get(d).peek()) {
					canMake=false;
					break Outer;
				}
			}
			idx.get(d).poll();
		}
		
		out.print(canMake ? 1 : 0);
		in.close();
		out.close();
	}
}
