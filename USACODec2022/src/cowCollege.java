/*
 * 
 */
import java.util.*;

public class cowCollege {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // # of cows
		long tuition[] = new long[n]; // tuitions cows are willing to pay
		for(int j=0;j<n;j++)tuition[j]=in.nextLong();
		
		Arrays.sort(tuition);
		int t=0; // index of current tuition
		long cMaxT=0; // current max profits
		int cMaxI=0; // current max profit's index for t
		
//		System.out.println(Arrays.toString(tuition));
		while(t<n) {
//			System.out.println("t: "+t+". cMaxT: "+cMaxT+". cMaxI:"+cMaxI);
			long cT=0; // calculate current profits if t were the tuition
			// profits = # of cows can pay x tuition
			// find # of cows that can pay
			if(t>0 && tuition[t]==tuition[t-1]) {
				t++;
				continue; // if this # already considered
			}
			// # of cows is n-t
			cT=(n-t)*tuition[t];
			if(cT>cMaxT) {
				cMaxT=cT;
				cMaxI=t;
			}
			t++;
		}
		System.out.println(cMaxT+" "+tuition[cMaxI]);
		
		in.close();
	}
}
