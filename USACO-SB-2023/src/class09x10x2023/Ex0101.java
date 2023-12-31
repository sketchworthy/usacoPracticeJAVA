package class09x10x2023;
/* Predict Winner. COMPLETE BUT CAN BE IMPROVED BY DP
 * Given an array A of n ints, 2 players take turns removing 1 int from the
 * left or right end. They are trying to have the most total sum at the
 * end. Given both players playing optimally, return who wins.
 * 
 * idea: simulate both players playing the game optimally with recursion
 * (possible bc n is only at max 20). Have a recursive function that
 * has parameters indexing the 'considered reason' start and end points.
 * returns the max diff of the current player and the opponents score
 * and model it so that it 'plays optimally' with itself
 * 
 * difficulty: idea was talked thru in class so im not sure abt that.
 * but implementation was actually rlly ez, tho we also talked about
 * some implementation details too so idk
 */
import java.util.*;
import java.io.*;

public class Ex0101 {
	static long[] A;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("10.in"));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(in.readLine()); // # of ints in array
		A = new long[n];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			A[j]=Long.parseLong(st.nextToken());
		}
		long pointDiff=yourTurn(0,n-1);
		if(pointDiff>=0)out.print(true);
		else out.print(false);
		in.close();
		out.close();
	}
	static long yourTurn(long j, long k) { // j=start index, k=end index
		// all the outer indices have been consumed already
		// given the remaining #s calculate the max score you can generate
		if(j==k)return A[(int)j];
		// return the max of the points u get if you choose A[j] 
		//  or if you choose A[k]
		long jDiff=A[(int)j]-yourTurn(j+1,k); // the points u get
		long kDiff=A[(int)k]-yourTurn(j,k-1); // the other possible # of points u get
		return Math.max(jDiff,kDiff);
	}
	
}
