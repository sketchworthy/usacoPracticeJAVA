package class01x21x2022;
/* In a grid [0, A] x [0,B] on the 2D plane, there is a rectangle table whose sides
 * are parallel to the x- and y-axes. The rectangle's lower left corner is (x1,y1),
 * and upper right corner is (x2,y2). Another rectangular table, with width w and height
 * h, is to be put int the grid so the 2 rectangles do't overlap (sharing an edge or a
 * vertex is fine)
 * 
 * The 1st rectangle can move in the horizontal direction or the vertical direction.
 * Find the minimum sum of moves in horizontal direction an vertical direction so
 * that the 2nd rectangle can be placed in the grid. If it's impossible, report -1.
 * 
 * difficulty: ez in the morning when i have a clear head
 */
import java.util.*;
import java.io.*;
public class HW0202 {
	public static void main(String[] args) throws Exception {
		Scanner in2 = new Scanner(System.in);
		String fileName = in2.next();
		in2.close();
		Scanner in = new Scanner(new File(fileName));
		int A = in.nextInt(); // max X val of grid
		int B = in.nextInt(); // max Y val of grid
		int x1 = in.nextInt(); int y1 = in.nextInt();
		int x2 = in.nextInt(); int y2 = in.nextInt();
		int w = in.nextInt();
		int h = in.nextInt();
		
		// find largest possible dimensions of 'surrounding' 4 rectangles
		int topSpaceY = B-y2; // y-space at top between grid border and rectangle
		int bottomSpaceY = y1;
		int leftSpaceX = x1;
		int rightSpaceX = A-x2;
		
		int moveX=0; // min amnt you'll have to shift table1 horizontally
		int moveY=0; // min amnt you'll have to shift table1 vertically
		
		// see if it's possible to shift table1 down and put table2 on top
		//  or if it's possible to shift it up and put table2 on bottom
		boolean yMovable=false;
		if(topSpaceY+bottomSpaceY>=h) {
			yMovable=true;
			moveY=Math.min(Math.max(h-topSpaceY,0), Math.max(0,h-bottomSpaceY));
		}
		// see if it's possible to shift table1 right/left and put table2 there
		boolean xMovable=false;
		if(leftSpaceX+rightSpaceX>=w) {
			xMovable=true;
			moveX=Math.min(Math.max(w-leftSpaceX,0), Math.max(0,w-rightSpaceX));
		}
		if(xMovable&&yMovable)	System.out.println(Math.min(moveY, moveX));
		else if(xMovable)System.out.println(moveX);
		else if(yMovable)System.out.println(moveY);
		else System.out.println(-1);
		in.close();
	}
}
