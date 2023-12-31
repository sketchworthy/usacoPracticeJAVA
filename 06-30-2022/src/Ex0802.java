/*
 * uses lambda stuff, basic idea is do ascii BS and sort by frequency in a 
 * 2d array
 */
import java.util.*;
public class Ex0802 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.next();
		int[][] freq = new int[128][2];
		for(int j = 0;j<128;j++) {
			freq[j][0]=j; // ascii val
		}
		for(int j=0;j<str.length();j++) {
			freq[str.charAt(j)][1]++;
		}
		// sort by descending order by frequency
		Arrays.sort(freq, (r1,r2) -> r1[1]!=r2[1] ? r2[1] - r1[1] : r1[0]-r2[0]);
		// display letters
		for(int j=0;j<128;j++) {
			if(freq[j][1]>0) {
				for(int x=0;x<freq[j][1];x++) {
					System.out.print((char)freq[j][0]);
				}
			}
			else break;
		}
		
		in.close();
	}
}
