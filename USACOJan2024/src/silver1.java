/* USACO 2024 Jan Silver Problem 1 _status{COMPLETE or not}_
 http://www.usaco.org/index.php?page=viewproblem&cpid=1362
 * FJ hiring a new cow out of N cows, giving each a competency score
 * in range [1,10^9]. He doesn't remember all competency scores
 * but remembers Q relationships of cow indices (a,b) where b is
 * smallest index cow with greater competency than cows 1 thru a. 
 * Find lexicographically smallest sequence of competency scores,
 * or that no sequence exists. 
 * 
 * idea: 
 * 
 * implementation: 
 * 
 * difficulty:
 * 
 */
import java.util.*;
import java.io.*;

public class silver1 {
	public static void main(String[] args) throws Exception {
		// take input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int t = Integer.parseInt(in.readLine());
		for(;t>0;t--) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(st.nextToken()); // # of cows interviewed
			int q = Integer.parseInt(st.nextToken()); // # of relations
			int c = Integer.parseInt(st.nextToken()); // max competency of a cow
			
			int[] competency = new int[n]; // if competency=0, FJ forgot it
			
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<n;j++) {
				competency[j]=Integer.parseInt(st.nextToken());
			}
			
			for(int j=0;j<q;j++) {
				st = new StringTokenizer(in.readLine());
				int a = Integer.parseInt(st.nextToken()); 
				int b = Integer.parseInt(st.nextToken());
				
				//
			}
			
			// find if 
		}

		out.println();

		in.close();
		out.close();
	}
}