/*
 * broken code, see try 2
 */
import java.io.File;
import java.util.*;

public class HW01 {
	public static void main(String[] args) throws Exception {
		Scanner in1=new Scanner(System.in);
		String fileName = in1.next();
		in1.close();
		Scanner in = new Scanner(new File(fileName));
		int n = in.nextInt(); // # of papers written
		int[] citations = new int[n]; // citations[x] is the # of citations xth paper has
//		System.out.println(Arrays.toString(citations));
		for(int j=0;j<n;j++) { 
			int x=in.nextInt();
//			System.out.println(x);
			citations[j]=x;
		}
		in.close();
		// sort citations in increasing order, loop through them as possible values
		//    for the current h-index
		Arrays.sort(citations);
//		for(int j=0;j<n;j++) {
//			System.out.println(citations[j]+" ");
//		}
//		int cHdex = 0; // current h index
//		int cHigherI = 0; // current higher index in citations[]
//		System.out.println(citations[1]);
		
		int i=0;
		for(int j=0;j<n;j++) { // iterate over elements of citations[]
// if there are less papers with at least citations[j] citations than citations[j], break
			if(n-j<citations[j]) {
//				cHdex=citations[j-1];
				i=j-1;
				break;
			}
		}
		System.out.println(citations[i]);
		
		for(int j=citations[i];j<citations[i-1];j++) {
			if(i+1<j) {
			// if there are less papers with at least j citations than j, break
				System.out.println(j-1);
				break;
			}
		}
		
		
		
		
		
		
		
		
//		for(int j=0;j<=citations[n-1];j++) {
//			if(j>citations[cHigherI]) {
//				if(citations[cHigherI+1]>citations[cHigherI])cHigherI++;
//				else 
//			}
//			if(n-cHigherI<j) {
//					// if there are less papers with at least j citations than j, break
//				System.out.println(j-1);
//				break;
//			}
//		}
		
		
		
		
		
		
		
		
		
		
		
//		int x=0;
////		int i = 0; // index
//		for(int j=0;j<n;j++) {
//			if(citations[j]<=n-j) {
////				System.out.println(j);
//				cHdex=citations[j];
//				x=n-j;
////				i=j;
//			}
//			else break;
//		}
//		if(x>=cHdex+2) {
//			for(int j=cHdex+1;j<=x;j++) {
//				if()
//			}
//			
//			System.out.println((cHdex+x)/2);
//		}
//		else System.out.println(cHdex);
////		if(citations[i])
////		System.out.println(citations[i]);
//		System.out.println(cHdex+ " " + x);
	}
}
