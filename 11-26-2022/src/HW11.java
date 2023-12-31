/* Count the number of 1’s in the binary representations of integers between 1 and n 
 * 
 */
import java.util.*;
import java.io.*;
public class HW11 {
	public static void main(String[] args) throws Exception {
		Scanner in2=new Scanner(System.in);
		String fileName=in2.next();
		in2.close();
		Scanner in = new Scanner(new File(fileName));
		
		long n = in.nextLong();
		String biN=Long.toBinaryString(n);
		int len=biN.length();
		for(int j=0;j<30-len;j++) {
			biN="0"+biN;
		}
		
//		System.out.println("len: "+len+". biN: "+biN+"\n length: "+biN.length());
		long cnt=0;
		int jsMet=0;
		
		char[] str = biN.toCharArray();
		for(int j=0;j<30;j++) {
			if(str[j]=='1') { // if 1 is present
				int k = 30-j-1;
//				System.out.println(k);
				cnt+=k*pow(2,k-1)+1+jsMet*pow(2,k);
				jsMet++;
			}
		}
		System.out.println(cnt);
		
		in.close();
	}
	public static long pow(int b, int p) {
		if(p==-1)return 0;
		long ans=1;
		for(int j=0;j<p;j++) {
			ans*=b;
		}
		return ans;
	}
}

/* # of 1s from 0 to 2^k -1:  k*2^(k-1)
 * 
 * 0, (k 0's) 00...001, 10, 11, ... 11..11 (k 1's)
 * 
 * 
 * 
 */
