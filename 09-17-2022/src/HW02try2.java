/*
 */
import java.util.*;
import java.io.*;

public class HW02try2 {
	public static void main(String[] args) throws Exception{
		Scanner in2 = new Scanner(System.in);
		String fileName = in2.next();
		in2.close();
		Scanner in = new Scanner(new File(fileName));
		int t = in.nextInt();
		
		int sum=0;
		for(int j=0;j<t;j++) {
			int n = in.nextInt();
			long ans = 0;
			int op = 1; // 1 means *, 2 means /, 3 +, 4 -
			int cSum=n; // current sum to be added to ans
			for(int k=n-1;k>=1;k--) {
				if(op==1)cSum*=k;
				else if(op==2)cSum/=k;
				else if(op==3) {
					ans+=cSum;
					cSum=k;
				}
				else {
					ans+=cSum;
					cSum=-1*k;
				}
				// shift op
				op++;
				if(op==5)op=1;
			}
			ans+=cSum;
			sum+=ans;
//			System.out.println(ans);
		}
		System.out.println(sum);
		in.close();
	}
	
	
//	public int clumsy(int n) {
//		if(n==1)return 1; //1 1
//		if(n==2)return 2; //2 2*1
//		if(n==3)return 6; //3 3*2/1
//		if(n==4)return 7; //0 4*3/2+1
//		if(n==5)return 7; //1 5*4/3+2-1
//		if(n==6)return 8; //2 6*5/4+3-2*1
//		if(n==7)return 6; //3 7*6/5+4-3*2/1
//		if(n==8)return 9; //0 8*7/6+5-4*3/2+1
//		if(n==9)return 11; //1 9*8/7+6-5*4/3+2-1
//		if(n==10)return 12; //2 10*9/8+7-6*5/4+3-2*1. 
//	}
}