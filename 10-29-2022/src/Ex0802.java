/* USACO 2020 Jan Bronze
 * literally fixed half of test cases just by checking bounds on A[k] :/
 */
import java.util.*;
import java.io.*;
public class Ex0802 {
	public static void main(String[] args) throws Exception {
		PrintWriter out = new PrintWriter(new File("photo.out"));
		Scanner in = new Scanner(new File("photo.in"));
		int n = in.nextInt(); // # of cows
		int[] B = new int[n-1];
		for(int j=1;j<n;j++) {
			B[j-1]=in.nextInt();
		}
		
		// A1+A2=B1
		// A2+A3=B2
		// A3+A4=B3...
		// minimize A1, so maximize A2
		// make A2 as min(B1,B2)-1 (A1=1)
		// there can be no duplicates
		int maxI = Math.min(n,B[0]-1);
		for(int j=1;j<=maxI;j++) { // j represents val of A1
			int[] A = new int[n];
			A[0]=j;
			boolean[] numLine = new boolean[n+1]; // contains 1 to n inclusive
			Arrays.fill(numLine, false); // if numLine[x]=true, a cow has been decreed for that location
			numLine[j]=true;
			boolean possible = true;
			for(int k=1;k<n;k++) {
				A[k]=B[k-1]-A[k-1]; // A1=B0-A0
				if(A[k]<=0 || A[k]>n || numLine[A[k]]==true) {
					possible=false;
					break;
				}
				else numLine[A[k]]=true;
			}
			if(possible) {
				// print
				out.print(A[0]);
				for(int i=1;i<n;i++) {
					out.print(" "+A[i]);
				}
				break;
			}
		}
		out.close();
		in.close();
	}
}
