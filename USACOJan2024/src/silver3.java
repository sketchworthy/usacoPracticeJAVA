/* USACO 2024 Jan Silver Problem 3 _status{COMPLETE or not}_
 * http://www.usaco.org/index.php?page=viewproblem&cpid=1364
 * N months, with xth month having A[x] days. Each week is L
 * days long. Each month is >= 4L days and there are at most
 * 3 diff values of A[x] mod L. Return sum of all possible L's.
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

public class silver3 {
	public static void main(String[] args) throws Exception {
		// take input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine()); // # of months
		long minLenMonth=Long.MAX_VALUE;
		long[] A = new long[n]; // A[x] = # of days in a month
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			A[j] = Long.parseLong(st.nextToken());
			minLenMonth = Math.min(minLenMonth, A[j]);
		}
		// L is <= minLenMonth/4, but this can be up to 10^9
		long ans = 0;
		if(minLenMonth/4>=1) {
			ans=1;
			if(minLenMonth/4>=2) {
				ans=3;
				if(minLenMonth/4>=3) {
					ans=6;
				}
			}
		}
		for(long L=4;L<=minLenMonth/4;L++) {
			boolean works=true;
			HashSet<Long> distinctMods = new HashSet<>();
			for(int j=0;j<n;j++) {
				distinctMods.add(A[j]%L);
				if(distinctMods.size()>3) {
					works=false;
					break;
				}
			}
			if(works) ans+=L;
		}

		out.println(ans);

		in.close();
		out.close();
	}
}