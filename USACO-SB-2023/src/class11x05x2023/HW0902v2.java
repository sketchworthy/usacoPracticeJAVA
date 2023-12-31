package class11x05x2023;
/* USACO 2019 Dec Silver: Milk Visits _status{COMPLETE or not}_
 * DOING IN CPP
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=968
 * 
 * idea: you have a tree, so every friend's pathing is unique/predetermined.
 * every edge either has same-cow endpoints or oppo-cow endpoints.
 * each friend will only be UNHAPPY if all the edges they traverse are
 * same-cow endpoints and the type of cow is the one the friend does NOT
 * prefer.
 * My initial idea of how to proceed was to floodfill and find all same-cow
 * cycles. However this problem is supposed to be done with dfs. Also,
 * flood fill would take too long, since N <= 10^5
 * Note with dfs, we can def find the path from a friend's start node to
 * their end node. if we also keep track of the same-ness of the pathing
 * so far, then we good! The function can be a boolean function passing
 * true if so far all edges have been the same and false if so far
 * there have been both cows present. Then once we hit upon the desired
 * end destination, we can just take a look at if the boolean is true or not.
 * However this is still probs too slow. How might we speed up the 
 * calculations?
 * 
 * 
 * difficulty:
 * 
 */
import java.util.*;
import java.io.*;

public class HW0902v2 {
	static ArrayList<ArrayList<Integer>> adj;
	static char[] farms;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of farms/nodes
		int m = Integer.parseInt(st.nextToken()); // # of friends/test cases
		farms = in.readLine().toCharArray();
		adj = new ArrayList<>();
		for(int j=0;j<n;j++) adj.add(new ArrayList<>());
		for(int j=1;j<n;j++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			adj.get(x).add(y);
			adj.get(y).add(x);
		}
		for(int j=0;j<m;j++) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			char favCow = st.nextToken().charAt(0);
			
			// dfs for end, from start
			
		}
		
		in.close();
		out.close();
	}
	
	static boolean dfs(int node, int parent) {
		
	}
}
