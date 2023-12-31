/* USACO Silver 2023 Problem 1
 * 
 * idea: fj first unhooks the cow that gives the least amnt of milk, then
 * the 2nd least, then the 3rd least, etc. So u first sort array of cow times
 * in ascending order and unhook them going from left to right
 */
import java.util.*;
import java.io.*;

public class silver1 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine()); // # of cows
		StringTokenizer st = new StringTokenizer(in.readLine());
		long[] A = new long[n]; // A[x]=# of units cow x gives in 1 min
		for(int j=0;j<n;j++) {
			A[j]=Integer.parseInt(st.nextToken());
		}
		for(int q=Integer.parseInt(in.readLine());q>0;q--) { // for each query
			st = new StringTokenizer(in.readLine());
			int i = Integer.parseInt(st.nextToken())-1; // cow i
			int j = Integer.parseInt(st.nextToken()); //  is set to j
			// calculate max amount of milk FJ can collect if he unhooks his
			//  cows in optimal order
			long max=0;
			long[] A2=A.clone();
			A2[i]=j;
			Arrays.sort(A2);
			for(int k=0;k<n;k++) {
				max+=(k+1)*A2[k];
			}
			out.println(max);
		}
		
		out.close();
	}
}
