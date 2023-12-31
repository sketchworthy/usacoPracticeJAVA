package class04x08x2023;
/* For each of n-1 ints A2,...An, their parent/direct boss is given.
 * There is 1 CEO that is the root of the employee hierarchy.
 * Return the # of subordinates for each employee.
 * 
 * idea: tree structure. keep a global variable of the # of subordinates
 * each employee has, and a global variable of the lists of direct subordinates 
 * each employee has. recursively go thru and fill the # of subordinates each
 * employee has
 * 
 * note: my current implementation doesnt work bc i shouldve used an
 * arraylist of arraylists for indexing instead of hashmap of <int,set>s
 */
import java.util.*;
import java.io.*;

public class Ex1001 {
	static int[] ans;
	static HashMap<Integer,HashSet<Integer>> children;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		children=new HashMap<>();
		// ^ <employee #, list of children(direct subordinates)>
//		int[] parent = new int[n+1]; // parent[x] gives direct parent of guy x
		for(int j=2;j<=n;j++) {
			int parentID=Integer.parseInt(st.nextToken());
			if(children.containsKey(parentID)) {
				HashSet<Integer> hs=children.get(parentID);
				hs.add(j);
				children.put(parentID, hs);
			}
			else {
				HashSet<Integer> hs=new HashSet<>();
				hs.add(j);
				children.put(parentID, hs);
			}
//			parent[j]=Integer.parseInt(st.nextToken());
		}
		ans=new int[n+1]; // ans[x] is # of subordinates ans has 
		solve(1);
		
		for(int j=0;j<n;j++) {
			
		}
		
		in.close();
		out.close();
	}
	
	public static void solve(int employee) { // given employee #, fill ans[]
		HashSet<Integer> hs=children.get(employee);
		if(hs.isEmpty()) {
			ans[employee]=0;
			return;
		}
		for(int child:hs) {
			solve(child);
			ans[employee]+=ans[child]+1;
		}
	}
}
