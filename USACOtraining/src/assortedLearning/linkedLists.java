package assortedLearning;
/* Compares ArrayLists & linkedLists
 * 
 * linkedLists have slower memory access but can spread out memory allocation
 * 
 * if you'll be doing lots of insertion/removal at the front of a list,
 * linkedLists are faster/better
 * 
 * arrayLists are faster/better for lots of access, searching, sorting, etc
 */
import java.util.*;
import java.io.*;

public class linkedLists {
	public static void main(String[] args) throws Exception {
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = 2000000;
		long t1 = System.currentTimeMillis();
		
		ArrayList<Integer> A = new ArrayList<>();
		for(int j=0;j<n;j++)A.add(j);
		for(int j=n-1;j>=0;j--)A.remove(j);
		long t2 = System.currentTimeMillis();
		
		LinkedList<Integer> B = new LinkedList<>();
		for(int j=0;j<n;j++)B.add(j);
		for(int j=n-1;j>=0;j--)B.remove(j);
		long t3 = System.currentTimeMillis();
		
		out.print(t2-t1+" "+(t3-t2));
		out.close();
	}
}
