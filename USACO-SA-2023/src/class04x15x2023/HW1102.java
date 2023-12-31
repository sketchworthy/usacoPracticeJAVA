package class04x15x2023;
/* USACO Silver Dec 2016 P3 Moocast COMPLETE IN HW1102v2
 * 
 * idea: first make an adjacency arr matrix of cows immediately
 * able to reach each other, then 
 * 
 * difficulty: this ones broken and bad look at HW1102v2
 */
import java.util.*;
import java.io.*;


public class HW1102 {
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visited;
	static int[] canReach; // canReach[x] returns # of cows cow x can reach 
								// (including self)
	
	public static void main(String[] args) throws Exception {
//		BufferedReader in = new BufferedReader(new FileReader("moocast.in"));
//		PrintWriter out = new PrintWriter(new FileWriter("moocast.out"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine()); // # of cows
		int[][] cows = new int[n][3]; // [x,y,p]
		for(int j=0;j<n;j++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			cows[j][0] = Integer.parseInt(st.nextToken());
			cows[j][1] = Integer.parseInt(st.nextToken());
			cows[j][2] = Integer.parseInt(st.nextToken());
		}
		adj=new ArrayList<>();
		for(int j=0;j<n;j++) {
			adj.add(new ArrayList<>());
		}
		// fill adj. for each cow, check cows in its immediate reach
		for(int j=0;j<n;j++) { // start cow
			for(int k=0;k<n;k++) {
				if(j==k)continue;
				// if j CAN transmit to k, add k to j's neighbor list
				double dist = Math.abs(cows[j][0]-cows[k][0]);
				dist*=dist;
				double dist2= Math.abs(cows[j][1]-cows[k][1]);
				dist2*=dist2;
				dist+=dist2;
				dist=Math.sqrt(dist); // dist is now proper distance from j to k
				if(dist<=(double)cows[j][2]) {
					adj.get(j).add(k);
				}
			}
		}
		visited = new boolean[n];
		canReach = new int[n];
		int max=0; // max # of cows any 1 cow can reach thru transmissions&hopping
		for(int j=0;j<n;j++) {
			findNumCanReach(j,-1);
		}
		for(int j=0;j<n;j++) {
			max=Math.max(max, canReach[j]);
		}
		out.print(max);
		in.close();
		out.close();
	}
	
	static void findNumCanReach(int node, int p){
		if(visited[node])return;
		visited[node]=true; // TODO see if this should go before or after for block
		for(int nbor:adj.get(node)) {
			if(nbor==p)continue;
			findNumCanReach(nbor,node);
			canReach[node]+=canReach[nbor];
		}
//		canReach[node]+=adj.get(node).size(); // nvm dont need this bc the +1 alr included
//		if(adj.get(node).contains(p))canReach[node]--; // nvm dont need this either bc nbor==p case is skipped
		canReach[node]++; // count itself
	}
}
