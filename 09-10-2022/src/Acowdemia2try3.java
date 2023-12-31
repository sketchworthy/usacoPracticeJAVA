/*
 * 
 */
import java.util.*;

public class Acowdemia2try3 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int k = in.nextInt(); // # of papers
		int n = in.nextInt(); // # of cows
		
		String[][] cows = new String[n][2]; // list of cow names
						// cows[n][0]=name, cows[n][1] original index
		Map<String,Integer> moo = new HashMap();
		String[][] papers = new String[k][n]; // tells authorship for k papers, n cows (# representation)
		
		// fill arrays
		for(int j=0;j<n;j++) {
			cows[j][0]=in.next();
			cows[j][1]=Integer.toString(j);
		}
//		Arrays.sort(cows, (n1,n2) -> n1[0].compareTo(n2[0]));
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
			for(int m=0;m<n;m++) papers[j][m]= in.next();
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
				String cow1 = papers[j][x];
				int idx_cow1 = moo.get(cow1);

				for(int y=x+1;y<n;y++) { // cow 2 in a pair
					String cow2 = papers[j][y];
					int idx_cow2 = moo.get(cow2);

//					if(output[idx_cow1][idx_cow2]=='?') {
						if(cow1.compareTo(cow2)>0) { // if y precedes x alphabetically
		// if a is behind b, a must be more senior and b must be more junior
							
		// papers[j][y] is the alphabetical # of cow y
		//  cows[papj,y][1] is the nonalphabetical ordering of cow y
		//    output[cows[papers[j][x]][1]][cows[papers[j][y]][1]] is cow x's perception of y
							output[idx_cow1][idx_cow2]='0';
							output[idx_cow2][idx_cow1]='1';
						//   everything behind a is also more senior than b
							
							for(int z=y+1;z<n;z++) {
								String cowz = papers[j][z];
								int idx_cowz = moo.get(cowz);
								output[idx_cow1][idx_cowz]='0';
								output[idx_cowz][idx_cow1]='1';
							}
							for(int z=x-1;z>=0;z--) {
								String cowz = papers[j][z];
								int idx_cowz = moo.get(cowz);
								output[idx_cowz][idx_cow2]='0';
								output[idx_cow2][idx_cowz]='1';
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
//				}
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
