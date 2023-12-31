/* finished, done easily
 * You are given an integer array A. The range of a subarray of A is the difference between the largest and smallest
element in the subarray.
Report the sum of ranges of all subarrays of A.
basic idea: loops all the way, go through each possibility and add it on
 */
import java.util.*;
public class Ex0204 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n=in.nextInt();
		int[] A = new int[n];
		for(int j=0;j<n;j++) {
			A[j]=in.nextInt();
			System.out.print(A[j]+" ");
		}
		
		long total = 0;
		for(int size=2;size<=n;size++) { // go through each possible subarr size, from 2 to n
			for(int i=0;i<=n-size;i++) { // first index of each possible subarr
				int min=Integer.MAX_VALUE;
				int max=Integer.MIN_VALUE;
				for(int j=i;j<i+size;j++) {
					min = Math.min(min, A[j]);
					max = Math.max(max, A[j]);
				}
				int range = max-min;
				System.out.println(range);
				total+=range; // calculate their range and add to a total sum
			}
		}
		System.out.println(total);
		
		in.close();
	}
}
