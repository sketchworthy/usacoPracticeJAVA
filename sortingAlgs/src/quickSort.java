/* unfinished
 * 
 */
import java.util.*;

public class quickSort {
	public static void main(String[] args) {
		int n = 10; // array size
		int[] A = new int[n];
		Random gen = new Random();
		for(int j=0;j<n;j++) {
			A[j]=gen.nextInt(100);
		}
		
		System.out.println("original array: " + Arrays.toString(A));
		
		quickSort(0,n-1,A);

		System.out.println("end result: "+Arrays.toString(A));
	}
	
	public static int[] quickSort(int i,int j, int[] A) {
		// quickSort from index i to index j in A[], inclusive
		int iPivot=i; // pivot
		int itemFromLeft=i+1; // index of 1st item from left larger than pivot
		int itemFromRight=j; // index of 1st item from right smaller than pivot
		System.out.println("Aa");
		while(true) {
			while(true) { // find itemFromLeft
				if(A[itemFromLeft]>A[iPivot])break;
				if(itemFromLeft+1<j)itemFromLeft++;
			}
			while(true) { // find itemFromRight
				if(A[itemFromRight]<A[iPivot])break;
				if(itemFromRight+1<j)itemFromRight--;
			}
			if(itemFromLeft<itemFromRight) swap(itemFromLeft,itemFromRight,A);
			else break; // we done! break
		}
		System.out.println("h");
		swap(itemFromLeft,iPivot,A);
		System.out.println(Arrays.toString(A));
		A=quickSort(i,itemFromLeft-1,A);
		A=quickSort(itemFromLeft+1,j,A);
		return A;
	}
	
	public static int[] swap(int i,int j, int[] A) {
		// swap A[i] and A[j]
		int temp=A[i];
		A[i]=A[j];
		A[j]=temp;
		return A;
	}
}
