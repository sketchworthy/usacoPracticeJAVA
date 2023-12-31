package class03x04x2023;
/* Given an 2D array, visit the elements in counterclockwise square order
 * and return a specific sorta sum mod 1e9+7
 * NOTE: Debugging took ages bc i never realized A[m][n] should have
 * (long) in front :///
 * 2nd version where i make a maxXmax array
 * idea: pretty simple
 * implementation: hard if only bc of debugging taking 10010 yrs. 
 * i need to write down outlines on paper to make it easier
 */
import java.util.*;
import java.io.*;

public class quizQ2 {
	public static long MOD = (long)1e9+7;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("10.in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int max = Math.max(m, n);
		int[][] A = new int[max][max];
		for(int j=0;j<max;j++)Arrays.fill(A[j], -1);
		for(int j=0;j<m;j++) {
			st = new StringTokenizer(in.readLine());
			for(int k=0;k<n;k++) {
				A[j][k]=Integer.parseInt(st.nextToken());
			}
		}
		long total = 0;
		int cnt=0;
		for(int j=0;j<max;j++) {
			for(int k=0;k<=j;k++) {
				if(A[j][k]==-1)continue;
				total+=cnt*(long)A[j][k];
				total%=MOD;
				cnt++;
			}
			for(int k=j-1;k>=0;k--) {
				if(A[k][j]==-1)continue;
				total+=cnt*(long)A[k][j];
				total%=MOD;
				cnt++;
			}
		}
		if(total<0)total+=MOD;
		out.print(total);
		
		out.close();
		in.close();
	}
}

