/*
 * Using letters A-Z to represent base 26 0-25, display rules for addition.
 */
public class base26adding {
	public static void main(String[] args) {
		for(char ch1='A';ch1<='Z';ch1++) {
			for (char ch2='A';ch2<='Z';ch2++) {
				int index1 = ch1-'A';
				int index2 = ch2-'A';
				int sumIndex = index1+index2;
				// if the sum is greater than 26
				String sum = ""+(char)(sumIndex%26+'A');
				if (sumIndex>25) {
					sum = "B"+sum;
				}
				System.out.println(ch1+"+"+ch2+"="+sum);
			}
		}
	}
}
