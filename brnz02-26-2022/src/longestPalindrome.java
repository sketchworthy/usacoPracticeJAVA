import java.util.Scanner;
public class longestPalindrome {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		String str = kb.next();
		kb.close();
		
		int[] lettersCount = new int[52]; // count A-Z and a-z frequency in string
		for (int j=0;j<str.length();j++) {
			lettersCount[str.charAt(j)-'A']++; // add 1 to this char's frequency
		}
		
		// palindrome thoughts
		int palLen = 0; // initialize max palindrome length
		boolean anyOdds = false;
		for (int k=0;k<52;k++) {
			if(lettersCount[k]%2==1) {
				anyOdds=true;
			}
		palLen+=lettersCount[k]/2;
		}
		if (anyOdds==true) {
			palLen++;
		}
		
		System.out.println(palLen);
	}
}
