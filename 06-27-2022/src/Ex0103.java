/* Unfinished, extension (Ex0104) is also unfinished
 * Given two points P1 = (x1, 0) and P2 = (x2, 0) on the x-axis, 
 where x1 < x2, and a point P3 = (x3, y3), count the number of lattice 
 points in or on the triangle determined by the three points P1, P2, P3 
 idea: rectangle grid */
import java.util.*;
public class Ex0103 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int x1 = in.nextInt();
		int x2 = in.nextInt();
		int x3 = in.nextInt();
		int y3 = in.nextInt();
		
		// translate x3 to 0
		x1-=x3; x2-=x3;x3=0;
		
		// find far coordinates of rect grid
		int leftx = 0;
		int rightx = 0;
		int topy = 0;
		int lowy = 0;
		// 5 cases: x3<x1, x3=x1, x3>x2, x3=x2, or x3 in between
		if(x3<x1) {
			leftx=x3; rightx=x2;
		}
		else if (x3>x2) {
			rightx = x3; leftx=x1;
		}
		else {
			leftx=x1;rightx=x2;
		}
		// 2 cases: y3>0 or y3<0
		if(y3>0) {topy=y3; lowy=0;}
		else {topy=0; lowy=y3;}
		
//		System.out.println(leftx+" "+rightx+" "+lowy+" "+topy);
		
		int cnt = 0; // # of lattice points inside
		// check if each coordinate is inside the triangle bounds
		// case 1: x3=x1
		if(x3==x1) {
//			System.out.println(1);
			for(int x=leftx;x<=rightx;x++) {
				for(int y=lowy;y<=topy;y++) {
					boolean inside = true;
					// checking if (x,y) is inside triangle bounds
					// bound 1: horizontal side will be ALWAYS satisfied due to rectangle nature
					// bound 2: left side (vertical side)
					if(x<x1) inside=false; 
					// bound 3: right side
					// equation is y<=mx+b. b is y3, m is y3/(x3-x2)
					else if (y>x*y3/(x3-x2)+y3) inside=false;
					else cnt++;
				}
			}
		}
		else if(x3==x2) {
//			System.out.println(2);
			for(int x=leftx;x<=rightx;x++) {
				for(int y=lowy;y<=topy;y++) {
					boolean inside = true;
					// checking if (x,y) is inside triangle bounds
					// bound 1: horizontal side will be ALWAYS satisfied due to rectangle nature
					// bound 2: left side
					// equation is y>=mx+b. b is y-intercept (y3). m is slope (y3/(x3-x1))
					if(y<x*y3/(x3-x1)+y3) inside=false; // TODO check integer division issues
					// bound 3: right side (vertical side)
					else if (x>x2) inside=false;
					else cnt++;
				}
			}
		}
		else { // x3 doesn't equal x1 or x2
//			System.out.println(3);
			for(int x=leftx;x<=rightx;x++) {
				for(int y=lowy;y<=topy;y++) {
					System.out.println("testing "+x+","+y);
					boolean inside = true;
					// checking if (x,y) is inside triangle bounds
					// bound 1: horizontal side will be ALWAYS satisfied due to rectangle nature
					// bound 2: left side
					// equation is y>=mx+b. b is y-intercept (y3). m is slope (y3/(x3-x1))
					if(y<x*y3/(x3-x1)+y3) inside=false; // TODO check integer division issues
					// bound 3: right side
					// equation is y<=mx+b. b is y3, m is y3/(x3-x2)
					else if (y>x*y3/(x3-x2)+y3) inside=false;
					else {cnt++; System.out.println(x+","+y);}
				}
			}
		}
		System.out.println(cnt);
		in.close();
	}
}
