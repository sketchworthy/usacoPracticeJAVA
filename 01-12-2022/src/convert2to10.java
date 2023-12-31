import java.util.Scanner;
public class convert2to10 {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter binary number: ");
		String biNum = kb.next();
		// convert from base 2 to base 10
		int deciNum = 0; // initialize
		for (int j=0;j<biNum.length();j++) {
			char ch = biNum.charAt(j);
			int c = ch-'0';
			deciNum+=c*(Math.pow(2, biNum.length()-j-1));
		}
		System.out.println(deciNum);
		kb.close();
	}
}
