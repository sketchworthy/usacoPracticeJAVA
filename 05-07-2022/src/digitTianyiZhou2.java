// this version takes digit-02.in and digit-02.out files, and compares
//  and tells what was done wrong
import java.util.*;
import java.io.*;
public class digitTianyiZhou2 {
	public static void main(String[] args) throws Exception {
		Scanner in1 = new Scanner(new File("digit-02.in"));
		Scanner in2 = new Scanner(new File("digit-02.out"));
		int q = in1.nextInt(); // # of queries
		int wrongCount = 0;
		
		// array of 10^pow
		long[] tenPow = new long[19]; // index j contains 10^j
		tenPow[0]=1;
		for(int j=1;j<19;j++) {
			tenPow[j]=tenPow[j-1]*10;
		}
		
		for(int j=0;j<q;j++) { // for each query
			long k = in1.nextLong(); // get position we're looking for
			long ourAns = posDig(k,tenPow);
			long ans = in2.nextLong();
			if(ourAns!=ans) {
				System.out.println("Input: "+k+". Your ans: "+ourAns+". Correct ans: "+ans );
				wrongCount++;
			}
//			System.out.println(posDig(k,tenPow)); // find digit at that pos
			// in string
		}
		System.out.println("You got "+wrongCount+" cases wrong out of 1000.");
		in1.close();
		in2.close();
	}
	
	static long posDig(long k, long[] tenPow) { // find the kth digit in string
		if(k<10) { // k is from 1 to 9
			return k;
		}
		int placeVal=0;
		long max=1;
		for(int numDig=1;numDig<=18;numDig++) {
			max+=9*tenPow[numDig-1]*numDig;
//			System.out.println("Max: "+max);
			if(k<max) {
				placeVal=numDig;
				break;
			}
		}
//		System.out.println("PV: "+placeVal);
		k=k-max+9*tenPow[placeVal-1]*placeVal+1;
//		System.out.println("k: "+k);
		long q=k/placeVal; long r=k%placeVal;
//		System.out.println("q: "+q+" r: "+r);
		if(r==0) {
			return (q-1)%10;
		}
		else if(r==1) {
			return q/tenPow[placeVal-1]+1;
		}
		else {
//			k--;
//			q=k/placeVal; r=k%placeVal;
			return q/tenPow[placeVal-(int)r]-q/tenPow[placeVal-(int)r+1]*10;
		}
		
//		if(k<10) { // k is from 1 to 9
//			return k;
//		}
//		else if(k<2*90+10) { // num from 10 to 99 (works out to be 190)
//			k=k-9; // ignore first 9 1-dig #s, start from 10
//			System.out.println("k "+k);
//			long q = k/2; long r=k%2; // quotient+remainder
//			if(r==1) return q/10+1; // if 1st digit in #
//			else return (q-1)%10;// r==0 or 2, if units digit
//		}
//		else if(k<3*900+190) { // num from 100 to 999 (2890)
//			k=k-189; // ignore first 9 1-dig #s and 90 2-dig #s
//			long q = k/3;long r=k%3; // quotient+remainder
//			if(r==1) return q/100+1; // 1st dig, hundreds place
//			else if(r==2) return q/10-10*q/100; // tens digit
//			else return (q-1)%10; // r==3 or 0, units dig	
//		}
//		else if(k<4*9000+2890) { // 1000 to 9999 (38890)
//			k=k-2889;
//			long q = k/4;long r=k%4;
//			if(r==1) return q/1000+1; // 1st dig, thousands
//			else if (r==2) return q/100-10*q/1000; // 2nd dig, hundreds
//		}
//		else { // TEMP
//			return 99999;
//		}
	}
}


//wrong: 906607. i get 0 but it should be 6
