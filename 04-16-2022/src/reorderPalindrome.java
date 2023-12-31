// NOTE: code is kinda broken but basic idea is to use StringBuilder

// reorder a string into a palindrome
import java.util.*;
public class reorderPalindrome {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char[] str = in.next().toUpperCase().toCharArray();
		int[] chCounts = new int[26]; 
// array of 26 indices representing the counts of the letters in str
		for(int j=0;j<str.length;j++) {
			chCounts[str[j]-'A']++; // add 1 to the value of the count
		}
		boolean broken = false;
		int smallestOdd = -1; 
		//^ index of lexicographically smallest odd count
		ArrayList<Character> halfAns = new ArrayList<Character>();
		//^ ans will ultimately be halfAns+smallestOdd+halfAns
		for(int j=0;j<26;j++) {
			if(chCounts[j]%2==1&&smallestOdd==-1) smallestOdd=j;
			else if(chCounts[j]%2==1) {
				System.out.println("NO SOLUTION");
				broken=true;
				break;
			}
			else { // if the count is even
//				int count = chCounts[j]/2; 
				// count is A...A # of a's at beginning or end
				for(int k=0;k<chCounts[j]/2;k++) halfAns.add((char) ('A'+j));
			}
		}
		if(broken==false) {
			if(smallestOdd==-1) { // if there's an odd index
				// print halfAns+smallestOdd+halfAns
				for(int j=0;j<halfAns.size();j++) {
					System.out.print(halfAns.get(j));
				}
				System.out.print(smallestOdd);
				for(int j=0;j<halfAns.size();j++) {
					System.out.print(halfAns.get(j));
				}
			}
			else {
				for(int k=0;k<2;k++) {
					for(int j=0;j<halfAns.size();j++) {
						System.out.print(halfAns.get(j));
					}
				}
			}
		}
		
		
		
		
		in.close();
	}

}
