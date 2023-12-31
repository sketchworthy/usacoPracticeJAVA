/* whew! ideas are honsestly pretty simple, implementation was annoying
 *  bc i kept making dumb mistakes like *cough* FORGETTING TO ACCEPT
 *   USER INPUT.
 * but other than that, ez :D
 */
import java.util.*;
import java.io.*;

public class HW06 {
	public static void main(String[] args) throws Exception {
		Scanner in2 = new Scanner(System.in);
		String fileName=in2.next();
		in2.close();
		Scanner in = new Scanner(new File(fileName));
		int n=in.nextInt(); // # of gas stations
		int[] g = new int[n]; // g[x]=amount of gas at xth gas station
		int[] c = new int[n]; // c[x]=cost of gas 
		//                      to travel from xth station to x+1th station
		
		for(int j=0;j<n;j++) {
			g[j]=in.nextInt();
		}
		for(int j=0;j<n;j++) {
			c[j]=in.nextInt();
		}
//		System.out.println(Arrays.toString(g));
//		System.out.println(Arrays.toString(c));
		
		boolean possible = false;
		// test each starting gas station
		for(int si=0;si<n;si++) { // for each starting index
			boolean possibleSI=true;
			int cg=g[si]; // current # of gas
			for(int j=1;j<=n;j++) { // go around the circle until its impossible
				// check to see if your current gas will take to next g[]
//				System.out.println("Starting index: "+si+". Cg: "+cg+". index where ur getting gas: "+((si+j-1)%n));
				if(c[(si+j-1)%n]>cg) { // not enough gas
					possibleSI=false;
//					System.out.println("fail");
					break;
				}
				else { // enough gas!
//					System.out.println("Starting index: "+si+". Enough gas to go to cg: "+((si+j)%n));
					// update current amnt of gas
					cg-=c[(si+j-1)%n]; // gas lost to travel
					cg+=g[(si+j)%n]; // gas gained at gas station
				}
			}
			if(possibleSI==true) {
				possible=true;
//				System.out.println(si+" possible");
				System.out.println(si);
				break;
			}
		}
		if(possible==false)System.out.println(-1);
		
		in.close();
	}
}
