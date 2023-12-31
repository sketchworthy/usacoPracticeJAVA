package class03x11x2023;

/* USACO Silver Feb 2018 Problem 1
 * 
 * idea: create a PriorityQueue of Stops (created class) sorted by tastiness.
 * while the simulation runs, Bessie tries to always go to the tastiest stop
 * she can and stay there eating until FJ catches up, then moves on. this
 * continues until there are no more stops
 * 
 * difficulty: finding the idea was probably hard but implementation p ez.
 * also debugging was annoying bc i had to make it a long but it refused to work
 */
import java.util.*;
import java.io.*;

public class Ex0702 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("reststops.in"));
		PrintWriter out = new PrintWriter(new FileWriter("reststops.out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		Integer.parseInt(st.nextToken()); // length of trail
		int n = Integer.parseInt(st.nextToken()); // # of rest stops
		int rf = Integer.parseInt(st.nextToken()); // seconds FJ takes to go 1m
		int rb = Integer.parseInt(st.nextToken()); // secs bessie needs to go 1m
		PriorityQueue<Stop> stops = new PriorityQueue<>((s1,s2)->
							s2.taste-s1.taste); // sorted in descending order
		for(int j=0;j<n;j++) {
			st = new StringTokenizer(in.readLine());
			Stop s=new Stop(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			stops.add(s);
		}
		long cb=0; // current loc of bessie & FJ
//		int cf=0; // current loc of FJ
		long max=0; // max tastiness points consumable
		while(!stops.isEmpty()) {
			// find next stop bessie will go to
			Stop s = stops.poll();
			if(s.loc>cb) {
				// now, head stop is the next stop bessie will go
				// bessie gets there in rb*(s.loc-cb)
				// FJ WILL get there in rf*(s.loc-cf)
				// bessie eats s.taste*(rf*(s.loc-cf)-rb*(s.loc-cb))
				//  tastiness points, bessie and FJ now both at loc stops.peek.loc()
				max+=(long)(s.loc-cb)*s.taste*(rf-rb);
				cb=s.loc;
	//			cf=cb;
			}
		}
		out.print(max);
		in.close();
		out.close();
	}
	
	static class Stop{
		public int loc;
		public int taste; // tastiness value
		
		Stop(int a, int b){
			loc=a;
			taste=b;
		}
	}
}
