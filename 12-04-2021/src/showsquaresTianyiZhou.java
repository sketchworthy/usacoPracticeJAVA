/* Read from a file a positive integer N and two characters ch1 and ch2, 
display the square shapes of size N by N.

Input (from file "square.in")
The only line contains integer N and two characters separated by white spaces, 
where N is in the range [3, 10].

Output (to file "square.out")
Display the two square shapes: In the first square, entries above the NW-SE diagonal are 
ch2, and ch1 for other entries; In the second square, entries above the SW-NE diagonal are 
ch1, and ch2 for other entries. The two squares are separated by one empty row.
 */
import java.io.File;
import java.io.PrintWriter;
import java.util.*;
public class showsquaresTianyiZhou {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("square.in"));
		PrintWriter out = new PrintWriter(new File("square.out"));
		int n = in.nextInt();
		char c1 = in.next().charAt(0);
		char c2 = in.next().charAt(0);
		if (n<=10 && n>=3) {
			// square1
			for (int j=1;j<=n;j++) {
				for (int k=1;k<=j;k++) {
					out.write(c1); // square1, write c1
				}
				for (int k=1;k<=n-j;k++) {
					out.write(c2); // square1, write c2
				}
				out.write("\n");
			}
			out.write("\n");
			out.write("\n");
			// square2
			for (int j=1;j<=n;j++) {
				for (int k=1;k<=n-j;k++) {
					out.write(c1); // square2, write c1
				}
				for (int k=1;k<=j;k++) {
					out.write(c2); // square2, write c2
				}
				out.write("\n");
			}
		}
		in.close();
		out.close();
	}
}