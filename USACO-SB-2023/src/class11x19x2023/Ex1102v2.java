package class11x19x2023;
/* Haybale Harvest COMPLETE
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
 * to rewrite it for clarity. after writing out separately all the
 * binary functions it finally worked. yay for clarity!
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
	static int smallerEqI;
	static int greaterEqI;
	static int smallerI;
	static int greaterI;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("10.in"));
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
		
		smallerEqI = findSmallerEqI(a);
		greaterEqI = findGreaterEqI(a);
		smallerI = findSmallerI(a);
		greaterI = findGreaterI(a);
		
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
		
		if(k<Math.abs(haybales[j][0]-a))return; // if bess can't even make it
		
		int currTaste=0;
		if(a>haybales[j][0]) { // bess goes left then back then right
			if(j==0) currTaste =ps[smallerEqI];
			else currTaste = ps[smallerEqI]-ps[j-1];
		}
		else { // bess goes right then back then left
			if(greaterEqI==0) currTaste = ps[j];
			else currTaste = ps[j]-ps[greaterEqI-1];
		}
		
		int lenLength=k-2*Math.abs(haybales[j][0]-a);
		if(lenLength<0) {
			// update maxTaste and end early
			maxTaste = Math.max(currTaste, maxTaste);
			return;
		}
		
		// calculate extra taste value from bess going now in oppo direction
		if(a>haybales[j][0]) { // bess went left now going right
			// bess can make it to position a+lenLength
			// add total tastiness in (a,a+lenLength]
			if(greaterI==0) currTaste+=ps[findSmallerEqI(a+lenLength)];
			else currTaste+=ps[findSmallerEqI(a+lenLength)]-ps[greaterI-1];
		}
		else { // bess goes right then back then left
			// bess can make it to position a-lenLength
			// add total tastiness in [a-lenLength,a)
			int temp = findGreaterEqI(a-lenLength);
			if(temp==0) currTaste+=ps[smallerI];
			else currTaste+=ps[smallerI]-ps[temp-1];
		}
		
		// update maxTaste
		maxTaste = Math.max(currTaste, maxTaste);
	}
	
	// binary search methods
	static int findGreaterEqI(int pos) { // given position, find smallest 
		// index i where haybales[i][0] >= pos
		int low=0;
		int high=n-1;
		while(low<high) {
			int mid = (low+high)/2;
			
			if(haybales[mid][0]>pos) {
				high=mid;
			}
			else if(haybales[mid][0]==pos) {
				low=mid;
				high=mid;
			}
			else {
				low=mid+1;
			}
		}
		return low;
	}
	static int findGreaterI(int pos) { // given position, find smallest 
		// index i where haybales[i][0] > pos
		int low=0;
		int high=n-1;
		while(low<high) {
			int mid = (low+high)/2;
			
			if(haybales[mid][0]>pos) {
				high=mid;
			}
			else {
				low=mid+1;
			}
		}
		return low;
	}
	static int findSmallerI(int pos) { // given position, find biggest 
		// index i where haybales[i][0] < pos
		int low=0;
		int high=n-1;
		while(low<high) {
			int mid = (low+high+1)/2;
			
			if(haybales[mid][0]<pos) {
				low=mid;
			}
			else {
				high=mid-1;
			}
		}
		return low;
	}
	static int findSmallerEqI(int pos) { // given position, find biggest 
		// index i where haybales[i][0] <= pos
		int low=0;
		int high=n-1;
		while(low<high) {
			int mid = (low+high+1)/2;
			
			if(haybales[mid][0]<pos) {
				low=mid;
			}
			else if(haybales[mid][0]==pos) {
				low=mid;
				high=mid;
			}
			else {
				high=mid-1;
			}
		}
		return low;
	}
}