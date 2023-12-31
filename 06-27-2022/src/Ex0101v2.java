/* done easily. basic idea: convert timepoints into minutes, sort array, compare adjacent tps
 * improved from 1st solution in that i found the linear way instead of the n^2 way
 */
import java.util.*;

public class Ex0101v2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] tps = new int[n]; // timepoints
		for(int j=0;j<n;j++) {
			String str=in.next();
			tps[j]=Integer.parseInt(str.substring(0,2))*60+Integer.parseInt(str.substring(3));
		}
		Arrays.sort(tps);
		int minD = tps[0]+24*60-tps[n-1]; // min time difference--initialize as difference between 1st and last index
		if(minD>12*60) minD=24*60-12*60;
		for(int j=0;j<n-1;j++) {
			// compare tps[j] and tps[j+1]
			int t = tps[j+1]-tps[j];
			t=Math.min(24*60-t, t);
			minD=Math.min(minD, t);
		}
		System.out.println(minD);
		in.close();
	}
}
