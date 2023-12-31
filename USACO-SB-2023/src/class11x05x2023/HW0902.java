package class11x05x2023;
/* USACO 2019 Dec Silver: Milk Visits _status{COMPLETE or not}_
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=968
 * DOING IN CPP
 * 
 * idea: straightforward dfs.
 *  you have a tree. for each friend, try to find the path to
 * the
 * 
 *  runs out of time on almost all test cases
 * 
 * difficulty:
 * 
 */
import java.util.*;
import java.io.*;

public class HW0902 {
	public static ArrayList<ArrayList<Integer>> adj;
	public static char[] farmCows; // farmCows[x] gives identity of cow in xth farm
	public static char goal;
	public static int src;
	public static int dest;
	public static boolean goalMet;
	public static void main(String[] args) throws Exception {
//		BufferedReader in = new BufferedReader(new FileReader("milkvisits.in"));
//		PrintWriter out = new PrintWriter(new FileWriter("milkvisits.out"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of nodes
		int m = Integer.parseInt(st.nextToken()); // # of friends
		adj = new ArrayList<>();
		for(int j=0;j<n;j++)adj.add(new ArrayList<Integer>());
		farmCows = in.readLine().toCharArray();
		int[] ans = new int[n]; // ans[x] is 1 if friend will be happy, 0 otherwise
		for(int j=1;j<n;j++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		
		for(int j=0;j<m;j++) { // for visit of friend
			st = new StringTokenizer(in.readLine());
			src = Integer.parseInt(st.nextToken())-1; // root node
			dest = Integer.parseInt(st.nextToken())-1; // destination node
			goal = st.nextToken().charAt(0); // H or G
			goalMet=false;
			visitNeighbors(src,-1);
			
			out.print(goalMet ? 1 : 0);
		}
		
		in.close();
		out.close();
	}
	
	public static boolean visitNeighbors(int n, int p) { 
		// returns true if dest is a child node somewhere down the line
		if(goalMet) return false; // goal already met somewhere else
		
		if(n==dest) {
			// reevaluate
			if(farmCows[n]==goal) {
				goalMet=true;
				return false;
			}
			return true;
		}
		
		for(int nbor:adj.get(n)) { // if not dest, seek dest
			if(nbor==p)continue;
			
			if(visitNeighbors(nbor,n)) { // if u find dest
				if(farmCows[n]==goal)
					{
						goalMet=true; 
						return false;
					}
				return true; 
			}
		}
		
		return false;
	}
}
