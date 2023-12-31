/* unfinished confuzzled
 * im so confused on how to recurse the permutations array from a previous permutations array
 * in theory sure, in practice i get tangled up BAD
 */

import java.util.Scanner;
public class Ex0506 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // # of conitions
		
		String[] names = {"Bessie"};
		int[][] perms = perm(8);
		for(int j=0;j<perms.length;j++) {
			// check if this jth permutation satisfied all conditions
		}
		
		in.close();
	}
	//generate n! permutations of the #s [1...n]
	static int[][] perm(int n){
		if(n==1) {
			int[][] A = {{1}};
			return A;
		}
		if(n==2) {
			int[][] A = {{1,2},{2,1}};
			return A;
		}
		int fact = 1;
		for(int i=1;i<n-1;i++) {
			fact*=i; // fact = (n-2)!
		}
		int[][] A = new int[fact*(n-1)][n];
		int[][] B = perm(n-1);
		for(int j=0;j<fact;j++) {
			for(int k=0;k<n-1;k++) {
				for(int i=0;i<n;i++) {
					A[j*fact+k]
				}
			}
		}
		
		return A;
	}
}
