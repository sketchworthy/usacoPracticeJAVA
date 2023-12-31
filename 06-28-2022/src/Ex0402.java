/*Done confusedly but implemened easily
 * Basic idea: min distance point is when vacc clinic is at median of points
 */

import java.util.*;
public class Ex0402 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n =in.nextInt();
		int[] xcoords = new int[n];
		int[] ycoords = new int[n];
		for(int j=0;j<n;j++) {
			xcoords[j]=in.nextInt();
			ycoords[j]=in.nextInt();
		}
		// find median of x- and y-values
		Arrays.sort(xcoords);
		Arrays.sort(ycoords);
		int xmed;
		int ymed;
		if(n%2==1) {
			xmed = xcoords[(n-1)/2];
			ymed = ycoords[(n-1)/2];
		}
		else { // if even # of stuff
			xmed = (xcoords[n/2]+xcoords[n/2-1])/2;
			ymed = (ycoords[n/2]+ycoords[n/2-1])/2;
		}
		// calculate min total of distances all cows walk
		int total = 0;
		for(int j=0;j<n;j++) {
			total+=Math.abs(xcoords[j]-xmed);
			total+=Math.abs(ycoords[j]-ymed);
		}
		System.out.println(total);
		in.close();
	}

}
