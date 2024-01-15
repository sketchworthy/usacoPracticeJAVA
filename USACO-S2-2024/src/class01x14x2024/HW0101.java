package class01x14x2024;
/* Inversion Pairs _status{COMPLETE or not}_
 * Given n integers A[i] in the range of [1,1e9], find the #
 * of pairs (j,k) s.t. j<k, but A[j]>A[k].
 * 
 * For example, if A is (2,4,1,3), we should get 3 b/c
 * 2>1, 4>1, and 4>3.
 * 
 * idea: loop thru array once. keep a treeSet of all A[j]
 * so far, then bring out 
 * 
 * implementation: 
 * 
 * difficulty:
 *
 * 
 */
import java.util.*;
import java.io.*;

public class HW0101 {
	static int w = 1;

	public static void main(String[] args) throws Exception {
		// take input
		// BufferedReader in = new BufferedReader(new FileReader("0"+w+".in"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); //

		out.println();

		in.close();
		out.close();
	}
}