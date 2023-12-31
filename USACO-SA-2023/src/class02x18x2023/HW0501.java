package class02x18x2023;
/* SOLUTION W/O USING STACKS: given arr A of n digits, find largest 
 * multiple of 3 makeable w digits of A
 * 
 * idea: a multiple of 3's digits add up to a multiple of 3. largest # will
 * have largest possible # of digits. now decide which 1s to remove by looking
 * at mod 3 and finding smallest possible mod 3 to remove. do this by using
 * a freq array of the 10 igits. how to include stacks? idek
 * 
 */
import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class HW0501 {
	static long MOD=(long) (1e9+7);

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(new File("10.in")));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		int[] freq = new int[10]; // digits 0 thru 9's freqs
		for(int j=0;j<n;j++) {
			freq[Integer.parseInt(st.nextToken())]++;
		}
		long total=0;
		for(int j=1;j<10;j++) {
			total+=j*freq[j];
		}
		long remainder=total%3; // 0, 1 or 2
		if(remainder==0) {
			// # that can be made is using all the digits. print that mod MOD
//			StringBuilder ans = new StringBuilder();
//			for(int j=9;j>=0;j--) {
//				for(int k=freq[j];k>0;k--)ans.append(j);
//			}
//			out.print(Long.parseLong(ans.toString())%MOD);
		}
		else if(remainder==1) {
			// in order of priority: remove an 1||4||7||(2&2)||(2&5)||(5&5)||
			// (2&8)||(5&8)||(8&8)
			// and then the remaining digits can be that mod MOD
			if(freq[1]>0)freq[1]--;
			else if(freq[4]>0)freq[4]--;
			else if(freq[7]>0)freq[7]--;
			else if(freq[2]>1)freq[2]-=2;
			else if(freq[2]>0&&freq[5]>0) {
				freq[2]--;
				freq[5]--;
			}
			else if(freq[5]>1)freq[5]-=2;
			else if(freq[2]>0&&freq[8]>0) {
				freq[2]--;
				freq[8]--;
			}
			else if(freq[8]>0&&freq[5]>0) {
				freq[8]--;
				freq[5]--;
			}
			else if(freq[8]>1)freq[8]-=2;
			else out.print("ERROR1"); // i dont think its actually possible for
			// this to happen but we'll see
		}
		else {
			// remove an 2||5||8||(1&1)||(1&4)||(4&4)||(1&7)||(4&7)||(7&7)
			if(freq[2]>0)freq[2]--;
			else if(freq[5]>0)freq[5]--;
			else if(freq[8]>0)freq[8]--;
			else if(freq[1]>1)freq[1]-=2;
			else if(freq[1]>0&&freq[4]>0) {
				freq[1]--;
				freq[4]--;
			}
			else if(freq[4]>1)freq[4]-=2;
			else if(freq[1]>0&&freq[7]>0) {
				freq[1]--;
				freq[7]--;
			}
			else if(freq[4]>0&&freq[7]>0) {
				freq[4]--;
				freq[7]--;
			}
			else if(freq[7]>1)freq[7]-=2;
			else out.print("ERROR2"); // i dont think its actually possible for
			// this to happen but we'll see
		}
		StringBuilder ans = new StringBuilder();
		for(int j=9;j>=0;j--) {
			for(int k=freq[j];k>0;k--)ans.append(j);
		}
		BigInteger x = new BigInteger(ans.toString());
		BigInteger mod = new BigInteger(String.valueOf(MOD));
		out.print(x.remainder(mod));
		
		out.close();
	}
}
