// status: incomplete+incorrect, need to fix+check solution

import java.util.*;
public class stationaryShuffle {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // # of numbers
		int k = in.nextInt(); // # of times shuffled
		
		int[] A = new int[n];
		for(int j=0;j<n;j++) {
			A[j]=in.nextInt();
		}
		
		// make a copy for reference
		int[] B = new int[n];
		for(int j=0;j<n;j++) {
			B[j]=A[j];
		}
		
		for(int j=0;j<k;j++) {
			A=shuffle(A);
		}
		
		// check # in original positions
		int orgCnt = 0;
		for(int j=0;j<n;j++) {
			if(B[j]==A[j]) {
				orgCnt++;
			}
		}
		// check # that are empty
		int emptyCnt=0;
		for(int j=0;j<n;j++) {
			if(A[j]==-168) {
				emptyCnt++;
			}
		}
		
		System.out.println(orgCnt+" "+emptyCnt);
		in.close();
	}
	
	static int[] shuffle(int[] A) {
		int[] B = new int[A.length];
		
		for(int j=0;j<A.length;j++) { // for index in B
			// look to see if they are a value in A
			boolean contains = false;
			for(int k=0;k<A.length;k++) {
				if(A[k]==B[j]) {
					contains=true;
					break;
				}
			}
			if(contains=true) {
				if(A[j]>0&&A[j]<A.length)
					B[A[j]]=A[j];
			}
			else {
				B[j]=-168;
			}
		}
		return B;
	}
}
