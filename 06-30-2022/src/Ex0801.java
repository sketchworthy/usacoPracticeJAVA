/*
 * basic idea: use pointer indices, fill array A from the back, biggest values first
 */
import java.util.*;
public class Ex0801 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		int[] A = new int[m+n];
		int[] B = new int[n];
		for(int j=0;j<m;j++) {
			A[j]=in.nextInt();
		}
		for(int j=0;j<n;j++) {
			B[j]=in.nextInt();	
		}
		merge(A,m,B,n);
		
		
		in.close();
	}
	
	static void merge(int A[], int m, int B[], int n) {
		// merge the n integers from B to A, and the final integers in A
		// are also sorted in ascending order
		int i = m-1; // index for old values in A
		int j = n-1; // index for values in B
		int k = m+n-1; // index for values of final A
		
		while(i>=0 && j>=0) {
			if(A[i]>B[j]) {
				A[k]=A[i];
				k--; i--;
			}
			else {
				A[k]=B[j]; k--; j--;
			}
		}
		// copy rest of ints in B to A
		while(j>0) {
			A[k]=B[j];
			k--;j--;
		}
		for(int x=0;x<m+n;x++) {
			System.out.print(A[x]+" ");
		}
	}
}
