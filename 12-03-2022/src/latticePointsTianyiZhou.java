/*
 * Given a circle and a straight line, find the number of lattice points above or on the straight line
 *  but inside (or on the boundary of) the circle.

 */
import java.util.*;
//import java.io.*;
public class latticePointsTianyiZhou {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		int x1=in.nextInt(); int y1=in.nextInt();
		int x2=in.nextInt(); int y2=in.nextInt();
		// (x1,y1) & (x2,y2) specifies a line. slope is (y2-y1)/(x2-x1). in point-slope form,
		//  the equation is y-y1=(x-x1)*(y2-y1)/(x2-x1)  (make sure to round when equating)
		int x0=in.nextInt(); int y0=in.nextInt(); // circle center
		int r = in.nextInt(); // radius
		// circle equation is (x-x0)^2+(y-y0)^2=r^2
		
		// in order for a lattice point to be viable it must satisfy 2 equations:
		//  y-y1>=(x-x1)*(y2-y1)/(x2-x1)
		//  (x-x0)^2+(y-y0)^2<=r^2
		
		int cnt=0; // # of satisfactory lattice points
		// start by checking each of points from in the big square where the circle is in
		for(int x=x0-r;x<=x0+r;x++) {
			for(int y=y0-r;y<=y0+r;y++) {
//				System.out.println("point: "+x+" "+y);
				double lhs=y-y1+0.0000001;
//				System.out.println(x+"-"+x1+" * "+y2+"-"+y1+"   /   "+x2+"-"+x1);
				double rhs=((x-x1)*(y2-y1)/Double.valueOf(x2-x1))-0.000001;
//				System.out.println("line lhs: "+lhs+". rhs: "+rhs);
				if(lhs>=rhs) { // above/on line
					// now check if inside/on circle
					lhs=Math.pow(x-x0, 2)+Math.pow(y-y0, 2)-0.00001;
					rhs=Math.pow(r, 2)+0.00001;
//					System.out.println("circ lhs: "+lhs+". rhs: "+rhs);
					if(lhs<=rhs) {
						cnt++;
//						System.out.println("added1");
					}
					
				}
//				System.out.println();
			}
		}
		System.out.println(cnt);
		
		in.close();
	}
}
