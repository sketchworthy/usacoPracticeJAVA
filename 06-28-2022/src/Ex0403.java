/*
 * done fairly easily
 *basic idea: 26x26 freq array of pairs
 */
import java.util.*;
public class Ex0403 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		int[][] freq = new int[26][26]; // freq[x][y] counts occurrences of pair combos of the xth letter of the alphabet
		for(int j=0;j<n;j++) {
			String str = in.next();
			freq[(int)(str.charAt(0)-'a')][(int)(str.charAt(1)-'a')]++;
		}
		
		int maxL = 0;
		for(int j=0;j<26;j++) {
			for(int k=0;k<26;k++) {
				if(j==k) {
					continue;
				}
				maxL+=2*Math.min(freq[j][k],freq[k][j]);
//				System.out.println("added "+2*Math.min(freq[j][k],freq[k][j])+" for pair "+j+" "+k);
			}
		}
		for(int j=0;j<26;j++) {
			if(freq[j][j]>0) {
				maxL+=2;
				break;
			}
		}
		
		System.out.println(maxL);
		
		in.close();
	}
}
