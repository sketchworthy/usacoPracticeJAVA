/* Seems hard but actually v simple once you get the basic idea
 * 
 * basically you want to sell the stock whenever the price lowers the next day
 * 
 * 20 1 2 8 9 8
 * 23 2 6 5 9 2 36
 */
import java.util.*;
import java.io.*;

public class HW01 {
	public static void main(String[] args) throws Exception {
		Scanner in2 = new Scanner(System.in);
		String fileName=in2.next();
		in2.close();
		Scanner in = new Scanner(new File(fileName));
		int n = in.nextInt();
		int[] P = new int[n];
		for(int j=0;j<n;j++)
			P[j]=in.nextInt();
		
		long maxP = 0; // max profit
		int lastMinVal=P[0];
		int minIndex=0;
		for(int j=1;j<n;j++) {
			if(P[j]<P[j-1]) {
				if(P[j]<lastMinVal) {
					lastMinVal=P[j];
				}
				maxP+=P[j-1]-P[minIndex];
					
				minIndex=j;
					
//				System.out.println("j: "+j+" lastMinVal: "+lastMinVal+" minIndex: "+minIndex);
			}
			
		}
		maxP+=P[n-1]-P[minIndex];
		System.out.println(maxP);
		in.close();
	}
}
