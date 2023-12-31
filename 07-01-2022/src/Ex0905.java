/* unfinished, hard to implement
 * review 'longest consecutive blocks' problems, i can do them but it takes too long
 * need to practice more
 * 
 */
import java.util.*;
public class Ex0905 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		char[] str = in.next().toCharArray();
// find 2 longest strings of 0s, their starting indices, and their ending indices
		int maxLen1 = 0; int startI1 = 0; int endI1 = 0;               // (non-inclusive)
		int maxLen2 = 0; int startI2 = 0; int endI2 = 0; // 1<2
		
		int cLen = 0; int cStartI = -1; int cEndI=0;// current length of 0 block
		for(int j=0;j<n;j++) {
			if(str[j]=='0') {
				if(cStartI==-1) { // if this is the 1st 0 of 1st 0 block
					cStartI=j;
				}
				else if(str[j-1]=='1') {
					cStartI=j;
				}
				if(j+1<n) cEndI=j+1;
				cLen++;
			}
			else { // current char is 1, either breaking the 0-block or starting off
				// adjust longest 0-block lens if necessary
				if(cLen>maxLen1) {
					if(cLen>maxLen2) {
						maxLen1=maxLen2; startI1=startI2; endI1=endI2;
						maxLen2=cLen; startI2=cStartI; endI2=cEndI;
					}
					else {
						maxLen1=cLen; startI1=cStartI; endI1=cEndI; 
					}
				}
				cLen=0;
			}
		}
		if(cLen>maxLen1) {
			if(cLen>maxLen2) {
				maxLen1=maxLen2; startI1=startI2; endI1=endI2;
				maxLen2=cLen; startI2=cStartI; endI2=cEndI;
			}
			else {
				maxLen1=cLen; startI1=cStartI; endI1=cEndI; 
			}
		}
		System.out.println("Max len1 "+maxLen1+" with start index "+startI1+" and end index "+endI1);
		System.out.println("Max len2 "+maxLen2+" with start index "+startI2+" and end index "+endI2);

		// find where to insert 2 cows
		// case 1: maxLen1 has a start/end edge that is very start/end edge of string
		if(startI1==0 || endI1==0) {
			cow1 = maxLen1-1;
		}
		
		
		
// case 2: both maxLen2 and maxLen1's starting and ending edges aren't very start or end of string
		// figure out which case: both cows in maxLen2 or 1 cow maxLen1 1 cow maxLen2
		if((maxLen2-2)/3>=(maxLen1-1)/2) // if preferable to place both in maxLen2                    ooooxoooxoooo   oooxooo
			System.out.println((maxLen2-2)/3);
		else System.out.println((maxLen1-1)/2);
		
		in.close();
	}
}
