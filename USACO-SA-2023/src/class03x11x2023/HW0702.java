package class03x11x2023;

/* USACO 2017 Jan Silver Problem 1 Cow Dance Show
 * Given n cows, each of the times they must dance on the stage, and max
 * possible time tmax, find the min size k of the stage (min # of cows that
 * can be on the stage at once)
 * 
 * idea: iterate thru possible values of k from 1 to n, run a priority queue
 * to simulate cows getting up on and getting off of the stage. To make the
 * priority queue accurately return the cow getting off next, add the current
 * starting time to any cow getting added into the pqueue, so that the
 * priority queue is sorting by cows' finishing times rather than their length
 * of individual performance
 * 
 * difficulty: once i understood key ideas outlined by class teacher, implementation
 * was ok
 */
import java.util.*;
import java.io.*;

public class HW0702 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cowdance.in"));
		PrintWriter out = new PrintWriter(new FileWriter("cowdance.out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of cows
		int tmax = Integer.parseInt(st.nextToken()); // max time show can take
		int[] cows = new int[n]; // cow times
		for(int j=0;j<n;j++) {
			cows[j]=Integer.parseInt(in.readLine());
		}
		
		int k=1;
		PriorityQueue<Integer> stage = new PriorityQueue<>();
		while(k<n) {
			// see if the dance performance will take <tmax time
			// push k cows onto stage
			for(int j=0;j<k;j++) {
				stage.add(cows[j]);
			}
			int nextCow=k;
			int ct=0; // current time
			while(nextCow<n) { // when the first cow onstage is finished dancing
				ct=stage.poll(); // cow time
				stage.add(cows[nextCow]+ct);
				nextCow++;
			}
			while(!stage.isEmpty())ct=stage.poll();
			if(ct<=tmax) {
				out.print(k);
				break;
			}
			
			k++;
		}
		in.close();
		out.close();
	}
	
}
/*
 * 6 134
34
75
25
83
3
6*/
