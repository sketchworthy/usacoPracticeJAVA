package codeTigerCodeforcesContest;
/* https://codeforces.com/contest/1703/problem/F
 * did not do during contest
 */
import java.util.*;

public class practice6 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		
		for(int j=0;j<t;j++) {
			int n = in.nextInt();
			int[] A = new int[n];
			for(int k=0;k<n;k++) {
				A[k]=in.nextInt();
			}
			ArrayList<Integer> valid = new ArrayList<Integer>(); 
			// ^ valid indices for i or j
			
			for(int k=0;k<n;k++) { // fill valid
				if(A[k]<k)valid.add(k);
			}
			
			
		}
		in.close();
	}
}
