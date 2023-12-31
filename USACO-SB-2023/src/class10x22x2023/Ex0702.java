package class10x22x2023;
/* USACO 2016 Silver Build Gates COMPLETE
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=596
 * 
 * idea: The amount of gates FJ needs is equal to the # of clusters
 * forms minus 1. 
 * 
 * Note we can't directly use floodfill to find the # of clusters 
 * by looking at fences as obstacles, bc we might get smthn like this:
 *  _
 * |_|
 *   |_
 * and the 4 square fences may be all adjacent, but they DO have a
 * hole inside them. So instead we scale everything by 2.
 * 
 * difficulty: once i implemented actually started, it was ez.
 * i was a little worried about a 4001x4001 floodfill going over tle,
 * but it ended up being completely fine!
 * 
 */
import java.util.*;
import java.io.*;

public class Ex0702 {
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("gates.in"));
		PrintWriter out = new PrintWriter(new FileWriter("gates.out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());
		boolean[][] grid = new boolean[4*n+1][4*n+1]; // true if fence, scaled by 2
		char[] path = in.readLine().toCharArray();
		grid[2*n][2*n]=true; // origin
		int cr=2*n, cc=2*n;
		for(int j=0;j<n;j++) { // fill grid with path[]
			if(path[j]=='N') {
				cc--;
				grid[cr][cc]=true;
				cc--;
				grid[cr][cc]=true;
			}
			else if(path[j]=='E') {
				cr++;
				grid[cr][cc]=true;
				cr++;
				grid[cr][cc]=true;
			}
			else if(path[j]=='S') {
				cc++;
				grid[cr][cc]=true;
				cc++;
				grid[cr][cc]=true;
			}
			else { // W
				cr--;
				grid[cr][cc]=true;
				cr--;
				grid[cr][cc]=true;
			}
		}
		
		boolean[][] visited = new boolean[4*n+1][4*n+1];
		
		int nclusters=0;
		for(int r=0;r<4*n+1;r++) {
			for(int c=0;c<4*n+1;c++) { // search floodfill for # of clusters
				if(visited[r][c]==true || grid[r][c]==true) {
					continue; // skip if already visited or a fence is at this point
				}
				
				nclusters++;
				visited[r][c]=true;
				ArrayDeque<int[]> dq = new ArrayDeque<>();
				dq.add(new int[] {r,c});
				while(!dq.isEmpty()) {
					int[] head = dq.poll();
					for(int j=0;j<4;j++) {
						int r1 = head[0]+dr[j];
						int c1 = head[1]+dc[j];
						
						if(r1>=0 && c1>=0 && r1<4*n+1 && c1<4*n+1 && !grid[r1][c1] && !visited[r1][c1]) {
							visited[r1][c1]=true;
							dq.add(new int[] {r1,c1});
						}
					}
				}
				
			}
		}
		
		
		out.print(nclusters-1);
		in.close();
		out.close();
	}
}
