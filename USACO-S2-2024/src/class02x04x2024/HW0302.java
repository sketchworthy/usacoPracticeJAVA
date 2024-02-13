//package class02x04x2024;
/* USACO 2021 Dec Silver P3: Convoluted Intervals _status{COMPLETE or not}_
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=1160
 * In [0...5000], there are N (<=2*10^5) intervals of a form [ai,bi].
 * 
 * For every k in the range [0,2M], we want to find the # of
 * interval indices x and y for which cows X and Y can choose 
 * respective intervals [ax,bx] and [ay,by] and find 
 * ax+ay < k < bx+by. 
 * 
 * idea: keep a prefix difference array. for every 
 * 2 intervals [ax,bx] and [ay,by], there is a sequence of
 * valid points between ax+ay and bx+by. On a 2M+1 size 
 * prefix diff arr of how many ordered pairs work, we can 
 * then add a +1 to ax+ay and a -1 to bx+by. this gets us
 * the # of ordered pairs for everyone!
 * however, it's still a bit too slow bc looping thru
 * every 2 intervals. but since the intervals must
 * lie in the range [0,...5000], we can create a freq
 * arr of intervals with 25*10^6 distinct possible
 * intervals. but this actually just makes it slower :(
 * 
 * AH. but the key idea is: you just have 2 freq arrs. you have
 * a left a1+a2 freq arr and a right b1+b2 freq arr and then it
 * works speed-wise!
 * 
 * difficulty:
 * 
 */
import java.util.*;
import java.io.*;

public class HW0302 {

	public static void main(String[] args) throws Exception {
		// take input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of intervals
		int m = Integer.parseInt(st.nextToken()); // max int in interval
		HashMap<Long,Integer> freq = new HashMap<>(); 
		// contains freqs of distinct intervals
		for(int j=0;j<n;j++) { // take in intervals
			st = new StringTokenizer(in.readLine());
			long key = Long.parseLong(st.nextToken())*10_000;
			key+=Long.parseLong(st.nextToken());
			freq.put(key, freq.getOrDefault(freq.get(key), 0)+1);
			// key = a*10,000+b
		}

		long[] pd = new long[2*m+2]; // prefix diff arr
		// if ans[2m+1] is the desired arr, pd[0]=ans[0],
		// ans[1]=ans[0]+pd[1]=pd[0]+pd[1], 
		// ans[x]=pd[0]+pd[1]+...pd[x] and
		// pd[x] = ans[x]-ans[x-1] unless x=0 in which case
		// pd[0] = ans[0]-0.		
		// len of pd[] is 2m+2 not 2m+1 bc there's an invisible 0 at
		// the end of ans
		
		for(Long interval1:freq.keySet()) { // TODO currently too slow
			for(Long interval2:freq.keySet()) {
				// update pd[] with all interval1&2's afflictions
				long a1 = interval1/10_000;
				long b1 = interval1%10_000;
				long a2 = interval2/10_000;
				long b2 = interval2%10_000;
				int freq1 = freq.get(interval1);
				int freq2 = freq.get(interval2);
				
				pd[(int)(a1+a2)]+=freq1*freq2;
				pd[(int)(b1+b2+1)]-=freq1*freq2;
				// TODO check
			}
		}

		long[] ans=new long[2*m+2];
		// get ans from pd[]
		ans[0]=pd[0];
		for(int j=1;j<2*m+2;j++) {
			ans[j]=ans[j-1]+pd[j];
		}
		
		for(int j=0;j<2*m+1;j++) {
			out.println(ans[j]);
		}
			
		in.close();
		out.close();
	}
}