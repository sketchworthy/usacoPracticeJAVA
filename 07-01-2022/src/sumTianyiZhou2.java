/* basic idea:
 * run conditions and try simulation? mb recursion? idk
 */
import java.util.*;
public class sumTianyiZhou2 {
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
			if (total%2==1) {
				
//				System.out.print("0");
//				continue;
			}
			total/=2; // sum to this
			Arrays.sort(A); // integers are sorted in ascending order
			if(A[n-1]>total) {
				System.out.print("0");
				continue;
			}
			else if(A[n-1]==total) {System.out.print("1"); continue;}
			
			// if all numbers in A are even and total is not even, return false
			if(total%2==1) {
				boolean allEven = true;
				for(int j=0;j<n;j++) {
					if(A[j]%2==1) {allEven=false; break;}
				}
				if(allEven==true) System.out.print("0"); continue;
			}
			
			System.out.print("1");
			
			
		}
		in.close();
	}
//	public static boolean canSum(int[] A, int total) {
//		// returns true if some combo of elements from A[] can sum to total
//		Arrays.sort(A);
//		
//		
//		
//	}
}
