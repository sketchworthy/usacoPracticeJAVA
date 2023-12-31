package class03x04x2023;
/* Given an 2D array, visit the elements in counterclockwise square order
 * and return a specific sorta sum mod 1e9+7
 * first rewrite version, same idea as 1st sol but more clean code
 * idea: pretty simple
 * implementation: hard if only bc of debugging taking 10010 yrs. 
 * i need to write down outlines on paper to make it easier
 */
import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class quizQ {
	public static long MOD = (long)1e9+7;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("02.in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[][] A = new int[m][n];
		for(int j=0;j<m;j++) {
			st = new StringTokenizer(in.readLine());
			for(int k=0;k<n;k++) {
				A[j][k]=Integer.parseInt(st.nextToken());
			}
		}
		
		BigInteger total = new BigInteger(String.valueOf(0));
		int cnt = 0; // how much to multply int by
		int min = Math.min(m, n)-1;
		for(int j=0;j<=min;j++) {
			for(int k=0;k<=j;k++) {
				total=total.add(BigInteger.valueOf((long)A[j][k]*cnt));
				cnt++;
			}
			for(int k=j-1;k>=0;k--) {
				total=total.add(BigInteger.valueOf((long)A[k][j]*cnt));
				cnt++;
			}
		}
		
		if(m>n) { // if more rows than cols/extra at bottom
			for(int j=min+1;j<=m-1;j++) {
				for(int k=0;k<n;k++) {
					total=total.add(BigInteger.valueOf((long)A[j][k]*cnt));
					cnt++;
				}
			}
		}
		else if(n>m) { // if more cols than rows/extra at right
			for(int j=m-1;j>=0;j--) {
				for(int k=min+1;k<n;k++) {
					total=total.add(BigInteger.valueOf((long)A[j][k]*cnt));
					cnt++;
				}
			}
		}
		BigInteger mod = new BigInteger(String.valueOf(MOD));
		out.print(total.remainder(mod));
		out.close();
		in.close();
	}
}

