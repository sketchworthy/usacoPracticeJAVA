// TODO review recording of sol

import java.util.*;
public class rotateArray {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int N = kb.nextInt(); int K = kb.nextInt();
		int[] A = new int[N];
		for (int i=0;i<N;i++) {
			A[i]=kb.nextInt();
		}
		
		kb.close();
		
		int[] B = new int[N];
		for (int j=0;j<N;j++) {
			B[(j+K)%N]=A[j];
		}
		
		for(int j=0;j<N;j++) {
			System.out.print(B[j]+" ");
		}
		// alt method: reverse all, then reverse first k elements, then reverse last n-k elements
		
	}
}
