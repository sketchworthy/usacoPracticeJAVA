import java.util.*;
public class countBits {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long n = in.nextLong();
		
		long pow2 = 1;
		long count = 0;
		while(pow2<n+1) {
			count+=(n+1)/(2*pow2)*pow2; // contribution from full cycles
			count+=Math.max(0, (n+1)%(2*pow2)-pow2); // partial cycle
			pow2*=2;
		}
		System.out.println(count);
		in.close();
	}
}
