package class01x15x2023;
/* You are given two positive integer arrays A and B, both of length n.
 * The sum of absolute difference of arrays A and B is defined as the sum of
 * |A[i]-B[i]| for each 0 <= i < n
You can replace at most one element of A with any other element in A to 
minimize the sum of absolute difference.
Report minimum sum of absolute differences after replacing at MOST 1 element
in A[]. Since ans may be large, report it modulo 1e9+7.

solution: 
Initial sum of abs difference is S= |A0-B0|+|A1-B1|+... etc. 
Replacing Ax with Ay gives the new sum as S-|Ax-Bx|+|Ay-Bx|. So for each Ax,
ideal Ay is with minimal |Ay-Bx|, then see the min of all S-|Ax-Bx|+|Ay-Bx| 
with the ideal Ay so we only need to look at 1 val Ay for each.
To find the ideal Ay, we use a set and find the floor/ceil of each Bx, then
make Ay the closest candidate and calculate the max sum.

Difficulty: Once u know how to do it, this problem isn't too bad (I got the
sol spoiled for me tho)
 */
import java.util.*;

public class Ex0102 {
	static int MOD = (int) (1e9+7);
	static int INF = 1<<30; // infinity. bitshift left by 30, returns 2^30
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] A = new int[n];
		int[] B = new int[n];
		for(int j=0;j<n;j++) A[j]=in.nextInt();
		for(int j=0;j<n;j++) B[j]=in.nextInt();
		
		int[] diffs = new int[n];
		for(int j=0;j<n;j++) diffs[j]=Math.abs(A[j]-B[j]);
		int maxSum=0;
		for(int j=0;j<n;j++)maxSum+=diffs[j];
		
		TreeSet<Integer> ts = new TreeSet<>(); // all ints in A
		for(int i : A)ts.add(i);
		ts.add(INF); // add 2 extreme vals
		ts.add(-1);
		int og=maxSum;
		// for each j, find out A[y] closest to B[j] and see if smaller
		//  maxSum is possible
		for(int j=0;j<n;j++) {
			int floor = ts.floor(B[j]);
			int ceil = ts.ceiling(B[j]);
			if(floor!=-1) {
				maxSum=Math.min(maxSum, og-Math.abs(A[j]-B[j])+
						Math.abs(floor-B[j]));
			}
			if(ceil!=INF) {
				maxSum=Math.min(maxSum, og-Math.abs(A[j]-B[j])+
						Math.abs(ceil-B[j]));
			}
		}
		System.out.println(maxSum%MOD);

		in.close();
	}
}
