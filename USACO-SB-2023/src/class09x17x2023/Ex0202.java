package class09x17x2023;
/* Min Max Difference COMPLETE
 * Given an array of ints A, report the min range of A after changing at most
 * 3 values of A to any number
 * 
 * idea: sort. then u can just look at the outermost few on the end of the
 * arr. look then just at the sliding window that excludes 3 ints
 * 
 * difficulty: v ez, i figured it out before any class talk
 * 
 */
import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class Ex0202 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		long[] A = new long[n];
		for(int j=0;j<n;j++) {
			A[j]=Long.parseLong(st.nextToken());
		}
		Arrays.sort(A);
		// only look at outermost 4 on each end
		// then once you have reduced to an array of just 8 or less integers,
		//  just look at the sliding window that excludes 3 ints
// aka, return the min of the diffs A[n-1]-A[3], A[n-2]-A[2],A[n-3]-A[1],A[n-4]-A[0]
		if(n>=4) {
			long mn=A[n-1]-A[3];
			mn=min(min(min(A[n-4]-A[0], A[n-3]-A[1]), A[n-2]-A[2]), mn);
			out.print(mn);
		}
		else {
			out.print(0);
		}
		
		
		in.close();
		out.close();
	}
}
