package class11x12x2023;
/* Millionaire Madness _status{COMPLETE or not}_
 * Given a grid of heights, find the shortest ladder len necessary to
 * somehow make it from the NW corner to the SE corner provided
 * going from height a to b where a<b requires a ladder length of
 * at least b-a.
 * 
 * idea: notice since we're looking for ladder height, this is an inverse
 * binary search problem.
 * you can perform dfs to find all possible paths from
 * top left corner to bottom right corner by just keeping a visited arr
 * 
 * difficulty: i wanted to make it so that every dfs call
 * has its own copy of a visited arr so it can track its own
 * visits but that didnt rlly work :(
 * instead, 
 * 
 */
import java.util.*;
import java.io.*;

public class HW1001 {
	static int m,n;
	static ArrayList<ArrayList<Integer>> adj; // every node is adj to 4 neighbors
	// node [x][y] is ID x*m+y, which covers 0 thru m*n-1
	static int[][] grid;
	static int[][] ans; // ans[x][y] min height of ladder that can get to that point on grid
	
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		grid = new int[m][n];
		for(int j=0;j<m;j++) {
			st = new StringTokenizer(in.readLine());
			for(int k=0;k<n;k++) {
				grid[j][k]=Integer.parseInt(st.nextToken());
			}
		}
		
		// fill adj
		adj = new ArrayList<>();
		for(int j=0;j<m*n;j++) {
			adj.add(new ArrayList<>());
		}
		for(int j=0;j<m;j++) {
			for(int k=0;k<n;k++) { // for node[j][k]
				int currI = j*m+k;
				for(int i=0;i<4;i++) {
					if(j+dr[i]>=0 && j+dr[i]<m && k+dc[i]>=0 && k+dc[i]<n) {
						adj.get(currI).add((j+dr[i])*m+k+dc[i]);
					}
				}
			}
		}
		
		// set up ans
		ans = new int[m][n];
		for(int j=0;j<m;j++) Arrays.fill(ans[j], Integer.MAX_VALUE);
		ans[0][0]=0;
		
		boolean[][] visited = new boolean[m][n];
		visited[0][0]=true;
		
		// dfs
		dfs(0,visited);
		
		out.println(ans[m-1][n-1]);

		in.close();
		out.close();
	}
	
	static void dfs(int node, boolean[][] visitd) { 
		if(node==m*n-1 || visitd[m-1][n-1]==true) return;
		
		// make a copy of bool array to pass on to future dfs calls
		boolean[][] visited = new boolean[m][n]; 
		for(int j=0;j<m;j++) {
			for(int k=0;k<n;k++) {
				visited[j][k]=visitd[j][k];
			}
		}
		
		// visited nodes so far, and prev node
		for(int nbor:adj.get(node)) {
			// visit nbor from node and update ans
			if(!visited[nbor/m][nbor%m]) {
				visited[nbor/m][nbor%m]=true;
				if(grid[nbor/m][nbor%m]<=grid[node/m][node%m]) { // if they fall to get to nbor
					ans[nbor/m][nbor%m]=Math.min(ans[node/m][node%m], ans[nbor/m][nbor%m]);
				}
				else {
					ans[nbor/m][nbor%m]=Math.min( Math.max(ans[node/m][node%m], 
						grid[nbor/m][nbor%m]-grid[node/m][node%m]),
						 ans[nbor/m][nbor%m]); // min of factoring in ladder len vs whats already there
				}
				dfs(nbor,visited);
			}
		}
	}
}
