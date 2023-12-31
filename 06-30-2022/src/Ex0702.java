/* finished
done easily
 * problem took a little bit of confusion to understand wording, but idea itself is simple
 * basically 2d halfpoint freq array. look at me synthesizing ideas!
 */
import java.util.*;
public class Ex0702 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int[][] freq = new int[1000][1000]; // freq[x][y] represents area of (x+0.5,y+0.5)
		for(int j=0;j<n;j++) {
			int x1=in.nextInt();
			int y1=in.nextInt();
			int x2=in.nextInt();
			int y2=in.nextInt();
			for(int x=x1;x<x2;x++) {
				for(int y=y1;y<y2;y++) {
					freq[x][y]++;
				}
			}
		}
		int area = 0;
		for(int x=0;x<1000;x++) {
			for(int y=0;y<1000;y++) {
				if(freq[x][y]==k) area++;
			}
		}
		System.out.println(area);
		
		in.close();
	}
}
