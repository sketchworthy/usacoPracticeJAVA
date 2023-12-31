/* note: currently missing a case for a scenario like rrwwwwbb
 * 
 */
import java.util.*;
import java.io.*;
public class HW07 {
	public static void main(String[] args) throws Exception{
		Scanner in2 = new Scanner(System.in);
		String fileName = in2.next();
		in2.close();
		Scanner in = new Scanner(new File(fileName));
		int n = in.nextInt();
		char[] necklace = in.next().toCharArray();
		
		int maxSum=0;
		for(int j=0;j<n;j++) {
			maxSum=Math.max(maxSum, groupSum(j,necklace,n));
		}
		System.out.println(maxSum);
		
		in.close();
	}
	public static int groupSum(int i, char[] necklace, int n) {
		// i: index of where you're starting. necklace describes beads.
		//     n is the length of necklace[]
		int ans=0;
		
		char cColor='w';
		for(int j=0;j<n;j++) {
			// check necklace[(i+j)%n]
			if(cColor==necklace[(i+j)%n]) { // colors def the same
				ans++;
			}
			else if (cColor=='w') { // set cColor to necklace[(i+j)%n]
				cColor=necklace[(i+j)%n];
				ans++;
			}
			else if (necklace[(i+j)%n]=='w') { // just add 1
				ans++;
			}
			else break; // no longer of the same color
		}
		if(ans==n) { // if just going by a single group has given you 
						// the whole necklace
			return ans;
		}
		else { // otherwise also go the other direction
			cColor='w';
			for(int j=1;j<=n;j++) {
				// check necklace[(i-j)%n];
				if(cColor==necklace[(i-j+n)%n]) { // colors def the same
					ans++;
				}
				else if (cColor=='w') { // set cColor to necklace[(i+j)%n]
					cColor=necklace[(i-j+n)%n];
					ans++;
				}
				else if (necklace[(i-j+n)%n]=='w') { // just add 1
					ans++;
				}
				else break; // no longer of the same color
			}
			
			return ans;
		}
		
	}
}
