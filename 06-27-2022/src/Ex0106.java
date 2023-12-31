/* Unfinished, doesn't work
 * Basic idea: find factors of total, loop through and see if the 'blocks' making up the factors are valid
 */
import java.util.*;
public class Ex0106 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int i=0;i<T;i++) {
			int n = in.nextInt();
			int[] A = new int[n]; // log of the # of times bes fell asleep
			int total = 0;
			int max = 0;
			for(int j=0;j<n;j++) {
				A[j]=in.nextInt();
				total+=A[j];
				max = Math.max(max, A[j]);
			}
			if(A[0]==0 && allSame(A)==true) {System.out.println(0); continue;}
			
			ArrayList<Integer> facts = new ArrayList<Integer>();
			// find factors of total
			for(int v=1;v<=total;v++) {
				if(total%v==0&&v>=max) facts.add(v); // only include factors same/more than the max sleep periods in A[]
				// facts now contains possible factors of total in increasing order
			}
			
			// for each factor go through and see if blocks of sum factor are possible
			for(int factor:facts) {
				int sSum = 0; // temp sum of sleep periods adding towards factor
				int index = -1;
				while (index<n){
					while(sSum<factor) {
						index++;
						sSum+=A[index];
					}
					if(sSum>factor) continue;
				}
				System.out.println(n-total/factor);
				break;
			}
			in.close();
			
		}
	}
	
	public static boolean allSame(int[] A) { // returns true if all values
		// in A are the same
		int base = A[0];
		for(int i=1;i<A.length;i++) {
			if(base!=A[i]) return false;
		}
		return true;
	}

}
// total sum = (n-mods)(values)