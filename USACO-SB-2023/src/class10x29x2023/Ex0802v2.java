package class10x29x2023;
/* USACO 2021 Dec Silver: Connecting Two Barns NOT COMPLETE
 * http://usaco.org/index.php?page=viewproblem2&cpid=1159
 * Given a graph with N nodes and M edges and diff weights to every
 * edge depending on the nodes the edge connects, find the min weight
 * sum of up to 2 new paths that could be built so that barn 0 connects
 * to barn N-1. Building an edge between i and j costs (i-j)^2
 * 
 * idea: 3 cases:
 * 0. cluster containing node 1 and cluster containing node N already connected
 * (path distance: 0)
 * 1. draw 1 path between cluster containing node 1 and N-cluster
 * (path distance: dist(1,N))
 * 2. draw 2 paths to connect a cluster between 1-cluster and N-cluster
 * (path distance: dist(1,x) + dist(x,N))
 * 
 * implementation:
 * first, detect clusters. then, check for case 0.
 * then, check for case 1, then 2.
 * 
 * difficulty: 
 * 
 */
import java.util.*;
import java.io.*;

public class Ex0802v2 {
	public static int n;
	public static void main(String[] args) throws Exception {
		// read input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int t = Integer.parseInt(in.readLine()); // # of test cases
		for(;t>0;t--) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			n = Integer.parseInt(st.nextToken()); // # of fields/nodes
			int m = Integer.parseInt(st.nextToken()); // # of edges
			ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
			for(int j=0;j<n;j++) adj.add(new ArrayList<Integer>());
			
			for(int j=0;j<m;j++) {
				st = new StringTokenizer(in.readLine());
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				adj.get(a).add(b);
				adj.get(b).add(a);
			}

			// detect all clusters
			int[] clusterID = new int[n]; // clusterID[x]: index of cluster x is in
			Arrays.fill(clusterID, -1);
			ArrayList<ArrayList<Integer>> clusters=new ArrayList<>();
			// use floodfill to find and fill all clusters
			int size=-1;
			for(int node=0;node<n;node++) {
				if(clusterID[node]!=-1)continue; // if node is already in a cluster
				
				size++;
				clusterID[node]=size;
				clusters.add(new ArrayList<Integer>());
				clusters.get(size).add(node); // node is part of new cluster
				
				// go thru all neighbors and add them to cluster
				int x=0; // index of curr node we evaluating within clusters.get(size)
				while(x<clusters.get(size).size()) { 
					// add neighbors of clusters[size][x] to the cluster
					for(int nbor:adj.get(clusters.get(size).get(x))) {
						if(clusterID[nbor]==-1) {
							clusterID[nbor]=size;
							clusters.get(size).add(nbor);
						}
					}
					x++;
				}
				Collections.sort(clusters.get(size)); // sort for cases 1 & 2 later
			}
			
			if(clusterID[0]==clusterID[n-1]) { // case 0 satisfied
				out.println(0);
				continue;
			}
						
			// case 1: make 1 path
			int d = dist(clusters,0,clusterID[n-1]);
			int minAns = d*d;
			
			// case 2: make 2 paths
			// loop thru every possible bridge cluster
			for(int x=0;x<clusters.size();x++) {
				int d1 = dist(clusters,0,clusterID[x]);
				int d2 = dist(clusters,clusterID[x],clusterID[n-1]);
				minAns = Math.min(minAns, d2*d2+d1*d1);
			}
			
			out.println(minAns);
		}
		in.close();
		out.close();
	}
	
	public static int dist(ArrayList<ArrayList<Integer>> clusters, int ci1, int ci2) {
		// given the index in clusters[][] of your 1st cluster 
		// and your 2nd cluster, return the minimum distance 
		// between any 2 nodes in the 2 clusters
		int minDist=n;
		
		int curr=0; // current index of node in ci2 that is >= than ci1's node
		for(int node:clusters.get(ci1)) {
			// iterate curr
			while(curr<clusters.get(ci2).size()-1 && clusters.get(ci2).get(curr)<node) {
				curr++;
			}
			
			// update minDist
			minDist = Math.min(minDist, clusters.get(ci2).get(curr)-node);
			if(curr>0) minDist = Math.min(minDist, clusters.get(ci2).get(curr-1)-node);
		}
		
		return minDist;
	}
}
