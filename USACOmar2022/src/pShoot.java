import java.util.*;
public class pShoot {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		String str = in.next();
		// count # of 2-char pairs with 1 G and 1 H
		ArrayList<Integer> G = new ArrayList<Integer>();
		for(int j=0;j<n;j++) { // add all indexes of G to list of G's
			if(str.charAt(j)=='G') {
				G.add(j+1); // j+1 b/c for the line, it starts from position 1
			}
		}
//		G.clear();
//		G.add(1);G.add(2);G.add(3);G.add(4);G.add(10);G.add(14);
		
////		System.out.println(G);
//		// trim even ints at the end that differ from n by 2
//		int cnt=0;
//		while (true){
//			if(G.get(G.size()-1)==n-cnt*2) {
//				G.remove(G.size()-1);
//				cnt++;
//			}
//			else break;
//		}
//		System.out.println(G);
		// remove any consecutive pairs starting with odd #
		int cnt=0;
		while (true) {
			cnt++;
			if(cnt>=G.size()) break;
			if(G.get(cnt)-1==G.get(cnt-1)) {
				G.remove(cnt); G.remove(cnt-1);
				cnt--;
			}
		}
		
		int reverses = 0;
		while(!G.isEmpty()) {
			while(true) { 
//				System.out.println(G);
				// if last index is even remove it
				if(G.size()==0) break;
				else if(G.get(G.size()-1)%2==0) {
					G.remove(G.size()-1);
				}
				else break;
			}
			if(G.size()==0) break;
			// largest is now odd, so reverse the elements
			int s = G.size();
			int largestOdd = G.get(s-1);
			ArrayList<Integer> Z = new ArrayList<Integer>();
			for(int k=0;k<s;k++) {
				Z.add(G.get(k));
			}
			G.clear();
			for(int k=s-1;k>=0;k--) {
				G.add(largestOdd+2-Z.get(k));
			}
			reverses++;
		}
		
		System.out.println(reverses);
		in.close();
	}

}
