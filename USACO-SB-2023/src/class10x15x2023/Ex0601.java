package class10x15x2023;
/* Trapping Water  
 * NEEDS REVIEW
 * Given n ints each >=0 representing an elevation map where the width of
 * each bar is 1, return how much water it can trap after raining.
 * 
 * idea:
 * 
 * difficulty:
 * 
 */
import java.util.*;
import java.io.*;

public class Ex0601 {
	static int x = 9;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("0"+x+".in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());
		int[] A = new int[n];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			A[j]=Integer.parseInt(st.nextToken());
		}
		int[] pmxL= new int[n]; // max from left
		pmxL[0]=A[0];
		for(int j=1;j<n;j++) {
			pmxL[j]=Math.max(pmxL[j-1], A[j]);
		}

		int[] pmxR= new int[n]; // max from right (suffix max)
		pmxR[n-1]=A[n-1];
		for(int j=n-2;j>=0;j--) {
			pmxR[j]=Math.max(pmxR[j+1], A[j]);
		}
		
		long sum = 0;
		for(int j=1;j<n;j++) {
			sum+=Math.min(pmxL[j],pmxR[j])-A[j];
		}
		out.println(sum);
		
		// check
		BufferedReader in2 = new BufferedReader(new FileReader("0"+x+".out"));
		out.println(in2.readLine());
		
		in.close();
		out.close();
	}
}
