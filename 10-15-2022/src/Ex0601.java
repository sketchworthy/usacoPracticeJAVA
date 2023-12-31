/* implementation should be pretty simple, but my current code has a bug.
 * usaco feb 2018 bronze, taming
 */
import java.util.*;

public class Ex0601 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); //# of days
		int[] log = new int[n]; // log of breakouts
		for(int j=0;j<n;j++) {
			log[j]=in.nextInt();
		}
//		System.out.println(Arrays.toString(log));
		
		// figure out which days HAD to have breakouts
		int[] days = new int[n];
		Arrays.fill(days, 0);
		days[0]=1;
		int certainDs=0; // # of days where a breakout is certain
		int lastBi=0; // last breakout index
		boolean confusion=false; // if true, events recorded are impossible
		for(int j=0;j<n;j++) {
			System.out.println(Arrays.toString(days));
			System.out.println("j: "+j+". days thing: "+days[j-log[j]]);
			// check log
			if(log[j]!=-1) {
				days[j-log[j]]=1;
				if(j-log[j]<lastBi)confusion=true;
				lastBi=j-log[j];
				certainDs++;
			}
		}
		if(confusion)System.out.println(-1);
		else {	
			int maxB=lastBi+1;
			int minB=certainDs;
			System.out.println(minB+" "+maxB);
		}
		
		
		in.close();
	}
}
