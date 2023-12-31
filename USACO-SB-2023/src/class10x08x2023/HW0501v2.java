package class10x08x2023;
/* Subarray Sum to Target COMPLETE
 * Given int[] A and int T, find min length of any non-empty subarray of A w
 * sum >=T. if none exist return -1
 * 
 * idea: Essentially same as given a bunch of ints, find the
 * min diff in index so that those end ints differ by >=T
 * 
 * idea: if you notice that increasing/decreasing is happening monotonically,
 * queue/stack is the way to go. here, key is to realize that when looping
 * from left to right thru the ps array, if you get a ps value <= a prev 
 * ps value, you can discard the prev ps value b/c any new ps val u compare 
 * it against will automatically go to the smaller more recent 1 anyway.
 * then you only have to consider ps values that are strictly increasing.
 * in that case just have a deque.
 * 
 * difficulty: once i realized the increasing/decreasing after i 
 * realized that it's not 'absolute difference>=T' but specifically 
 * 'later minus earlier >= T', using queue fell into place
 * (in java always use deque)
 * 
 */
import java.util.*;
import java.io.*;

public class HW0501v2 {
	public static void main(String[] args) throws Exception {
//		System.out.println(33); .. debug
		BufferedReader in = new BufferedReader(new FileReader("10.in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		int[] A = new int[n];
		st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			A[j]= Integer.parseInt(st.nextToken());
		}
		long[] ps = new long[n+1];
		ps[0]=0;
		for(int j=0;j<n;j++) {
			ps[j+1]=ps[j]+A[j];
		}
		
		long mn=(long)1e14+1;
		ArrayDeque<Integer> dq = new ArrayDeque<>(); // deque of indices
		dq.add(0);
		
		for(int j=1;j<n+1;j++) {
			while(!dq.isEmpty()) {
				if(ps[j]>=ps[dq.peek()]+t) {
					mn=Math.min(mn, j-dq.poll());
				}
				else break;
			}
			while(!dq.isEmpty()) { // add to deque to be considered against future indices
				if(ps[j]<=ps[dq.getLast()]) {
					dq.removeLast();
				}
				else break;
			}
			dq.add(j);
		}
		
		out.println(mn==(long)1e14+1 ? -1 : mn);
		
		in.close();
		out.close();
	}
}
