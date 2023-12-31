package oldUsacoProbs;
/* USACO 2015 December Silver p3 Breed Counting COMPLETE
 * 
 * _problem desc_
 * 
 * idea: create 3 arrays, 1 holding # of holseins up to an index, 1 w # of
 * Gs up to index, 1 holding Js up to index. then just subtract for each query
 * (prefix sums basically)
 * 
 * difficulty: EZEZ
 * 
 */
import java.util.*;
import java.io.*;

public class breedCounting {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		int[] H = new int[n+1];
		int[] G = new int[n+1];
		int[] J = new int[n+1];
		for(int j=0;j<n;j++) {
			int next = Integer.parseInt(in.readLine());
			
			if(next==1) {
				
			}
		}
		
		in.close();
		out.close();
	}
}
