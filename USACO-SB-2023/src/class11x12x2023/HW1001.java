package class11x12x2023;
/* Millionaire Madness DOESN'T WORK, BUT NEITHER DOES CLASS SOL LMAO
 * Given a grid of heights, find the shortest ladder len necessary to
 * somehow make it from the NW corner to the SE corner provided
 * going from height a to b where a<b requires a ladder length of
 * at least b-a.
 * 
 * idea: i was originally stuck on this problem bc i thought i needed
 * to use dfs straight up, but got stuck on how to keep a separate
 * visited arr for every function call. however that is actually not the way.
 * notice since we're looking for ladder height, this is an inverse
 * binary search problem. then, for every possible ladder, we can 
 * perform dfs to find the cluster of all paths reachable from the
 * top left corner and keep a visited arr. if bottom right corner
 * reachable, this ladder len works.
 * 
 * difficulty: i wanted to make it so that every dfs call
 * has its own copy of a visited arr so it can track its own
 * visits but that didnt rlly work :(
 * instead, inverse binary search ftw! and dfs to detect
 * clusters! i hit stack overflow errors on my dfs tho :(
 * EDIT: even the class solution hits stack overflow errors
 * on java OR cpp dfs lmao. nvm, dfs is not the way
 * 
 */
import java.util.*;
import java.io.*;

public class HW1001 {
	static int m,n;
	static ArrayList<ArrayList<Integer>> adj; // every node is adj to 4 neighbors
	// node [x][y] is ID x*n+y, which covers 0 thru m*n-1
	static int[][] grid;
	static int ladderLen;
	static boolean[][] visited;
	
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws Exception {
//		BufferedReader in = new BufferedReader(new FileReader("02.in"));
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
		
		// fill adj and find tallest height of coin pile
		adj = new ArrayList<>();
		for(int j=0;j<m*n;j++) {
			adj.add(new ArrayList<>());
		}
		int tallestCoinHeight=0;
		for(int j=0;j<m;j++) {
			for(int k=0;k<n;k++) { // for node[j][k]
				tallestCoinHeight = Math.max(tallestCoinHeight, grid[j][k]);
				int currI = j*n+k;
				for(int i=0;i<4;i++) {
					if(j+dr[i]>=0 && j+dr[i]<m && k+dc[i]>=0 && k+dc[i]<n) {
						adj.get(currI).add((j+dr[i])*n+k+dc[i]);
					}
				}
			}
		}
		
		// inverse binary search for smallest ladder length that will work
		int low = 0;
//		int high = tallestCoinHeight;
		int high=(int)1e9;
		while(low<high) {
			ladderLen = (low+high)/2;
			
			visited = new boolean[m][n];
			
			// find cluster of all visit-able squares with dfs			
			if(dfs(0)) {
				high = ladderLen;
			}
			else low = ladderLen+1;
		}
		
		out.print(low);

		in.close();
		out.close();
	}
	
	static boolean dfs(int node) {
		// find cluster of all visit-able squares with ladderLen 
		visited[node/n][node%n]=true;
		if(node==m*n-1)return true;
		
		boolean ans=false;
		for(int nbor:adj.get(node)) {
			if(visited[nbor/n][nbor%n])continue;
			if(visited[m-1][n-1])return true;
			if(grid[nbor/n][nbor%n]<=ladderLen+grid[node/n][node%n]) {
				ans |= dfs(nbor);
			}
		}
		return ans;
	}
}
