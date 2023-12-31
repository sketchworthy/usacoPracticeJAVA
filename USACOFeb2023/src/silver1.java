/* USACO Feb 2023 Silver Problem 1
 * idea: we essentially have a set of equalities for each test case,
 * where tc*ai+tm*bi<=ci. multiply up the inequalities so the coefficient
 * of tc is all identical, so you have smthn like
 * x1*tc+y1*tm<=z1, x1*tc+y2*tm<=z2,  x1*tc+y3*tm<=z3, ...
 * remove inequalities k where yk<yi but zk>zi
 * then find the inequality w the smallest z remaining & loop thru all
 * possible tc's to get each of their greatest possible tms (accounting
 * for the og costs of tc & tm) and print the smallest possible diff u
 * could have
 */
import java.util.*;
import java.io.*;

public class silver1 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		for(int t=Integer.parseInt(in.readLine());t>0;t--) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(st.nextToken()); // # of friends
			int tc = Integer.parseInt(st.nextToken()); // time to make cookie
			int tm = Integer.parseInt(st.nextToken()); // time to make muffin
			int[][] orders = new int[n][3]; // orders from friends & how long
			// each friend is willing to wait
			for(int j=0;j<n;j++) {
				st = new StringTokenizer(in.readLine());
				orders[j][0]=Integer.parseInt(st.nextToken()); // cookies ordered
				orders[j][1]=Integer.parseInt(st.nextToken()); // muffins ordered
				orders[j][2]=Integer.parseInt(st.nextToken()); // time fren waits
			}
			
			
		}
		out.close();
	}
}
