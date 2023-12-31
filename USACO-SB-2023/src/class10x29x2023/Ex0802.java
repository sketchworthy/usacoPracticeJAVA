package class10x29x2023;
/* USACO 2021 Dec Silver: Connecting Two Barns NOT COMPLETE
 * IN CPP
 * http://usaco.org/index.php?page=viewproblem2&cpid=1159
 * Given a graph with N nodes and M edges and diff weights to every
 * edge depending on the nodes the edge connects, find the min weight
 * sum of up to 2 new paths that could be built so that barn 0 connects
 * to barn N-1. Building an edge between i and j costs (i-j)^2
 * 
 * idea: We CAN'T loop thru all possible 2 new paths that could be built
 * and test if they will connect all the barns bc that wastes resources.
 * We have 3 cases: 1 and N are already connected, we build a path from
 * node i connected to 1 and node j connected to N, and we build 
 * 2 paths from a node connected to 1-cluster to a middle cluster to
 * the N-cluster. 
 * To do this, we first detect all the clusters
 * 
 * 
 * difficulty: i got stuck at several points in the implementation unsure
 * of how to proceed. this would be a good problem to come back to 
 * later to see if i then understand how i'd approach this type of
 * problem. also to speed up & clarify code, make repeated code into
 * functions--this can be useful for debugging
 * 
 */
import java.util.*;
import java.io.*;

public class Ex0802 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int t = Integer.parseInt(in.readLine()); // # of test cases
		for(;t>0;t--) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(st.nextToken()); // # of fields/nodes
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
			for(int node=0;node<n;node++) {
				if(clusterID[node]!=-1)continue; // if node is already in a cluster
				
				int size = clusters.size();
				clusters.add(new ArrayList<Integer>());
				clusters.get(size).add(node); // node is part of new cluster
				clusterID[node]=size;
				
				// go thru all neighbors and add them to cluster
				ArrayDeque<Integer> nbors = new ArrayDeque<>();
				for(int nbor:adj.get(node)) {
					nbors.add(nbor);
					clusterID[nbor]=size;
					clusters.get(size).add(nbor);
				}
				
				while(!nbors.isEmpty()) {
					int nbor = nbors.poll();
					
					// add neighbors of nbor to nbors
					for(int nbornbor:adj.get(nbor)) { // TODO
						if(clusterID[nbornbor]==-1) {
							clusterID[nbornbor]=size;
							nbors.add(nbornbor);
							clusters.get(size).add(nbornbor);
						}
					}
				}
			}
			
			if(clusterID[0]==clusterID[n-1]) { // case 0 satisfied
				out.println(0);
				continue;
			}
			
			for(ArrayList<Integer> list:clusters) {
				Collections.sort(list);
			}
			// case 1: find min weight of 1-path that can be built to
			// bridge node 1 to node n
			// find closest node to 1 in node n's cluster
			// loop through each of the nodes in 1's cluster and
			// iterate for their closest counterpart in n-cluster
			
			ArrayList<Integer> nCluster = clusters.get(clusterID[n-1]);
			// min path weight that will succeed
			int minAns=nCluster.get(0)*nCluster.get(0);
			
			int curr = 0; // current smallest index of nCluster >= node
			for(int node:clusters.get(0)) {
				// iterate for the closest node to node 1 in cluster n
				while(nCluster.get(curr)<node && curr<nCluster.size()-1) {
					curr++;
				}

				minAns=Math.min(minAns, (nCluster.get(curr)-node)*(nCluster.get(curr)-node));
				if(curr>0)
					minAns=Math.min(minAns, (nCluster.get(curr-1)-node)*(nCluster.get(curr-1)-1-node));
			}
			
			// case 2: find min sum of weights of 2 paths that can be
			// built to bridge node 1 to node n. use the min path lens
			// identified in case 1			
			// idea: loop thru every node not yet connected to 1cluster
			// and imagine building a path from closest node in 1cluster to
			// that node. then, see the min cost of building another path
			// from the NEW 1cluster to ncluster!
			
			curr=0; // current largest index <= node in 1cluster we're evaluating for bridge
			for(int newNode=0;newNode<n;newNode++) {
				if(clusterID[newNode]==0)continue;
				
				while(curr<clusters.get(0).size()-1 && clusters.get(0).get(curr+1)<newNode) {
					curr++;
				}
				
				// evaluate curr and curr+1 as bridges
				// sanity check to make sure they're even smaller
				int contribution = 
					(clusters.get(0).get(curr)-newNode)*(clusters.get(0).get(curr)-newNode);
				if(curr<clusters.get(0).size()-1)
					contribution = Math.min(minAns, 
					(clusters.get(0).get(curr+1)-newNode)*(clusters.get(0).get(curr+1)-newNode));
				if(minAns<contribution) {
					continue;
				}
				
				// find min cost of connecting cluster newNode to nCluster
				ArrayList<Integer> newCluster = clusters.get(clusterID[newNode]);
				curr=0; // index of smallest node in nCluster >= node in newCluster
			    for(int nowNode:newCluster) {
			    	// iterate curr until curr is closest node to nowNode in nCluster
			    	while(nCluster.get(curr)<nowNode && curr<nCluster.size()-1) {
			    		curr++;
			    	}
			    	
			    	minAns=Math.min(minAns, contribution+
			    			(nowNode-nCluster.get(curr))*(nowNode-nCluster.get(curr)));
				    if(curr>0) {
				    	minAns=Math.min(minAns, contribution+
				     	(nowNode-nCluster.get(curr-1))*(nowNode-nCluster.get(curr-1)));
					      
			    	}
			    	
			    }
				
			}
			
			out.println(minAns);
		}
		in.close();
		out.close();
	}
}
