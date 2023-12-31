/*
 * broken code, see acowdemia 2
 */
import java.util.*;

public class Acowdemia2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int k = in.nextInt(); // # of papers
		int n = in.nextInt(); // # of cows
		
		String[][] cows = new String[n][2]; // list of cow names
						// cows[n][0]=name, cows[n][1] original index
		String[][] papers = new String[k][n]; // tells authorship for k papers, n cows
		
		// fill arrays
		for(int j=0;j<n;j++) {
			cows[j][0]=in.next();
			cows[j][1]=Integer.toString(j);
		}
		for(int j=0;j<k;j++) {
			for(int m=0;m<n;m++) papers[j][m]=in.next();
		}
		Arrays.sort(cows, (n1,n2) -> n1[0].compareTo(n2[0]));
//		for(int j=0;j<n;j++) {
//			for(int x=0;x<2;x++) {
//				System.out.print(cows[j][x]+" ");
//		}
//			System.out.println();
//	}
		// cows is now alphabetically sorted
		
		char[][] output = new char[n][n]; //output[x][y] is cow x's perception of y
		Arrays.fill(output, '?');
		for(int j=0;j<n;j++)
			output[j][j]='B'; // tis the cow
		
		// go through the pairs in papers[][]
		for(int j=0;j<k;j++) { // go through the papers
			for(int x=0;x<n;x++) { // cow 1 in a pair
				for(int y=x;y<n;y++) { // cow 2 in a pair
					if(output[x][y]=='?') {
						if(papers[j][x].compareTo(papers[j][y])>0) { // if y precedes x
		// if a is behind b, a must be more senior and b must be more junior
							
							output[x][y]='0';
							output[y][x]='1';
						//   everything behind a is also more senior than b
							
							for(int z=y+1;z<n;z++) {
								
								output[x][z]='0';
								output[z][x]='1';
							}
						}
						else { // if x precedes y
							
						}
					}
				}
			}
		}
		// unsort
		
		// print
		
		in.close();
	}
}
