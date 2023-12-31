package codeTigerCodeforcesContest;
/*
 * ran out of time during practice contest :/
 */
import java.util.*;

public class practice4 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int j=0;j<t;j++) {
			int n = in.nextInt(); // # of strings
			String[] strs = new String[n];
			int[] lens = new int[n]; // lengths of strs
			for(int k=0;k<n;k++) {
				strs[k]=in.next();
				lens[k]=strs[k].length();
			}
			for(int k=0;k<n;k++) { // for str you're looking to concat
				boolean ans=false;
				for(int m=0;m<n;m++) {
					for(int i=m;i<n;i++) {
						if(lens[k]==lens[m]+lens[i]) {
							if(strs[k].equals(strs[m]+strs[i]))
								ans=true;
							else if(strs[k].equals(strs[i]+strs[m]))
								ans=true;
							if(ans) { // if true
								break;
							}
						}
					}
					if(ans) { // if true
						break;
					}
				}
				if(ans)System.out.print(1);
				else System.out.print(0);
			}
			System.out.println();
		}
		in.close();
	}
}