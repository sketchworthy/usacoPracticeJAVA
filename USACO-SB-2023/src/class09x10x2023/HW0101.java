package class09x10x2023;
/* Decode String COMPLETE
 * Given an encoded str, find the LENGTH of the encoded string where
 * k[encoded_string] decoes to encoded_string repeated k times, k
 * being a pos num and encoded_string a string of lowercase engl letters
 * 
 * idea: you don't need to actually make the str, you can just
 * read the # of chars in. Do this w recursion. keep parameters as the
 * ends of the region of the str/thing you're considering, and
 * use the decode function to decode smaller bits of itself bc the
 * encryption is layered.
 * 
 * difficulty: Once I realized that we were just looking at the length
 * and not the string itself this problem was a lot easier, also helped
 * that i figured out a lot of the recursion logic already at that point.
 * i need to be more careful in reading the problem and in being
 * careful about my recursion logic. like mb i need to write it out
 * beforehand so i have a good birds eye view of it. but otherwize
 * ez difficulty, main difficulty implementation. recursion very
 * satisfying when done right.
 * 
 * NOTE: YOU CAN DO THIS WITH A STACK. ESP FINDING THE [, ] PART
 */
import java.util.*;
import java.io.*;

public class HW0101 {
	static int[] s;
	static String str;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("10.in"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		str=in.readLine();
		s = new int[str.length()]; // if 0, its a letter. if 1 its num
		// if 2 its open bracket. if 3 its close bracket
		for(int j=0;j<str.length();j++) {
			char c=str.charAt(j);
			if(c=='[') {
				s[j]=2;
			}
			else if(c==']') {
				s[j]=3;
			}
			else if(c<='9'&&c>='0') {
				s[j]=1;
			}
		}
		out.print(decode(0,str.length()-1));
		in.close();
		out.close();
	}
	static long decode(int j, int k) { // start & end indices
		// of recursion. returns len of decoded str
		int firstRectOpenI=getFirstRectOpenIndex(j,k);
		if(firstRectOpenI==-1) { // if you have no []
			return k-j+1;
		}
		int prevIntStartI= getPrevIntIndex(firstRectOpenI);
		int closeRectI=getFirstRectCloseIndex(firstRectOpenI+1,k);
		long x = Long.parseLong(str.substring(prevIntStartI, firstRectOpenI));
		long total=x*(decode(firstRectOpenI+1,closeRectI-1)); //the part where it repeats
		total+=prevIntStartI-j; // beginning noncoded part
		total+=decode(closeRectI+1,k); // decode() again for the rest of the str
		return total;
	}
	
	static int getPrevIntIndex(int j) {
		// given j is index of [, find the starting index of k 
		//  before [ so that we know k[]
		while(j>0) {
			j--;
			int c = s[j];
			if(!(c==1)) {
				return j+1;
			}
		}
		return 0;
	}
	
	static int getFirstRectOpenIndex(int j, int k){ 
		// get index of first [ in s[i] to s[j] inclusive
		for(int i=j;i<=k;i++) {
			if(s[i]==2)return i;
		}
		return -1; // otherwise returns -1
	}
	static int getFirstRectCloseIndex(int j, int k){
		// get index of first ] in s[i] to s[j] inclusive, given that
		//  right before j was a [
		int openBracts=1; // counts how many open brackets have stacked
		for(int i=j;i<=k;i++) {
			if(s[i]==2)openBracts++;
			if(s[i]==3) {
				if(openBracts==1)return i;
				else openBracts--;
			}
		}
		return -1; // otherwise returns -1
	}
}
