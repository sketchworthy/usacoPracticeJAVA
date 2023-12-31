package class01x21x2023makeup;
/*
 * given an array of N ints, find max sum of an continuous subarray
 * 
 * O(n) solution: if sum ever dips below 0, reset to 0
 * 
 * difficulty: i noticed that you could group positive neighbors and negative 
 * neighbors but not this owo
 * idea is somehow simple yet impossible to think of at the same time
 */
import java.util.*;
import java.io.*;

public class Ex0201 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		int[] A = new int[n];
		for(int j=0;j<n;j++)A[j]=Integer.parseInt(st.nextToken());
		
		long maxSum=0;
		long sum=0;
		for(int j=0;j<n;j++) {
			sum+=A[j];
			maxSum=Math.max(maxSum,sum);
			if(sum<0)sum=0;
		}
		
		out.print(maxSum);
				
		out.close();
	}
}
