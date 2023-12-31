/*
 * 
 */
import java.util.*;

public class silver1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for(int t=in.nextInt();t>0;t--) {
			int cnt=0;
			int distinctChar=0;
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
					distinctChar++;
					if(str1[j]!=str2[j]) {
						cnt++;
						if(checkForLoop(hm,str1[j])) {
							if(distinctChar==52) {
								possible=false;
								break;
							}
							cnt++;
						}
					}
				}
			}
			if(!possible)System.out.println(-1);
			else { // calculate min # of keystrokes required
//				for(int j=0;j<n;j++) {
//					if(str1[j]!=str2[j] && checkForLoop(hm,str1[j])) {
//						if(distinctChar==52) {
//							possible=false;
//							break;
//						}
//						cnt++;
//					}
//				}
				System.out.println(cnt);
			}
		}
		in.close();
	}
	public static boolean checkForLoop(HashMap<Character,Character> hm, char key) {
		// return true if values loop
		char key2=hm.get(key);
		while(hm.containsKey(key2)) {
			if(key2==hm.get(key2))return false;
			key2=hm.get(key2);
			if(key2==key)return true;
		}
		return false;
	}
}
