package class10x01x2023;
/* Given n locations on the number line print the distances from those points
 * to any of the points in between. and/or print the max time it will take
 * for a person at any given point w a given speed to arrive at a loc
 * 
 * Input format: 
 * n (# of ints in next line)
 * a_1, a_2, ... a_n (n ints. locs)
 * a_1, a_2, ... a_n (n diff ints. speeds)
 * 
 * I was using this helper program to check if the min max distance really
 * had just 1 global minimum and not local minima (WHICH IS TRU!!! :D)
 */
import java.util.*;
import java.io.*;

public class HW0401helper {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());
		int[][] A = new int[n][2];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			A[j][0]=Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			A[j][1]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A,(a,b)->a[0]-b[0]);
		for(int j=0;j<n;j++) {
			out.print(A[j][0]+" ");
		}
		out.println("is the og point locs");
		for(int j=0;j<n;j++) {
			out.print(A[j][1]+" ");
		}
		out.println("is the og point speeds");
		
		
		for(int j=A[0][0];j<=A[n-1][0];j++) {
			out.println("\nHere's the times for rendevous at "+j+":");
			double minMax=(double)(Math.abs(A[0][0]-j))/(double)A[0][1];
//			double sum=0;
			for(int k=0;k<n;k++) {
//				out.println(Math.abs(A[k]-j));
//				sum+=Math.abs(A[k]-j);
				minMax=Math.max(minMax, (double)(Math.abs(A[k][0]-j))/(double)A[k][1]);
			}
//			out.println("...summing to "+sum);
			out.println("...with a minimum max distance of "+minMax);
		}
		
		in.close();
		out.close();
	}
}
