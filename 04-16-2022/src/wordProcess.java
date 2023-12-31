// status: completed, though could consider using a boolean firstWord?
//     in place of variable linePlace
// nvm, checked class solution and they just had numLetters instead of chsLeft.
//  seems more efficient since you can just see it's the 1st in the line when
//   there are 0 numLetters.
import java.io.File;
import java.io.PrintWriter;
import java.util.*;
public class wordProcess {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("word.in"));
		PrintWriter out = new PrintWriter(new File("word.out"));
		
		int n = in.nextInt(); // # of words
		int k = in.nextInt(); // max # of chars in line (excluding spaces)
		
		int chsLeft = k; // # of chars left in current line
		String word = ""; // initialize
		int chCnt = 0; // # of chars in word
		int linePlace = 0; // place in line
		for(int j=0;j<n;j++) { 
			// for word in essay:
			word = in.next();
			chCnt = word.length();
			chsLeft-=chCnt;

			if(chsLeft<0) { // if word doesn't fit into line
				out.print("\n"+word);
				chsLeft=k-chCnt;
				linePlace=0;
			}
			else { // if word does fit
				if(linePlace==0) { // if this is the first word in the line
					out.print(word);
				}
				else {
					out.print(" "+word);
				}
			}
			linePlace++;
		}
		
		in.close();
		out.close();
	}
}
