package class02x04x2023;

/* COMPLETE
 * problem: A string is a kalindrome if following process terminates up to k
 * iterations:
 * 1. if str is palindrome, stop
 * 2. else, add reverse to string (usng base 26) & go back to step 1
 * read n strs, report # of kalindromes. str is made of uppercase chars.
 * Addition done w base 26: A=0,B=1,...Z=25
 * 
 * idea: the main cost of computation is in creating new space for appending
 * str / inserting stuff to front of arrayList / creating space for entirely
 * new arrays. so to get around this we just add to end of arraylist, initiate
 * space in front of arrays at the beginning, etc. after that, simulation
 * cost is actually not too bad.
 * 
 * basically, instead of overflowing towards front & having to insert to front
 * of arrayList which is costly, overflow towards back--there is no difference
 * b/c you're adding the reversed str anyway
 * 
 * This solution solves the problem using an ArrayList.
 * 
 * 
 * NOTE that it is also possible to do this problem w an int array. basically
 * at the beginning, initialize k extra empty spaces at the front/back in case
 * of carryover as insurance, so you don't need to keep making extra space
 * 
 */
import java.util.*;
import java.io.*;
public class Ex0301 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of strings
		int k = Integer.parseInt(st.nextToken()); // max # of iterations
		
		int cnt=0; // # of strings that are kalindromes
		for(int t=0;t<n;t++) {
			String str = in.readLine();
			
			ArrayList<Integer> A = new ArrayList<>();
			for(int x=0;x<str.length();x++)A.add(str.charAt(x)-'A');
			int rounds=0;
			while(rounds<k && !checkIfPalindrome(A)) {
				// add reversed A to A
				// instead of overflowing towards front, overflow towards back
				A=addReverse(A);
				
				rounds++;
			}
			if(rounds<k)cnt++;
		}
		out.print(cnt);
		out.close();
	}
	public static boolean checkIfPalindrome(ArrayList<Integer> A) {
		// checks if A is palindrome
		int s = A.size();
		for(int j=0;j<=s/2;j++) {
			if(A.get(j)!=A.get(s-j-1))return false;
		}
		return true;
	}
	public static ArrayList<Integer> addReverse(ArrayList<Integer> A){
		// adds reversed A to A, base 26, overflows towards back instead of front
		ArrayList<Integer> B = new ArrayList<Integer>();
		
		int s = A.size();
		int overflow=0;
		for(int j=0;j<s;j++) {
			int add = A.get(j)+A.get(s-j-1)+overflow;
			B.add(add%26);
			overflow=add/26;
		}
		if(overflow>0)B.add(overflow);
		return B;
	}
}


