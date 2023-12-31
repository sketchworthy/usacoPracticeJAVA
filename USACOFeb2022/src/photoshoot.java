import java.util.*;
public class photoshoot {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int N = kb.nextInt(); // # of cows
		
		int[] start = new int[N]; // starting position
		int[] end = new int[N]; // ending position
		
		// fill arrays
		for (int i=0;i<N;i++) start[i]=kb.nextInt();
		for (int i=0;i<N;i++) end[i]=kb.nextInt();
		
		int mods = 0; // initialize min # of modifications
		
		for (int i=0;i<N;i++) { // for index in start[] position
			int cow = start[i]; // cow we're looking for
			// find index of cow in end[] position
			for (int j=0;j<N;j++) {
				if (end[j]==cow) { // j is index of cow in end[]
					if (j>i) { // if cow needs to move right
						// check to see if distance needed to move right is larger than current mods
						if (j-i>mods) mods=j-i;
					}
				}
			}
		}
		
		System.out.println(mods);
		kb.close();
	}
}

/* idea:
 * if the cow needs to move left let it
 * if it needs to move right skip it
 */

// idea v2: min modifiations needed is the longest distance a cow needs to move right