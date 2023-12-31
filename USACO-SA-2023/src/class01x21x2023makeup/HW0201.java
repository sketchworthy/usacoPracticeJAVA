package class01x21x2023makeup;
/* COMPLETE
 * given positive integers a,b,c & integers M & N, find # of int solutions (x,y)
 * to equation ax+by=c and add to # of sols for -c<=ax+by<=c where x & y in range
 * [M,N]
 * 
 * idea: loop thru possible vals of x & determine all possible values of y & add
 * it on for each x
 * 
 * difficulty: pretty ez to realize once i took a 3 1/2 hr nap
 * also NOTE VARIABLES SHOULD BE LONGS
 * double NOTE REMEMBER WHEN DOING INTEGER DIVISION TO DOUBLE CHECK ROUNDING
 * EVEN IF YOU THOUGHT YOU CHECKED IT. CHECK IT AGAIN
 */
import java.util.*;
import java.io.*;

public class HW0201 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("10.in"));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		long c = Long.parseLong(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		long cnt=0;
		
		for(long x=M;x<=N;x++) {
			// if ax+by=c, y=(c-ax)/b
			long y=(c-a*x)/b;
			y=Math.min(y, N); // ymax
			if(y<M)continue;
			if(y*b==c-a*x){
				cnt++; // if y had no removed fraction part
			}
			else if(y*b>c-a*x)y--; // note to debuggng self: forgot to do the round-down here
			long ymin=(-c-a*x)/b;
			if(ymin*b<-c-a*x)ymin++;
			ymin=Math.max(ymin,M);
			if(ymin>N)continue;
//			if(y-ymin+1>0) {
				cnt+=(y-ymin+1);
//				out.println(y-ymin+1);
//			}
		}
		out.print(cnt);
		in.close();
		out.close();
	}
}
