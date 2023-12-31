package oldUsacoProbs;
/* USACO 2022 Mar Bronze Problem 1: Photoshoot COMPLETE
 * 
 * key idea: simplify, simplify, simplify. Once you understand the problem's logic,
 * it's SO ez.
 * basically, the blocks of "HH" and "GG" cows can be ignored, bc they
 * don't change the # of G cows on evens you have even when reversed. what u
 * want to care about are the "HG" and "GH" blocks. also, notice that adjacent
 * 'superblocks' of HGHGHG... and GHGHGH... can be summarized as 1 HG or 1 GH.
 * remove the "HG" blocks from the end. then, it can be shown/the pattern can be 
 * noticed that the # of blocks left is min # of reversals needed. implementing
 * that algorithm is also super easy.
 * 
 * solved/implemented easily. hardest part is understanding & simplifying og prob.
 */
import java.util.*;

public class photoshoot {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // # of cows, must be even
		char[] cows = in.next().toCharArray();
		ArrayList<String> str = new ArrayList<>();
		String lastBlock="";
		for(int j=0;j<n;j+=2) { // trim str to only contain distinct 2-cow blocks of HG & GH
			String block = cows[j]+""+cows[j+1];
			if((block.equals("HG")||block.equals("GH")) && (!block.equals(lastBlock))) {
				str.add(block);
				lastBlock=block;
			}
		}
		// remove 'HG' blocks from end
		int lastI=str.size()-1;
		while(str.get(lastI).equals("HG") && lastI>=0) {
			str.remove(lastI);
			lastI--;
		}
		// return # of blocks left in str<>
		System.out.println(str.size());
		in.close();
	}
}

/*
* max possible # of G cows at even position is # of non-consec G's
* 
* ex:
* 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 
* G G|H G|H G|H G|H G|H G|G G|G H|G H|H H|H G|H G|G G
* 
* 26 cows, max of 12 G's at even position (1 HH block)
* 
* 
*  4 types of 2-cow blocks:
*  1   2   3   4
*  HG  GH  HH  GG  (HH and GG can be ignored bc they don't change the # of G's in even pos's
*  HG must be kept, GH must be flipped
*  
*  if there are HG's at the end, remove them--they're already perfect
*  if there are GH's anywhere, u must flip them
*  -  find & flip earliest GH
* 
*/