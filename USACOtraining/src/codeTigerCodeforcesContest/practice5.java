package codeTigerCodeforcesContest;
/* https://codeforces.com/contest/1703/problem/E
 * did not do during practice contest
 * pretty simple but implementation is annoying
 * basic idea: all 4 quadrants have to look the same, so split
 * the square into 4 quadrants & rotate each quadrant so they're
 * all overlapping over the same quarter square. add up every
 * entry. if it's not a 4 or not a 0, you have to change the entry
 * 4-[entry] or [entry]-0 times to make up the difference. note the case for
 * odd-sided squares. other than that, p ez.
 */
import java.util.*;

public class practice5 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int j=0;j<t;j++) {
			long cnt=0;
			int n = in.nextInt();
			boolean[][] A = new boolean[n][n];
			for(int k=0;k<n;k++) {
				String x=in.next();
				for(int i=0;i<n;i++) {
					if(x.charAt(i)=='1') A[k][i]=true;
					else A[k][i]=false;
				}
			}
			boolean oddN=false; // is n even?
			if(n%2==1)oddN=true;
			/* look at 4 rotations of 4 quarters */
			// divide into 4 rotated 'quarter' arrays
			int s; // side length of a quarter array
			if(oddN) s = n/2+1;
			else s=n/2;
			int[][] quadsSum=new int[s][s];
			for(int k=0;k<s;k++) {
				for(int i=0;i<s;i++) {
					if(A[k][i]) quadsSum[k][i]++; // add quad2
					if(A[i][n-k-1]) quadsSum[k][i]++; // add quad1
					if(A[n-k-1][n-i-1]) quadsSum[k][i]++; // add quad4
					if(A[n-i-1][k]) quadsSum[k][i]++; // add quad3
					
					if(oddN&& i!=s-1)cnt+=Math.min(quadsSum[k][i], 4-quadsSum[k][i]);
					else if(!oddN)cnt+=Math.min(quadsSum[k][i], 4-quadsSum[k][i]);
				}
			}
//			for(int k=0;k<s;k++) {
//				System.out.println(Arrays.toString(quadsSum[k]));
//			}
			System.out.println(cnt);
		}
		in.close();
	}
}
