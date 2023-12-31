/* Given a positive integer n, you can apply one of the following operations:
If n is even, replace n with n/2.
If n is odd, replace n with n-1 OR n+1
Report the minimum number of operations needed for n to become 1.*/
// status: complete+correct
import java.util.*;
public class intReplacement {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int k = in.nextInt(); // # of test cases
		for(int j=0;j<k;j++) {
			int n=in.nextInt();
			System.out.println(replace(n));
		}
		
		in.close();
	}
    
	static int replace(int n) {
		if(n==2) return 1;
		if(n==3)return 2;
		if(n==4)return 2;
		if(n==5)return 3;
		if(n==6)return 3;
		if(n==7)return 4;
		
		if(n%2==0) {
			return replace(n/2)+1; // if even
		}
		if(n%4==1) {
			return replace(n-1)+1;
		}
		else { // if odd, mod 4 is 3
			return replace(n+1)+1;
		}
	}
}
