/* USACO 2023 Dec Silver _status{COMPLETE or not}_
 * http://usaco.org/index.php?page=viewproblem&cpid=1338
 * 
 * Given nodes with N diff weights, a balanced tower
 * is a list of nodes where every node is at least K 
 * greater in weight than the node below them. Each
 * cow can only belong in 1 tower.
 * What is the max amount of cows that can become
 * involved in at most M balanced towers?
 * 
 * idea: To involve the most cows: start with smallest weight 
 * cow and keep iterating up to smallest-diff cow, add to cnt.
 * keep a list of lists, towers, sorted by weight of 1st index.
 * 
 *  
 * in order to iterate, keep an arr of weights&freqs.
 * binary search for smallest index such that weight at that
 * index >= weight of arr[0]+K. 
 * 
 * difficulty: 
 * 
 */
import java.util.*;
import java.io.*;

public class silver1sol {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of diff weights
		int m = Integer.parseInt(st.nextToken()); // max # of towers left
		int k = Integer.parseInt(st.nextToken()); // min difference of tower nbors
		
		

		in.close();
		out.close();
	}
}