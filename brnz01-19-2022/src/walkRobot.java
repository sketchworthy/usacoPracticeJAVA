import java.util.Scanner;
public class walkRobot {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int N = kb.nextInt();
		int M = kb.nextInt();
		// save next line of N integer commands to an array
		int[] commands = new int[N];
		for(int j=0;j<N;j++) commands[j]=kb.nextInt();
		// save next line to 2D array of obstacles (col 0 x-coord, col 1 y-coord)
		int[][] obstacles = new int[M][2];
		for (int j=0;j<M;j++) {
			obstacles[j][0]=kb.nextInt();
			obstacles[j][1]=kb.nextInt();
		}
		
		int maxD = 0; // initialize max distance (squared) variable
		int x=0; int y=0; // coords
		int facing=0; // 0=N,1=E,2=S,3=W (direction variable)
		
//		for (int j=0;j<N;j++) { // debugging
//			System.out.print(commands[j]+" ");
//		}
//		System.out.println();
				
		// simulate walking process
		for (int j=0;j<N;j++) {
			int command = commands[j];
			// follow command
			if (command==-2) { // if turn left
				if (facing==0) facing=3;
				else facing--;
			}
			else if (command==-1) facing=(facing+1)%4; // if turn right
			else { // if command is to move forward
				int tempx=x; int tempy=y; // temporary variables to test for obstacle
				if (facing==0) tempy+=command;
				else if (facing==1) tempx+=command;
				else if (facing==2) tempy-=command;
				else if (facing==3) tempx-=command;
				// check to see if you'll run into an obstacle
				boolean blocked = false;
				for (int k=0;k<M;k++) {
					int kX=obstacles[k][0]; int kY=obstacles[k][1];
					if (tempx>=kX && kX>=x && tempy==kY) {
						x=kX-1;
						blocked=true;
						break;
					}
					if (tempx<=kX && kX<=x && tempy==kY) {
						x=kX+1;
						blocked=true;
						break;
					}
					if (tempx==kX && tempy>=kY && kY>=y) {
						y=kY-1;
						blocked=true;
						break;
					}
					if (tempx==kX && tempy<=kY && kY<=y) {
						y=kY+1;
						blocked=true;
						break;
					}
				}
				if (blocked==false) {
					x=tempx; 
					y=tempy;
				}
				if (x*x+y*y>maxD) maxD=x*x+y*y; // calculate maxD and check if it's increased
			}
//			System.out.println(x+","+y+" Direction: "+facing);// for debugging
		}
		System.out.println(maxD);
		kb.close();
	}
}
