package class10x01x2023;
/* USACO 2016 Dec Silver COMPLETE
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=666
 * Given n points along a road, return the number of points in
 * any interval query (Q from 1 to 10^5)
 * 
 * idea: given the array points[], sort it first. then just
 * for each query, find the index of the 1st element of points[]
 * within the interval query range and the index of the last 
 * element of points[] in that range using binary search
 * 
 * difficulty: ez once i sat down and thought about it
 * 
 */
import java.util.*;
import java.io.*;

public class Ex0401 {
	public static int[] points;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("haybales.in"));
		PrintWriter out = new PrintWriter(new FileWriter("haybales.out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		points = new int[n];
		st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			points[j]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(points);
		for(;q>0;q--) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			// do binary search from start to end, inclusive
			// find 1st&last indices of points that within [A,B]
			int j=findHigher(start,0,n-1);
			int k=findLower(end,0,n-1);
			if(j==-1)out.println(0);
			else out.println(k-j+1);
		}
		
		in.close();
		out.close();
	}
	
	static int findHigher(int start, int j, int k) {
		// find index of 1st ele of sorted points[] >= start. if nonexistent return -1
		// you have already narrowed it down that the index is between j and k, j<k
		
		if(j>k)return -1;
		if(j==k) {
			if(points[j]>=start)return j;
			return -1;
		}
		
		int ci=(j+k)/2;
		if(points[ci]>start) { // may be better stuff under points that works
			int low =findHigher(start,j,ci-1);
			if(low==-1)return ci; // if no lower one, ci is ideal
			return low; // if lower exists return it
		}
		if(points[ci]==start) {
			return ci;
		}
		// else if points[ci]<start
			return findHigher(start,ci+1,k);
	}
	
	static int findLower(int end, int j, int k) {
		// find index of last ele of sorted points[] <=end. if nonexistent return -1
		// you've already narrowed it down that index between j and k where j<k
		if(j>k)return -1;
		if(j==k) {
			if(points[j]<=end)return j;
			return -1;
		}
		
		int ci = (j+k)/2;
		if(points[ci]<end) { // ci already an option
			int high = findLower(end,ci+1,k); // maybe better option?
			if(high!=-1)return high;
			return ci;
		}
		if(points[ci]==end) {
			return ci;
		}
		return findLower(end,j,ci-1);
	}
}