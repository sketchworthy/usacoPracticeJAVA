package class03x04x2023;
/* N ppl 1,2,...N standing in a circle. ur given an array M w n-1 #s.
 * starting from pos 1, excluding pos 1, the M1th person is removed 
 * from the circle. whats the last person still in the circle?
 * 
 * key idea: queues can be used kinda like circles. u can take something from
 * the front and add it to the back
 * 
 * sol: use a queue to circle thru all the ppl, remove the ppl actually
 * removed. basically, simulate w queue
 */
import java.util.*;
import java.io.*;

public class Ex0601 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine()); // # of ppl
		Queue<Integer> ppl = new ArrayDeque<Integer>();
		for(int j=1;j<=n;j++) {
			ppl.add(j);
		}
		StringTokenizer st = new StringTokenizer(in.readLine());
		int[] M = new int[n-1]; // M[x] is removing from circle
		for(int j=0;j<n-1;j++) {
			M[j]=Integer.parseInt(st.nextToken());
		}
		ppl.add(ppl.remove()); // remove the 1st 1 as its a special case
		for(int x=0;x<n-1;x++) { // for each M[x], run thru that many ppl
			M[x]=M[x]%(ppl.size()-1);
			// remove M[x] ppl from front and add to back
			for(int j=0;j<M[x]-1;j++) ppl.add(ppl.poll());
			// actually remove the person at the front
			ppl.remove();
		}
		out.print(ppl.peek());
		
		out.close();
	}
}
