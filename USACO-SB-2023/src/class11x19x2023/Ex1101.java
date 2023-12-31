package class11x19x2023;
/* Statistics COMPLETE
 * Given a frequency array, return the min ele, max ele, mean of sample,
 * and median of sample.
 * 
 * difficulty: p ez. i need to focus and draw diagrams on paper tho
 * 
 */
import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Ex1101 {
	static int k=9;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("0"+k+".in"));
		BufferedReader in2 = new BufferedReader(new FileReader("0"+k+".out"));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		long[] F = new long[256]; // freq array
		for(int j=0;j<256;j++) {
			F[j]=Integer.parseInt(st.nextToken()); 
		}
		
		long min = Integer.MAX_VALUE;
		boolean minFound = false;
		long max = 0;
		long totalSum = 0;
		long num = 0; // # of numbers in arr
		long modeN=0; // value of mode
		long modeI=0; // index where mode is found
		long[] psI = new long[256]; // prefix array of indices (to find median)
		psI[0]=F[0];
		
		// find min, max, totalSum, num, mode, median
		for(int j=0;j<256;j++) {
			if(!minFound && F[j]>0) { // update min
				min=j;
				minFound=true;
			}
			if(F[j]>0) max=j; // update max
			
			totalSum+=F[j]*j; // update avg
			num+=F[j];
			
			if(F[j]>modeN) { // new mode
				modeI=j;
				modeN=F[j];
			}
			
			// update psI
			if(j!=0) {
				psI[j]=psI[j-1]+F[j];
			}
		}
		
		// finish finding avg and median
		double avg = totalSum/(double)num;
		
		double median=-1;
		if(num%2==0) { // even
			// median should be an average of the (num-1)/2 th number and the (num+1)/2 th number
			boolean med1i = false; // if 1st median index found
			long med1=-1;
			boolean med2i = false; // if 2nd median index found
			long med2=-1;
			for(int j=0;j<256;j++) {
				if(!med1i && psI[j]>=(num)/2) {
					med1=j;
					med1i=true;
				}
				if(!med2i && psI[j]>=(num)/2+1) {
					med2=j;
					med2i=true;
				}
			}
			median = (med1+med2)/2.0;
		}
		else { // odd
			// median should be num/2 th number
			// the num/2 th number is in
			for(int j=0;j<256;j++) {
				if(psI[j]>=num/2) {
					median=j;
					break;
				}
			}
		}
		out.println((double)min+" "+(double)max+" "+avg+" "+median+" "+(double)modeI);
		out.println(in2.readLine());
		in.close();
		out.close();
	}
}
