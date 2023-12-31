/* print parallelogram word
 * COMPUTER
    OMPUTERC
     MPUTERCO
      PUTERCOM
       UTERCOMP
        TERCOMPU
         ERCOMPUT
          RCOMPUTE
           COMPUTER
 */
import java.util.Scanner;
public class printComputers {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.print("Enter a word: ");
		String word = kb.next();
		kb.close();
		int len = word.length();
		int[][] A = new int[len+1][len*2];
		
		for (int r=0;r<len+1;r++) {
			for (int c=0;c<2*len;c++) {
				if(c>=r && c<r+len)	A[r][c] = word.charAt(c%len);
			}
		}
		
		// print array
		for (int r=0;r<A.length;r++) {
			for(int c=0;c<A[0].length;c++) {
				System.out.print((char)A[r][c]);
			}
			System.out.println();
		}
	}
}
