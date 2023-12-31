package class04x08x2023;
/* USACO 2020 Dec Silver Problem 1 
http://usaco.org/index.php?page=viewproblem2&cpid=1062
 * 
 * idea: b/c u dont know when given an edge which node is the parent or child,
 * have an adjacency matrix instead of a parents<> or children<> matrix.
 * Note that b/c there are N-1 roads, the structure is a tree.
 * Because of the tree structure, the # of min days is always the same no
 * matter where u start from first, as long as from each parent node u
 * first generate enough cows for all the node's children, then distribute 
 * the cows. so u dont need a days[] int array for every node, u can
 * just keep a running days int variable.
 * 
 * difficulty: 
 */
import java.util.*;
import java.io.*;

public class Ex1002 {
	
	public static ArrayList<ArrayList<Integer>> adj; // all the nodes adjacent to node
	public static int days;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());
		adj=new ArrayList<>();
		days=0;
		for(int j=0;j<n;j++) adj.add(new ArrayList<Integer>());
		for(int j=0;j<n-1;j++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		for(int j=0;j<n;j++) {
			Collections.sort(adj.get(j)); // sort in ASCENDING order
		}
		addDays(0,-1);
		out.print(days);
		in.close();
		out.close();
	}
	
	static void addDays(int node, int p) { // given node and 'parent' (node origin)
//		days[node]+=days[p];
		int size=adj.get(node).size();
		if(p!=-1)size--;
		days+=exp2(size)+size;
		for(int c:adj.get(node)) { // go thru in DEScEnDING order
			if(c!=p) {
				addDays(c,node);
			}
		}
	}
	
	static int exp2(int n) { // returns smallest exponent x so that 2^x>n
		int num=1;
		int pow=0;
		while(num<=n) {
			num*=2;
			pow++;
		}
		return pow;
	}
}
