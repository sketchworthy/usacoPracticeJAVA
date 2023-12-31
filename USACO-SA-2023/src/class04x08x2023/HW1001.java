package class04x08x2023;
/* Given a tree w n nodes, return max distance between any 2 nodes (diameter)
 * 
 * idea: to find the diameter of a tree structure, first find the max
 * depth node, then turn the tree around so that THAT node is the root and
 * find the max depth of the new node. basically, find a furthest 'end'
 * and find furthest distance from that end to another node
 * 
 * difficulty: only found the idea after seeing the class solution. once
 *  i understood tho it was SO EZ
 * 
 * this method is intended and inspired by class solution.
 * ANOTHER IMPLEMENTATION THAT I CAME UP W ON MY OWN IS IN HW1001v2
 */
import java.util.*;
import java.io.*;
public class HW1001 {
	static ArrayList<ArrayList<Integer>> adj;
	static int depth;
	static int deepN; // max depth node
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader in = new BufferedReader(new FileReader("10.in"));
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
		
		// now, find the max depth node
		depth=0;
		visit(0,-1,0);
		out.println(deepN);
		depth=0;
		// now find max depth node if we're looking FROM the prev max depth node
		visit(deepN,-1,0);
		out.print(depth);
		in.close();
		out.close();
	}
	public static void visit(int n, int p, int d) { //n=node,p=parent,d=depth
		
		for(int c:adj.get(n)) {
			if(c==p)continue;
			visit(c,n,d+1);
		}
		if(depth<d) {
			depth=d;
			deepN=n;
		}
	}
	
}