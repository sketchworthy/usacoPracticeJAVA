/* done fairly easily, has a cool solution too
 * im proud i figured it out before rest of my class :3
 * 
 * basic idea: look at each index with char C,O, or W,
 * then calculate the # of substrings containing it by looking
 * at # of starting indices to the left+including the index with C, O or W and ending
 * indices to the right+including and multiplying them to get
 * # of substrings easily. this counts all of the # of substrings
 * without overcounting bc later substrings with other C, O's or W's will
 * count them on their own
 */

import java.util.*;
public class Ex0303 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.next();
		int n = str.length();
//		boolean[] cowHere = new boolean[n];
//		for(int j=0;j<n;j++) {
//			if(str.charAt(j)=='C' || str.charAt(j)=='O' || str.charAt(j)=='W') {
//				cowHere[j]=true;
//			}
//			else cowHere[j]=false;
//		}
		long substrings = 0;
		for(int j=0;j<n;j++) {
			if(str.charAt(j)=='C' || str.charAt(j)=='O' || str.charAt(j)=='W') {
				// calculate # of substrings containing this
				substrings+=(j+1)*(n-j);
			}
		}
		System.out.println(substrings);
		in.close();
	}

}
