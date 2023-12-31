import java.util.*;
public class blocks {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int N = kb.nextInt(); // words to spell
		
		// initialize letters you have--4 rows/blocks, 6 cols/letters
		char[][] blocks = new char[4][6];
		for (int row=0;row<4;row++) {
			String block = kb.next();
			for (int col=0;col<6;col++) {
				blocks[row][col]=block.charAt(col);
//				System.out.println(blocks[row][col]);
			}
		}
		
		// words to spell
		for (int i=1;i<=N;i++) { // go 1 word at a time
			String word = kb.next();
			boolean buildable=true; // start out being true
			
//			for (int letter=0;letter<word.length();letter++) { // for letter in word
//				boolean appears = false; // if letter appears in blocks
//				for ()
//				
//				// if: letter appears in blocks
//				//   'remove' the possibility of using that block again
//				// else: buildable=false
//			}
			
			
			
			int[] count = new int[26]; // count frequency of letters of the alphabet, a-z
			for (int j=0;j<word.length();j++) count[word.charAt(j)-'A']++;
			
			// see if you have the letters in ur blocks, and if u have ENOUGH letters
			for (int k=0;k<26;k++) {
				if (count[k]>0) {
					char c = (char)(k+'A'); // char we're looking for
					
					// check how many times c appears in the rows of blocks[]
					int occurences = 0;
					for (int row=0;row<4;row++) {
						boolean occurence=false;
						for (int col=0;col<6;col++) {
							if (blocks[row][col]==c) {
								occurence=true;
							}
						}
						if (occurence==true) occurences++;
					}
					if (occurences<count[k]) buildable=false;
				}
			}
			// see if the letters u have won't overlap over 1 another when u use the blocks
			// TODO:^^^
			
			if(buildable==true) System.out.println("YES");
			else System.out.println("NO");
		}
		
		kb.close();
	}
}
