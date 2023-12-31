/*ran out of time for last 4 test cases
 * ^edit for above: ----
 * i was timing out over the STUPIDEST thing
 * when i was printing i printed out chars 1-by-1
 * instead of printing them all at once.
 * 
 * AAAAHHHHGHHHHH
 * ----------------------
 * 
 * basic idea: fill up the grass with '.'s first. Then, for each groups of Gs and Hs,
 * add 1 
 * 
 * 
 * note that current code has an error:
1
6 1
HHHHHH
returns the wrong thing
 */
import java.util.*;

public class feedingTheCows {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int i=0;i<t;i++) {
			int n=in.nextInt(); // # of cows
			int k = in.nextInt(); // how far every cow's willing to move
			char[] cows = in.next().toCharArray();
			
			char[] ans = findConfig(n,k,cows);
			// find # of patches of grass there are
			int cnt = 0;
			for(int j=0;j<n;j++) {
				if(ans[j]=='G' || ans[j]=='H')cnt++;
			}
			System.out.println(cnt);
			System.out.println(ans);
//			for(int j=0;j<n;j++) {
//				System.out.print(ans[j]);
//			}
//			System.out.println();
		}
		in.close();
	}
	public static char[] findConfig(int n, int k, char[] cows) {
		// returns a configuration of grass patches satisfying conditions
		if(k==0)return cows; // cows not willing to move
		
		char[] cConfig = new char[n];
		Arrays.fill(cConfig, '.');
		
		// check if cows[] only consists of 1 cow type
		boolean allSame=true;
		int firstG=0;
		int firstH=0;
		char c='G';
		if(cows[0]=='G') { // look for an 1st instance of H
			int i=1;
			while(i<n) {
				if(cows[i]=='H') {
					firstH=i;
					allSame=false;
					break;
				}
				i++;
			}
		}
		else {
			c='H';
			int i=1;
			while(i<n) {
				if(cows[i]=='G') {
					firstG=i;
					allSame=false;
					break;
				}
				i++;
			}
		}
		if(allSame) {
			int rangeLen=2*k+1;
			int numRanges=n/rangeLen;
			if(n%rangeLen!=0)numRanges++;
			for(int j=0;j<numRanges-1;j++) {
				cConfig[j*rangeLen+k]=c;
			}
			cConfig[n-1-k]=c;// special last case
			return cConfig;
		}
		
		ArrayList<Integer> GRs = new ArrayList<Integer>();
		// consists of stuff like (0,5,11)--there must be a G within each
		//   range (inclusive). each range is of size k*2+1. G ranges
		// note that it represents ([0,4][5,9][11,15])
		ArrayList<Integer> HRs = new ArrayList<Integer>();
		// H ranges
		
		// fill GRs & HRs
		GRs.add(firstG); // starting index of current G range
		HRs.add(firstH); // starting index of current H range
		for(int j=0;j<n;j++) {
			if(cows[j]=='G') { // check if considered within current G range
				if (j>GRs.get(GRs.size()-1)+2*k)GRs.add(j);
			}
			else { // check if considered within current H range
				if (j>HRs.get(HRs.size()-1)+2*k)HRs.add(j);
			}
		}
		
		// create cConfig so that GRs and HRs are both satisfied
		boolean[] lastCases = new boolean[2];
		int lastGrange=GRs.get(GRs.size()-1);
		if(lastGrange+2*k>=n)	{
			GRs.remove(GRs.size()-1);
			lastCases[0]=true;
		}
		int lastHrange=HRs.get(HRs.size()-1);
		if(lastHrange+2*k>=n)	{
			HRs.remove(HRs.size()-1);
			lastCases[1]=true;
		}
		// for each range in GRs
		for(int i:GRs) {
			cConfig[i+k]='G';
		}
		for(int i:HRs) {
			cConfig[i+k]='H';
		}
		// look at special last cases
		if(lastCases[0]&&(!lastCases[1])) { // only G considered
			if(cConfig[n-1-k]=='.') cConfig[n-1-k]='G';
			else cConfig[n-k]='G';
		}
		else if((!lastCases[0])&&lastCases[1]) { // only H considered
			if(cConfig[n-1-k]=='.') cConfig[n-1-k]='H';
			else cConfig[n-k]='H';
		}
		else if(lastCases[0]&&lastCases[1]) { // both cases are considered
			if(lastHrange>lastGrange) {
				cConfig[n-1-k]='G';
				cConfig[n-k]='H';
			}
			else {
				cConfig[n-1-k]='H';
				cConfig[n-k]='G';
			}
		}		
		
		return cConfig;
	}
}
