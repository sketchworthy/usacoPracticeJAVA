// status: complete
import java.util.*;
public class abc {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] A = new int[7];
		Arrays.sort(A);
		
		// smallest 2 are A and B
		int a = A[0];
		int b = A[1];
		int c = A[6]-a-b;
		
		System.out.println(a+" "+b+" "+c);
		
	}

}
