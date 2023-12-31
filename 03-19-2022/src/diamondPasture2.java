/*
 * unfinished
 */
import java.util.*;
public class diamondPasture2 {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int n = kb.nextInt();
		int m = kb.nextInt();
		int p = kb.nextInt(); // diamond specification
		int[][] T = new int[n][m];
		for(int j=0;j<n;j++) {
			for(int k=0;k<m;k++) {
				T[j][k]=kb.nextInt();
			}
		}
		
		int[][] D = new int[p][3]; // diamond specification
		for(int j=0;j<p;j++) {
			for(int k=0;k<3;k++) {
				D[j][k]=kb.nextInt();
			}
		}
		kb.close();
		
		int sum=0;
		for(int diamond=0;diamond<p;diamond++) { // for diamond
			sum=0;
			int d = D[diamond][2]; // diagonal length
			for(int j=0;j<=d;j++) { // line straight across
				sum+=T[n-D[diamond][1]][m-(D[diamond][0]-d/2+j)];
				System.out.print(T[n-D[diamond][1]][m-(D[diamond][0]-d/2+j)]);
			}
			for(int across=0;across<d/2;d++) { // upper half of diamond
				for(int j=0;j<2*across-1;j++) {
					sum+=T[n-(D[diamond][1]+across+1)][m-(D[diamond][0]-across+j)];
				}
			}
			System.out.println(sum);
		}
	}
}