package class09x24x2023;
/* Towers. COMPLETE & DONE IN CPP W MULTISET
 * Given n cubes in a certain order, you want to always place
 * the cube on an existing tower or start a new tower. Cubes
 * can only be placed on cubes with a bigger side length. What's
 * the min # of towers makeable?
 * 
 * idea: every time u are putting a new cube, see the sorted freq map
 * of towers and put the cube on the smallest top cube that is
 * capable of holding it. if none capable, make a new tower.
 * 
 * difficulty: i mean the class had a whole walkthru so idrk. but
 * implementation was p ez
 * 
 */
import java.util.*;
import java.io.*;

public class Ex0302 {
	public static void main(String[] args) throws Exception {
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader in = new BufferedReader(new FileReader("10.in"));
		BufferedReader in2 = new BufferedReader(new FileReader("10.out"));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		out.println(".out file output: "+in2.readLine());
		int n=Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		TreeMap<Integer,Integer> tm = new TreeMap<>(); // freq treeMap
		for(int j=0;j<n;j++) {
			int height = Integer.parseInt(st.nextToken());
			if(tm.higherKey(height)!=null) { // NOTE: Can only compare an Integer w null. 
// so if u set k to tm.higherkey, you would need Integer k = tm.higherKey not int k = tm.higherkey
				int current=tm.higherKey(height); // key value we will add height to
				tm.put(height, tm.getOrDefault(height, 0)+1);
				if(tm.get(current)==1)tm.remove(current);
				else tm.put(current, tm.get(current)-1);
			}
			else {
				tm.put(height, tm.getOrDefault(height, 0)+1);
			}
			
		}
		int total=0;
		for(int key:tm.keySet()) {
			total+=tm.get(key);
		}
		out.print(total);
		
		in.close();
		out.close();
	}
}
