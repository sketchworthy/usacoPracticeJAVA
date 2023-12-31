import java.util.Scanner;
public class convert10to2 {
	public static void main(String[] args) {
	Scanner kb = new Scanner(System.in);
	System.out.println("Enter base 10 number to be converted into base 2:");
	int n = kb.nextInt();
	String num = "";
	while(n>0) {
		if(n%2==1) {
			num = num+"1";
			n=(n-1)/2;
		}
		else {
			num=num+"0";
			n=n/2;
		}
	}
	System.out.println(num);
	kb.close();
	}
}
