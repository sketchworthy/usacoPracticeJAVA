package class02x11x2024;
/* Solve Maze _status{COMPLETE or not}_
 * Bessie has a nxm grid maze. Each of the grid cells is either:
 * empty: '.'. Wall: '#'. Good person: 'G'. Bad person: 'B'
 * Determine if there is a way to replace any # of empty cells
 * with walls to allow all Good people to reach (n,m)
 * and prevent all Bad people to reach (n,m). If possible print
 * "Yes" else print "No".
 * 
 * idea: If any bad person is adjacent to a good person,
 * the ans is No. If a good person starts out in a cluster
 * trapped by 'walls' of legit walls and Bad people that
 * does not contain the ending, the ans is No. If Bad
 * person adjacent to (n,m) and >=1 Good person exists
 * then ans is No. 
 * To put walls to block the Bad ppl, we make sure to
 * surround Bs as tightly as possible with # walls
 * 
 * For ex:
 * | G     |
 * | ##  # |
 * |#BB##B#|
 * | ##  #!|
 * 
 * implementation: 
 * 
 * difficulty: putting the walls to block the Bad ppl
 * is hard
 * 
 */
import java.util.*;
import java.io.*;

public class Ex0402 {
	static int w = 1;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};

	public static void main(String[] args) throws Exception {
		// take input
		// BufferedReader in = new BufferedReader(new FileReader("0"+w+".in"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int q = Integer.parseInt(in.readLine());
		
		for(;q>0;q--) {
			boolean WORKS = false;
			
			StringTokenizer st = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(st.nextToken()); // # of rows in maze
			int m = Integer.parseInt(st.nextToken()); // # of cols in maze
			
			char[][] grid = new char[n][m];
			for(int j=0;j<n;j++) {
				grid[j]=in.readLine().toCharArray();
			}
			
			boolean[][] visited = new boolean[n][m];
			// try to put walls tightly around Bad ppl
			// floodfill & detect clusters of B's and put walls around them
			for(int j=0;j<n;j++) {
				if(!WORKS) break;
				for(int k=0;k<m;k++) {
					if(visited[j][k]) continue;
					
					if(grid[j][k]=='B') { // new B cluster discovered
						visited[j][k]=true;
						
						Deque<Integer> Bs = new ArrayDeque<>();
						Bs.add(j*m+k);
						
						while(!Bs.isEmpty()) {
							// check nbors that no adj Good ppl,
							// then add #s
							for(int i=0;i<4;i++) {
								int curr = Bs.poll();
								int r0 = curr/m;
								int c0 = curr%m;
								
								int r = r0+dr[i];
								int c = c0+dc[i];
								if(r>=0 && c>=0 && r<n && c<m &&
										grid[r][c]!='#') {
									if(grid[r][c]=='G') {
										WORKS=false;
										break;
									}
									if(grid[r][c]=='.') {
										grid[r][c]='#';
									}
									if(grid[r][c]=='B') {
										Bs.add(r*m+c);
									}
									visited[r][c]=true;
								}
							}
						}
						if(!WORKS) break; // if a G was adj to a B						
					}
				}
			}
			
			if(grid[n][m]=='#') WORKS=false; // TODO fix so that if
			// no Good ppl it still has WORKS=true;
			
			int[][] cid = new int[m][n]; // gives clusterID of cell
			// floodfill again to  ensure all Good ppl can escape
			// TODO
			
			out.println(WORKS ? "Yes" : "No");
		}

		in.close();
		out.close();
	}
}