/*
 * 
 */
import java.util.*;

public class bronze1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // # of cows
		char[] str = in.next().toCharArray(); // list of cows
		int[] E = new int[n]; // ending cow (inclusive)
		for(int j=0;j<n;j++) {
			E[j]=in.nextInt()-1; // shift down so that all cows numbered 0 to n-1
		}
		
		List<Integer> Gis = new ArrayList<Integer>(); // contains indices of all G cows
		List<Integer> His = new ArrayList<>(); // contains H cow indices
		for(int j=0;j<n;j++) {
			if(str[j]=='G')Gis.add(j);
			else His.add(j);
		}
		
		int cnt=0;
		for(int cow1=0;cow1<n;cow1++) { // loop through possible first-cows that are leaders
			// cow1's list contains everyone from cow1 to E[cow1] (inclusive)
			if(str[cow1]=='G') {
				int cow2=His.get(0); // cow2 must be this. add to cnt if this is possible
				// check if cow2 list contains all of his cows:
				if(E[cow2]<His.get(His.size()-1))continue;
				// check if all cows of G breed are contained in cow1's list
				if(cow1==Gis.get(0) && E[cow1]>=Gis.get(Gis.size()-1)) {
					// cow1's list is fine
					if(cow2>cow1) cnt++;
				}
				else { // cow1's list must contain cow2
					if(cow2>cow1 && cow2<=E[cow1])cnt++;
				}
			}
			else { // cow1=H
				int cow2= Gis.get(0);
				// check if cow2 list contains all of his cows:
				if(E[cow2]<Gis.get(Gis.size()-1))continue;
				// check if all H breed cows in cow1's list
				if(cow1==His.get(0)&&E[cow1]>=His.get(His.size()-1)) {
					// cow1 list fine
					if(cow2>cow1)cnt++;
				}
				else { // cow1 list must contain cow2
					if(cow2>cow1 && cow2 <=E[cow1])cnt++;
				}
			}
		}
		
		System.out.println(cnt);
		
		// for each cow1, check what cow2 leader options are from cow1 onwards
		//  if from 1st cow onward all cows of that breed are contained
		//    then cow1's list is fine, and we must choose a cow2 ahead of cow1 such that
		//    cow2 contains all of their cows in there
		//  else cow1 must contain cow2, and cow2 must contain all of their cows

		in.close();
	}
}
