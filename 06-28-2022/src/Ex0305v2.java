/* done fairly easily
 * basic idea: create a timeline and branch from the initial point on the timeline
 * i procastinated a lot on this, but overall it was decently easy. had to do some
 * debugging with string equality being .equals() instead of ==
 */
import java.util.*;

public class Ex0305v2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		String[][] timeline = new String[n+1][2]; //timeline[x][0] is xth cow's name,
		// timeline[x][1] is xth cow's place in the timeline in relation to bessie
		// bessie is 0, if someone was born 1 year ahead they would be 12
		String[] months = {"Ox","Tiger","Rabbit","Dragon","Snake","Horse","Goat",
				"Monkey","Rooster","Dog","Pig","Rat"};
		timeline[0][0]="Bessie";
		timeline[0][1]="0";
		for(int j=1;j<n+1;j++) { // each line of input
			timeline[j][0]=in.next();
//			System.out.println(timeline[j][0]);
			in.next(); // born
			in.next(); // in
			String relation = in.next(); // previous or next
			String sign = in.next(); // Dragon, Monkey, Ox, etc.
			// convert sign to an integer
			int s=-1;
//			System.out.println(sign);
			for(int k=0;k<12;k++) {
				if(months[k].equals(sign)) s=k;}
			in.next(); // year
			in.next(); // from
			String cName = in.next(); // name of cow this one is based off of
			
			// find timeline[j][1]
			// find the place in the timeline of cow cName
			int anchor = 0;
			int cAindex = 0; // anchor cow's index
			for(int k=0;k<j;k++)
				if(timeline[k][0].equals(cName)) {
					anchor = Integer.parseInt(timeline[k][1]);
					cAindex=k;
				}
			int cs = anchor%12; // sign of the anchoring cow
			if(relation.equals("previous")) {
				int diff = (cs-s+24)%12; // difference between cows in years
//				System.out.println("prev "+diff);
				timeline[j][1]=Integer.toString(Integer.parseInt(timeline[cAindex][1])-diff);
			}
			else { // next
				int diff = (s-cs+24)%12;
//				System.out.println("next "+diff);
				timeline[j][1]=Integer.toString(Integer.parseInt(timeline[cAindex][1])+diff);
			}
//			System.out.println(timeline[j][1]);
			if(timeline[j][0].equals("Elsie")) {
				System.out.println(Math.abs(Integer.parseInt(timeline[j][1])));
				break;
			}
		}
		in.close();
	}
}