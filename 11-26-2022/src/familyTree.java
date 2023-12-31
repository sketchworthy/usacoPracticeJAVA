/* USACO 2018 Bronze p3 Family Tree
 * my current idea is to check the level of c1 and c2 on the tree, then check if c2 is directly
 * related to c1
 * 
 * OUTLINE:
 * cases to consider:
- B is A's sibling
- 1. B is A's direct ancestor
- B is one of A's direct ancestor's siblings
- 4. B is somehow related to A: COUSINS
- 5. NOT RELATED

how i'll find these cases:
system: String[][] 'levels' array. String[x][0]is the name, [x][1] is the level.0 is A's level. 
1 is A's mom's level. etc. After initialization, all of [x][1]'s should be made -1 until determined.

1. first, traverse upwards through direct descendants. once the most ancient ancestor of A has been 
found, see if B is any of those now-determined as direct descendents. if not, continue.
2. fill out immediate children of already-found descendants (maybe list down the indices of descendants
already determined, then loop thru them and determine levels of their children). if B becomes 
determined, and B is lvl 0, then B is A's sibling. if B is determined but not 0 lvl, they're an aunt 
of some sort.
3. recursively fill out everyone's levels as much as possible. if B even has a level, then B is a 
cousin of A. if B doesn't have a level (at -1,) then they're not related.
 ended up only implementing 1 and 2. step 3 did not do, and as such i only have 8/15 test cases
 */
import java.util.*;
import java.io.*;
public class familyTree {
	public static void main(String[] args) throws Exception{
		PrintWriter out = new PrintWriter(new File("family.out"));
		Scanner in = new Scanner(new File("family.in"));
		
		int n = in.nextInt();
		String c1=in.next(); // cow 1 name
		String c2=in.next(); // cow 2 name
		
		String[][] relations = new String[n][2]; // relations[x][0] is always mom of relations[x][1]
		
		for(int j=0;j<n;j++) {
			relations[j][0]=in.next();
			relations[j][1]=in.next();
		}
		
		String[][] cows = new String[2*n][2]; // cow[x][0] is the cow's name. cow[x][1] is cow's level
		// fill cows[][] with the names of the cows and set all the levels to -1 for now
		
		int i=0; // pointer for relations[][]
		for(int j=0;j<2*n;j+=2) { // for index in cows[][]
			cows[j][1]="-1"; cows[j+1][1]="-1"; // set all the levels to -1
			cows[j][0]="ajda"; cows[j+1][0]="ajda"; // filler so that it isn't null
			
			
			// fill cows[][] with the names of the cows from relations[][]
			// if relations[i][0] or relations[i][1] isn't in cows[][] already, put them in
			// check relations[i][0]
			boolean contains=false;
			for(int k=0;k<j;k++) { // looking to see if they're already in cows[][]
				if(relations[i][0].equals(cows[k][0])) {
					contains=true;
					break;
				}
			}
			if(contains==false)cows[j][0]=relations[i][0];
			
			// check relations[i][1]
			contains=false;
			for(int k=0;k<j;k++) { // looking to see if they're already in cows[][]
				if(relations[i][1].equals(cows[k][0])) {
					contains=true;
					break;
				}
			}
			if(contains==false)cows[j+1][0]=relations[i][1];
			i++;
		}
//		for(int j=0;j<2*n;j++) {
//			System.out.println(Arrays.toString(cows[j]));
//		}
		
		// put c1 as lvl 0 in cows[]
				for(int j=0;j<2*n;j++) {
					if(cows[j][0].equals(c1)) {
						cows[j][1]="0";
						break;
					}
				}
				
		// find c1 & c2's respective 'levels' on the tree, where c1 is lvl0, c1's mom lvl1, etc.
		
		// first check if c2 is a direct ancestor of c1
		String momN = ""; // mother cow's name
		String childN=c1; // daughter cow's name
		
		while(true) {
			boolean foundMom=false;
			// find momN by searching relations[][]
			for(int j=0;j<n;j++) {
				if(relations[j][1].equals(childN)) {
					momN=relations[j][0];
					foundMom=true;
					break;
				}
			}
			if(!foundMom)break; // break if mom doesn't exist, child is the oldest
			
			// add mom's level: find level of childN, and subsequently momN, by name in cows[][]
			for(int j=0;j<2*n;j++) {
				if(cows[j][0].equals(childN)) {
//					System.out.println(j+" is child's index, mom's name is "+ momN);
					// j is the child's index
					// cows[j][1] (to int) is the child's level, cows[j][1]+1 is the mom's level
					for(int k=0;k<2*n;k++) {
						if(cows[k][0].equals(momN)) {
							cows[k][1]=String.valueOf(Integer.valueOf(cows[j][1])+1); // set mom
							break;
						}
					}
//					System.out.println("hi, lookin at mom as "+momN);
					break;
				}
			}
			childN=momN;
			momN="";
//			System.out.println("do i exist?");
		}
		int c2i=-1; // index of c2
		for(int j=0;j<2*n;j++) {
			if(cows[j][0].equals(c2)) {
				c2i=j;
				break;
			}
		}
		
		if(c2i==-1) {
			out.print("NOT RELATED");
			in.close();
			out.close();
			return;
		}
		
		// now, see if c2 is direct ancestor
		if(!cows[c2i][1].equals("-1")) { // if c2's level became determined
			// c2 is a direct ancestor!
			int c2lvl=Integer.valueOf(cows[c2i][1]);
			if(c2lvl==1)out.println(c2+" is the mother of "+c1);
			else if(c2lvl==2)out.println(c2+" is the grand-mother of "+c1);
			else {
				out.print(c2+" is the ");
				for(int j=0;j<c2lvl-2;j++) {
					out.print("great-");
				}
				out.print("grand-mother of "+c1);
			}
		}
		else {
			// find if B is A's sibling or aunt of sm sort
			ArrayList<Integer> determined = new ArrayList<Integer>();
			//^ indices of determined cows
			for(int j=0;j<2*n;j++) {
				if(cows[j][1].equals("-1")) {
					determined.add(j);
				}
			}
			// determine levels of all children of determined<> cows
			for(int j=0;j<determined.size();j++) {
				momN=cows[j][0]; // current cow's name
				int lvl=Integer.valueOf(cows[j][1]); // current cow's level
				// for all of current cow's children
				for(int k=0;k<n;k++) {
					if(relations[k][0].equals(momN)) {
						childN=relations[k][1];
						// change child's lvl in cows[][] to lvl-1
						for(int x=0;x<2*n;x++) {
							if(cows[x][0].equals(childN)) {
								cows[x][1]=String.valueOf(lvl-1);
							}
						}
						
					}
				}
				
			}
			
			// see if c2 is c1's sibling, aunt, or other
			if(!cows[c2i][1].equals("-1")) {
				if(cows[c2i][1].equals("0"))out.print("SIBLINGS"); // siblings
				else { // c2 is c1's aunt, g-aunt, etc.
					if(cows[c2i][1].equals("1"))out.print(c2+" is the aunt of "+c1);
					else {
						out.print(c2+" is the ");
						for(int j=0;j<Integer.valueOf(cows[c2i][1])-1;j++)
							out.print("great-");
						out.print("aunt of "+c1);
					}
				}
			}
			else {
				out.println("NOT RELATED");
			}
		}
		in.close();
		out.close();
	}
}
