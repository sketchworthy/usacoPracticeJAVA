/* finished, done easily
 * basic idea: take factors of total sum and check each factor in ascending order
 */
import java.util.*;

public class Ex0106v2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int i=0;i<t;i++) {
			int n = in.nextInt();
			int[] A = new int[n];
			int total = 0;
			for(int j=0;j<n;j++) {
				A[j]=in.nextInt();
				total+=A[j];
			}
			if(total==0)System.out.println(0);
			else {
				ArrayList<Integer> factors = new ArrayList<Integer>(); // factors of total
				for(int j=1;j<=total;j++) {
					if(total%j==0)factors.add(j);
				}
				while(true) {
					int partSum=factors.get(0);
//					System.out.println(partSum);
					// see if partsum can be added to each time
					int cSum = 0;
					int j = 0;
					while(j<n) { // loop through sleep logs
						cSum+=A[j];
						if(cSum==partSum) cSum=0;
						else if(cSum>partSum) break;
						j++;
					}
					if(j==n) {// if this factor for partsum works
						System.out.println(n-total/partSum);
						break;
					}
					factors.remove(0);
				}
			}
		}
		in.close();
	}
}
