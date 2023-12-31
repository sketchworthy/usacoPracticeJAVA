package class11x05x2023;
/* USACO 2020 Feb Silver P3: Clock Tree COMPLETE
 * http://usaco.org/index.php?page=viewproblem2&cpid=1016
 * 
 * idea: bc there are n-1 edges and n nodes, this is a tree.
 * bc its a tree, each node of the graph can act as a root
 * node. then we have an algorithm: 
 * recursively visit root nodes and walk back-and-forth
 * between the child node and parent node until the child
 * node is 12, and do this for all of the children.
 * if at the end your parent node is 0 /OR 1/, then you
 * 
 * difficulty: idk how long it wouldve taken me to think of
 * the algorithm if i wasnt just straight up told it.
 * also i had some debug trouble at the end bc i forgot abt
 * the algorithm can have the root node be 0 OR 1 at the end
 * and almost screwed myself over, and teacher had to remind
 * me.
 * 
 */
import java.util.*;
import java.io.*;

public class Ex0902 {
	
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visited; 
	static int[] clocks;
	static int MOD = 12;
	
	public static void main(String[] args) throws Exception {
		// take input
		BufferedReader in = new BufferedReader(new FileReader("clocktree.in"));
		PrintWriter out = new PrintWriter(new FileWriter("clocktree.out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine()); // # of rooms
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		clocks = new int[n]; // clock settings for each of n rooms
		int[] clockOg = new int[n]; // keep as reference to reset to
		for(int j=0;j<n;j++) {
			clockOg[j]=Integer.parseInt(st.nextToken());
		}
		
		adj = new ArrayList<>();
		for(int j=0;j<n;j++) adj.add(new ArrayList<Integer>());
		for(int j=1;j<n;j++) { // fill adj
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		visited = new boolean[n];

		int ans=0;
		for(int root=0;root<n;root++) { // for root node
			
			// reset stage
			Arrays.fill(visited, false);
			for(int j=0;j<n;j++) clocks[j]=clockOg[j];
			
			// visit root node and their children, adjust until they all have 12's
			adjustChildren(root,-1);
			if(clocks[root]==0 || clocks[root]==1) ans++;
		}
		
		out.print(ans);
		in.close();
		out.close();
	}
	
	public static void adjustChildren(int node, int parent) {
		if(visited[node]) return;
		
		visited[node]=true;
		// adjust children then adjust parent
		for(int x:adj.get(node)) { // For child x, adjust em
			if(x==parent)continue;
			
			adjustChildren(x,node);
		}
		
		// adjust parent if not root node
		if(parent!=-1) {
			clocks[parent]+=MOD-clocks[node];
			clocks[parent]%=MOD;
			clocks[node]=0;
		}
		return;
	}
}
