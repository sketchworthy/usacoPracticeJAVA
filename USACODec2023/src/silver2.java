/* USACO 2023 Dec Silver _status{COMPLETE or not}_
 * http://usaco.org/index.php?page=viewproblem2&cpid=1351
 * 
 * Given N nodes and K edges, Anna assesses that
 * K nodes form a cycle. Bess independently assesses
 * that K more nodes form a cycle.
 * _problem desc_
 * 
 * idea: what's ezier is to find the min # of nodes that
 * CAN'T be labelled same way. 
 * we want to find max # of nodes in the 2 cycles that
 * can be labelled same way.
 * 
 * difficulty:
 * 
 */
import java.util.*;
import java.io.*;

public class silver2 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of barns/nodes
		int k = Integer.parseInt(st.nextToken()); // # of barns in cycle
		
		out.println(n);
		
		in.close();
		out.close();
	}
}
