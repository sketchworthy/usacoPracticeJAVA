package class11x19x2023;
/* Haybale Harvest status{COMPLETE or not}_
 * Given N nodes w unique weights and their locations on the number line,
 * find the max weight u can have if you can only move K steps from a
 * start location A
 * 
 * idea: in order for bess to consume the max possible tastiness,
 * she moves straight right/left until she hits some haybale,
 * then immediately turns around and heads in other direction as 
 * far as possible. using this algorithm, we can simulate the n 
 * possible versions of this algorithm by bessie using
 * each of the n haybales as turning points!
 * 
 * difficulty: my v1 code was bugging out and not working so i tried
 * to rewrite it for clarity.
 * 
 */
import java.util.*;
import java.io.*;

public class Ex1102v2 {
	static int[][] haybales; // [position, tastiness] of haybales 
	static int[] ps; // prefix sum array of total haybale tastiness up to an index
	static int n; // # of haybales
	static int a; // bess's initial position
	static int k; // # of steps bess can move
	static long maxTaste=0; // ans: max total tastiness bess can eat
	
	public static void main(String[] args) throws Exception {
//		BufferedReader in = new BufferedReader(new FileReader("02.in"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken()); // # of haybales
		a = Integer.parseInt(st.nextToken()); // bess's initial position
		k = Integer.parseInt(st.nextToken()); // # of steps bess can move
		
		haybales = new int[n][2]; // [position, tastiness] of haybales  
		for(int j=0;j<n;j++) {
			st = new StringTokenizer(in.readLine());
			haybales[j][0] = Integer.parseInt(st.nextToken()); // pos
			haybales[j][1] = Integer.parseInt(st.nextToken()); // taste
		}
		// sort haybales by position (ascending)
		Arrays.sort(haybales, (x,y)->x[0]-y[0]);
		
		ps = new int[n]; // prefix sum array of total haybale tastiness
		ps[0]=haybales[0][1];
		for(int j=1;j<n;j++) {
			ps[j]=ps[j-1]+haybales[j][1];
		}
		
		// simulate for each of n haybales being the turning point haybale
		for(int j=0;j<n;j++) { // j: index of haybale bess turns around on
			solve(j);
		}
		
		out.print(maxTaste);
		
		in.close();
		out.close();
	}
	
	static void solve(int j) { 
		// update maxTaste when index j of haybales[][] is the turning point
		// TODO
		
		// update maxTaste
		maxTaste = Math.max(currTaste, maxTaste);
	}
}