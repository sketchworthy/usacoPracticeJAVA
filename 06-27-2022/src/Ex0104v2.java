/* basic idea: gratituous use of formulas, including pick's theo and shoelace theo
 * actually pretty ez in implementation
 */
import java.util.*;

public class Ex0104v2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int x1 = in.nextInt(); int y1 = in.nextInt();
		int x2 = in.nextInt(); int y2 = in.nextInt();
		int x3 = in.nextInt(); int y3=in.nextInt();
		in.close();
		// pick's theorem says that A = i+b/2-1
		// we want i+b
		// so we calculate b, A with shoelace, and calculate i=A+1-b/2
		int A = Math.abs(x1*y2+x2*y3-x2*y1-x3*y2)/2;
//		System.out.println(A);
		// calculate # of boundary points for x1,y1 to x2, y2
		int b12 = gcd(x2-x1,y2-y1)+1;
		int b23 = gcd(x3-x2,y3-y2)+1;
		int b13 = gcd(x3-x1,y3-y1)+1;
//		System.out.println(b12+b23+b13-3);
		System.out.println(A+1+b12+b23+b13-3-(b12+b23+b13-3)/2);
		
	}
	
	public static int gcd(int a, int b) {
		// returns the gcd of a and b
		int g = 1;
		for(int j=1;j<Math.min(a, b);j++) {
			if (a%j==0&&b%j==0) g=j;
		}
		return g;
	}
}
