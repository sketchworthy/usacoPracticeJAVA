/*
 * 
 */
import java.util.*;

public class silver1v1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for(int t=in.nextInt();t>0;t--) {
			int cnt=0;
			boolean possible=true;
			char[] str1 = in.next().toCharArray(); // input str
			char[] str2 = in.next().toCharArray(); // desired str
			int n = str1.length;
			HashMap<Character, Character> hm = new HashMap<>();
			
			// check if it is impossible to make str2 from str1
			for(int j=0;j<n;j++) { // loop thru str1/str2
				if(hm.containsKey(str1[j])) { // if hm contains str1[j] as key
					// check if str2 is the same
					if(hm.get(str1[j])!=str2[j]) {
						possible=false;
						break;
					}
				}
				else { // if it doesn't contain str1[j] as key add it in
					hm.put(str1[j], str2[j]);
					if(str1[j]!=str2[j]) {
						cnt++;
						if(hm.containsKey(str2[j]) && 
									hm.get(str2[j])==str1[j])cnt++;
					}
				}
			}
			if(!possible)System.out.println(-1);
			else { // calculate min # of keystrokes required
				
				System.out.println(cnt);
			}
		}
		in.close();
	}
}