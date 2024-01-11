/* USACO 2023 Dec Silver Q2 _status{COMPLETE or not}_
 * http://usaco.org/index.php?page=viewproblem2&cpid=1351
 * My personal official post-contest solution
 * 
 * Given N nodes and K edges, Anna assesses that
 * K nodes form a cycle. Bess independently assesses
 * that K more nodes form a cycle. There is only 1
 * cycle. How many nodes max might A and B have labelled
 * the same?
 * 
 * idea: there are only K ways each cycle can be rotated.
 * all we have to do is rotate A's cycle for each of K possible
 * degree rotations and find the max # of nodes that match up w
 * B's specifications. Then we add the nodes not found in either
 * cycle bc we can assume those are the same. Then we're done!
 * In order to find the max # of nodes that match for each of
 * A's rotated cycles and B's cycle, we can keep a 'difference'
 * freq array. If node x is in A's og cycle at index j and in
 * B's og cycle at index k, we do freqDiff[k-j]++;
 * Then at the end, we find the max freq of differences and just
 * have that many nodes that are the same. Yay we done!
 * 
 * difficulty: the idea was easy to come up with once i was
 * thinking clearly and not under time pressure. need to practice
 * more timed contest stress w coding
 * 
 */
import java.util.*;
import java.io.*;

public class silver2sol {
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
