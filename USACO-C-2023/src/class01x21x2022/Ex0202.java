package class01x21x2022;
/* given array A of n positive ints, determine if there exist 3 distinct indices i,j,k
 * such that A[i]+A[j]+A[k] ends in the digit 3
 * 
 * difficulty: ez to see idea, implementation was kiiinda annoying bc i did it bashily
 * idea: you don't need any digit except the last 1, which is always from 0-9, 
 * so u can use a freq array that keeps track of how many #s ending in 0-9 you have.
 * also, note that you only need max 3 copies of each 0-9, bc you'll only use 3 at max
 */
import java.util.*;

public class Ex0202 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt(); // # of queries
		for(int i=0;i<q;i++) {
			int n = in.nextInt(); // # of positive ints
			int[] freq = new int[10];
			for(int j=0;j<n;j++) {
				String s = in.next();
				freq[s.charAt(s.length()-1)-'0']++;
			}
			// see if the freq array can have 3 digits that will add to smthin that ends
			//  in 3
			
			/* i got bashy & hard-coded triplets that add to a # that ended in 3
			 {0,0,3} {0,1,2} {1,1,1} add to 3
			 {0,6,7} {0,5,8} {0,4,9}
			 {1,6,6} {1,5,7} {1,4,8} {1,3,9}
			 {2,5,6} {2,4,7} {2,3,8} {2,2,9}
			 {3,5,5} {3,4,6} {3,3,7}
			 {4,4,5}  all add to 13
			 {5,9,9} {6,8,9} {7,7,9} {7,8,8} add to 23
			 */
			if(freq[0]>=2&&freq[3]>=1)System.out.print(1);
			else if(freq[0]>=1&&freq[1]>=1&&freq[2]>=1)System.out.print(1);
			else if(freq[1]>=3)System.out.print(1);
			else if(freq[0]>=1&&freq[6]>=1&&freq[7]>=1)System.out.print(1);
			else if(freq[0]>=1&&freq[5]>=1&&freq[8]>=1)System.out.print(1);
			else if(freq[0]>=1&&freq[4]>=1&&freq[9]>=1)System.out.print(1);
			else if(freq[1]>=1&&freq[6]>=2)System.out.print(1);
			else if(freq[1]>=1&&freq[5]>=1&&freq[7]>=1)System.out.print(1);
			else if(freq[1]>=1&&freq[4]>=1&&freq[8]>=1)System.out.print(1);
			else if(freq[1]>=1&&freq[3]>=1&&freq[9]>=1)System.out.print(1);
			else if(freq[2]>=1&&freq[5]>=1&&freq[6]>=1)System.out.print(1);
			else if(freq[2]>=1&&freq[4]>=1&&freq[7]>=1)System.out.print(1);
			else if(freq[2]>=1&&freq[3]>=1&&freq[8]>=1)System.out.print(1);
			else if(freq[2]>=2&&freq[9]>=1)System.out.print(1);
			else if(freq[3]>=1&&freq[5]>=2)System.out.print(1);
			else if(freq[3]>=1&&freq[4]>=1&&freq[6]>=1)System.out.print(1);
			else if(freq[3]>=2&&freq[7]>=1)System.out.print(1);
			else if(freq[4]>=2&&freq[5]>=1)System.out.print(1);
			else if(freq[5]>=1&&freq[9]>=2)System.out.print(1);
			else if(freq[6]>=1&&freq[8]>=1&&freq[9]>=1)System.out.print(1);
			else if(freq[7]>=2&&freq[9]>=1)System.out.print(1);
			else if(freq[7]>=1&&freq[8]>=2)System.out.print(1);
			else System.out.print(0);
		}
		in.close();
	}
}