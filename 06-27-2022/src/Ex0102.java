/* Integer division
 * Given two integers x and y, divide the two integers without using multiplication, division, or mod operator.
The integer division should truncate toward zero, which means losing its fractional part. For example, 1.234
would be truncated to 1, and -1.234 would be truncated to -1.
 */
import java.util.*;
public class Ex0102 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		int y = in.nextInt();
		// calculate x/y's integer part
		int ax = Math.abs(x); // absolute x
		int ay = Math.abs(y); // absolute y
		int ySum = 0;
		int q = 0;
		while(ySum<=ax) {
			q++;
			ySum+=ay;
		}
		boolean posX = true;
		boolean posY = true;
		if(x<0) posX=false;
		if(y<0) posY=false;
		if(posX==true&&posY==false)System.out.println(1-q);
		else if (posX==false&&posY==true)System.out.println(1-q);
		else System.out.println(q-1);
		in.close();
	}

}
