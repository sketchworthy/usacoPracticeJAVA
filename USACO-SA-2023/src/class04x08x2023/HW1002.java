package class04x08x2023;
/* USACO Silver Dec 2018 Mootube
 * 
 * idea: keep an arraylist of arraylists of 2-ele arraylists to keep score 
 * of all relevance variables. basically, each node has
 * an arraylist of <neighbors,relevanceForThatNeighbor>s. 
 * Then, for each query, simulate. you're looking for all the nodes u can visit
 * (every node) so that their relevance will >= this query's k. so once you
 * hit a node whose relevance val makes you dip >=k, all the nodes u can
 * visit from there (w/o ever doubling back to ur parent node) will be gud
 * edit i made to save time after watching class vid: if an edge is <relevant
 * case, do NOT traverse over it, all future nodes downstream r a waste of time
 * 
 * an alt idea is that instead of making adj a 3d arraylist of ints,
 * make it an 2d arraylist of a homemade class called Edge
 * 
 * difficulty: p ez to implement. works, but times out on 2 test cases
 * 
 */
import java.util.*;
import java.io.*;

public class HW1002 {
	static int[] relevance; // tracks relevance of all nodes to root
	static ArrayList<ArrayList<ArrayList<Integer>>> adj;//adj[x]=neighbors, adj[x][y]=neighbor and relevance, adj[x][y][0]=neighbor
	static int relevants;
	static int k;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
//		BufferedReader in = new BufferedReader(new FileReader("mootube.in"));
//		PrintWriter out = new PrintWriter(new FileWriter("mootube.out"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of nodes
		int q = Integer.parseInt(st.nextToken()); // # of queries
		adj = new ArrayList<>();
		for(int j=0;j<n;j++) {
			adj.add(new ArrayList<ArrayList<Integer>>());
		}
		for(int j=0;j<n-1;j++) { // fill adj
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int r = Integer.parseInt(st.nextToken());
			ArrayList<Integer> info = new ArrayList<>();
			info.add(a); info.add(r);
			adj.get(b).add(info);
			ArrayList<Integer> info2 = new ArrayList<>();
			info2.add(b); info2.add(r);
			adj.get(a).add(info2);
		} // now that adj has been constructed, look at queries
		for(;q>0;q--) {
			st = new StringTokenizer(in.readLine());
			k = Integer.parseInt(st.nextToken()); // min relevance
			int v = Integer.parseInt(st.nextToken())-1; // target video
			// find all vids whos relevance to v is >= k
			relevants=0;
			traverse(v,-1,(int)1e9+1);
			out.println(relevants-1); // -1 bc the initial relevance is infinity and doesnt count
		}
		in.close();
		out.close();
	}
	public static void traverse(int n,int p, int r) { // node,parent,relevance
		if(r>=k)relevants++;
		for(ArrayList<Integer> neighborInfo:adj.get(n)) {
			if(neighborInfo.get(0)==p)continue;
			// if this is a non-parent neighbor, visit em
			if(neighborInfo.get(1)>=k||r>=k) {
				traverse(neighborInfo.get(0),n,Math.min(neighborInfo.get(1), r));
			}
		}
	}
}
