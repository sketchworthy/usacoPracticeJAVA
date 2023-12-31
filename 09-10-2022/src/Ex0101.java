/* USACO 2019 Jan Bronze p2 solution
 * very ez
 * basic idea: answer is just the # of indices before the last amount of increasing
 *  values at the end of the array
 */
import java.util.*;

public class Ex0101 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] A = new int[n]; // cows order
		for(int j=0;j<n;j++) A[j]=in.nextInt();
		// go backwards from the back, check for last few being in the increasing order
		int earliestIndex = n-1;
//		System.out.println(earliestIndex);
		for(int j=n-1;j>0;j--) {
			if(A[j]<A[j-1]) 
				break;
			earliestIndex--;
//			System.out.println(earliestIndex);
		}
		System.out.println(earliestIndex);
		in.close();
	}
}
