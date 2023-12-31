package class09x24x2023;
/* Traffic Lights COMPLETE
 * n traffic lights are added 1 after another on a street w len x.
 * Find the len of the longest passage between traffic lights
 * after each addition. Print the sum of these max lens
 * 
 * idea: keep a treeset of the traffic light locations and a
 * freq treemap of the lengths of the passages. update both
 * after each traffic light addition.
 * 
 * difficulty: THESE INDICES CONFUSED THE HELL OUTTA ME
 * 
 */
import java.util.*;
import java.io.*;

public class HW0301 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader in = new BufferedReader(new FileReader("10.in"));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int x = Integer.parseInt(st.nextToken()); // len of street
		int n = Integer.parseInt(st.nextToken()); // # of traffic lights
		st = new StringTokenizer(in.readLine());
		
		long total=0;
		TreeSet<Integer> lights = new TreeSet<Integer>(); // locations of lights
		lights.add(0); lights.add(x); // add bounds
		TreeMap<Integer,Integer> lens = new TreeMap<>(); // lengths of passages
		lens.put(x, 1); // add the x-1 length
		
		for(int j=0;j<n;j++) { // for traffic light
			int light = Integer.parseInt(st.nextToken());
			int higherLight=-222;
				higherLight=lights.higher(light);
			int lowerLight=-222;

			int len;
			len=higherLight-lowerLight;
				if(lens.getOrDefault(len,0)==1)lens.remove(len);
				else lens.put(len, lens.getOrDefault(len,0)-1);
				lens.put(higherLight-light, lens.getOrDefault(higherLight-light, 0)+1);
				lens.put(light-lowerLight, lens.getOrDefault(light-lowerLight, 0)+1);
//			}
			lights.add(light);
			total+=lens.lastKey(); // add highest len to total
		}
		out.println(total);
		
		in.close();
		out.close();
	}
}
