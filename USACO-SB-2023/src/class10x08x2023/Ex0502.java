package class10x08x2023;
/* USACO 2016 Jan Silver COMPLETE
 * http://usaco.org/index.php?page=viewproblem2&cpid=595
 * 
 * idea: prefix sums
 * 
 * difficulty: ez
 * 
 */
import java.util.*;
import java.io.*;

public class Ex0502 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("div7.in"));
		PrintWriter out = new PrintWriter(new FileWriter("div7.out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());
		int[] A = new int[n+1];
		for(int j=0;j<n;j++) {
			A[j+1] = Integer.parseInt(in.readLine())%7;
		}
		
		int[] ps = new int[n+1];
		for(int j=1;j<n+1;j++) {
			ps[j]=(ps[j-1]+A[j])%7;
		}

		// track 1st and last appearances of each mod 7
		int[][] ends = new int[7][2];
		boolean[] endFound = new boolean[7]; // if the start of a mod has been found
		
		ends[0][0]=0; endFound[0]=true;
		for(int j=1;j<n+1;j++) {
			if(!endFound[ps[j]]) {
				ends[ps[j]][0]=j;
				endFound[ps[j]]=true;
			}
			ends[ps[j]][1]=j;
			
		}
		int ans=ends[0][1]-ends[0][0];
		for(int j=1;j<7;j++) {
			ans=Math.max(ans, ends[j][1]-ends[j][0]);
		}
		out.print(ans);
		
		in.close();
		out.close();
	}
}
