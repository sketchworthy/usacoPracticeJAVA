/* ughhhh implementation took wayy too long. basic idea is still easy, but it's a pain in the ass
 * to implement. i got stuck+discouraged by this problem for days, and its the basic version.
 * finally got it work at least. this version is improved in that it at least gives the right answer
 */
import java.util.*;

public class Ex0103v2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int x1=in.nextInt();
		int x2=in.nextInt();
		int x3=in.nextInt();
		int y3=in.nextInt();
		
		int leftx = 0; // leftmost x on rect grid
		int rightx=0; //rightmost x on rect grid
		boolean right = false; // whether the triangle is right or not
		// set up rectangle grid borders
		// 5 cases: x3<x1, x3=x1, x3>x1 and x3<x2, x3=x2, x3>x2
		if(x3<x1) {
			// left border x is x3, right x2
			leftx=x3; rightx=x2;
		}
		else if(x3==x1) {
			// left border x is x3, right x2 (RIGHT TRIANGLE)
			leftx=x3; rightx=x2; right=true;
		}
		else if(x3>x1&&x3<x2) {
			// left border x is x1, right x2
			leftx=x1; rightx=x2;
		}
		else if(x3==x2) {
			// left border x is x1, right x3 (RIGHT TRIANGLE)
			leftx=x1; rightx=x3; right=true;
		}
		else if(x3>x2) {
			// left border x is x1, right x3
			leftx=x1; rightx=x3;
		}
//		System.out.println(leftx+" "+rightx+" "+right);
		int cnt = 0; // # of points in the triangle
		for(int x=leftx;x<=rightx;x++) {
			for(int y=0;y<=y3;y++) {
//				System.out.println("testing "+x+","+y);
				// for each point in rect grid, see if its in the triangle
				if(right==true) {
					if(leftx==x3) { // if vertical side is left
						// hypotenuse includes points x3,y3 and x2,0. slope is y3/(x3-x2). 
						// y-intercept is y3+x3*y3/(x3-x2)
						// equation for hypotenuse is y<y3*x/(x3-x2)+y3+x3*y3/(x3-x2)
						if(y<=y3*x/(x3-x2)+x2*y3/(x2-x3)) cnt++;
					}
					else { // if vertical side is right
						// hypotenuse includes points x3,y3 and x1,0
						if(y<=y3*x/(x3-x1)-x1*y3/(x3-x1)) cnt++;
					}
				}
				// else: triangle is not right
				else if(x3<x1) {
					// left side includes x3,y3 and x1,0. right includes x3y3 and x20
					if(y>=y3*x/(x3-x1)+x1*y3/(x1-x3)&&y<=y3*x/(x3-x2)+x2*y3/(x2-x3)) cnt++;
				}
				else if(x3>x2) {
					if(y<=y3*x/(x3-x1)-x1*y3/(x3-x1)&&y>=y3*x/(x3-x2)-x2*y3/(x3-x2)) cnt++;
				}
				else { // x3 between x1 and x2
					if(y<=y3*x/(x3-x1)-x1*y3/(x3-x1)&&y<=y3*x/(x3-x2)+x2*y3/(x2-x3)) cnt++;
				}
			}
		}
		
		System.out.println(cnt);
		in.close();
	}
}
