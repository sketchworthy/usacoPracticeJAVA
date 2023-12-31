/*
 * unfinished confused tired
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Ex0405 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int k = in.nextInt();
		int n = in.nextInt();
		ArrayList<ArrayList<Integer>> ref = new ArrayList<ArrayList<Integer>>();
		
		
//		int[][] ref = new int[k][n];
		for(int i=0;i<k;k++) {
			ArrayList<Integer> I = new ArrayList<Integer>();
			for(int j=0;j<n;j++) {
//				ref[i][j]=in.nextInt();
				I.add(in.nextInt());
			}
			ref.add(I);
			System.out.println("AAAA");
			System.out.println(I);
		}
		System.out.println(ref);
		// create pairs array
		boolean[][] pairs = new boolean[n][n]; // pairs[a][b] checks if so far,
													// cow a is consistently better then b
		
		Arrays.fill(pairs, true);
		for(int i=0;i<n;i++) { // check each pair to see if its violated
			for(int j=0;j<n;j++) {
				if(i==j)continue;
				for(int r=0;r<k;r++) { // check each class ranking
					if(ref.get(r).indexOf(i)>ref.get(r).indexOf(j)) pairs[i][j]=false;
				}
			}
		}
		int ans = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(pairs[i][j]==true)ans++;
			}
		}
		System.out.println(ans);
		
		
		in.close();

	}

}
