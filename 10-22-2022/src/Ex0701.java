/* given a string of letters A-Z, make the lexographically least palindrome
 * if not possible, print NO SOLUTION
 */
import java.util.*;

public class Ex0701 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char[] str = in.next().toCharArray();
		int[] freq = new int[26]; // freq[0]=# of A's in str
		int oddFreqCnt=0; // # of odd #'s of letters in str[]
		int oddFreqI=-1; // index of odd val of freq[]
		int n = str.length;
		
		for(int j=0;j<n;j++) { // fill freq[]
			char c = str[j];
			freq[c-'A']++;
		}
		for(int j=0;j<26;j++) {
			if(freq[j]%2==1) {
				oddFreqCnt++;
				oddFreqI=j;
			}
		}
		if(oddFreqCnt>1)System.out.print("NO SOLUTION"); // can't make pal
		else {
			for (int j=0;j<26;j++) {
				for(int k=0;k<freq[j]/2;k++) {
					System.out.print((char)('A'+j));
				}
			}
			if(oddFreqCnt==1) {	// center is 'A'+oddFreqI 
				System.out.print((char)('A'+oddFreqI));
			}
			for(int j=25;j>=0;j--) {
				for(int k=0;k<freq[j]/2;k++) {
					System.out.print((char)('A'+j));
				}
			}
		}
		in.close();
	}
}
