/* From the standard input (keyboard), read two integers A and B, report the largest 
multiple of B that is no more than A. Assume the integers A and B are in the range [1, 10^6]. */
import java.util.*;
public class multipleTianyiZhou {
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
		
		// Don't display the prompt asking user to enter two integers!
		
		int A = in.nextInt(); // upper bound
		int B = in.nextInt();
		
		in.close();

        // find the largest multiple of B (<=A)
		int multiple = A/B;
		System.out.println(multiple*B);
    }
}
