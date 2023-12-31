package class10x22x2023;
/* Angry Horse COMPLETE
 * idea: standard floodfill except instead of checking neighbor cells,
 * check L-hop-away cells!
 * 
 * difficulty: idea ez to find. implementation also ez
 * 
 */
import java.util.*;
import java.io.*;

public class HW0701 {
	static int l=0;
	static int[] dr = {2, 2,1, 1, -2,-2,-1,-1};
	static int[] dc = {1,-1,2,-2, -1, 1, 2,-2};
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("1"+l+".in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		char[][] grid = new char[n][m];
		boolean[][] visited = new boolean[n][m];
		for(int j=0;j<n;j++) {
			grid[j]=in.readLine().toCharArray();
		}
		
		int nclusters=0;
		for(int r=0;r<n;r++) {
			for(int c=0;c<m;c++) {
				if(visited[r][c] || grid[r][c]=='C')continue;
				
				visited[r][c]=true;
				nclusters++;
				ArrayDeque<int[]> dq = new ArrayDeque<>();
				dq.push(new int[] {r,c});
				while(!dq.isEmpty()) {
					int[] head = dq.poll();
					for(int j=0;j<8;j++) {
						int r1 = head[0]+dr[j];
						int c1 = head[1]+dc[j];
						
						if(r1>=0 && c1>=0 && r1<n && c1<m && grid[r1][c1]=='G' && !visited[r1][c1]) {
							dq.push(new int[] {r1,c1});
							visited[r1][c1]=true;
						}
					}
				}
			}
		}

		out.print(nclusters);
		
		in.close();
		out.close();
	}
}
