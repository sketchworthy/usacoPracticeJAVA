/*
 * 
 */
import java.util.*;

public class bronze3 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for(int q=in.nextInt();q>0;q--) {
			char[] str = in.next().toCharArray();
			// if the string OOO, OOM, MOM or MOO is inside the str, you can create MOO
			// aka as long as there is an O from indices 1 to size-2 inclusive u can make MOO
			
			int minCnt=Integer.MAX_VALUE;
			int Ocnt=0;
			for(int j=1;j<str.length-1;j++) {
				if(str[j]=='O') {
					Ocnt++;
					if(str[j-1]=='M' && str[j+1]=='O') {
						minCnt=str.length-3;
						break;
					}
					else if(str[j-1]=='M' || (str[j-1]=='O' && str[j+1]=='O')) { // MOM
						minCnt=Math.min(str.length-2, minCnt);
					}
					else if(str[j-1]=='O' && str[j+1]=='M') minCnt=Math.min(str.length-1, minCnt);
				}
			}
			if(Ocnt==0)System.out.println(-1);
			else System.out.println(minCnt);
		}
		in.close();
	}
}
