package class10x01x2023;
/* USACO 2018 Dec Silver Convention COMPLETE
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=858
 * 
 * idea: 'inverse binary search problem': want to find min waiting time,
 * then we binary search thru all possible waiting times it can be. 
 * to check if a waiting time works, we just loop thru all arriving cows
 * and see how many buses are needed to get them all home. if it takes
 * too many buses then we know we need to increase the time ppl wait.
 * otherwise, its sufficient.
 * 
 * difficulty: ez once i understood how to do it. i had some trouble
 * debugging bc for 1 case i accidentally set my binary search 'goalposts'
 * too greedily, so i need to be careful when initiating low & high
 * 
 */
import java.util.*;
import java.io.*;

public class Ex0402 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
//		BufferedReader in = new BufferedReader(new FileReader("convention.in"));
//		PrintWriter out = new PrintWriter(new FileWriter("convention.out"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of cows
		int m = Integer.parseInt(st.nextToken()); // # of buses
		int c = Integer.parseInt(st.nextToken()); // cow capacity of bus
		int[] times = new int[n];
		st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) times[j] = Integer.parseInt(st.nextToken());
		Arrays.sort(times);
		
		long high=times[n-1];
		long low = 0;
		while(low<high) {
			long curr=(high+low)/2;
			// check if curr as time the bus will wait before leaving works
			int numBuses=1;
			int ci=1; // cow currently boarding
			long t=times[0]+curr; // time current bus is schedule to leave
			int boarded=1; // # of cows already on current bus being boarded
			while(ci<n) {
				// check if ci needs a new bus
				if(boarded>=c || times[ci]>t) { 
					// if no more space on bus, or bus has already left
					numBuses++;
					t=times[ci]+curr;
					boarded=1;
				}
				else { // ci can board current bus
					boarded++;
				}
				ci++;
			}
			
			if(numBuses>m) {
				low = curr+1;
			}
			else {
				high=curr;
			}
		}
		if(n!=0)out.print(high);
		else out.print(0);
		in.close();
		out.close();
	}
	
}
