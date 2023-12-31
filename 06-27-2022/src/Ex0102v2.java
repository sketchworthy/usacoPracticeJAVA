/* done easily
 * basic idea: keep track if you have to negate both integers at the end, convert both integers to positive,
 * then iteratively add y until you go over the limit of x
 * improved on first version by using a lot less unecessary variables
 */
import java.util.*;

public class Ex0102v2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		int y = in.nextInt();
		in.close();
		boolean negate = false;
		if(x<0&&y>0) negate=true;
		else if (x>0&&y<0) negate=true;
		x=Math.abs(x); y=Math.abs(y);
		
		int sum = 0;
		int q = 0;
		while(sum+y<=x) {
			q++;
			sum+=y;
		}
		if(negate) q*=-1;
		System.out.println(q);
	}
}
