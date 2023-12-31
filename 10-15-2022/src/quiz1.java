/* Super easy, idea was sound and implementation ez :D
 * Basic idea: only need to consider the odds so yeet the evens.
 * then, you can sort the odds. then it's just seeing which chains are
 * broken.
 * problem:
 * Given an array of integers, find the maximum length of any block of 
 * consecutive odd integers that can be formed using integers from the array.
 * Sample input
7
9 5 11 1 2 4 3
Sample output
3
 */
import java.util.*;
import java.io.*;
public class quiz1 {
	public static void main(String[] args) throws Exception{
		Scanner in2 = new Scanner(System.in);
		String fileName=in2.next();
		in2.close();
		Scanner in = new Scanner(new File(fileName));
		int n=in.nextInt();
		ArrayList<Integer> A = new ArrayList<Integer>();
		for(int j=0;j<n;j++) {
			int k=in.nextInt();
			if(k%2==1) A.add(k);
		}
		Collections.sort(A);
//		for(int i:A) {
//			System.out.print(i+" ");
//		}
//		System.out.println();
		
		// A is now ascending order of odd integers
		long maxBL=0; // max block length
		long cBL=1; // current block length
		if(A.size()==0)System.out.println(0);
		else {
			long k=A.get(0); // last odd found
			for(int j=1;j<A.size();j++) {
				if(A.get(j)==k+2) { // if continuing current block
					cBL++;
	//				System.out.println(cBL);
					k+=2;
				}
				else { // if new block started
					k=A.get(j);
					maxBL=Math.max(maxBL, cBL);
					cBL=1;
				}
			}
			maxBL=Math.max(maxBL, cBL);
			
			System.out.println(maxBL);
		}
		in.close();
	}
}
