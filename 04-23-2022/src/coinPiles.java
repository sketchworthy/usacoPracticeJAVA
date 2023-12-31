// status: complete, but much simpler solution is just
// if 2a>b && 2b>a && (a+b)%3=0, print yes, otherwise no

import java.util.*;
public class coinPiles {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t=in.nextInt();
		for(int j=0;j<t;j++) {
			int a=in.nextInt();
			int b=in.nextInt();
			
			int min=Math.min(a, b);
			b=a+b-min; // make b the bigger one
			a=min;
//			System.out.println(a+" "+b);
			int diff = b-a; // difference
//			System.out.println(diff);
			// remove difference, 1+2's on a+b so that it becomes
			// a-(b-a)|b-2(b-a)
			a-=diff;
			b-=2*diff;
//			System.out.println(a+" "+b);
			
			if(a<0||b<0) {
				System.out.println("NO");
			}
			else if(a%3!=0) System.out.println("NO");
			else System.out.println("YES");
		}
		
		in.close();
	}
}
