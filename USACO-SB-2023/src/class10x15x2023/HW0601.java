package class10x15x2023;
/* Partition array COMPLETE
 * Given array A, partition it into 2 continuous 
 * subarrays L & R and report L's length so that
 *  every ele in L is <= every element in R,
 *  L & R both non-empty,
 *  and L is as small as possible.
 * 
 * idea: 
 * take prefix max arr from left and suffix min arr.
 * at every point, check if prefix max is bigger
 * or smaller than suff min. if pmx<smn, then you know
 * you have found your partition point
 * 
 * difficulty: p ez
 */
import java.util.*;
import java.io.*;

public class HW0601 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("10.in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		int[] A = new int[n];
		for(int j=0;j<n;j++) {
			A[j]=Integer.parseInt(st.nextToken());
		}
		
		int[] pmx = new int[n];
		pmx[0]=A[0];
		int[] smn = new int[n];
		smn[n-1]=A[n-1];
		
		for(int j=1;j<n;j++) {
			pmx[j]=Math.max(pmx[j-1], A[j]);
			smn[n-j-1]=Math.min(smn[n-j], A[n-j-1]);
		}
		
		int ans = -1;
		for(int j=0;j<n-1;j++) {
			if(pmx[j]<smn[j+1]) {
				ans=j+1;
				break;
			}
		}
		out.print(ans);
		
		
		in.close();
		out.close();
	}
}
