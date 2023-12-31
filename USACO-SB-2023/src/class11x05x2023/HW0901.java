package class11x05x2023;
/* USACO 2016 March Silver: Closing the Farm COMPLETE
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=644
 * 
 * idea: straightforward dfs
 * 
 * difficulty: implementation and idea ez
 * 
 */
import java.util.*;
import java.io.*;

public class HW0901 {
	public static ArrayList<ArrayList<Integer>> adj;
	public static boolean[] closed; // closed[x] true if barn x closed
	public static boolean[] visited;
	public static int clusterSize;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("closing.in"));
		PrintWriter out = new PrintWriter(new FileWriter("closing.out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of nodes
		int m = Integer.parseInt(st.nextToken()); // # of edges
		adj = new ArrayList<>();
		for(int j=0;j<n;j++)adj.add(new ArrayList<Integer>());
		for(int j=0;j<m;j++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		closed = new boolean[n];
		// when no barns are closed
		visited= new boolean[n];
		// n nodes left
		clusterSize=0;
		visitNeighbors(0,-1);
		out.println( clusterSize==n ? "YES" : "NO"); // if cluster size is n print yes
		
		for(int c=1;c<n-1;c++) { // barns being closed 1 by 1
			Arrays.fill(visited, false);
			closed[Integer.parseInt(in.readLine())-1]=true;
			// n-c nodes left
			// start from a non-closed node and check if all # of farms connected
			clusterSize=0;
			// find first node that isn't closed
			for(int j=0;j<n;j++) {
				if(!closed[j]) {
					visitNeighbors(j,-1);
					break;
				}
			}
			out.println(clusterSize==n-c ? "YES" : "NO");
		}
		
		out.println("YES");
		in.close();
		out.close();
	}
	
	public static void visitNeighbors(int n, int p) { // n is node being visited, p parent
		if(visited[n] || closed[n])return; // if n invalid to be visited
		visited[n]=true;
		clusterSize++;
		
		for(int nbor:adj.get(n)) {
			if(nbor==p)continue;
			
			visitNeighbors(nbor,n);
		}
		return;
	}
}
