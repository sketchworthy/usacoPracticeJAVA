/*
 * 
 */
import java.util.*;

public class insertionSort {
	public static void main(String[] args) {
		int n = 10; // array size
		int[] A = new int[n];
		Random gen = new Random();
		for(int j=0;j<n;j++) {
			A[j]=gen.nextInt(100);
		}
		
		System.out.println(Arrays.toString(A));
		
		// insertion sort
		for(int j=0;j<n;j++) { //A[j] is next element to be inserted
			int listTemp=A[j];
			int i=j;
			while(i>0 && listTemp<A[i-1]) { // go backward from A[j-1]
				// shift elements right until you find element A[i]'s place
				A[i]=A[i-1];
				i--;
			}
			A[i]=listTemp;
		}

//		A=swap(0,1,A);
		System.out.println(Arrays.toString(A));
	}
	public static int[] swap(int i,int j, int[] A) {
		// swap A[i] and A[j]
		int temp=A[i];
		A[i]=A[j];
		A[j]=temp;
		return A;
	}
}
