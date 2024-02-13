package class02x04x2024;
/* Beauty Sum II COMPLETE
 * Given arr A[], the beauty of A[i] is: 
 * 2 if A[j]<A[i]<A[k] for all j<i and all k>i
 * 1 if prev condition not true and A[i-1]<A[i]<A[i+1]
 * 0 if no prev conditions.
 * report sum of beauty for all indices except 1st and last indices.
 * 
 * idea: prefix max and min arrs
 * 
 * difficulty: really easy. the only tiny note is that
 * for checking if A[i] is strictly greater than all
 * A[x] before it, then just check the condition
 * pm[i-1]<A[i] instead of comparing pm[i] and A[i]
 * 
 */
import java.util.*;
import java.io.*;

public class Ex0301 {
	static int w = 9;

	public static void main(String[] args) throws Exception {
		// take input
		 BufferedReader in = new BufferedReader(new FileReader("0"+w+".in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());
		int[] A = new int[n];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			A[j] = Integer.parseInt(st.nextToken()); 
		}
		
		int[] pm = new int[n]; // prefix max
		pm[0]=A[0];
		
		for(int j=1;j<n;j++) {
			pm[j]=Math.max(pm[j-1], A[j]);
		}
		
		int[] smin = new int[n]; // suffix min
		smin[n-1]=A[n-1];
		for(int j=n-2;j>=0;j--) {
			smin[j]=Math.min(smin[j+1], A[j]);
		}
		
		int beautySum=0;
		
		// find beautySum
		for(int j=1;j<n-1;j++) {
			// check if 2
			if(pm[j-1]<A[j] && A[j]<smin[j+1]) {
				beautySum+=2;
			}
			else if(A[j-1]<A[j] && A[j]<A[j+1]){
				beautySum++;
			}
		}
		
		
		out.println(beautySum);

		in.close();
		out.close();
	}
}