package class02x11x2024;
/* Labyrinth _status{COMPLETE or not}_
 * Given a labyrinth with start cell A and end cell B,
 * and '.' cells you can walk through, only being able
 * to move NESW, print "YES" if you can get from A to B
 * and "NO" otherwise. if a path exists, print one
 * of the shortest possible paths
 * 
 * idea: to check if yes or no, straightforward floodfill.
 * to find smallest dist path between A and B do more
 * floodfill using a 'distFromSrc' arr instead of a
 * clusterID arr. Then to find a shortest path,
 * we can just go backwards from B, looking to
 * go thru each path that makes it decrease len by 1
 * 
 * implementation: 
 * 
 * difficulty:
 * 
 */
import java.util.*;
import java.io.*;

public class Ex0401 {
	static int w = 1;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};

	public static void main(String[] args) throws Exception {
		// take input
		// BufferedReader in = new BufferedReader(new FileReader("0"+w+".in"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // height of grid
		int m = Integer.parseInt(st.nextToken()); // width of grid
		char[][] grid = new char[n][m];
		for(int j=0;j<n;j++) {
			grid[j]=in.readLine().toCharArray();
		}
		
		int[][] cid = new int[n][m]; // gives cluster id of cell grid[n][m].
		// cid[x][y] is 0 if grid[x][y] not visited
		int numClusters=0;
		
		int srcR=-1;
		int srcC=-1;
		int destR=-1;
		int destC=-1;
		
		// split grid into clusters
		for(int j=0;j<n;j++) {
			for(int k=0;k<m;k++) {
				if(cid[j][k]!=0 || grid[j][k]=='#') continue;
				
				// new cluster discovered. evaluate new cluster of cell grid[j][k]
				numClusters++;
				Deque<Integer> nbors = new ArrayDeque<>();
				nbors.add(j*m+k);
				cid[j][k]=numClusters;
				
				while(!nbors.isEmpty()) {
					// take off top of nbors, process it, and add its nbors in
					int currCell = nbors.poll();
					int r = currCell/m;
					int c = currCell%m;
					
					for(int i=0;i<4;i++) {
						int r1 = r+dr[i];
						int c1 = c+dc[i];
						if(r1>=0 && c1>=0 && r1<n && c1<m &&
								grid[r1][c1]!='#') { // if valid neighbor
							nbors.add(r1*m+c1);
							cid[r1][c1]=numClusters;
						}
					}
				}
				
			}
		}
		
		

		out.println();

		in.close();
		out.close();
	}
}