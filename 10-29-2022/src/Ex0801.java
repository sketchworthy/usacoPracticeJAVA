/* You have two coin piles containing a and b coins. On each move, you can either remove one coin from the left
pile and two coins from the right pile, or two coins from the left pile and one coin from the right pile.
Your task is to efficiently find out if you can empty both piles.
pretty easy, the idea is to always remove 2 from the greater coin pile
implementation pretty easy as well
 */
import java.util.*;

public class Ex0801 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt(); // # of queries
		for(int j=0;j<q;j++) {
			int a = in.nextInt();
			int b = in.nextInt();
			
			if((a+b)%3!=0) {
				System.out.println("NO");
				continue;
			}
			int max = Math.max(a, b);
			int min = Math.min(a, b); // deal with max & min instead of a&b
			int diff = max-min;
			if(max>2*min) {
				System.out.println("NO");
				continue;
			}
			// now (diff<min) so equalize them
			// subtract diff*2 from max and diff from min
			max-=diff*2; min-=diff; // should now both be equal
			if(max%3==0)System.out.println("YES");
			else System.out.println("NO");
			
			// if we were simulating this:
			/*
			 * for each step, we subtract 2 from the larger # and 1 
			 * from the smaller 
			 */
			
		}
		
		
		in.close();
	}
}
