/* idea somewhat hard to come by/swallow, implementation decent
 * basic idea: count to see if die A or die B wins. then
 * literally just brute force check all 10^4 possibilities. i feel freakin cheated
 */
import java.util.*;

public class Ex0105v2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt(); // # of test cases
		for(int i=0;i<t;i++) {
			int[] dA = new int[4];
			int[] dB = new int[4];
			for(int j=0;j<4;j++)
				dA[j]=in.nextInt();
			for(int j=0;j<4;j++)
				dB[j]=in.nextInt();
			// check if die 1 beats die 2
			if(beats(dA,dB)==1) { // A beats B
				// if it does, then see if theres a die that beats die 1 and is beaten by die 2
				if(find(dA,dB)==true) System.out.println("yes");
				else System.out.println("no");;
			}
			else if(beats(dA,dB)==2){
				// otherwise find die that beats die 2 and is beaten by die 1
				if(find(dB,dA)==true) System.out.println("yes");
				else System.out.println("no");;
			}
			else // neither beats the other
				System.out.println("no");
		}
		in.close();
	}
	public static int beats(int[] dA, int[] dB) {
		// returns 1 if dice A beats dB, 2 if dB beats dA, 0 if neither beats the other
		int AbeatCnt = 0;
		int BbeatCnt = 0;
		for(int j=0;j<4;j++) {
			for(int k=0;k<4;k++) {
				if(dA[j]>dB[k]) AbeatCnt++;
				else if(dA[j]<dB[k]) BbeatCnt++;
			}
		}
		if(AbeatCnt>BbeatCnt) return 1; // A beats B
		else if(BbeatCnt>AbeatCnt) return 2;
		return 0;
	}
	public static boolean find(int[] dA, int[] dB) {
		// assumes A beats B
		// returns true if there exists a dC that beats A and is beaten by B
		for(int a=1;a<=10;a++) {
			for(int b=1;b<=10;b++) {
				for(int c=1;c<=10;c++) {
					for(int d=1;d<=10;d++) {
						int[] dC = {a,b,c,d};
						if(beats(dC,dA)==1 && beats(dB,dC)==1) return true;
					}
				}
			}
		}
		return false;
	}
}
