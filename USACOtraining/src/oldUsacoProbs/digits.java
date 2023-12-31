package oldUsacoProbs;
/* 2011 USACO Bronze problem 2, Awkward Digits COMPLETED
 * completed, had to look at official solution
 *  was way simpler than i thought. literally just loop 
 *  thru every possible value and test to see if its equal
 */
import java.util.*;

public class digits {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char[] b2 = in.next().toCharArray(); // base 2 rep of N w 1 incorrect digit
		char[] b3 = in.next().toCharArray(); // base 3 rep of N w 1 incorrect digit
		int n2=b2.length;
		int n3=b3.length;
		boolean found = false;
		for(int j=0;j<n2;j++) {
			if(found)break;
			b2[j]=(char) ('0'+('1'-b2[j]));
			long ans=convertToBase(b2,2);
			for(int k=0;k<n3;k++) {
				if(found)break;
				for(int i=0;i<2;i++) {
					if(found)break;
					b3[k]=(char)('0'+(((b3[k]-'0')+1)%3));
					// compare
					if(ans==convertToBase(b3,3)) {
						found=true;
						System.out.println(ans);
					}
				}
				b3[k]=(char)('0'+(((b3[k]-'0')+1)%3));
			}
			b2[j]=(char) ('0'+('1'-b2[j]));
			
		}
		
		
		in.close();
	}
	public static long convertToBase(char[] bStr, int b) {
		// converts a char array bStr from base b to base 10, returns base
		long ans = 0;
		for(int j=bStr.length-1;j>=0;j--) {
			ans+=(bStr[j]-'0')*pow(b,bStr.length-j-1);
		}
		return ans;
	}
	public static long pow(long base, int power) {
		long ans=1;
		for(int j=0;j<power;j++) {
			ans*=base;
		}
		return ans;
	}
}
