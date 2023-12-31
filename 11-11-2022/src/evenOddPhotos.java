/* USACO Jan 2021 Bronze
 * Order n numbers into as many groups as possible so that sum of numbers in a group alts between even
 *  and odd. return max # of groups
 */
import java.util.*;

public class evenOddPhotos {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n=in.nextInt();
		int oddCnt=0; // # of odd IDs 
		for(int j=0;j<n;j++) {
			if(in.nextInt()%2==1) oddCnt++;
		}
		int evenCnt=n-oddCnt;
		
//		System.out.println("evenCnt: "+evenCnt+". oddCnt: "+oddCnt);
		
		// case 1: only evens
		if(oddCnt==0)System.out.println(1);
		// case 2: some of both, more odds than evens
		// aka if all evens can be used and then be accompanied by odds, and still have odds left over
		else if(evenCnt<oddCnt) {
			// use up as many evens as possible to make their own even groups
			int ans=evenCnt*2; // eo eo eo...
			oddCnt-=evenCnt;
//			System.out.println("evenCnt: "+evenCnt+". oddCnt: "+oddCnt);

			// divvy up the other odds
			if(oddCnt%3==0) { // 2 odds=1 even, can be divided into [(oo)o][(oo)o]...
				System.out.println(ans+oddCnt*2/3);
			}
			else if(oddCnt==2) {
				System.out.println(ans+1);
			}
			else if(oddCnt%3==2) { // divides into [(oo)o][(oo)o]...[(oo)o](oo)
				System.out.println(ans+(oddCnt-2)*2/3+1);
			}
			else if(oddCnt==1) { // one eo group becomes (eoo), removing 1 group
				System.out.println(ans-1);
			}
			else if(oddCnt==4) {
				System.out.println(ans+1);
			}
			else { // oddCnt mod 3 is 1
				// divides into [(oo)o][(oo)o]...[(oooo)]
			System.out.println(ans+(oddCnt-4)*2/3+1);
			}
		}
		// case 3: same amnt of both
		else if (evenCnt==oddCnt) {
			System.out.println(n); // e o e o
		}
		// case 4: some of both, more evens than odds
		else if(evenCnt>oddCnt) {
			System.out.println(oddCnt*2+1);
		}
		// case 5: only odds
		else if(evenCnt==0) {
			if(oddCnt%3==0) { // 2 odds=1 even, can be divided into [(oo)o][(oo)o]...
				System.out.println(oddCnt*2/3);
			}
			else if(oddCnt%3==2) { // divides into [(oo)o][(oo)o]...[(oo)o](oo)
				System.out.println((oddCnt-2)*2/3+1);
			}
			else { // oddCnt mod 3 is 1
				// divides into [(oo)o][(oo)o]...[(oooo)]
				System.out.println((oddCnt-4)*2/3+1);
			}
		}
		
		in.close();
	}
}
