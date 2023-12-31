/*Done easily
 *  sort indices, keep track of 'extra' whitespace, add it on and concatenate with substrs
 */
import java.util.*;
public class Ex0301 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.next();
		int n = in.nextInt();
		int[] indices = new int[n];
		for(int i=0;i<n;i++) {
			indices[i]=in.nextInt();
		}
		Arrays.sort(indices);
		int extra = 0;
		for(int i=0;i<n;i++) {
			str=str.substring(0,indices[i]+extra)+" "+str.substring(indices[i]+extra);
			extra++;
		}
		System.out.println(str);
		
		in.close();
	}
}
