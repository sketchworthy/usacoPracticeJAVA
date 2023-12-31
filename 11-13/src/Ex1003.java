import java.util.*;
public class Ex1003 {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter an integer:");
		int number = kb.nextInt();
		kb.close();
		
		// find the sum of digits in 'number'
		int sum = 0;
		while (number>0) {
			sum+=number%10;
			number/=10;
		}
		System.out.println(sum);
	}

}
