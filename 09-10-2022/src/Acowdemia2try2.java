/*
 * 
 */
import java.util.*;

public class Acowdemia2try2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int k = in.nextInt(); // # of papers
		int n = in.nextInt(); // # of cows
		
		String[][] cows = new String[n][2]; // list of cow names
						// cows[n][0]=name, cows[n][1] original index
		Map<String,Integer> moo = new HashMap();
		int[][] papers = new int[k][n]; // tells authorship for k papers, n cows (# representation)
		
		// fill arrays
		for(int j=0;j<n;j++) {
			cows[j][0]=in.next();
			cows[j][1]=Integer.toString(j);
		}
		Arrays.sort(cows, (n1,n2) -> n1[0].compareTo(n2[0]));
//		for(int j=0;j<n;j++) {
//			for(int x=0;x<2;x++) {
//				System.out.print(cows[j][x]+" ");
//		}
//			System.out.println();
//	}
		// cows is now alphabetically sorted
		for(int j=0;j<n;j++)
			moo.put(cows[j][0],j); // returns the alphabetical position of cow
		
		for(int j=0;j<k;j++) {
			for(int m=0;m<n;m++) papers[j][m]=moo.get(in.next());
		}
		
		char[][] output = new char[n][n]; //output[x][y] is cow x's perception of y
		// output not alphabetical
		for(int j=0;j<n;j++) {
			Arrays.fill(output[j], '?');
		}
		
		for(int j=0;j<n;j++)
			output[j][j]='B'; // tis the cow
		
		// go through the pairs in papers[][]
		for(int j=0;j<k;j++) { // go through the papers
			for(int x=0;x<n-1;x++) { // cow 1 in a pair
				for(int y=x+1;y<n;y++) { // cow 2 in a pair
					if(output[x][y]=='?') {
						if(papers[j][x]>papers[j][y]) { // if y precedes x alphabetically
		// if a is behind b, a must be more senior and b must be more junior
							
		// papers[j][y] is the alphabetical # of cow y
		//  cows[papj,y][1] is the nonalphabetical ordering of cow y
		//    output[cows[papers[j][x]][1]][cows[papers[j][y]][1]] is cow x's perception of y
							output[Integer.valueOf(cows[papers[j][x]][1])][Integer.valueOf(cows[papers[j][y]][1])]='0';
							output[Integer.valueOf(cows[papers[j][y]][1])][Integer.valueOf(cows[papers[j][x]][1])]='1';
						//   everything behind a is also more senior than b
							
							for(int z=y+1;z<n;z++) {
								output[Integer.valueOf(cows[papers[j][x]][1])][Integer.valueOf(cows[papers[j][z]][1])]='0';
								output[Integer.valueOf(cows[papers[j][z]][1])][Integer.valueOf(cows[papers[j][x]][1])]='1';
							}
							for(int z=x-1;z>=0;z--) {
								output[Integer.valueOf(cows[papers[j][z]][1])][Integer.valueOf(cows[papers[j][y]][1])]='0';
								output[Integer.valueOf(cows[papers[j][y]][1])][Integer.valueOf(cows[papers[j][z]][1])]='1';
							}
//							for(int s=0;s<n;s++) {
//								for(int m=0;m<n;m++) {
//									System.out.print(output[s][m]);
//								}
//								System.out.println();
//							}
//							System.out.println();
						}
					}
				}
			}
		}
		// print
		for(int j=0;j<n;j++) {
			for(int m=0;m<n;m++) {
				System.out.print(output[j][m]);
			}
			System.out.println();
		}
		in.close();
	}
}
