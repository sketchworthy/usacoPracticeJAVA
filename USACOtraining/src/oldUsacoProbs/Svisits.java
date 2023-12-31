package oldUsacoProbs;

/* USACO 2022 March Silver P1 TODO debug and optimize program
 * each of N cows is going to visit a buddy. find max score of a visiting
 * order where each successful visit has a score. 
 * A-visiting-B visit is only successful if B has not visited their cow yet
 * 
 * idea: use a directed graph. keep an arr of the scores of each possible 
 * route and update it to be the max as you go. loop thru each of the nodes
 * as an 'end' node, going backwards to the ones who can visit the parent node,
 * etc, etc, so that you can get thru all possibilities. TODO address case w
 * multiple island groups
 * 
 * difficulty: 
 */
import java.util.*;
import java.io.*;

public class Svisits {
	public static ArrayList<ArrayList<Child>> adj; 
	// list of each node's list of children that may visit that node
//	public static long[] scores; // each starting node route's max-score
	public static long maxScore;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		
		long n = Long.parseLong(in.readLine());
		adj = new ArrayList<ArrayList<Child>>();
		for(int j=0;j<n;j++) {
			adj.add(new ArrayList<Child>());
		}
		for(long j=0;j<n;j++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			long p = Long.parseLong(st.nextToken())-1;
			long s = Long.parseLong(st.nextToken());
			adj.get((int)p).add(new Child(j,s));
		} // adj has been constructed. now to calculate route w max score
//		scores = new long[(int)n];
		maxScore=0;
		for(long j=0;j<n;j++) {
			long cScore=0; // score of current route
			boolean[] visited = new boolean[(int)n];
			// go thru a route for j until u cant no more
			visit(j,visited,cScore);
		}
//		long max=0;
//		for(int j=0;j<n;j++) {
//			max=Math.max(max, scores[j]);
//		}
		out.println(maxScore);
		in.close();
		out.close();
	}
	public static void visit(long p,boolean[] visited, long cScore) {
		if(visited[(int)p]) { // if at journey's end
//			scores[(int)og]=Math.max(scores[(int)og], cScore);
			maxScore=Math.max(maxScore, cScore);
			return;
		}
		visited[(int)p]=true;
		for(Child c:adj.get((int)p)) { // for each of the children
			if(!visited[(int)c.index]) {
				visit(c.index,visited,cScore+c.score);
			}
			else { // at journey's end
//				scores[(int)og]=Math.max(scores[(int)og], cScore);
				maxScore=Math.max(maxScore, cScore);
			}
		}
	}
}

class Child {
//	public long parent;
	public long index; // this one's index
	public long score;
	Child(long i, long s){
//		parent=p;
		index=i;
		score=s;
	}
}
