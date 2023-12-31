package class12x03x2023;
/* Multiples of 7 COMPLETE
 * Given string S that is an int with digits each 1-9, find # of substrs
 * such that the integer that is the substring is a multiple of 7
 * 
 * idea: 
 * prefix sum arr of numbers mod 7
 * 
 * difficulty: once i actually read the problem right and implemented
 * without bugs my code was solid! idea p ez to find, prefix sums op
 */
import java.util.*;
import java.io.*;

public class TianyiZhouMultiplesOf7 {
	static int[] tenPowXmod7 = {1, 3, 2, 6, 4, 5};
	public static void main(String[] args) throws Exception {
//		BufferedReader in = new BufferedReader(new FileReader("10.in"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		long cnt=0; // # of pairs (i,j)
		char[] S = in.readLine().toCharArray();
		int N = S.length;
		
		// make ps arr
		int[] ps = new int[N];
		ps[0]=(S[N-1]-'0')%7;
		for(int j=N-1;j>0;j--) {
			ps[N-j]= ((S[j-1]-'0')*(tenPowXmod7[(N-j)%6]) 
					+ps[N-j-1])%7; // ^ times 10^(N-j,place value mod 6) mod 7
		}
		
		// make a freq mod arr from ps
		long[] freq = new long[7];
		freq[0]=1; // bc of 0 when there are no numbers
		for(int j=0;j<N;j++) {
			freq[ps[j]]++;
		}
		
		// update cnt
		for(int j=0;j<7;j++) {
			cnt+=freq[j]*(freq[j]-1)/2;
		}
		
		
		out.print(cnt);
		in.close();
		out.close();
	}
}
