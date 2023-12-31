package class03x04x2023;
/* Given an 2D array, visit the elements in counterclockwise square order
 * and return a specific sorta sum mod 1e9+7
 * original version
 * idea: pretty simple
 * implementation: hard if only bc of debugging taking 10010 yrs. 
 * i need to write down outlines on paper to make it easier
 */
import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class quiz1 {
	public static long MOD = (long) 1e9+7;
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
		int min = Math.min(m, n);
		int cnt = 0;
		long ans = 0;
		// order: go from 0,0 to 1,0;1,1;0,1 to 2,0;2,1;2,2;1,2;0,2...
		//  until you've reached the limit of min(m,n)-1
		//   then u either go only up or only right until u hit m or n
		for(int j=0;j<min;j++) {
			for(int k=0;k<=j;k++) {
				ans+=cnt*(long)A[j][k];
				cnt++;
//				out.print(A[j][k]+" ");
				ans=ans%MOD;
			}
			ans=ans%MOD;
			for(int k=j-1;k>=0;k--) {
				ans+=cnt*(long)A[k][j];
				cnt++;
//				out.print(A[k][j]+" ");
				ans=ans%MOD;
			}
			ans=ans%MOD;
//			out.println("h");
		}
		// reached limit of min
		if(m<n) { // only go up for m-min cols
			for(int j=min-1;j>=0;j--) {
				for(int k=m;k<=n-1;k++) {
					ans+=cnt*(long)A[j][k];
					cnt++;
//					out.print(A[j][k]+" ");
					ans=ans%MOD;
				}
//				out.println();
				ans=ans%MOD;
			}
		}
		else if(m>n) { // only go right for n-min rows
			for(int j=min;j<m;j++) {
				for(int k=0;k<n;k++) {
					ans+=cnt*(long)A[j][k];
					cnt++;
//					out.print(A[j][k]+" ");
					ans=ans%MOD;
				}
				ans=ans%MOD;
			}
		}
		ans%=MOD;
		if(ans<0)ans+=MOD;
//		out.print(ans);
		BigInteger x = new BigInteger(String.valueOf(ans));
		BigInteger mod = new BigInteger(String.valueOf(MOD));
		out.print(x.remainder(mod));
		
		out.close();
	}
}
