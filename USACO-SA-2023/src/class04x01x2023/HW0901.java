package class04x01x2023;
/* Given 3 int arrs of len N: A,B,C, consisting only of ints in 1 to N inclusive
 * How many pairs (i,j) where 1<=i,j<=N satisfy A[i]=B[C[j]]?
 * 
 * og idea:
 * note A[i] is independent of B[C[j]], so we can use a
 * freq arr for the frequencies of 1...N in A. according to teacher, this
 * is actually the most efficient method.
 * class idea is literally just to use a freq hashmap instead of a freq array,
 * tho freq arr is better when the # of indices is within java int range
 * 
 * difficulty: ez
 * ALSO: Watch out for indexing!! starts from 1 for EVERYTHING, not 0
 */
import java.util.*;
import java.io.*;

public class HW0901 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("10.in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());
		long[] freq = new long[n+1]; // frequencies of #s 1 to N, ignore 0 index
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int j=1;j<=n;j++) freq[Integer.parseInt(st.nextToken())]++;
		st = new StringTokenizer(in.readLine());
		HashMap<Integer,Integer> B = new HashMap<>(); // <index,value>
		for(int j=1;j<=n;j++) B.put(j,Integer.parseInt(st.nextToken()));
		st = new StringTokenizer(in.readLine());
		HashMap<Integer,Integer> C = new HashMap<>(); // <index,value>
		for(int j=1;j<=n;j++) C.put(j,Integer.parseInt(st.nextToken()));
		
		long ans = 0;
		for(int j=1;j<=n;j++) {
			// ans+=freq[C[B[j]]]
//			int x = B.get(C.get(j));
			if(B.get(C.get(j))!=null) ans+=(long)freq[B.get(C.get(j))];
//			out.print(ans);
		}
		
		out.print(ans);
		in.close();
		out.close();
	}
}