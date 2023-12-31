package class04x15x2023;
/* given q queries, find # of queries where the given graph CANT reach any node
 * if some 1 connection is severed
 * 
 * idea: in order for it to be possible that everyone can stay in contact,
 * every node needs 2 or more connections. so just make an adj matrix and
 * check each neighbors size
 * 
 * difficulty: very ez once i realized the key mathematical idea of every
 * successful graph needing each node to have 2+ neighbors
 */
import java.util.*;
import java.io.*;

public class HW1101 {
	
	static ArrayList<ArrayList<Integer>> adj; // adjacency matrix
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("10.in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int ans=0;
		for(int q=Integer.parseInt(in.readLine());q>0;q--) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int p = Integer.parseInt(st.nextToken()); // ppl james is inviting including himself
			int c = Integer.parseInt(st.nextToken()); // connections
			adj=new ArrayList<>();
			for(int i=0;i<p;i++) adj.add(new ArrayList<>());
			for(int j=0;j<c;j++) {
				st = new StringTokenizer(in.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adj.get(a).add(b);
				adj.get(b).add(a);
			}
			
			boolean works=true;
			for(int i=0;i<p;i++) {
				if(adj.get(i).size()<2) {
					works=false;
					break;
				}
			}
			if(!works)ans++;
			
		}
		out.print(ans);
		in.close();
		out.close();
	}
}
