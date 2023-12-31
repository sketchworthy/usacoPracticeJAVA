/*
 * idea: FLIPS!!!! FLIPPASSSSS!!!!
 */
import java.util.*;

public class Ex0101 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // size of array
		int k = in.nextInt(); // # of steps to the right array is moved
		int[] A = new int[n];
		for(int j=0;j<n;j++) A[j]=in.nextInt();
		
		// solution 1: not using constant amount of space
		int[] B = new int[n];
		for(int j=0;j<n;j++) {
			B[(j+k)%n]=A[j];
		}
		System.out.println(Arrays.toString(B));
		
		// solution 2: 3 flips
		// (flips can be done with swaps mb? idk)
		A.
		
		in.close();
	}
}
