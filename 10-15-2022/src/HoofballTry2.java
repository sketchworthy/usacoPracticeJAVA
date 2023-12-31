/*
 * Basic idea: detect how many descending patterns there are in the
 *  differences of the cow distances, accounting for the fact that if
 *  2 cows are the same distance, left cow is picked.
 *  
 *  So basically detecting a difference in if the next cow is increasing
 *  or decreasing in a sequence of already strictly increasing or strictly
 *  decreasing.
 */
import java.util.*;
import java.io.*;
public class HoofballTry2 {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		ArrayList<Integer> distances = new ArrayList<Integer>();
		for(int j=0;j<n;j++) distances.add(in.nextInt());
		Collections.sort(distances);
		
		boolean startI=true;
		//^ when startI is true, first cow has not yet been passed a ball
		//  when it's false, first cow has been passed to, start from 2nd cow
		
		int cnt=0;
		while(!distances.isEmpty()) {
			// start from 1st cow, see max amnt of cows you can pass to w 1 ball
			//   aka: remove a block
			int s = distances.size();
			if(distances.size()<=2) { // if size <=2
				cnt++;
				distances.clear();
				break; // done!
			}

			// otherwise if size is >2 (more than 2 ppl)
			if(startI==true) {
				int cI=1; // current index of cow
				if(distances.get(cI)-distances.get(cI-1)>
					distances.get(cI+1)-distances.get(cI)){//if diff(1,0)>diff(2,1)
					// ideally, ball is passed to index 0 cow
					
					// remove a decreasing block from left -> right
					cI++; // now cI=2
					while(distances.get(cI-1)>distances.get(cI)) {
						cI++; // if diff(2,1)>diff(3,2) cI becomes 3
						    // if not [aka diff(3,2)>=diff(2,1)], cI stays 2
					      // then you only remove from d(0) to d(cI-2)
						// (inclusive), and let d(cI-1) be present but make startI false
					}
					for(int j=0;j<cI-1;j++) distances.remove(0); // remove first cI-1 elements
					startI=false;
				}
				else { // if diff(1,0)<=diff(2,1)
					// ball holder travels to 2nd cow
					// wait can you just delete cow 0?? let's try...
//					distances.remove(0); // TODO check if this ends correctly
					startI=false; // edit: TDO check if its actually this one that's correct
				}
			}
			else {//1st cow has been passed to, but distance still important knowledge
				int cI=2;
				if(distances.get(cI)-distances.get(cI-1)>
				distances.get(cI+1)-distances.get(cI)){//if diff(2,1)>diff(3,2)
				// ideally, ball is passed to index 1 cow (left cow)
				
				// remove a decreasing block from left -> right
				}
				
			}
			System.out.println(); // Print distances array and startI
			for(int j=0;j<distances.size();j++) {
				System.out.print(distances.get(j)+" ");
			}
//			System.out.println(startI);
		}
		System.out.println(cnt);
		
		
		
		PrintWriter out = new PrintWriter(new File("hoofball.out"));
		out.print(cnt+1);
		out.close();
//		System.out.println(cnt+1);
		
		
		
		in.close();
	}
}

// TODO: make sure all OUTOFBOUNDS exceptions get accounted for