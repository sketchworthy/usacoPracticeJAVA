package class04x15x2023;
/* USACO 2019 Silver Feb P3: Revegetate
http://usaco.org/index.php?page=viewproblem2&cpid=920

 * idea: cows are in islands of graphs. for each island, there are 2 choices.
 * so problem's answer is w^(# of islands). if it is impossible for an island
 * to be made with the conditions, output 0
 * 
 * difficulty: dr zhang helped tutor me thru this problem & also gave me the og
 *  idea so it wasnt too hard. straightforward once i got used to it
 */
import java.util.*;
import java.io.*;

public class Ex1102 {
	
	static boolean[] visited;
	static int[] grassType;
	static ArrayList<ArrayList<Integer>> adj1;
	static ArrayList<ArrayList<Integer>> adj2;
	
	public static void main(String[] args) throws Exception {
//		BufferedReader in = new BufferedReader(new FileReader("revegetate.in"));
//		PrintWriter out = new PrintWriter(new FileWriter("revegetate.out"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of pastures
		int m = Integer.parseInt(st.nextToken()); // # of cows/diet restrictions
		adj1 = new ArrayList<>(); // neighbors w same grass
		adj2 = new ArrayList<>(); // neighbors w diff grass
		visited=new boolean[n];
		grassType=new int[n];
		for(int j=0;j<n;j++) {
			adj1.add(new ArrayList<>());
			adj2.add(new ArrayList<>());
		}
		for(int j=0;j<m;j++) {
			st = new StringTokenizer(in.readLine());
			String c=st.nextToken();
			int n1=Integer.parseInt(st.nextToken())-1; // node 1
			int n2=Integer.parseInt(st.nextToken())-1; // node 2
			if(c.equals("S")) {
				adj1.get(n1).add(n2);
				adj1.get(n2).add(n1);
			}
			else { // c.equals("D")
				adj2.get(n1).add(n2);
				adj2.get(n2).add(n1);
			}
		}
		boolean works=true;
		int ans=0;
		// traverse & fill visited
		for(int j=0;j<n;j++) { // for each block
			if(visited[j])continue;
			if(!fillGrass(j,0)) {
				works=false;
				break;
			}
			else {
				ans++;
			}
		}
		
		if(!works)out.print(0);
		else {
			out.print(1);
			for(int j=0;j<ans;j++) {
				out.print(0);
			}
		}
		in.close();
		out.close();
	}
	
	static boolean fillGrass(int node, int grass) { // fill this block with visited[]
		// & check that grass types work
		if(visited[node]) {
			// check if grasstype works
			if(grass!=grassType[node])return false;
			return true;
		}
		visited[node]=true;
		grassType[node]=grass;
		for(int neighbor:adj1.get(node)) {
			boolean x = fillGrass(neighbor,grass);
			if(!x)return false;
		}
		for(int neighbor:adj2.get(node)) {
			boolean x = fillGrass(neighbor,3-grass);
			if(!x)return false;
		}
		return true;
	}
}
