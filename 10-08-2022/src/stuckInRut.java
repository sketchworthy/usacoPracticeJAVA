/*
 * 
 */
import java.util.*;

public class stuckInRut {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
//		ArrayList<Integer> Eis = new ArrayList<Integer>(); // east-facing indexes of cows
//		ArrayList<Integer> Nis = new ArrayList<Integer>(); // north-facing indexes of cows
//		ArrayList<Integer> aliveCis = new ArrayList<Integer>(); // cows that haven't been stopped yet
		int n = in.nextInt(); // # of cows
		int[][] C = new int[n][5];// kth cows x coord: C(k,0). y-coord:C(k,1)
		//direction: C(k,2)1 if N, 0 if E. # of cells eaten: C(k,3), 
		// -1 if infinity. C(k,4)=0 if still alive, 1 if cow's been stopped.
		int maxX = 0;
		int maxY = 0;
		for(int j=0;j<n;j++) {
			String dir = in.next();
			if(dir.equals("N")) {
				C[j][2]=1;
//				Nis.add(j);
				maxX=Math.max(maxX, C[j][0]);
			}
			else if(dir.equals("E")) {
				C[j][2]=0;
//				Eis.add(j);
				maxY=Math.max(maxY, C[j][1]);
			}
//			aliveCis.add(j);
			C[j][0]=in.nextInt(); C[j][1]=in.nextInt();
			C[j][3]=Integer.MAX_VALUE;
			C[j][4]=0;
		}
		boolean[][] grid = new boolean[Math.max(maxX, maxY)+1][Math.max(maxX, maxY)+1];
		for(int j=0;j<Math.max(maxY, maxX)+1;j++) Arrays.fill(grid[j], false);
		//grid[x][y]=false if the square hasn't been eaten, true if it has
		
		// simulate     // NVM THIS WON'T WORK. TOO BIG.
		ArrayList<ArrayList<Integer>> cStep = new ArrayList<ArrayList<Integer>>();
		// ^current grid cells becoming occupied
		for(int t=0;t<=Math.max(maxY, maxX);t++) { // time
			cStep.clear();
			for(int j=0;j<n;j++) {
				int cx;
				int cy;
				if(C[j][2]==0) {
					cx=C[j][0]+t;
					cy=C[j][1];
				}
				else { // moving N
					cx=C[j][0];
					cy=C[j][1]+t;
				}
				ArrayList<Integer> info = new ArrayList<Integer>();
				info.add(cx); info.add(cy); info.add(j); //(cx,cy,i) (index)
				cStep.add(info);
			}
			// fill in all the grid cells at the end
			
		}
		
		
		
//		// determine whether a cow will go to infinity or not
//		// check it against cows of opposite orientation (will never hit parallel cow)
//		for(int j:Eis) { // compare east-facing cow j against
//			for(int k:Nis) { // north-facing cow k
//				// check which cow is stopped and which continues
//				int ix=C[k][0]; int iy=C[j][1]; // intersection point
//				int jD = ix-C[j][0]; // distance from j to intersection point
//				int kD = iy-C[k][1]; // distance from k to intersection point
//				if(jD<kD) { // if k is stopped
//					C[k][3]=Math.min(kD, C[k][3]);
//					System.out.println("Cow E "+j+" met cow N "+k+" and cow "+k+" was stopped");
//				}
//				else if(kD<jD) { // if j is stopped
//					C[j][3]=Math.min(jD, C[j][3]);
//					System.out.println("Cow E "+j+" met cow N "+k+" and cow "+j+" was stopped");
//				}
////				else { // if equal both keep going
////					
////				}
//			}
//		}
//		for(int j=0;j<n;j++) {
//			if(C[j][3]==Integer.MAX_VALUE)System.out.println("Infinity");
//			else System.out.println(C[j][3]);
//		}
		in.close();
	}
}

// current error in code: assumes all cows will meet, but highly likely
//   2 cows may not meet bc 1 is stopped earlier