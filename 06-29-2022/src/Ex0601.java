/*
 * was new stuff, review
 */
import java.util.Scanner;
public class Ex0601 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		int y = in.nextInt();
		int z = x^y; // XOR
		// count # of 1's in z's binary form with popcount
		System.out.println(Integer.bitCount(z));
		
		in.close();
	}
}
