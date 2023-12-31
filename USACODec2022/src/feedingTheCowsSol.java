/*
 * i was timing out over the STUPIDEST thing
 * when i was printing i printed out chars 1-by-1
 * instead of printing them all at once.
 * 
 * AAAAHHHHGHHHHH
 */
import java.util.*;

public class feedingTheCowsSol {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int i=0;i<t;i++) {
			int n=in.nextInt();
			int k = in.nextInt();
			char[] str = in.next().toCharArray();
			
			char[] ans = new char[n];
			Arrays.fill(ans, '.');
			
			int cnt=0;
			
			int j=k; // pointer
			int lastGpatch=-k-1; // index of last g patch
			int lastHpatch=-k-1; // index of last h patch
			while(j<n) {
//				System.out.println("j: "+j);
				// determine if str[j] is G or H
				if(str[j-k]=='G') { // if it's G, consider the G-patch
					if(j-k>lastGpatch+k) { // if outside lastGpatch
						cnt++;
						ans[j]='G';
						lastGpatch=j;
					}
				}
				else { // if it's H, consider the H-patch
					if(j-k>lastHpatch+k) { // if outside lastHpatch
						cnt++;
						ans[j]='H';lastHpatch=j;
					}
				}
				j++;
			}
			boolean gNeeds=false;
			boolean hNeeds=false;
			for(int x=n-1;x>=0;x--) {
				if(str[x]=='G' && x>lastGpatch+k) {
					gNeeds=true;
				}
			}
			for(int x=n-1;x>=0;x--) {
				if(str[x]=='H' && x>lastHpatch+k) {
					hNeeds=true;
				}
			}
			
			if(gNeeds) {
				for(int x=n-1;x>=0;x--) {
					if(ans[x]=='.') {
						cnt++;
						ans[x]='G';
						break;
					}
				}
			}
			if(hNeeds) {
				for(int x=n-1;x>=0;x--) {
					if(ans[x]=='.') {
						cnt++;
						ans[x]='H';
						break;
					}
				}
			}
			
			System.out.println(cnt);
			System.out.println(ans);
		}
		in.close();
	}
}
