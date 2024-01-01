package class11x19x2023;
/* Haybale Harvest COMPLETE SEE Ex1102v2
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
 * difficulty: got into a flow state w implementing this problem. 
 * straightforward once u get the idea of simulating for each of 
 * n possibilities! however my 1st draft hit bugs. i found out
 * later it was bc th binary search, my mid = (low+high)/2 should
 * have been (low+high+1)/2. ez fix. ez find bug by just inputting
 * tiny sample test case.
 * even after that there were still bugs tho. i ended up just
 * rewriting the whole program which worked! yay for clarity!
 * (its in Ex1102v2)
 * 
 */
import java.util.*;
import java.io.*;

public class Ex1102 {
	static int[][] haybales; // [position, tastiness] of haybales 
	static int[] ps; // prefix sum array of total haybale tastiness up to an index
	static int n; // # of haybales
	static int a; // bess's initial position
	static int k; // # of steps bess can move
	static int downer; // greatest index i s.t. haybales[i][0]<=a
	static int upper; // smallest index i s.t. haybales[i][0]>=a
	static long maxTaste=0; // ans: max total tastiness bess can eat
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("02.in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
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
		
		// binary search for downer
		int higher = n-1;
		int lower = 0;
		while(higher>lower) {
			int mid = (higher+lower+1)/2;
			
			if(haybales[mid][0]<a) {
				lower = mid;
			}
			else if (haybales[mid][0]>a){
				higher=mid-1;
			}
			else { // if equal
				higher=mid;
				lower=mid;
			}
		}
		downer=lower;
		if(haybales[downer][0]==a) upper = lower; // find upper as well
		else upper = lower+1;
		
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
		
		int higher, lower;
		if(Math.abs(haybales[j][0]-a)>k) return; 
		// leave for another iteration if k is not big enough for
		// bess to go all the way from a to haybales[j][0]
		
		// now, bess will definitely make it from a to haybales[j][0].
		// how much tastiness gathered along the way?
		int currTaste=0;
		if(haybales[j][0]<a) { // left of a
			if(j==0) currTaste = ps[downer];
			else currTaste = ps[downer]-ps[j-1];
		}
		else { // on a, or to the right
			if(haybales[0][0]==a) currTaste = ps[j];
			else currTaste = ps[j]-ps[upper-1];
		}
		
		
		int lenLeft = k-2*Math.abs(haybales[j][0]-a); 
		// how many steps bess has left after going from position a 
		// to haybales[j][0] and back
		if(lenLeft>0) { // if bess has enough juice left to
			// go from position a in opposite direction to 
			// grab more cows
			if(haybales[j][0]<a && haybales[upper][0]-a<=lenLeft) {
				// turning point was left of a and we can get to more haybales
				// binary search for largest haybale index
				// bess can make it to
				higher = n-1;
				lower = upper;
				while(lower<higher) {
					int mid = (higher+lower+1)/2;
					
					if(haybales[mid][0]-a<lenLeft) {
						lower = mid;
					}
					else if(haybales[mid][0]-a>lenLeft) {
						higher = mid-1;
					}
					else {
						higher=mid;
						lower=mid;
					}
				}

				// use ps array to update currTaste
				currTaste+=ps[higher]-ps[upper-1];
				
			}
			else if (a-haybales[downer][0]<=lenLeft){ 
				// turning point to the right of a and can make it
				// binary search for smallest haybale index
				// bess can make it to
				higher = downer;
				lower = 0;
				while(lower<higher) {
					int mid = (higher+lower)/2;
					
					if(a-haybales[mid][0]<lenLeft) {
						higher = mid;
					}
					else if(a-haybales[mid][0]>lenLeft) {
						lower = mid+1;
					}
					else {
						higher=mid;
						lower=mid;
					}
				}

				// use ps array to update currTaste
				if(higher==0) currTaste+=ps[lower];
				else currTaste+=ps[lower]-ps[higher-1];
				
			}
		}
		
		// update maxTaste
		maxTaste = Math.max(currTaste, maxTaste);
	}
}