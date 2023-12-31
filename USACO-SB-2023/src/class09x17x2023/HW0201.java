package class09x17x2023;
/* Max # of Events COMPLETE
 * Given a bunch of intervals' startpoints and corresponding endpoints,
 * figure out the max # of intervals you can get if one point can only
 * get 1 interval.
 * 
 * idea: originally i thought to do this with prefix diff arrays, however
 * that is unideal bc if you do the diff array of how many events happening
 * on each day, you can't tell how many of what stuff is on which day (ie u lose
 * information). instead, just frickin sort. then you can sort first by day
 * the event starts, then by day it ends, and simulate. to simulate, note
 * that if you have sorted it in this way, you can then go by which event ur on.
 * make sure each free event tries to take the earliest day possible to the left.
 * then, u just have to check that the earliest day after the prev event is
 * available for the next sorted event
 * 
 * difficulty: v ez bc i did mountains first. basically just know to sort in
 * the v specific way, then simulate.
 * 
 */
import java.util.*;
import java.io.*;

public class HW0201 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("10.in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());
		int[][] events = new int[n][2];
		for(int j=0;j<n;j++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			events[j][0]=Integer.parseInt(st.nextToken());
			events[j][1]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(events,(a,b)-> a[0]!=b[0] ? a[0]-b[0] : a[1]-b[1]); 
		// sort first by day it begins, then the day it ends
		
		int total=1; // max # of events attendable
		int cLeft=events[0][0]+1; // current leftmost free day
		for(int j=1;j<n;j++) { // iterate thru events
			if(cLeft<=events[j][1]) { // cLeft within reach of event
				total++; // this new event can take the cLeft slot
				cLeft=events[j][0]+1;
			}
		}
		out.println(total);

		in.close();
		out.close();
	}
}
