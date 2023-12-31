package class10x01x2023;
/* Meeting Place v2 COMPLETE
 * Given several points on a # line, return the min max-time
 * a given location is from the given points. each point has a
 * varying speed
 * 
 * idea: inverse binary search for the time the cows take. 
 * to check if a time t is sufficient for the cows to meet 
 * at 1 point, find the overlapping interval where all cows can meet
 * given the endpoints of each indiv cow's reach for a time t.
 * in order to make this time t accurate up to a 10^-6 degree
 * of accuracy, multiply all the location parameters by
 * 10^6 and divide the time by 10^6 at the end.
 * 
 * a key thing to note is that if you are given a bunch of
 * intervals and need to check if several points are in them all,
 * you don't have to check every interval, you can just 
 * take the min of the intervals endpoints and the max of the
 * intervals startpoints to get the mini-interval of where the
 * intervals all intersect. then any point in there is in all
 * the intervals and any other point isn't! :0 AMAZING!
 * 
 * difficulty in implementation (i was already given the idea):
 *  debug notes: BE CAREFUL ABOUT YOUR INVERSE BINARY SEARCH
 *  ENDPOINTS!!! I accidentally put the startpoint too high and it
 *  kept giving me the wrong values bc my initial range was incorrect
 *  :/
 */
import java.util.*;
import java.io.*;

public class HW0401v2 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("03.in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine()); // # of frens
		long[][] A = new long[n][2]; // loc, then speed
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			A[j][0]=Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			A[j][1]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A,(a,b)->(int)(a[0]-b[0]));
		
		long low = 0; // has to be 0 bc time, cant be A[j][0] bc that's loc
		long high = (long)1e18; // t measured in nanoseconds
		while(low<high) {
			long mid=(low+high)/2; // check if mid time is enough to get 
			// [an interval of] point[s] that all frens can reach
			double minEnd=A[0][0]+(mid+0.0)/1e9*A[0][1]; // minimum ending point of an interval
			double maxStart=A[0][0]-(mid+0.0)/1e9*A[0][1]; // max starting point of an interval
			
			for(int j=1;j<n;j++) { // for fren from 0 to n
				minEnd=Math.min(minEnd, A[j][0]+(mid+0.0)/1e9*A[j][1]);
				maxStart=Math.max(maxStart, A[j][0]-(mid+0.0)/1e9*A[j][1]);
			}
			
			if(minEnd>=maxStart) { // if there does exist point(s) all cows can reach
				high=mid;
			}
			else { // mid is too low
				low = mid+1;
			}
		}
		out.print(high/1e9);
				
		in.close();
		out.close();
	}
}
