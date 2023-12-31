/* USACO 2015 dec bronze, Contaminated Milk
 * tedious but straightforward, all things considered. pretty easy, need to read problem carefully.
 * 3/10 test cases, this code doesn't quite work
 */
import java.util.*;
import java.io.*;

public class badMilk {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("badmilk.in"));
		PrintWriter out = new PrintWriter(new File("badmilk.out"));
		
		int n=in.nextInt(); // # of friends
		int m=in.nextInt(); // # of milks
		int d=in.nextInt(); // drinks
		int s=in.nextInt(); // sick ppl
		
		int[][] drinks = new int[d][3]; // person drinks[x][0] drinks milk drinks[x][1] at time
		// drinks[x][2]
		for(int j=0;j<d;j++) {
			drinks[j][0]=in.nextInt()-1;
			drinks[j][1]=in.nextInt()-1;
			drinks[j][2]=in.nextInt();
		}
		
		int[][] sicks = new int[s][2]; // person sicks[x][0] gets sick at time sicks[x][1]
		for(int j=0;j<s;j++) {
			sicks[j][0]=in.nextInt()-1;
			sicks[j][1]=in.nextInt();
		}
		
		boolean[] iOptions=new boolean[m]; // possibilities for indices of bad milk
		// if iOptions[x]=false, then x can NOT be the bad milk
		Arrays.fill(iOptions, true);
		
		// for each person who got sick, remove all milks they did NOT drink at some point earlier
		for(int x=0;x<s;x++) { // for each person who got sick
			int t=sicks[x][1]; // time person x got sick
			
			ArrayList<Integer> drankMilks = new ArrayList<Integer>();
			// find all drinks they drank before time t
			for(int j=0;j<d;j++) {
				if(drinks[j][0]==x) { // they drank it
					if(drinks[j][2]<t) { // before time t
						drankMilks.add(drinks[j][1]); // add milk to the milks drank
					}
				}
			}
			// remove all milks NOT in drankMilks from iOptions
			for(int j=0;j<m;j++) {
				if(!drankMilks.contains(j)) { // not in drankMilks
					iOptions[j]=false; // remove from bad milk possibilities
				}
			}
		}
		
		int maxCnt=0;
		
		for(int j=0;j<m;j++) {
			if(iOptions[j]==true) {
				// j is a possible bad milk index
				// count the # of ppl who drank j
				int cnt=0; // # of ppl who drank this bad milk
				
				boolean[] pDrank = new boolean[n]; // ppl who drank
				for(int k=0;k<d;k++) {
					if(drinks[k][1]==j) { // drank bad milk
						pDrank[drinks[k][0]]=true;
					}
				}
				for(int k=0;k<n;k++) {
					if(pDrank[k])cnt++;
				}
				maxCnt=Math.max(maxCnt, cnt);
			}
		}
		
		out.println(maxCnt);
		out.close();
		in.close();
	}
}
