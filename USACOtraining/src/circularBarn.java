/* USACO 2022 Dec Silver Problem 2 NOT COMPLETE
 * http://usaco.org/index.php?page=viewproblem2&cpid=1255
 */
import java.util.*;

public class circularBarn {
	static TreeSet<Integer> primes = new TreeSet<>();
	public static void main(String[] args) {
		// fill primes<>
		for(int j=2;j<=5e6;j++) {
			if(isPrime(j))primes.add(j);
		}
		
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int i=0;i<t;i++) {
			int n = in.nextInt(); // # of rooms
			int[] roomCows = new int[n]; // roomCows[x] is # of cows in room x
			for(int j=0;j<n;j++)roomCows[j]=in.nextInt();
			if(player1wins(n,roomCows))System.out.println("Farmer John");
			else System.out.println("Farmer Nhoj");
		}
		in.close();
	}
	public static boolean player1wins(int n, int[] roomCows) { // true if P1 wins, else false
		if(n==1) { // if there's 1 rm
			if (roomCows[0]%4==0) return false; // if # of cows is 4k, P2 wins
			else return true; // else if 1 rm and # of cows ISN'T 4k, P1 wins
		}
		if(primes.contains(roomCows[0]))return true; // if 1st rm has prime # of cows P1 wins
		
		
	// if P2 reduces a room's cowNum to 0, P1 MUST win before they come back to the rm
		// P1 wins if a room they walk into is ever left as a prime or as 1
	// if P2 reduces a room to prime or 1, they MUST win before they come back to the rm
		
		int[][] minToWin = new int[n][2]; // rm x takes a min of minToWin[x][0] circles
		 // around the barn for P{minToWin[x][1]} to win
		int minI=0; // index of winning room (aka room where ppl will win in min circles)
		for(int j=0;j<n;j++) {
			if(roomCows[j]%4==0) minToWin[j][1]=2; // fill minToWin[j][1]
			else minToWin[j][1]=1;
			// fill minToWin[j][0]
			// if P1 destined to win, P1 wants to win as fast as possible while P2 stalls
			if(minToWin[j][1]==1) { // roomCows[j] NOT divisible by 4
				if(roomCows[j]%4==2) minToWin[j][1]=roomCows[j]/4+1; // note: mb INCORRECT
				else {
					int remainder = roomCows[j]%4;
					int p = primes.floor(roomCows[j]);
					while((p%4!=remainder || !primes.contains(p)) && p>3) {
						p-=4;
					}
					if(p<=3)p=remainder;
					minToWin[j][1]=(roomCows[j]-p)/4+1;
//					if(roomCows[j]>=13)minToWin[j][1]=(roomCows[j]-13)/4+1; // note: mb INCORRECT
//					else if(roomCows[j]>=5)minToWin[j][1]=(roomCows[j]-5)/4+1; 
				}
			}
			// if P2 destined to win, P2 tries to win as fast as possible while P1 stalls
			else { // roomCows[j] IS divisible by 4
				minToWin[j][0]=roomCows[j]/4+1; // note: mb INCORRECT
			}
			
			// update minI
			if(minToWin[j][0]<minToWin[minI][0]) minI=j;
		}
		if(minToWin[minI][1]==1)return true;
		return false; 
	}
	
	public static boolean isPrime(int x) { // true if x is prime, else false
		int max = (int)Math.sqrt(x);
		for(int p:primes) {
			if(p>max)break;
			if(x%p==0)return false;
		}
		return true;
	}
}
