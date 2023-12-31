package class10x29x2023;
/* USACO 2019 March Silver Fence Plan COMPLETE
 * http://usaco.org/index.php?page=viewproblem2&cpid=944
 * Given a graph formed of separate networks, find the network with
 * the min 2(maxX-minX+maxY-minY) [perimeter]
 * 
 * idea: Use floodfill, except on a graph instead of neighboring 4 on a grid
 * (aka, BFS).
 * 
 * difficulty: actually p ez! noice :D
 * 
 */
import java.util.*;
import java.io.*;

public class Ex0801 {
	static ArrayList<ArrayList<Integer>> adj; // adjacency list of graph
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("fenceplan.in"));
		PrintWriter out = new PrintWriter(new FileWriter("fenceplan.out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of nodes
		int m = Integer.parseInt(st.nextToken()); // # of edges
		int[][] coords = new int[n][2]; // gives coords of each cow/node
		for(int j=0;j<n;j++) {
			st = new StringTokenizer(in.readLine());
			coords[j][0]=Integer.parseInt(st.nextToken());
			coords[j][1]=Integer.parseInt(st.nextToken());
		}
		adj = new ArrayList<>();
		for(int j=0;j<n;j++) {
			adj.add(new ArrayList<Integer>());
		}
		for(int j=0;j<m;j++) {
			st = new StringTokenizer(in.readLine());
			int a=Integer.parseInt(st.nextToken())-1;
			int b=Integer.parseInt(st.nextToken())-1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		boolean[] visited = new boolean[n];
		// do bfs on graph adj
		int minPerim=Integer.MAX_VALUE; 
		for(int a=0;a<n;a++) { 
			// for node a in graph, go thru their neighbors and figure out network size
			if(visited[a])continue;
			
			// initialize
			int minX, maxX, minY, maxY; // to figure out perimeter
			visited[a]=true;
			minX=coords[a][0];
			maxX=coords[a][0];
			minY=coords[a][1];
			maxY=coords[a][1];
			
			// create deque and input 1st starting point
			ArrayDeque<Integer> dq = new ArrayDeque<>();
			dq.add(a);
			
			while(!dq.isEmpty()) { // loop thru neighbors
				int a1 = dq.poll();
				
				for(int b:adj.get(a1)) {
					if(visited[b]) continue;
					
					dq.add(b);
					visited[b]=true;
					
					// update min&max coords

					minX=Math.min(coords[b][0],minX);
					maxX=Math.max(coords[b][0],maxX);
					minY=Math.min(coords[b][1],minY);
					maxY=Math.max(coords[b][1],maxY);
				}
			}
						
			// update total min perim
			if((maxX-minX+maxY-minY)*2<minPerim)minPerim=(maxX-minX+maxY-minY)*2;
		}
		out.print(minPerim);
		
		in.close();
		out.close();
	}
}
