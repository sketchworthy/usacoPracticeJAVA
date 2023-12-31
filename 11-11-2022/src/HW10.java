/* Given two integers A and B that represent the range [A, B], report the bitwise AND of all numbers 
 * in this range, inclusive.
 * Bitwise AND: in base2 representation, if both are 1, then its bitwise AND is also 1, else 0.
 */
import java.util.*;

public class HW10 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int b = in.nextInt();
		
		int ans=0;
		
		int p=0; // power
		while(true) { // find smallest power p where a<=2^p<=b. if it doesn't exist and >2, then stop
			if(pow(2,p)>=a && pow(2,p)<=b)	break;
			else if(pow(2,p)>b) {
				p--; break;
			}
			p++;
		}
		if(pow(2,p)>=a && pow(2,p)<=b && pow(2,p)!=a) System.out.println(0);
		else if(pow(2,p)>=a && pow(2,p)<=b && pow(2,p)==a) {
			// if equals a, then check if the 1 is always kept
			if(pow(2,p+1)<=b) System.out.println(0);
			else System.out.println(pow(2,p));
		}
		else { // power of 2 not in [a,b], 2^p is largest pow of 2 less than a
			// all of [a,b] therefore share a leading '1'
			// subtract the leading '1' from both bitwise ops
			ans+=pow(2,p);
			a-=pow(2,p);
			b-=pow(2,p);
			// now run the entire program again on new a and b
		}
		
		// find the number in between with the least # of 1s in its bitwise representation
		//  (aka smallest power of 2)
		//    if there r no powers of 2, the next best thing would be smallest power of 2 
		//     plus another power of 2... etc
		// once you've found this number, compare to see how many of its 1s are shared by 
		//  every other #
		
		
		in.close();
	}
	public static int pow(int base,int exponent) {
		int ans=1;
		for(int j=0;j<exponent;j++) {
			ans*=base;
		}
		return ans;
	}
}
