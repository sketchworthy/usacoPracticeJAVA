package class03x04x2023;
/* Given an array of integers and an integer K, find the sum of the max eles of
 * each continuous subarray size K.
 * 
 * idea: use a queue of k ints and circle thru all n stuffs and return max sum
 * TODO figure out a cleaner way to do it w queue, i feel like im on the fence
 * between queue and arr and in this one it feels way ezier to use array w
 * no change at all
 * 
 */
import java.util.*;
import java.io.*;

public class HW0601 {
	static long MOD = (long) 1e9+7;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("10.in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] A = new int[n];
		st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++)A[j]=Integer.parseInt(st.nextToken());
		long maxSum=0; // sum of maximums
		long cMax=0;
		Deque<Integer> q = new ArrayDeque<>();
		for(int j=0;j<k;j++) {
			q.add(A[j]);
			cMax=Math.max(cMax, A[j]);
		}
		maxSum=cMax;
		for(int j=k;j<n;j++) { // last index
			if(cMax==q.poll()) {
				// find new cMax
				cMax=A[j-k+1];
				for(int x=j-k+2;x<=j;x++) {
					cMax=Math.max(cMax, A[x]);
				}
			};
			q.add(A[j]);
			cMax=Math.max(A[j], cMax);
			maxSum+=cMax;
		}
		out.print(maxSum%MOD);
		
		out.close();
	}
}
