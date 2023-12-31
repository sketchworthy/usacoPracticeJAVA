package class04x08x2023;
/* Given a tree w n nodes, return max distance between any 2 nodes (diameter)
 * 
 * idea: first, make the adjacency matrix (w root 1).
 * to find the diameter of a tree structure,
 * or each parent node, record the longest 2 distances to child nodes
 * the 2 distances must be from distinct branches btw, so that the longest
 * diameter for everything under the parent node is those 2 distances added.
 * then recurse up the tree and keep a running max distance count mb.
 * 
 * difficulty: thought this sol entirely up on my own.
 * once i thought hard for like 2 minutes the idea became clear.
 * implementing was a bit annoying bc its been like a month since i last coded
 * (10th grade AP tests, i took 2ish weeks off all work except studying for
 * those). but it was chill! hopefully this is the best solution
 * 
 * ANOTHER IMPLEMENTATION THATS FASTER & INTENDED
 * IS IN HW1001
 */
import java.util.*;
import java.io.*;

public class HW1001v2 {
	static ArrayList<ArrayList<Integer>> adj;
	static int[][] dist;
	
	public static void main(String[] args) throws Exception {
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader in = new BufferedReader(new FileReader("10.in"));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n=Integer.parseInt(in.readLine()); // # of nodes
		adj = new ArrayList<ArrayList<Integer>>();
		for(int j=0;j<n;j++) {
			adj.add(new ArrayList<Integer>());
		}
		for(int j=0;j<n-1;j++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		// tree has been created.
		// now, make dist[][] to track max 2 distances to 2 diff-branch kids
		dist=new int[n][2]; // dist[x][0] is longest,[x][1] 2nd longest diff branch
		fillDist(0,-1); // fill dist[][] starting from root node
		// now go through and update maxDistance
		int maxD=0; // max distance between any 2 nodes
		for(int j=0;j<n;j++) {
			maxD=Math.max(maxD, dist[j][0]+dist[j][1]);
		}
		out.print(maxD);
		in.close();
		out.close();
	}

	public static void fillDist(int node, int parent) {
		// finds max 2 distances to children of this node
		if(adj.get(node).size()==0) return;// if no children, fill w 0
		if(adj.get(node).size()==1) {// check if no children or only 1 branch
			if(parent!=-1) return; // if not root node, no children
			// else only 1 branch
			fillDist(adj.get(node).get(0),node);
			dist[node][0]=dist[adj.get(node).get(0)][0]+1;
			return;
		}
		if(adj.get(node).size()==2) { // check if only 1 branch or 2 branches
			if(parent!=-1) {// if not root node, 1 branch
				int i=0; // index of parent node in neighbor arrlist
				for(int j=0;j<adj.get(node).size();j++) {
					if(adj.get(node).get(j)==parent) {
						i=j;
						break;
					}
				}
				fillDist(adj.get(node).get(1-i),node);
				dist[node][0]=dist[adj.get(node).get(1-i)][0]+1;
				return;
			}
		}
		// 2 or more branches
			int i=-1; // index of parent node in neighbor arrlist
			for(int j=0;j<adj.get(node).size();j++) {
				if(adj.get(node).get(j)==parent) {
					i=j;
					break;
				}
			}
			// go thru all children, fillDist
			PriorityQueue<Integer> pq = new PriorityQueue<>
					((x1,y1)->(y1-x1)); // sort in descending order child lens
			for(int j=0;j<adj.get(node).size();j++) {
				if(j==i)continue;
				fillDist(adj.get(node).get(j),node);
				pq.add(dist[adj.get(node).get(j)][0]+1);
			}
			// now fill dist[node][0&1] by returning max 2 children 
		dist[node][0]=pq.poll();
		dist[node][1]=pq.poll();
	}
}
