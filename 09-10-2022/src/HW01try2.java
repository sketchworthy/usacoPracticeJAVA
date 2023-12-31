/*
 * 
 */
import java.io.File;
import java.util.*;

public class HW01try2 {
	public static void main(String[] args) throws Exception {
		Scanner in1=new Scanner(System.in);
		String fileName = in1.next();
		in1.close();
		Scanner in = new Scanner(new File(fileName));
		int n = in.nextInt(); // # of papers written
		int[] freq = new int[1001]; // freq[x] = # of papers with x citations
		
		for(int j=0;j<n;j++) { 
			int x=in.nextInt();
//			System.out.println(x);
			freq[x]=freq[x]+1;
		}
		System.out.println(Arrays.toString(freq));
		in.close();
		
		for(int j=0;j<=1000;j++) {// iterate over [0,1000]
// if there are less papers with at least j citations than j, break
			int sum=0; // find the # of papers with at least j citations
			for(int k=j;k<=1000;k++)sum+=freq[k];
			if(sum<j) {
				System.out.println(j-1);
				break;
			}
		}
	}
}
