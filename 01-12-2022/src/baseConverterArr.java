/* uses array of 0 to F */
import java.util.Scanner;
public class baseConverterArr {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.print("Enter an integer n, base 10, to convert to base x: ");
		int n = kb.nextInt();
		System.out.print("Enter an integer x from 2-16 to convert "+n+" to: ");
		int x = kb.nextInt();
		
		char[] digits = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		
		// convert n base 10 to base x
		String converted = "";
		while(n>0) {
			converted = digits[n%x]+converted;
			n = n/x;
		}
		System.out.println(converted);
		
		kb.close();
	}
}
