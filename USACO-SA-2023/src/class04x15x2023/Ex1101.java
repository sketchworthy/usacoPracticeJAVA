package class04x15x2023;
/* USACO Silver Jan 2019 p1: Grass Planting
 * Find min number of grass types needed if adjacent nodes can't have the
 * same grass types. 
 * 
 * idea: basically, find node w max amount of neighbors
 *
 * difficulty: rlly ez
 * 
 */
import java.util.*;
import java.io.*;

public class Ex1101 {
	static ArrayList<ArrayList<Integer>> adj;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("planting.in"));
		PrintWriter out = new PrintWriter(new FileWriter("planting.out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine()); // # of fields
		adj=new ArrayList<>();
		for(int j=0;j<n;j++) adj.add(new ArrayList<Integer>());
		for(int j=0;j<n-1;j++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		int max=0;
		for(int j=0;j<n;j++) {
			max=Math.max(max, adj.get(j).size());
		}
		out.print(max+1);
		out.close();
	}
}
