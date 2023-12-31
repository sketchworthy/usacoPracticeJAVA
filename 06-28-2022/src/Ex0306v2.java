/* fairly simple idea+implementation, just count the # of cols that don't
 *  have spotted cows share letters with normal cows
 */
import java.util.*;

public class Ex0306v2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n=in.nextInt();
		int m=in.nextInt();
		char[][] genome1 = new char[n][m]; // spotted cow genomes
		char[][] genome2 = new char[n][m]; // plain cow genomes
		for(int j=0;j<n;j++)
			genome1[j]=in.next().toCharArray();
		for(int j=0;j<n;j++)
			genome2[j]=in.next().toCharArray();	
		ArrayList<Character> letters = new ArrayList<Character>();
		
		// add 1 if none of the spotted cow genomes
		//   share any letters with plain cow genomes
		int cnt=0;
		for(int k=0;k<m;k++) { // for each col
			letters.clear();
			letters.add('A'); letters.add('C'); letters.add('G'); letters.add('T');
			for(int j=0;j<n;j++) { // genome 1
				if(letters.contains(genome1[j][k])) {
					letters.remove(Character.valueOf(genome1[j][k]));
				}
			}
			// genome 2
			boolean addcnt=true;
			for(int j=0;j<n;j++) {
				if(!letters.contains(genome2[j][k])) {
					addcnt=false;
					break;
				}
			}
			if(addcnt==true)cnt++;
		}
		System.out.println(cnt);
		
		in.close();
	}
}
