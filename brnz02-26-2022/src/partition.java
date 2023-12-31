import java.util.*;
public class partition {
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		
		String str = in.next();
		int n=str.length();
		
		int[] last = new int[26]; // array of last indexes of letters in the alphabet 
		                            // found in str
		int[] first = new int[26]; // same
		// fill array
		for (int k=0;k<26;k++) {
			last[k]=str.lastIndexOf((char)'a'+k); // character we're looking for
			first[k]=str.indexOf((char)'a'+k);
		}
//		for (int k=0;k<26;k++) {
//			System.out.print(last[k]-first[k]+1+" ");   IGNORE
//		}
		
		// look for first rightEnd
		int rightEnd = last[str.charAt(0)-'a'];
		
		
		for (int k=0;k<n;k++) { // going through actual string
			System.out.print(str.charAt(k)-'a'); // letter index from 0-26
		}
		
		
		// find the parts: if a letter is between left and right end points of a current part,
		//  then last[y] should also be in current part:
		//   right end R = Max(R,last[y])
		
	}
}
