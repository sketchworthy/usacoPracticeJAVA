/*unfinished, logistics are annoying and haven't fully comprehended 
 * how to do it yet
 * 
 */
import java.util.*;
public class Ex0305 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = Integer.parseInt(in.nextLine()); // # of statements
		System.out.println(n);
		String[][] statements = new String[n][4];
		// index 0 is name1, next prev or next (-1 or 1), then year, then name2
		for(int j=0;j<n;j++) {
			String str = in.nextLine();
			statements=translateStatement(statements,str,j);
		}
		for(int j=0;j<n;j++) {
			for(int k=0;k<4;k++) {
				System.out.print(statements[j][k]+" ");
			}
			System.out.println();
		}
		
		
		
		
		in.close();
	}
	
	public static String[][] translateStatement(String[][] statements, String str, int i){
		// place info from statement str into array statements for index i
//		System.out.println(str);
		String[] words=str.split(" ");
		statements[i][0]=words[0];
		System.out.println("words[3]"+words[3]+". ");
		if(words[3].equals("previous"))statements[i][1]="-1";
		else statements[i][1]="1";
		if(words[4].equals("Ox")) statements[i][2]="12";
		else if(words[4].equals("Tiger")) statements[i][2]="1";
		else if(words[4].equals("Rabbit")) statements[i][2]="2";
		else if(words[4].equals("Dragon")) statements[i][2]="3";
		else if(words[4].equals("Snake")) statements[i][2]="4";
		else if(words[4].equals("Horse")) statements[i][2]="5";
		else if(words[4].equals("Goat")) statements[i][2]="6";
		else if(words[4].equals("Monkey")) statements[i][2]="7";
		else if(words[4].equals("Rooster")) statements[i][2]="8";
		else if(words[4].equals("Dog")) statements[i][2]="9";
		else if(words[4].equals("Pig")) statements[i][2]="10";
		else statements[i][2]="11"; // rat
		statements[i][3]=words[7];	
		return statements;
	}
}
