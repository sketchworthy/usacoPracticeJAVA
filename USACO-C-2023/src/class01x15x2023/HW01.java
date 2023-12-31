package class01x15x2023;
/* Consider the leftmost and rightmost appearances of some integer in array 
 * A, and the "span" is the number of elements in between. If an integer 
 * appears once in the array, then it has a span of 1. Return the largest 
 * span found in array A.
 * 
 * idea:
 * Have a map HM of keys being the distinct element values of A
 * HM's values being the 1st index of where the distinct element val appeared
Difficulty: Ez, and i came up w my solution myself (it was the 'right' sol, too!)
 */
import java.util.*;
import java.io.*;
public class HW01 {
	public static void main(String[] args) throws Exception {
		Scanner in2 = new Scanner(System.in);
		String fileName = in2.next();
		in2.close();
		Scanner in = new Scanner(new File(fileName));
		int n = in.nextInt();
		int[] A = new int[n];
		for(int j=0;j<n;j++) A[j]=in.nextInt();
		HashMap<Integer,Integer> hm = new HashMap<>();
		int maxSpan=1;
		for(int j=0;j<n;j++) {
			if(hm.containsKey(A[j])) {
				maxSpan=Math.max(maxSpan, j-hm.get(A[j])+1);
			}
			else hm.put(A[j], j);
		}
		System.out.println(maxSpan);
		
		in.close();
	}
}
