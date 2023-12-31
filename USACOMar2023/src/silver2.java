/* USACO Silver 2023 Problem 2
 * N barns each have a team of C cows, each of which is a G or a H.
 * The difference between 2 teams is the max # of positions i where the
 * breeds in pos i differ. For each team, find their max difference w
 * another team
 * 
 * idea: bit shifting(?)
 * 
 */
import java.util.*;
import java.io.*;

public class silver2 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int c = Integer.parseInt(st.nextToken()); // # of cows
		int n = Integer.parseInt(st.nextToken()); // # of teams
		char[][] strs = new char[n][c];
		for(int j=0;j<n;j++) {
			strs[j]=in.readLine().toCharArray();
		}
//		int[][] teams = new int[n][c];
//		for(int j=0;j<n;j++) {
//			for(int k=0;k<c;k++) {
//				if(strs[j][k]=='G')teams[j][k]=0;
//				else teams[j][k]=1;
//			}
//		}
		int[][][] comparisons = new int[n][n][c+1];
		// ^ comparisons[a][b][p] compares team a's pth pos against team b's
		//     1 if they're different, 0 if not
		for(int a=0;a<n;a++) {
			for(int b=0;b<n;b++) {
				for(int p=0;p<c;p++) {
					comparisons[a][b][p]=Math.abs(strs[a][p]-strs[b][p]);
					comparisons[a][b][c]+=comparisons[a][b][p];
				}
			}
		}
		for(int a=0;a<n;a++) { // for each team, find their max diff
			// find the max diff by summing each
			int max=0;
			for(int b=0;b<n;b++) {
				max=Math.max(max, comparisons[a][b][c]);
			}
			out.println(max);
		}
		out.close();
	}
}
