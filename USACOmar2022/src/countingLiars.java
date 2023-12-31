import java.util.*;
public class countingLiars {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[][] states = new int[n][2]; // statements made by cows. 
		// col 1 is 0 (L) or 1 (G), col 2 is p_i (number they're saying)
		for(int j=0;j<n;j++) { // fill array states[][]
			if(in.next().equals("L")) {
				states[j][0]=0;
			}
			else {
				states[j][0]=1;
			}
			states[j][1]=in.nextInt();
		}
		
//		// check: print array states[][]
//		for(int r=0;r<n;r++) {
//			for(int c=0;c<2;c++) {
//				System.out.print(states[r][c]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		
		// find greatest and smallest G's and L's
		int minL=Integer.MAX_VALUE;
//		int maxL=Integer.MIN_VALUE;
//		int minG=Integer.MAX_VALUE;
		int maxG=Integer.MIN_VALUE;
		for(int j=0;j<n;j++) {
			if(states[j][0]==0) { // if statement is L
				if(states[j][1]<minL) {
					minL=states[j][1];
				}
//				if(states[j][1]>maxL) {
//					maxL=states[j][1];
//				}
			}
			if(states[j][0]==1) { // if statement is G
//				if(states[j][1]<minG) {
//					minG=states[j][1];
//				}
				if(states[j][1]>maxG) {
					maxG=states[j][1];
				}
			}
		}
		
		if(maxG<minL) {
			System.out.println("0");
		}
		else {
			
		
			
			int totalMin = Math.min(minL, maxG);
			int totalMax = Math.max(minL, maxG);
					
			
	//		int totalMin = Integer.MAX_VALUE;
	//		int totalMax = Integer.MIN_VALUE;
	//		for(int j=0;j<n;j++) {
	//			if(states[j][1]<totalMin) {
	//				totalMin=states[j][1];
	//			}
	//			if(states[j][1]>totalMax) {
	//				totalMax=states[j][1];
	//			}
	//		}
			
	//		System.out.println(totalMin+ " "+totalMax);
			
			int minLiars = Integer.MAX_VALUE;
			for(int k=totalMin-1;k<=totalMax+1;k++) { // possibly add tM-1, tM+1 // test all possible places where Bessie
				// could be hiding, then see min # of liars
	//			System.out.println(k);
				int liars=0;
				for(int j=0;j<n;j++) {
					if(states[j][0]==0) { // if L
						if(k>states[j][1]) {
							liars++;
						}
					}
					else { // if G
						if(k<states[j][1]) {
							liars++;
						}
					}
	//				System.out.print(liars+" ");
					
				}
				if(liars<minLiars) {
					minLiars=liars;
				}
	//			System.out.println();
			}
			
			System.out.println(minLiars);
		}
		in.close();
	}
}
