/*
 * this is wrong, order of operations must be followed
 * see try2 for successful code 
 */
import java.util.*;

public class HW02 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		
		int sum=0;
		for(int j=0;j<t;j++) {
			int n = in.nextInt();
			long ans = n;
			int op=1; // operation. op=1 means *, 2 /, 3 +, 4 -
			for(int k=n-1;k>=0;k--) {
				if(op==1)ans*=k;
				else if(op==2)ans/=k;
				else if(op==3)ans+=k;
				else ans-=k;
				op++;
				if(op==5)op=1;
				System.out.println(ans);
			}
			System.out.println("ans: "+ans);
			sum+=ans;
		}
		System.out.println(sum);
		in.close();
	}
}