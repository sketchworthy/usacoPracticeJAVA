/* finished, done easily
 * basic idea: loop/recursive function
 */
import java.util.*;
public class Ex0203 {
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		int n = in.nextInt();
		String str = "1";
		for(int i=1;i<n;i++) {
			str=genNext(str);
		}
		System.out.println(str);
		
		in.close();
	}
	public static String genNext(String prev) {
		String next = "";
		char[] str = prev.toCharArray();
		char c = str[0]; // current c being repeated
		int cCnt = 1; // current count of c's that have been repeated
		for(int i=1;i<str.length;i++){
			if(str[i]==c)cCnt++; // continuing chain/block
			else {
				next=next+cCnt+c;
				cCnt=1;
				c=str[i];
			}
		}
		next=next+cCnt+c;
		return next;
	}
}
