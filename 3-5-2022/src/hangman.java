/*
 * hangman game
 */
import java.util.*;
import java.io.*;
public class hangman {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("cleanPhrases"));
		ArrayList<String> allPhrases = new ArrayList<String>();
		while(in.hasNext()) {
			allPhrases.add(in.nextLine());
		}
		in.close();
		
		// pick 1 phrase randomly
		Random gen = new Random(1); // fix seed of random # generator
		int pick = gen.nextInt(allPhrases.size());
		
		String target = allPhrases.get(pick);
//		System.out.println(pick+" "+target);    //DEBUGGING
		
		ArrayList <Character> lettersAll = new ArrayList<Character>(); // letters in phrase
		for(int j=0;j<target.length();j++) {
			char ch = Character.toLowerCase(target.charAt(j));
			if(ch<='z'&&ch>='a') {
				if(!lettersAll.contains(ch)) {
					lettersAll.add(ch);
				}
			}
		}
		// lists of letters correct&incorrect (for user)
		ArrayList<Character> lettersCorrect = new ArrayList<Character>();
		ArrayList<Character> lettersWrong = new ArrayList<Character>();
		
		int maxMisses = 6; // max misses allowed
		int misses = 0; // player's misses
		
		Scanner kb = new Scanner(System.in);
		while (misses<maxMisses) {
			printPhrase(target,lettersAll,lettersCorrect,lettersWrong);
			// user prompt
			System.out.print("Your guess: ");
			// read the char
			char guess = Character.toLowerCase(kb.next().trim().charAt(0));
			if(lettersAll.contains(guess)) {
				if(!lettersCorrect.contains(guess)) {
					lettersCorrect.add(guess);
				}
				if(lettersCorrect.size()==lettersAll.size()) break;
			}
			else {
				lettersWrong.add(guess);
				misses++;
			}
		}
		printPhrase(target,lettersAll,lettersCorrect,lettersWrong);
		if(lettersCorrect.size()==lettersAll.size()) System.out.println("Congratulations!");
		else System.out.println("Good try!");
		kb.close();
	}
	
	
	// letters correct or special char will be displayed as normal, others shown in '*'
	static void printPhrase(String target, ArrayList<Character> lettersAll, 
			ArrayList<Character>lettersCorrect,ArrayList<Character>lettersWrong) {
		
		System.out.println();
		System.out.print("Phrase:" );
		for (int j=0;j<target.length();j++) {
			char ch = target.charAt(j);
			if(ch>='a'&&ch<='z') { // lowercase
				if(lettersCorrect.contains(ch)) System.out.print(ch);
				else System.out.print("*");
			}
			else if(ch>='A'&&ch<='Z') {
				if(lettersCorrect.contains((char)(ch-'A'+'a'))) {
					System.out.print(ch);
				}
				else {
					System.out.print("*");
				}
			}
			else System.out.print(ch); // special character
		}
		System.out.println();
		
		// correct+wrong guesses
		System.out.println("Correct: "+lettersCorrect);
		System.out.println("Wrong: "+lettersWrong);
		// print ascii hangman
		if(lettersWrong.size()==0) {
			System.out.println("   ____");
			System.out.println("  |    |");
			System.out.println("       |");
			System.out.println("       |");
			System.out.println("       |");
			System.out.println("       |");
			System.out.println(" ______|_\n");
		}
		else if(lettersWrong.size()==1) {
			System.out.println("   ____");
			System.out.println("  |    |");
			System.out.println("  o    |");
			System.out.println("       |");
			System.out.println("       |");
			System.out.println("       |");
			System.out.println(" ______|_\n");
		}
		else if(lettersWrong.size()==2) {
			System.out.println("   ____");
			System.out.println("  |    |");
			System.out.println("  o    |");
			System.out.println("  |    |");
			System.out.println("  |    |");
			System.out.println("       |");
			System.out.println(" ______|_\n");
		}
		else if(lettersWrong.size()==3) {
			System.out.println("   ____");
			System.out.println("  |    |");
			System.out.println("  o    |");
			System.out.println(" \\|    |");
			System.out.println("  |    |");
			System.out.println("       |");
			System.out.println(" ______|_\n");
		}
		else if(lettersWrong.size()==4) {
			System.out.println("   ____");
			System.out.println("  |    |");
			System.out.println("  o    |");
			System.out.println(" \\|/   |");
			System.out.println("  |    |");
			System.out.println("       |");
			System.out.println(" ______|_\n");
		}
		else if(lettersWrong.size()==5) {
			System.out.println("   ____");
			System.out.println("  |    |");
			System.out.println("  o    |");
			System.out.println(" \\|/   |");
			System.out.println("  |    |");
			System.out.println(" /     |");
			System.out.println(" ______|_\n");
		}
		else {
			System.out.println("   ____");
			System.out.println("  |    |");
			System.out.println("  o    |");
			System.out.println(" \\|/   |");
			System.out.println("  |    |");
			System.out.println(" / \\   |");
			System.out.println(" ______|_\n");
		}
	}
}

/*
 *   ____
 *  |    |
 *  o    |
 * \|/   |
 *  |    |
 * / \   |
 * ______|_
 *   
 */
