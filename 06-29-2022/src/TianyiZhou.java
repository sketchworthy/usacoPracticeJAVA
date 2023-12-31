/*
 * stuck on stupid tiny issue of the min maxCow being 0 instead of 1... im an idiot
 */
import java.util.Scanner;
public class TianyiZhou {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // # of aisles
		int f = in.nextInt(); // # of friends
		int b = in.nextInt(); // power of energy drink
		boolean[] aislesChecked = new boolean[n]; // aislesChecked[x]=true, aisle has been checked. false, np.
		int[] A = new int[f]; // A[x] = starting aisle of cow x
		int[] E = new int[f]; // E[x] = energy of cow x
		
		for(int i=0;i<f;i++) { // fill arrays A, E and aislesChecked
			A[i]=in.nextInt()-1;
			E[i]=in.nextInt();
			for(int e=0;e<E[i];e++) {
				if(A[i]+e<n) aislesChecked[A[i]+e]=true;
			}
		}
		
		// simulate giving each friend the edrink, then count # of extra trues
		int maxCow=1;
		int maxExtraTs = 0;
		int extraTs=0;
		for(int i=0;i<f;i++) {
			extraTs=0;
			for(int e=E[i]; e<E[i]+b;e++) {
				if(A[i]+e<n) {
					if(aislesChecked[A[i]+e]==false) {
						extraTs++;
					}
				}
			}
			if(extraTs>maxExtraTs) {
				maxExtraTs=extraTs;
//				System.out.println(extraTs);
				maxCow=i+1;
			}
		}
		System.out.println(maxCow);
		
		
		
		
		
		
//		for(int i=0;i<n;i++) {
//			System.out.print(aislesChecked[i]+" ");
//		}
//		// look for earliest gap length bigger than b, or just max gapL in general, 
//		//      and give the Edrink to the
//		int startingI = -1; //  earliest cow that has contributed to the latest 'true' before the max gap
//		int maxGapL = 0;
//		int cGapL = 0; // current gap length
//		for(int i=0;i<n;i++) {
//			if(aislesChecked[i]==false && startingI!=-1) {
//				cGapL++; // if this aisle wasn't checked, and its had a true aisle before, then increase cGapL
//			}
//			else { // if this aisle was checked, it either starts
//				if(startingI==-1)startingI=i;
//				if(cGapL>maxGapL) {
//					maxGapL=cGapL;
//					startingI=i-cGapL-1;
//					if(maxGapL>b) break;
//				}
//				cGapL=0; // update cgl
//			}
////			System.out.println(cGapL);
////			System.out.println(startingI);
//		}
////		System.out.println(startingI);
//		// now give Edrink to the earliest cow that has contributed to startingI
//		for(int i=0;i<f;i++) {
//			if(A[i]+E[i]-1==startingI) {
//				System.out.println(i+1); break;
//			}
//		}
		
		in.close();
	}
}
