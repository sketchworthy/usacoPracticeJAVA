package class10x29x2023;
/* Flights With Minimum Stops COMPLETE, COMPLETE
 * Given a graph with N nodes and M directed edges, find the minimum
 * distance from node A to node B.
 * 
 * idea: # of stops along the way is min dist from A to B minus 1. 
 * To find the min path len for all the variables, do BFS, except
 * in the visited[] array record the min lengths from 1 of each
 * node. Using the deque ensures you go in increasing length of
 * path, so you will never have to lower path length to a node
 * you've already addressed after the initial addressal, you will
 * only discard larger path lengths you've found later.
 * 
 * difficulty: pretty ez once i thought about it a lil
 * 
 */
import java.util.*;
import java.io.*;

public class HW0801 {
	static int l = 0;
	static ArrayList<ArrayList<Integer>> adj; // adj[x] specifies neighbors of x
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("1"+l+".in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of nodes
		int m = Integer.parseInt(st.nextToken()); // # of edges
		
		adj = new ArrayList<>(); // initialize adj
		for(int j=0;j<n;j++)adj.add(new ArrayList<Integer>());
		
		st = new StringTokenizer(in.readLine());
		int a = Integer.parseInt(st.nextToken())-1; // source node
		int b = Integer.parseInt(st.nextToken())-1; // destination node
		
		for(int j=0;j<m;j++) { // fill adj
			st = new StringTokenizer(in.readLine());
			int f = Integer.parseInt(st.nextToken())-1;
			int t = Integer.parseInt(st.nextToken())-1; 
			adj.get(f).add(t);
		}
		
		int[] visited = new int[n]; // visited[x]=-1 if not visited, 
			// = min path len from node a if visited
		Arrays.fill(visited, -1);
		visited[a]=0;
		
		// use BFS to find min length from node a to b
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		dq.add(a);
		
		while(!dq.isEmpty()) {
			int node = dq.poll();
			for(int i:adj.get(node)) {
				if(visited[i]==-1) {
// note if node alr been visited, can't minimize the len more so don't bother
					dq.add(i);
					visited[i]=visited[node]+1;
				}
			}
		}
		
		out.print(visited[b] == -1 ? -1 : visited[b]);
		in.close();
		out.close();
	}
}
