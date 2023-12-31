/* basic idea:
 * run conditions and try simulation? mb recursion? idk
 */
import java.util.*;
public class sumTianyiZhou {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt();
		for(int i=0;i<q;i++) {
			int n = in.nextInt();
			int[] A = new int[n];
			int total = 0;
			for(int j=0;j<n;j++) {
				A[j]=in.nextInt();
				total+=A[j];
			}
			
			// case 1: no number is removed
			if(total%2==0) {
				int t = total/2; // sum to this
				Arrays.sort(A); // integers sorted in ascending order
				if(A[n-1]==t) {System.out.print("1"); continue;}
				else if(A[n-1]<t) {
					t-=A[n-1]; // assign A[n-1] to a group
					for(int j=0;j<n-1;j++) {
						
					}
					
				}
			}
			// case 2: a number is removed (check parity and loop through)
			
			
			
			
			
		}
		in.close();
	}
	public static boolean canSum(int[] A, int total) {
		boolean can = true;
		// returns true if some combo of elements from A[] can sum to total
		// case 1: no number is removed
		if(total%2==0) {
			int t = total/2; // sum to this
			Arrays.sort(A); // integers sorted in ascending order
			if(A[n-1]==t) {System.out.print("1"); continue;}
			else if(A[n-1]<t) {
				t-=A[n-1]; // assign A[n-1] to a group
				for(int j=0;j<n-1;j++) {
					
				}
				
			}
		}
		// case 2: a number is removed (check parity and loop through)
		
		return can;
	}
}
