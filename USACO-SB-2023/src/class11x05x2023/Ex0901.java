package class11x05x2023;
/* Loud and Rich COMPLETE
 * COMPLETE. ALSO DONE (less optimized-ly) IN CPP
 * U have a graph of N nodes with each node having a quietness val.
 * Each one also has an unknown amount of money.
 * You are given m relations depicting a node being richer than another.
 * For each person x, report the # of the person w the smallest
 * quietness from all the ppll with at least as much money as x.
 * xxx
 * 
 * idea: you have an adj graph with explicit money relations.
 * however, as long as you can reach along some amount of paths
 * from 1 node to another, they have a superior-richness relation.
 * do this w BFS? DFS? How to decide? Can u do both?
 * 
 * 
 * difficulty:
 * 
 */
import java.util.*;
import java.io.*;

public class Ex0901 {
	static ArrayList<ArrayList<Integer>> adj; // if adj[a] has b, b has more money than a
	static int[] quiet;
	static int[] ans; // dp. keeps track of each person's quietest richer guy 
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of ppl/nodes
		int m = Integer.parseInt(st.nextToken()); // # of edges/relations
		quiet = new int[n]; // quietness values for ppl
		st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			quiet[j]=Integer.parseInt(st.nextToken());
		}
		adj = new ArrayList<>();
		for(int j=0;j<n;j++) adj.add(new ArrayList<>());
		for(int j=0;j<m;j++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj.get(b).add(a); // a has more money than b
		}
		
		ans = new int[n]; Arrays.fill(ans, Integer.MAX_VALUE);
		for(int j=0;j<n;j++) {
			dfs(j);
		}
		
		for(int j=0;j<n;j++) out.println(ans[j]);

		in.close();
		out.close();
	}
	
	static void dfs(int node) {
		if(ans[node]!=Integer.MAX_VALUE) return; // skip if already processed
		
		ans[node]=node;
		for(int child: adj.get(node)) {
			dfs(child);
			if(quiet[ans[child]]<quiet[ans[node]]) {
				ans[node]=ans[child];
			}
		}
		return;
	}
}
