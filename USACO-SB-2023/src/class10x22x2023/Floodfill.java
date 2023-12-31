package class10x22x2023;
/* Floodfill test COMPLETE
 * User inputs a num n. then user inputs an nxn grid of 0s and 1s.
 * system returns # of clusters of 0s, and max size of a cluster of 0s.
 * 
 * idea: loop thru each of the points on the grid (O(n^2)).
 * Then for each point, check if its part of a new cluster
 * (if its been visited). If it hasnt, then its a part of a
 * new cluster and we start checking all its neighbors and 
 * the neighbors of its neighbors and etc. We keep a deque
 * of the cells weve encountered in the cluster and go thru
 * it, adding any true neighbors we havent visited yet of
 * the poll of the deque as we go. eventually weve got it
 * all! yay :D
 * 
 * difficulty: Once u know the idea of the deque used to
 * keep track of blooming neighbors we havent addressed yet,
 * combining with visited to keep track of neighbors we
 * HAVE addressed, implementation p simple
 * 
 */
import java.util.*;
import java.io.*;

public class Floodfill {
	// direction arrays
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());
		char[][] grid = new char[n][n];
		for(int j=0;j<n;j++) {
			grid[j]=in.readLine().toCharArray();
		}
		boolean[][] visited = new boolean[n][n];
		
		int clusters=0;
		int mx = 0;
		// loop thru and process all of grid[r][c] to find clusters
		for(int r=0;r<n;r++) {
			for(int c=0;c<n;c++) {
				if(visited[r][c] || grid[r][c]=='1')continue;
				
				// new cluster found
				clusters++;
				int size=0; // size of current cluster
				ArrayDeque<Tuple> dq = new ArrayDeque<>();
				//
				dq.add(new Tuple(r,c)); // instead of doing tuple u can also do new int[] {x,y}
				while(!dq.isEmpty()) {
					Tuple curr=dq.poll(); // check out neighbors of this point
					for(int j=0;j<4;j++) { // attempt visiting grid[cr+dr[j]][cc+dc[j]]
						if(curr.r+dr[j]>=0&&curr.r+dr[j]<n&&curr.c+dc[j]>=0
								&&curr.c+dc[j]<n&&grid[curr.r+dr[j]][curr.c+dc[j]]!='1') {
							if(!visited[curr.r+dr[j]][curr.c+dc[j]]) {
								visited[curr.r+dr[j]][curr.c+dc[j]]=true;
								size++;
								dq.add(new Tuple(curr.r+dr[j],curr.c+dc[j]));
							}
						}
					}
				}
				//
				mx=Math.max(mx, size);
			}
		}
		
		out.print("Max size of cluster: "+mx+"\n# of clusters: "+clusters);
		in.close();
		out.close();
	}
}

class Tuple{
	public int r;
	public int c;
	
	Tuple(int x, int y){
		r=x;
		c=y;
	}
}