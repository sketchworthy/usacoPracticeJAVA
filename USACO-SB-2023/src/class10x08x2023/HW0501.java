package class10x08x2023;
/* Subarray Sum to Target NOT COMPLETE. SCRAPPED
 * I tried to work on this but couldn't find a
 * sol on my own. tried to understand official sol
 * which uses a deque, but kept abandoning it. now
 * moved to a new doc, HW0501v2
 * 
 * Given int[] A and int T, find min length of any non-empty subarray of A w
 * sum >=T. if none exist return -1
 * 
 * idea: Essentially same as given a bunch of ints, find the
 * min diff in index so that those end ints differ by >=T
 * 
 * difficulty: impossible skull emoji. see HW0501v2
 * zoom link of sol: https://us02web.zoom.us/rec/play/a9doTa9iz0sLNzrbzHRG6xXckkCruVRoF-0OBHEpTQoyZn8vimJ3J3FQx4bVm8WCk5jjTCPmhKtxQ-5t.oXv6Y7MWl3HBmqi-?canPlayFromShare=true&from=share_recording_detail&continueMode=true&componentName=rec-play&originRequestUrl=https%3A%2F%2Fus02web.zoom.us%2Frec%2Fshare%2FTZt6BU6XYjv8lbPDFdV8UKBfSzE04q_4w4Fy9VPw_Oy5MObR8MSTiXBerYRszYzj.KmvxzJY9ZrGpt3QK
 * 
 */
import java.util.*;
import java.io.*;

public class HW0501 {
	public static void main(String[] args) throws Exception {
//		BufferedReader in = new BufferedReader(new FileReader("01.in"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		int[] A = new int[n];
		st = new StringTokenizer(in.readLine());
		for(int j=0;j<n;j++) {
			A[j]= Integer.parseInt(st.nextToken());
		}
		long[][] ps = new long[n+1][4]; //ps[x+1][0] after sorting is value of sums up to that point
		// ps[x+1][1] is OG index+1, x+1
		
		long mx=0;
		long mn=A[0];

		// 2 ps treemaps to store relevant ps values. psMin stores min OG index of a ps val x
		TreeMap<Long, Integer> psMin = new TreeMap<>();
		TreeMap<Long, Integer> psMax = new TreeMap<>();
		psMin.put((long)0, 0);
		psMax.put((long)0, 0);
		for(int j=1;j<n+1;j++) {
			ps[j][0]=ps[j-1][0]+A[j-1];
			ps[j][1]=j;
			mn=Math.min(mn, ps[j][0]);
			mx=Math.max(mx, ps[j][0]);
			if(!psMin.containsKey(ps[j][0]) || psMin.get(ps[j][0])>ps[j][1]) {
				psMin.put(ps[j][0], j);
			}
			psMax.put(ps[j][0], j);
		}
		if(mx-mn<t) { // if cant ever meet target
			out.println(-1);
		}
		else { // if theres a way to do it
			// loop thru psMin and psMax. for each key, aka each relevant ps val,
			//  check if 
			int mnlen=n+1;
			for(long val:psMax.keySet()) {
				Long highVal = psMin.ceilingKey(val+t);
				long x = psMax.get(val);
				if(highVal==null || (psMin.get(highVal)==psMax.get(val) && 
						val<t))continue;
				int len = psMin.get(highVal)-psMax.get(val)+1;
				if(val==0 && psMax.get(val)==0)len--; // 0,0 doesn't count
				if(len>0 && len<mnlen)
					mnlen=len;
			}
			if(mnlen==n+1)out.print(-1); // if the only subset that works is an empty subset
			else 
				out.print(mnlen);
		}
		
		
		in.close();
		out.close();
	}
}
