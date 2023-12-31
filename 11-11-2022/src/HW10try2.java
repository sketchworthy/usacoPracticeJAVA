/* Given two integers A and B that represent the range [A, B], report the bitwise AND of all numbers 
 * in this range, inclusive.
 * Bitwise AND: in base2 representation, if both are 1, then its bitwise AND is also 1, else 0.
 * implements looping of try1
 * this code does not work.
 * 
 * the right way to do this is: look at 31-bit rep of #s, from the 30th bit (31th bit tells if pos/neg)
 * go down the line. if they're both 1 then that bit is always the same. if they ever begin to differ
 * then every further bit is also different.
 */
import java.util.*;
import java.io.*;

public class HW10try2 {
	public static void main(String[] args) throws Exception{
		Scanner in2=new Scanner(System.in);
		String fileName=in2.next();
		in2.close();
		Scanner in = new Scanner(new File(fileName));
		int a = in.nextInt();
		int b = in.nextInt();
		
		int ans=0;
		
		
		while(a>0&&b>0) {
			int p=0; // power
			
			while(true) { // find smallest power p where a<=2^p<=b. if it doesn't exist and >2, then stop
				if(pow(2,p)>=a && pow(2,p)<=b)	break;
				else if(pow(2,p)>b) {
					p--; break;
				}
				p++;
			}
			if(pow(2,p)>=a && pow(2,p)<=b && pow(2,p)!=a) {
//				System.out.println(ans);
				break;
			}
			else if(pow(2,p)>=a && pow(2,p)<=b && pow(2,p)==a) {
				// if equals a, then check if the 1 is always kept
				if(pow(2,p+1)<=b) {
//					System.out.println(ans);
					break;
				}
				else {
//					System.out.println(ans+pow(2,p));
					ans+=pow(2,p);
					break;
				}
			}
			else { // power of 2 not in [a,b], 2^p is largest pow of 2 less than a
				// all of [a,b] therefore share a leading '1'
				// subtract the leading '1' from both bitwise ops
				ans+=pow(2,p);
				a-=pow(2,p);
				b-=pow(2,p);
				// now run the entire program again on new a and b
			}
		}
		System.out.println(ans);
		in.close();
		
		// basic idea:
				// find the number in between with the least # of 1s in its bitwise representation
				//  (aka smallest power of 2)
				//    if there r no powers of 2, the next best thing would be smallest power of 2 
				//     plus another power of 2... etc
				// once you've found this number, compare to see how many of its 1s are shared by 
				//  every other #
		
		
	}
	public static int pow(int base,int exponent) {
		int ans=1;
		for(int j=0;j<exponent;j++) {
			ans*=base;
		}
		return ans;
	}
}
