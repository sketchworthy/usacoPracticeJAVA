/* USACO 2017 December Contest, Bronze Problem 3. Milk Measurement
 *  Got worried about running out of time for computation but it ended
 *  up being fine
 *  I just did a lOOOT of bashing ugh this code is messy as hecc
 * w current code, cases 4, 7 and 10 on usaco are failed :/
 * this took so long... the way i was trying to do it, confusing+tedious
 * 
 * update: bruh there r only 3 cows. how did i not realize that
 * TODO: revise in light of this update i discovered
 */
import java.util.*;
import java.io.*;
public class measuringMilk {
	public static void main(String[] args) throws Exception {
		PrintWriter out = new PrintWriter(new File("measurement.out"));
		Scanner in = new Scanner(new File("measurement.in"));
		int n=in.nextInt();
		ArrayList<CowsDay> days = new ArrayList<CowsDay>();
		ArrayList<Cow> cows = new ArrayList<Cow>();
		
//		ArrayList<String> topCows=new ArrayList<String>(); // names of top cows
		
		for(int j=0;j<n;j++) {
			CowsDay c = new CowsDay();
			c.day=in.nextInt();
			c.name=in.next();
			// check if the cow referenced is already in cows<>, add it if not
			boolean cowReffed=false;
			for(int k=0;k<cows.size();k++) {
				if(cows.get(k).name.equals(c.name)) {
					cowReffed=true;
					break;
				}
			}
			if(cowReffed==false) {
				Cow cow = new Cow();
				cow.name=c.name;
				cow.milk=7;
				cows.add(cow);
			}
//			if(!topCows.contains(c.name)) topCows.add(c.name); // fill topCows
			String s = in.next();
			c.change=Integer.valueOf(s.substring(1));
			if(s.charAt(0)=='-') c.change*=-1;
			days.add(c);
		}
		
		Collections.sort(days, (c1,c2) -> c1.day-c2.day);
		for(int j=0;j<n;j++) {
			CowsDay c=days.get(j);
//			System.out.println("day: "+c.day+". name: "+c.name+". change: "+c.change);
		}
		int changes=0; // # of changes
		
		boolean[] topCows=new boolean[cows.size()]; // topCows[x]=true, that cow's leading
		for(int j=0;j<cows.size();j++)topCows[j]=true;
		
		for(int j=0;j<n;j++) { // go through each of the days
			CowsDay c = days.get(j);
			int cowI=-1; // index of current cow in cows<> and topCows[]
			// find index of current cow in cows<> and topCows[]
			for(int k=0;k<cows.size();k++) {
				if(cows.get(k).name.equals(c.name)) {
					cowI=k;
					break;
				}
			}
			
			int projMilk=cows.get(cowI).milk+c.change; // projected value for milk once it's been changed
			// see if there'll be a leaderboard change
			int leadCowI=-1;
			boolean cLeading = false; // current cow leading?
			ArrayList<Integer> othersLeadingI = new ArrayList<Integer>();
			ArrayList<Integer> beatenIs = new ArrayList<Integer>();
			// if projMilk is less than the max other cow milk val
//			System.out.println("Trying day "+days.get(j).day+" cowname="+cows.get(cowI).name+" projMilk="+projMilk+". current milk:"+cows.get(cowI).milk);
			for(int k=0;k<cows.size();k++) {
				// update beaten
				if(k!=cowI&&projMilk<=cows.get(k).milk) {
					if(beatenIs.size()==0)beatenIs.add(k); // 1st beaten
					else { // check beatenI against new beating I
						if(cows.get(k).milk>cows.get(beatenIs.get(0)).milk) {
							beatenIs.clear();
							beatenIs.add(k);
						}
						else if (cows.get(k).milk==cows.get(beatenIs.get(0)).milk)
							beatenIs.add(k);
					}
				}
				// find current topcows
				if(topCows[k]==true) {
					leadCowI=k;
					if(k==cowI)cLeading=true;
					else if (k!=cowI)othersLeadingI.add(k);
//					else othersLeading=true;
				}
			}
			
			int topMilk=cows.get(leadCowI).milk;
			// if current cow didn't used to be on leaderboard see if they've tied or 
			//   risen above the current topMilk
			if(cLeading==false) {
				if(projMilk>=topMilk) { // surpassed or tied
					changes++;
//					System.out.println("1 day "+days.get(j).day);
					topCows[cowI]=true;
				}
				if(projMilk>topMilk) { // rose above
					for(int otherI:othersLeadingI)
						topCows[otherI]=false; // kill leads of other cows
				}
			}
			// if current cow used to be on leaderboard, see if they've dropped from it
			//   or if they've risen above a tie with someone else
			else {
				if(othersLeadingI.size()>1) { // if they were tied with others
					if(projMilk>=topMilk) { // rose above
						changes++;
//						System.out.println("2 day "+days.get(j).day);
						for(int otherI:othersLeadingI)
							topCows[otherI]=false; // kill leads of other cows
						topCows[cowI]=true;
					}
					// dropped from being tied with others
					else {
						changes++;
//						System.out.println("4 day "+days.get(j).day);
						topCows[cowI]=false;
					}
				}
				else { // if they were sole leader
					if(beatenIs.size()>0) { // fell to someone else
						// check to see if fell completely or fell to tie w someone else
						if(cows.get(beatenIs.get(0)).milk!=projMilk) { // fell completely
							topCows[cowI]=false;
						}
						for(int k=0;k<beatenIs.size();k++) { // crown other cows
							topCows[beatenIs.get(k)]=true;
						}
						changes++;
//						System.out.println("3 day "+days.get(j).day);
						
					}
				}
			}
			cows.get(cowI).milk=projMilk;
		}
//		System.out.println(changes);
		out.print(changes);
		in.close();
		out.close();
	}
	
	static class CowsDay {
		String name;
		int day;
		int change; // change from last time milk was measured
	}
	static class Cow{
		String name;
		int milk; // current amount of milk they produce
	}
}
