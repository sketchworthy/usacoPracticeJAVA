// http://www.usaco.org/index.php?page=viewproblem2&cpid=1012
import java.util.*;
public class flipSubString {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int len = in.nextInt();
		char[] A = in.next().toUpperCase().toCharArray();
		char[] B = in.next().toUpperCase().toCharArray();
		
		ArrayList<Integer> flippable = new ArrayList<Integer>();
		//^ indices that need to be flipped		
		for(int j=0;j<len;j++) { // note: you CAN bypass this arraylist
			if(A[j]!=B[j]) {
				flippable.add(j);
			}
		}
		
		// look to see which flippable indexes are consecutive
		int fCnt = 0; // flip count
		int lastFnum = -2; // most recent flip's number
		for(int j=0;j<flippable.size();j++) {
			if(flippable.get(j)!=lastFnum+1) { 
				// if not consecutive to last flip's num
				fCnt++;
			}
			lastFnum=flippable.get(j);
		}
		System.out.println(fCnt);
		
		in.close();
	}

}
