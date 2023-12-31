/* maybe we can look at distinctions between pairs? idk
 *  i probably could have solved this with the time i had in the contest, but i was
 *  overwhelmed and unfocused and confused with a lack of clarity :(
 *  
 *  This is the code I wrote during the contest, unchanged. for my revised answer
 *   see reverseEngineeringSol.
 */
import java.util.*;

public class reverseEngineering {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int i=0;i<t;i++) {
			int n = in.nextInt(); // # of chars tested
			int m = in.nextInt(); // # of inputs
			boolean[][] inputs = new boolean[m][n]; // m rows, n cols
			// inputs[x] provides the xth input test series
			boolean[] results = new boolean[m]; // results[x]=xth result
			// inputs[x] corresponds w/ results[x]
			
			for(int j=0;j<m;j++) { // fill inputs[][] & results[]
				String s = in.next();
				for(int k=0;k<s.length();k++) {
					if(s.charAt(k)=='1') inputs[j][k]=true;
					else inputs[j][k]=false;
				}
				int result=in.nextInt();
				if(result==1) results[j]=true;
				else results[j]=false;
			}
//			// print to check
//			for(int j=0;j<m;j++)System.out.println(Arrays.toString(inputs[j]));
//			System.out.println(Arrays.toString(results));
//			System.out.println();
			
			if(check(n,m,inputs,results)) System.out.println("OK");
			else System.out.println("LIE");
			
		}
		in.close();
	}
	public static boolean check(int n, int m, boolean[][] inputs,boolean[] results) {
		// returns true or false depending on if its OK or LIE
		if(n==1) {
			boolean works=true;
			for(int j=0;j<m-1;j++) {
				if((inputs[j][0]==results[j])!=(inputs[j+1][0]==results[j+1])) {
					works=false;
					break;
				}
				if(works)return true;
			}
		}
		// check if all of results are equal
		boolean identical=true;
		for(int j=0;j<m-1;j++) {
			if(results[j]!=results[j+1]) {
				identical=false;
				break;
			}
		}
		if(identical)return true;
		
		// check if there are ever 2 of the same input that give diff outputs
		for(int j=0;j<m;j++) {
			for(int k=0;k<m;k++) {
				if(inputs[j].equals(inputs[k])) {
					if(results[j]!=results[k])return false;
				}
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		for(int i=0;i<pow(2,n);i++) {
//			// bit masking
//			String s = Integer.toBinaryString(i);
//			boolean[] mask = new boolean[n];
//			for(int j=0;j<n;j++) {
//				if(s.charAt(i)=='0') mask[j]=false;
//				else mask[j]=true;
//			}
//			// look for a scenario where results[#] is true when mask is applied
//			// returns mask[x] when inputs[#][x] is true
//			boolean works=false;
//			for(int j=0;j<m;j++) {
//				for(int k=0;k<n;k++) {
//					
//				}
//			}
//		}
		
		
		
		
//		for(int j=0;j<n;j++) { // case where inputs[#][j] determines results[#]
//			// with priority
//			boolean works=false;
//			// case 1: inputs[#][j] returns 1 when true
//			// case 2: inputs[#][j] returns 0 when true
//			
////			if()
//		}
		
		boolean found1=false;
		boolean found0=false;
		for(int j=0;j<m;j++) {
			if(results[j]==true)found1=true;
			else if(found1) {
				found0=true;
				break;
			}
		}
		if(found0)return false;
		else return true;
//		return false;
	}
	public static int pow(int base,int exponent) {
		int ans=1;
		for(int j=0;j<exponent;j++)ans*=base;
		return ans;
	}
}
