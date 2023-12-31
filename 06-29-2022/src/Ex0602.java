/*
 * lots of new notation + ideas, needs review
 */
import java.util.Scanner;
public class Ex0602 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int b = in.nextInt();
		int c = in.nextInt();
		
		int cnt = 0; // min # of flips to make a or b = c
		for(int j=0;j<32;j++) {
			int mask = (1<<j); // shift to the left by j
			
			if((c&mask)==0) // c_j is 0
				cnt+=((a&mask)==1 ? 1 : 0) + ((b&mask)==1 ? 1:0);
			// if true, take 1, else, take 0
			else // c_j is 1
				cnt+=((a&mask)==0 && (b&mask)==0) ? 1:0;
		} 
		// mask checks for 1 at each pos of the binary form of the integer
		System.out.println(cnt);
		
//		int ab = a | b;
//		int abc = ab ^ c;
//		// convert abc to binary, then check each 1 bit and convert a and b's stuff so its the same
//		String biabc = "";
//		while(true) {
//			
//		}
		
		in.close();
	}
}