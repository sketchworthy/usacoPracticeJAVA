/*
 * done somewhat confusedly
 * recursion's confusing, tho an easier recursion problem
 */

import java.util.*;
public class Ex0501 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		int y = in.nextInt();
		
		int[] L = new int[y+1];
		int maxL = 0; // max cycle length of int in [x,y]
		ArrayList<Integer> maxInts = new ArrayList<Integer>(); // integers with max cycle len
		for(int i=x;i<=y;i++) {
			L[i]=cycleLen(i);
			if(L[i]>maxL) {
				maxInts.clear();
				maxL=L[i];
				maxInts.add(i);
			}
			else if(L[i]==maxL) maxInts.add(i);
		}
		System.out.println(maxL);
		for(int i=0;i<maxInts.size();i++) {
			System.out.print(maxInts.get(i)+" ");
		}
		in.close();
	}
	public static int cycleLen(int i) {
		// eventually returns length of cycle
//		System.out.println(i);
		if(i==1) {
			return 1;
		}
		if(i%2==1) { 
			return 1+cycleLen(3*i+1);
		}
		else { // i is even
			return 1+cycleLen(i/2);
		}
		
	}
}
