/*
 * basic idea: sort array, then simulate every one of the N integers as the number of 
 * beans it comes to
 */
import java.util.*;
public class Ex0803 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] A = new int[n];
		for(int i=0;i<n;i++) {
			A[i]=in.nextInt();
		}
		Arrays.sort(A);

		int minCnt = Integer.MAX_VALUE;
		for(int j=0;j<n;j++) { // simulate each of N numbers
			int cnt = 0;
			for(int k=0;k<n;k++) {
				if(k<j)cnt+=A[k];
				else cnt+=A[k]-A[j];
			}
			minCnt=Math.min(minCnt, cnt);
		}
		System.out.println(minCnt);
		
		in.close();
	}
}
