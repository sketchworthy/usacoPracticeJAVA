/* Write a program able to convert a decimal integer to any numeral basis 2-16. */
import java.util.*;
public class baseConverter {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.print("Enter an integer n, base 10, to convert to base x: ");
		int n = kb.nextInt();
		System.out.print("Enter an integer x from 2-16 to convert "+n+" to: ");
		int x = kb.nextInt();
		// convert n base 10 to base x
		String converted = "";
		while(n>0) {
			if (n%x>9) {
				converted = (char)('A'+(n%x-10))+converted;
			}
			else {
				converted = ((char)n%x)+converted;
			}
			n = (n-n%x)/x;
		}
		System.out.println(converted);
		
		kb.close();
	}
}
