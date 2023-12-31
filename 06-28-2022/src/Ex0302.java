/*Done easily
 * basic idea: split string, read array backwards
 */
import java.util.*;
public class Ex0302 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();		
		in.close();
		
		String[] words = str.split(" ");
//		for(int i=0;i<words.length;i++) {
//			System.out.print(words[i]);
//		}
//		System.out.println();
		
		String ans = words[words.length-1];
		for(int i=words.length-2;i>=0;i--) {
			ans=ans+" "+words[i];
		}
		System.out.println(ans);
	}
}
