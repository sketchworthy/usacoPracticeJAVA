package assortedLearning;
import java.io.*;
import java.util.*;
public class BufferedReaderPractice {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		
		int n = Integer.parseInt(in.readLine()); // # of tokens (words) in next line
		StringTokenizer st = new StringTokenizer(in.readLine()); // reads a line
		int[] lineStuff = new int[n];
		for(int j=0;j<n;j++)lineStuff[j]=Integer.parseInt(st.nextToken()); // read into array
		
		for(int j=0;j<n;j++) {
			out.print(lineStuff[j]+" ");
		}
		
		out.close();
	}
}
