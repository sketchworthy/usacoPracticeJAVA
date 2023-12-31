package class10x22x2023;
/* USACO 2019 Silver Icy Perimeter COMPLETE
 * http://usaco.org/index.php?page=viewproblem2&cpid=895
 * 
 * idea: pretty straightforward floodfill. in order to check perim,
 * just try probing every neighboring cell. if it isn't visited already
 * but still fails then you have a border side u can add!
 * 
 * difficulty: implementation p ez
 * 
 */
import java.util.*;
import java.io.*;



public class Ex0701 {
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("perimeter.in"));
		PrintWriter out = new PrintWriter(new FileWriter("perimeter.out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine()); // # of lines to read / rows in matrix
		char[][] grid = new char[n][n];
		for(int j=0;j<n;j++) {
			grid[j]=in.readLine().toCharArray();
		}
		boolean[][] visited = new boolean[n][n];
		int maxSize=0;
		int mPerim=0; // perimeter of max size
		
		for(int j=0;j<n;j++) {
			for(int k=0;k<n;k++) {
				if(visited[j][k] || grid[j][k]=='.')continue;
				
				// process new cluster
				int size=1;
				int perim=0;
				ArrayDeque<int[]> dq = new ArrayDeque<>();
				dq.add(new int[] {j,k});
				visited[j][k]=true;
				while(!dq.isEmpty()) {
					int[] head = dq.poll();
					for(int i=0;i<4;i++) {
						int r1 = head[0]+dr[i];
						int c1 = head[1]+dc[i];
						
						// probe and check grid[r1][c1] neighbor to existing cell
						if(r1>=0 && c1>=0 && r1<n && c1<n) { // if r1,c1 exists
							if(grid[r1][c1]=='.') // border next to a '.'
								perim++;
							else if(grid[r1][c1]=='#' && !visited[r1][c1]) {
								// 
								visited[r1][c1]=true;
								dq.add(new int[] {r1,c1}); // r1,c1 in cluster
								size++;
							}
						}
						else { // on grid border
							if(r1==-1)perim++;
							else {
								if(r1==n)perim++;
							}
							if(c1==-1)perim++;
							else {
								if(c1==n)perim++;
							}
						}
					}
					
				}
				if(size>maxSize || (size==maxSize && perim<mPerim)) {
					maxSize=size;
					mPerim=perim;
				}
			}
		}
		out.print(maxSize+" "+mPerim);
		
		in.close();
		out.close();
	}
}
