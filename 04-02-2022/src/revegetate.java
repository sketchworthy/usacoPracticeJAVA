import java.util.*;
public class revegetate {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(); // # of pastures
		int M = in.nextInt(); // # of cows
		// fill array
		int[][] favs = new int[M][2]; // favorite grasses of cows
		for(int k=0;k<M;k++) {
			favs[k][0]=in.nextInt();
			favs[k][1]=in.nextInt();
		}
		
		int[] num = new int[N]; // final result of pasture grasses that will be a num
		for(int j=0;j<N;j++) { // let the num be as small as possible
			num[j]=1;
		}
		
		for(int j=0;j<N;j++) { // for each num[] digit index
			for(int k=0;k<M;k++) { // loop through favs
				if(favs[k][0]==num[j]) { // if a cow has the favorite pasture j
					if(favs[k][1]>j) num[favs[k][1]-1]++;
				}
				else if(favs[k][1]==num[j]) {
					if(favs[k][0]>j) num[favs[k][0]-1]++;
				}
			}
		}
		
		// trim the output
		for(int k=4;k>1;k--) {
			boolean hasl = false;
			for(int j=0;j<N;j++) {
				if(num[j]==k) hasl=true;
			}
			boolean hass = false;
			for(int j=0;j<N;j++) {
				if(num[j]==k-1) hass=true;
			}
			if(hasl==true && hass==false) {
				for(int j=0;j<N;j++) {
					if(num[j]==k) num[j]--;
				}
			}
		}
		
		
		// print output
		for(int j=0;j<N;j++) {
			System.out.print(num[j]);
		}
		
		in.close();
	}
}
