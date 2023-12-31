/* finished
done fairly easily, idea is simple, implementation tedious but ez
 * basically just a bunch of conditions to check and account for
 */
import java.util.*;
public class Ex0304 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String num = in.next();
		num=num.toLowerCase();
		int n = num.length();
		int eCnt = 0;
		int decCnt = 0;
		boolean t = true;
		for(int i=0;i<n;i++) {
			if((num.charAt(i)<'0'||num.charAt(i)>'9')&&num.charAt(i)!='.'&&
					num.charAt(i)!='+'&&num.charAt(i)!='-'&&num.charAt(i)!='e') {
				t=false; // get rid of strings with impossible characters
				break;
			}
			else if(num.charAt(i)=='-'||num.charAt(i)=='+') {
				if(i!=0) {
					if(num.charAt(i-1)!='e') {
						t=false;
						break;
					}
				}
			}
			else if(num.charAt(i)=='e') {
				eCnt++;
				if(eCnt>1) {t=false; break;}
				if(i==n-1||i==0) {t=false; break;} // if e is last or 1st char
			}
			else if(num.charAt(i)=='.') {
				decCnt++;
				if(decCnt>1 || eCnt>0) {t=false; break; } // if decimal point is after the e
			}
		}
		System.out.println(t);
		
		in.close();
	}
}
