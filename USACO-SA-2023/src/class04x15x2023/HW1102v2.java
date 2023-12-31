package class04x15x2023;
/* USACO Silver Dec 2016 P3 Moocast COMPLETE
 * 
 * idea: first make an adjacency arr matrix of cows immediately
 * able to reach each other. then loop thru cows and find max # of cows
 * 1 cow can reach. 

bc of small size, you can just loop thru every single cow's every single
vertex visit. then for each vertex u visit reset the visited arr and 
keep a max variable that you update. 
 * 
 * 
 * difficulty: hardhardhard bc i am inexperienced in graphing
 * 
 * problems encountered when i tried to keep track of # of nodes each
 * node can visit w arr: if you have a loop in a graph and are trying to
 * keep an int arr of the # of nodes each node in the graph can reach,
 * looping causes issues in counting the # each node can reach bc you
 * can't just straight-up increment
 * A C
 * B D
 * All are connected but their tracking only gives 4 3 2 1
 */
import java.util.*;
import java.io.*;


public class HW1102v2 {
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visited;
	static int cnt;
//	static int[] canReach; // int arr of # of cows reachable
//	static ArrayList<ArrayList<Integer>> canReach; // arrlist of arrlists
											// for the cows 1 cow can reach
	
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
//		canReach=new int[n];
//		Arrays.fill(canReach, 1);
//		canReach=new ArrayList<>();
//		for(int j=0;j<n;j++) {
//			canReach.add(new ArrayList<>());
//		}
		
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
		} // adj now filled
		int max=0; // max # of cows u can visit
		for(int j=0;j<n;j++) { // for each cow find cows it can reach
			visited=new boolean[n];
			cnt=1; // check if cnt even needed TODO
			visit(j);
			max=Math.max(cnt, max);
		}
		out.print(max);
		in.close();
		out.close();
	}
	
	static void visit(int node){  // TODO FIX UGHHHHHH
		if(visited[node])return; // if visited, canReach dont need updating
		visited[node]=true;
		for(int nbor:adj.get(node)) {
			if(!visited[nbor]) { // if this neighbor hasnt been visited		
				cnt++;
				visit(nbor);
			}
		}
	}
}
