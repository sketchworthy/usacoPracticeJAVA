package class01x21x2022;
/* A non-empty digit string is diverse if the frequency of each character doesn't exceed 
 * the number of distinct characters in it. Given a string, find # of diverse substrings.
 * 
 * problem is with complexity: you have to find an optimized way that doesn't exceed
 * time limit
 * 
 * difficulty: pretty ez once you see the trick!
 * 
 * key insight: you can only have digits from 0-9 so you can only have a max 
 * [# of distinct characters] as 10. so you only have to look at up to 99 chars ahead.
 * you can also build upon your prev count of frequency by using the same frequency array 
 * and adding to it as you build your substring, rather than recreating it every time
 * you advance the substring
 */
import java.util.*;

public class Ex0201 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char[] str = in.next().toCharArray();
		in.close();
		
		long cnt = str.length; // add all 1-char substrings
		
		for(int start=0;start<str.length;start++) { // start index
			int diffNs = 1; // # of distinct chars
			int maxCharFreq=1;
			int[] freq = new int[10]; // freq array for digits 0-9
			freq[str[start]-'0']++; // add the 1st #
			for(int len=2;len<100;len++) { 
				// substring contains str[start] to start[start+len-1], inclusive
				if(start+len>str.length)break; // break if substring doesn't exist
				
				// check if substring is diverse: find # of distinct chars
				int i = (str[start+len-1])-'0'; // newly included index
				freq[i]++;
				if(freq[i]==1)diffNs++;
				if(freq[i]>maxCharFreq)maxCharFreq=freq[i];
				// check against character frequency
				if(diffNs>=maxCharFreq)cnt++;
				if(maxCharFreq>10)break;
			}
		}
		System.out.println(cnt);
		// diverse TRUE if [# of distinct characters]>=[each character frequency]
	}
}
