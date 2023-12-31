package class10x08x2023;
/* USACO 2017 Feb Silver COMPLETE
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=715
 * 
 * idea: prefix sum arr & thats it
 * 
 * difficulty: ez
 * 
 */
import java.util.*;
import java.io.*;

public class Ex0501 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("maxcross.in"));
		PrintWriter out = new PrintWriter(new FileWriter("maxcross.out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of signals
		int k = Integer.parseInt(st.nextToken()); // # of working signals in continuous block
		int b = Integer.parseInt(st.nextToken()); // # of broken signals
		int[] broken = new int[n]; // IDs of broken signals 
		for(int j=0;j<b;j++) {
			broken[Integer.parseInt(in.readLine())-1]=1;
		}
		int[] ps = new int[n]; // prefix sum array of # of broken lights
		ps[0]=broken[0];
		for(int j=1;j<n;j++) {
			ps[j]=ps[j-1]+broken[j];
		}
		int ans=ps[k-1]-ps[0];
		// search how many broken lights from x to x+k-1 inclusive
		for(int j=0;j<n-k+1;j++) {
			ans = Math.min(ans, ps[j+k-1]-ps[j]);
		}
		out.print(ans);
		
		in.close();
		out.close();
	}
}
