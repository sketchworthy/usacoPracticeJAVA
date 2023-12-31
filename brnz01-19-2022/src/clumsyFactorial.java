import java.util.*;
public class clumsyFactorial {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a number: ");
		int n = in.nextInt();
		in.close();
		
		System.out.println(cFactorial(n));
	}
	
	static int cFactorial(int n) {
		if (n==1) return 1;
		else if (n==2) return 2;
		else if (n==3) return 6;
		else if (n==4) return 7;
		else if (n==5) return 7;
		else if (n==6) return 8;
		else if (n==7) return 6;
		else if (n==8) return 9;
//		else if (n==9) return 11;      was just to confirm pattern
//		else if (n==10) return 12;
		
		// let n=9+4k+r where r is n-9 mod 4. then n-4k=r+9
		int k = (n-9)/4;
		k++;
		return cFactorial(n-4*k)+4*k;
	}
}