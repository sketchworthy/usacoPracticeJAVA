/* Given A[] w/ N integers, report # of pairs i<j so that after removing A[i] and A[j],
 new array's average is same as the average of the original array.
 
 ez!
 */
import java.util.*;
//import java.io.*;
public class deleteTwoElementsTianyiZhou {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // # of ints
		int[] A = new int[n];
		double sum=0;
		for(int j=0;j<n;j++) {
			A[j]=in.nextInt();
			sum+=A[j];
		}
		double avg=sum/n;
		
		// find out the difference between the original sum and new sum, then find pairs
		//   that will make that sum
		long cnt=0;
		double diff = avg*2;
		// round diff
		if(diff-Math.floor(diff)<=0.000000001)diff=Math.floor(diff);
		if(Math.ceil(diff)-diff<=0.000000001)diff=Math.ceil(diff);
		
		// if diff is not an integer, return 0
		if(diff!=Math.floor(diff)) {
			System.out.print(0);
			in.close();
			return;
		}
		
		for(int i=0;i<n-1;i++) {
			for(int j=i+1;j<n;j++) {
				if(A[i]+A[j]==(long)diff) {
					cnt++;
//					System.out.println(A[i]+"+"+A[j]);
				}
			}
		}
		System.out.println(cnt);
		
		in.close();
	}
}
