package oldUsacoProbs;
/* 2011 USACO Bronze problem 1, Contest Timing
 * really ez
 */
import java.util.*;
import java.io.*;
public class cTiming {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("ctiming.in"));
		PrintWriter out = new PrintWriter(new File("ctiming.out"));
		int d = in.nextInt(); // day of november
		int h = in.nextInt(); // hour
		int m = in.nextInt(); // minute
		
		int mincnt=0;
		if(d<11) {
			out.println(-1);
		}
		else {
			if(d>11) {
				mincnt+=1440*(d-11);
			}
			// look at hour and min
			mincnt+=60*h+m-61*11;
			if(mincnt<0)out.println(-1);
			else out.println(mincnt);
		}

		in.close();
		out.close();
	}
}
