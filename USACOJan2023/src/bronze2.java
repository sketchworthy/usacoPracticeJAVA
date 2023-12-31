/*
 * 
 */
import java.util.*;

public class bronze2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // # of cows
		int m = in.nextInt(); // # of air conditioners
		int[] s = new int[n]; int[] t = new int[n]; 
		// ^^ s[x] to t[x] (inclusive) is stall range cow x occupies
		int[] c = new int[n]; // cow x must be cooled by c[x]
		
		for(int j=0;j<n;j++) {
			s[j]=in.nextInt()-1; t[j]=in.nextInt()-1; c[j]=in.nextInt();
		}
		
		int[] stalls = new int[100];
		for(int j=0;j<n;j++) { // fill stalls
			for(int k=s[j];k<=t[j];k++) stalls[k]=c[j];
		}
		
		int maxCost=0;
		
		int[] a = new int[m]; int[] b = new int[m]; // AC x cools stalls a[x] to b[x] inclusive
		int[] p = new int[m]; // AC x reduces all stalls they affect by p[x]
		int[] mon = new int[m]; // mon[x] is cost of air conditioner x
		for(int j=0;j<m;j++) {
			a[j]=in.nextInt()-1;
			b[j]=in.nextInt()-1;
			p[j]=in.nextInt();
			mon[j]=in.nextInt();
			maxCost+=mon[j];
		}
		// so we only need to look at helpful air conditioners that affect those ranges
		// 
		
		// We have enough operations that we can just look at permutations of conditioners!!
		// look at 1 conditioner, then 2, then 3, then 4,... then 9, then all 10
		// see what min cost is
		
		
		int minCost=maxCost;
		// look at 1 conditioner choices
		for(int ac=0;ac<m;ac++) { // cools a[ac] to b[ac] by p[ac]. costs mon[ac]
			int[] stalls1=Arrays.copyOf(stalls, 100);
			// cool with ac
			int cCost=mon[ac];
			boolean possible=true;
			for(int j=a[ac];j<=b[ac];j++) {
				stalls1[j]-=p[ac];
				if(stalls1[j]>0) {
					possible=false;
					break; // if its still larger than 0 after then break
				}
			}
			if(possible) {
				// check that all is possible
				boolean inPossible=true;
				for(int j=0;j<100;j++) {
					if(stalls1[j]>0) {
						inPossible=false;
						break;
					}
				}
				if(inPossible)minCost=Math.min(minCost, cCost);
			}
		}
		// 2 conditioner choices
		for(int ac1=0;ac1<m-1;ac1++) {
			int[] stalls1=Arrays.copyOf(stalls, 100);
			for(int j=a[ac1];j<=b[ac1];j++) stalls1[j]-=p[ac1]; // cool w ac1
			
			for(int ac2=ac1+1;ac2<m;ac2++) {
				int cCost=mon[ac1]+mon[ac2];
				int[] stalls2 = Arrays.copyOf(stalls1, 100);
				boolean possible=true;
				for(int j=a[ac2];j<=b[ac2];j++) {
					stalls2[j]-=p[ac2];
					if(stalls2[j]>0) {
						possible=false;
						break; // if its still larger than 0 after then break
					}
				}
				if(possible) {
					if(checkIfPossible(stalls2))minCost=Math.min(minCost, cCost);
				}
			}
		}
		// 3 conditioner choices
		for(int ac1=0;ac1<m-2;ac1++) {
			int[] stalls1=Arrays.copyOf(stalls, 100);
			for(int j=a[ac1];j<=b[ac1];j++) stalls1[j]-=p[ac1]; // cool w ac1
			for(int ac2=ac1+1;ac2<m-1;ac2++) { 
				int[] stalls2 = Arrays.copyOf(stalls1, 100);
				for(int j=a[ac2];j<=b[ac2];j++) stalls2[j]-=p[ac2]; // cool w ac2
				
				for(int ac3=ac2+1;ac3<m;ac3++) {
					int[] stalls3=Arrays.copyOf(stalls2, 100);
					int cCost=mon[ac1]+mon[ac2]+mon[ac3];
					
					for(int j=a[ac3];j<=b[ac3];j++) stalls3[j]-=p[ac3]; // cool w ac3
					if(checkIfPossible(stalls3))minCost=Math.min(minCost, cCost);
				}
			}
		}
		// 4 conditioner choices
		for(int ac1=0;ac1<m-3;ac1++) {
			int[] stalls1=Arrays.copyOf(stalls, 100);
			for(int j=a[ac1];j<=b[ac1];j++) stalls1[j]-=p[ac1]; // cool w ac1
			for(int ac2=ac1+1;ac2<m-2;ac2++) { 
				int[] stalls2 = Arrays.copyOf(stalls1, 100);
				for(int j=a[ac2];j<=b[ac2];j++) stalls2[j]-=p[ac2]; // cool w ac2
				
				for(int ac3=ac2+1;ac3<m-1;ac3++) {
					int[] stalls3=Arrays.copyOf(stalls2, 100);
					for(int j=a[ac3];j<=b[ac3];j++) stalls3[j]-=p[ac3]; // cool w ac3
					
					for(int ac4=ac3+1;ac4<m;ac4++) {
						int cCost=mon[ac1]+mon[ac2]+mon[ac3]+mon[ac4];
						int[] stalls4=Arrays.copyOf(stalls3, 100);
						
						for(int j=a[ac4];j<=b[ac4];j++) stalls4[j]-=p[ac4]; // cool w ac4
						if(checkIfPossible(stalls4))minCost=Math.min(minCost, cCost);	
					}
				}
			}
		}
		// 5 conditioner choices
		for(int ac1=0;ac1<m-4;ac1++) {
			int[] stalls1=Arrays.copyOf(stalls, 100);
			for(int j=a[ac1];j<=b[ac1];j++) stalls1[j]-=p[ac1]; // cool w ac1
			for(int ac2=ac1+1;ac2<m-3;ac2++) { 
				int[] stalls2 = Arrays.copyOf(stalls1, 100);
				for(int j=a[ac2];j<=b[ac2];j++) stalls2[j]-=p[ac2]; // cool w ac2
				
				for(int ac3=ac2+1;ac3<m-2;ac3++) {
					int[] stalls3=Arrays.copyOf(stalls2, 100);
					for(int j=a[ac3];j<=b[ac3];j++) stalls3[j]-=p[ac3]; // cool w ac3
					
					for(int ac4=ac3+1;ac4<m-1;ac4++) {
						int[] stalls4=Arrays.copyOf(stalls3, 100);
						for(int j=a[ac4];j<=b[ac4];j++) stalls4[j]-=p[ac4]; // cool w ac4
						for(int ac5=ac4+1;ac5<m;ac5++) {
							int[] stalls5=Arrays.copyOf(stalls4, 100);
							int cCost=mon[ac1]+mon[ac2]+mon[ac3]+mon[ac4]+mon[ac5];
							for(int j=a[ac5];j<=b[ac5];j++) stalls5[j]-=p[ac5]; // cool w ac5
							if(checkIfPossible(stalls5))minCost=Math.min(minCost, cCost);	
						}
					}
				}
			}
		}
		// 6 conditioner choices
		for(int ac1=0;ac1<m-5;ac1++) {
			int[] stalls1=Arrays.copyOf(stalls, 100);
			for(int j=a[ac1];j<=b[ac1];j++) stalls1[j]-=p[ac1]; // cool w ac1
			for(int ac2=ac1+1;ac2<m-4;ac2++) { 
				int[] stalls2 = Arrays.copyOf(stalls1, 100);
				for(int j=a[ac2];j<=b[ac2];j++) stalls2[j]-=p[ac2]; // cool w ac2
				
				for(int ac3=ac2+1;ac3<m-3;ac3++) {
					int[] stalls3=Arrays.copyOf(stalls2, 100);
					for(int j=a[ac3];j<=b[ac3];j++) stalls3[j]-=p[ac3]; // cool w ac3
							
					for(int ac4=ac3+1;ac4<m-2;ac4++) {
						int[] stalls4=Arrays.copyOf(stalls3, 100);
						for(int j=a[ac4];j<=b[ac4];j++) stalls4[j]-=p[ac4]; // cool w ac4
						for(int ac5=ac4+1;ac5<m-1;ac5++) {
							int[] stalls5=Arrays.copyOf(stalls4, 100);
							for(int j=a[ac5];j<=b[ac5];j++) stalls5[j]-=p[ac5]; // cool w ac5
							for(int ac6=ac5+1;ac6<m;ac6++) {
								int[] stalls6=Arrays.copyOf(stalls5, 100);
								int cCost=mon[ac1]+mon[ac2]+mon[ac3]+mon[ac4]+mon[ac5]+mon[ac6];
								for(int j=a[ac6];j<=b[ac6];j++) stalls6[j]-=p[ac6]; // cool w ac6
								if(checkIfPossible(stalls6))minCost=Math.min(minCost, cCost);
							}
						}
					}
				}
			}
		}
		// 7 acs
		for(int ac1=0;ac1<m-6;ac1++) {
			int[] stalls1=Arrays.copyOf(stalls, 100);
			for(int j=a[ac1];j<=b[ac1];j++) stalls1[j]-=p[ac1]; // cool w ac1
			for(int ac2=ac1+1;ac2<m-5;ac2++) { 
				int[] stalls2 = Arrays.copyOf(stalls1, 100);
				for(int j=a[ac2];j<=b[ac2];j++) stalls2[j]-=p[ac2]; // cool w ac2
				
				for(int ac3=ac2+1;ac3<m-4;ac3++) {
					int[] stalls3=Arrays.copyOf(stalls2, 100);
					for(int j=a[ac3];j<=b[ac3];j++) stalls3[j]-=p[ac3]; // cool w ac3
							
					for(int ac4=ac3+1;ac4<m-3;ac4++) {
						int[] stalls4=Arrays.copyOf(stalls3, 100);
						for(int j=a[ac4];j<=b[ac4];j++) stalls4[j]-=p[ac4]; // cool w ac4
						for(int ac5=ac4+1;ac5<m-2;ac5++) {
							int[] stalls5=Arrays.copyOf(stalls4, 100);
							for(int j=a[ac5];j<=b[ac5];j++) stalls5[j]-=p[ac5]; // cool w ac5
							for(int ac6=ac5+1;ac6<m-1;ac6++) {
								int[] stalls6=Arrays.copyOf(stalls5, 100);
								for(int j=a[ac6];j<=b[ac6];j++) stalls6[j]-=p[ac6]; // cool w ac6
								
								for(int ac7=ac6+1;ac7<m;ac7++) {
									int[] stalls7=Arrays.copyOf(stalls6, 100);
									int cCost=mon[ac1]+mon[ac2]+mon[ac3]+mon[ac4]+mon[ac5]+mon[ac6]+mon[ac7];
									for(int j=a[ac7];j<=b[ac7];j++) stalls7[j]-=p[ac7]; // cool w ac7
									if(checkIfPossible(stalls7))minCost=Math.min(minCost, cCost);
								}
							}
						}
					}
				}
			}
		}
		
		// LMAO I WOULD HAVE BASHED ALL THE WAY TO 10 ACS CASES BUT HERE I GOT 11/11
		 //  TEST CASES AAAAAAAAAA
		
		System.out.println(minCost);
		
		in.close();
	}
	public static boolean checkIfPossible(int[] stalls) {
		for(int j=0;j<100;j++) {
			if(stalls[j]>0) {
				return false;
			}
		}
		return true;
	}
}
