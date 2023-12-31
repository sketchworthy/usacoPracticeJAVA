package class02x11x2023;
/* load trucks based on some conditions and return total price
 * difficulty: pretty ez once i understood the problem. just simulation
 * i think
 * note that when working w big #s use long... i was stuck for so long debugging
 * bc my int values were too small :/
 */
import java.util.*;
import java.io.*;

public class HW0401 {
	public static void main(String[] args) throws Exception {
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader in = new BufferedReader(new FileReader("02.in"));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of packages
		int m = Integer.parseInt(st.nextToken()); // # of trucks
		long[][] packages = new long[n][2]; // packages[k][0] # of units, k,1 unit price
		int[] trucks = new int[m]; // truck capacities
		long[] prices = new long[m]; // truck prices (to be filled)
		long total=0;
		for(int j=0;j<n;j++) {
			StringTokenizer st2 = new StringTokenizer(in.readLine());
			packages[j][0]=Long.parseLong(st2.nextToken());
			packages[j][1]=Long.parseLong(st2.nextToken());
//			total+=packages[j][0]*packages[j][1];
		}
		for(int j=0;j<m;j++) {
			trucks[j]=Integer.parseInt(in.readLine());
		}
		// now fill prices[] of trucks by filling truck capacities from package 0 onward
		int ct = 0; // index of current truck we're filling
		int cc = trucks[ct]; // current remaining capacity of truck ct
		long cp = 0; // current price for current truck
		int j = 0; // package index pointer
		while(j<n && ct<m) {
//		for(int j=0;j<n;j++) { // loop thru packages
			// fill current truck with as many units of package j as possible
			if(packages[j][0]<=cc) { // if current capacity enough for all of package j
				cp+=packages[j][0]*packages[j][1];
				cc-=packages[j][0];
				j++;
				if(cc==0) { // move to next truck
					prices[ct]=cp;
					ct++;
					if(ct>=m)break;
					cc=trucks[ct];
					cp=0;
				}
			}
			else { // if truck's capacity too small, increase ct and fill next truck
				cp+=packages[j][1]*cc;
				packages[j][0]-=cc;
				
				prices[ct]=cp;
				ct++;
				if(ct>=m)break;
				cc=trucks[ct];
				cp=0;
				
				// check if this truck can be filled
			}
			
		}
		if(j==n) { // if ran out of packages
			prices[ct]+=cp;
		}
		for(int t=0;t<m;t++) { // for every truck
			total+=prices[t];
		}
		for(int x=0;x<m;x++) {
			out.println(prices[x]);
		}
		out.print(total);
		in.close();
		out.close();
	}
}

