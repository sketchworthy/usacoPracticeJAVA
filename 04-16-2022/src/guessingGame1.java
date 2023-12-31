// implementation of class solution for this problem, uses lexographics
 // to make search efficient
import java.util.Arrays;
import java.util.Scanner;

public class guessingGame1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(); // # of animals being guessed
		
		String[][] animals = new String[N][]; // dynamic array
		for(int j=0;j<N;j++) {
			in.next();
			int len = in.nextInt();
			animals[j] = new String[len];
			for(int k=0;k<len;k++) {
				animals[j][k]=in.next();
			}
			Arrays.sort(animals[j]); // sort lexographically
		}

		// find max # of common traits for all pairs of animals, add 1
		int maxCommon = 0;
		for(int j=0;j<N;j++) { // comparing all possible arrays
			for(int k=j+1;k<N;k++) { // animals[j] and animals[k]
				int common=0;
				int x=0; // index of animals[j][]
				int y=0; // index of animals[k][]
				while(x<animals[j].length && y<animals[k].length) {
					int res = animals[j][x].compareTo(animals[k][y]);
					if(res<0) { // if ky is ahead of jx
						x++; // move lexographically closer
					}
					else if (res>0) {
						y++;
					}
					else { // if they the same
						x++;
						y++;
						common++;
					}
				}
				maxCommon = Math.max(maxCommon, common);
			}
		}
		System.out.println(maxCommon+1);
		in.close();
	}

}
