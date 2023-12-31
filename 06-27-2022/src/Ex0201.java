/*
 * Finished+done fairly easily. Given two integer arrays A and B, return an array of their intersection. Each element in the result must appear as
many times as it shows in both A and B.
idea: sort arrays, have 2 pointer indices, compare+adjust
alternatively another method is to count freq of a size 1001 array
on how many times every number from 0 to 1000 is in each array (bc
the range of the numbers is from 0 to 1000, this is valid.) then its
ez to compare
 */
import java.util.*;
public class Ex0201 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int b = in.nextInt();
		int[] A = new int[a];
		int[] B = new int[b];
		for (int j=0;j<a;j++) {
			A[j]=in.nextInt();
		}
		for (int j=0;j<b;j++) {
			B[j]=in.nextInt();
		}
		Arrays.sort(A); Arrays.sort(B);
		ArrayList<Integer> AB = new ArrayList<Integer>();
		int j=0; int k=0;
		while (j<a && k<b){
			if(A[j]==B[k]) {AB.add(A[j]); j++; k++;}
			else if(A[j]>B[k]) k++;
			else j++; // if A[j]<B[k]
		}
		System.out.print(AB.get(0));
		for(int i=1;i<AB.size();i++) {
			System.out.print(" "+AB.get(i));
		}
		
		in.close();
	}
}
