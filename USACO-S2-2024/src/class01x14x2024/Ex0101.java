package class01x14x2024;
/* Diagonal Sort COMPLETE but messy and can be improved
 * Given a 2D matrix of integers with dimension m x n, sort the 
 * integers along the diagonals. Each diagonal goes from NW-SE.
 * 
 * idea: make 2000 lists of diagonals and sort each of those
 * loop through each of the diagonals and put them into an 
 * arraylist, then sort the arraylist, then put the sorted #s back in
 * 
 * difficulty: debugging hard bc i used for loops so i had to
 * find those finicky max's which i didn't get correct. 
 * would have been a lot easier if i used while loops,
 * like while(x<n-1 && y<n-1)
 * 
 */
import java.util.*;
import java.io.*;

public class Ex0101 {
	public static void main(String[] args) throws Exception {
		// take input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int m = Integer.parseInt(st.nextToken()); // 
		int n = Integer.parseInt(st.nextToken()); //
		
		int[][] A = new int[m][n];
		for(int j=0;j<m;j++) {
			st = new StringTokenizer(in.readLine());
			for(int k=0;k<n;k++) {
				A[j][k] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int j=0;j<n;j++) { // sort each of diagonals A[j][0], 
			// A[j+1][1], ... A[m-1][m-1-j] OR A[j+n-1][n-1] whichever comes 1st
			ArrayList<Integer> tempDiag = new ArrayList<>();
			int end = Math.min(m-1-j, n-1);
//			int end = Math.min(m-1-j, j+n-1);
			for(int k=0;k<=end;k++) {
				tempDiag.add(A[j+k][k]);
			}
			Collections.sort(tempDiag);
			
			// put back into A[][]
			for(int k=0;k<=end;k++) {
				A[j+k][k]=tempDiag.get(k);
			}
		}
		for(int j=1;j<n;j++) { // sort each of diagonals A[0][j], 
			// A[1][j+1], ... A[n-j-1][n-1] OR A[m-1][j+m-1] whichever comes 1st
			ArrayList<Integer> tempDiag = new ArrayList<>();
			int end = Math.min(n-j-1, m-1);
//			int end = Math.min(n-j-1, j+m-1);
			for(int k=0;k<=end;k++) {
//			for(int k=0;k<end;k++) {
				tempDiag.add(A[k][j+k]);
			}
			Collections.sort(tempDiag);
			
			// put back into A[][]
			for(int k=0;k<=end;k++) {
//			for(int k=0;k<end;k++) {
				A[k][j+k]=tempDiag.get(k);
			}
		}
		
		// print
		for(int j=0;j<m;j++) {
			for(int k=0;k<n;k++) {
				out.print(A[j][k]+" ");
			}
			out.println();
		}

		in.close();
		out.close();
	}
}
//test case
//8 10
//4 9 2 8 4 9 1 8 4 10
//4 8 12 8 2 8 2 3 9 1
//5 9 2 5 2 4 5 6 3 2
//82 5 2 5 9 1 3 5 9 2
//6 9 2 8 9 1 5 7 0 40
//6 9 2 0 5 2 8 0 2 95
//9 2 54 2 9 9 4 2 5 3
//4 9 6 29 4 9 6 1 4 3
