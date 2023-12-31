package class03x04x2023;
/* METHOD USING QUEUES: Given an array of integers and an integer K, 
 * find the sum of the max eles of each continuous subarray size K.
 * 
 * idea: use a monotonically decreasing queue. or at least, a queue of
 * indices whose A[i]s are monotonically decreasing. We know we want
 * it to decrease because we're looking at how the MAX sum changes,
 * so when the largest value is slashed off, we can just look at the
 * slightly smaller but new largest value. in other words, the queue
 * always saves the largest few values. in that way, the 'new' value
 * is ALWAYS added to the queue somewhere, saved w the largest vals.
 * for each index i in A[], put i into the queue tail. do this by
 * removing the last few values whose A[] are smaller than A[i].
 * then, iterate thru A indices, while checking if the
 * check the head of the queue and see if
 * they are lagging more than K behind and need to be removed. 
 * 
 * 
 * also, test on a decreasing series like 104 24 12 8 6 4 1 to 
 * see what happens if queue head removed before you can
 * add to queue tail
 * 
 */
import java.util.*;
import java.io.*;

public class HW0601v2 {
	static long MOD = (long) 1e9+7;
	public static void main(String[] args) throws Exception {
//		BufferedReader in = new BufferedReader(new FileReader("10.in"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] A = new int[n];
		st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++)A[j]=Integer.parseInt(st.nextToken());
		long maxSum=0; // sum of maximums
		Deque<Integer> q = new ArrayDeque<>(); // monotonically decreasing
		q.add(0);
		int ci=1; // current index in A, the 'last' index that is being added
		while(ci<n) {
			// check if should remove head from queue
			if(q.peek()<=ci-k)q.poll();
						
			// check where to add ci to queue tail
			while(true) {
				if(!q.isEmpty()&&A[ci]>A[q.getLast()]) {
					q.removeLast();
				}
				else {
					q.add(ci);
					break;
				}
			}
			// add max ele to maxSum
			if(ci>=k-1) {
				maxSum+=A[q.peek()];
				maxSum%=MOD;
			}
			ci++;
		}
		
		
		out.print(maxSum%MOD);
		
		out.close();
	}
}
