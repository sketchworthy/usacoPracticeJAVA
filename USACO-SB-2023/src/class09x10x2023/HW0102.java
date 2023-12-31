package class09x10x2023;
/* USACO 2020 March Silver P2: Cereal COMPLETE
 * http://usaco.org/index.php?page=viewproblem2&cpid=1039
 * Given n cows that each have a fav and 2nd fav cereal out of m
 * cereals, report the # of cows that get to take 1 of their fav cereals.
 * if a cow has already taken a cereal another cow can't take it.\
 * 
 * idea: think about this problem from a diff perspective. Instead of going
 * forward, starting with all the cows having taken and then removing
 * cows from front of line, start at back of line and 'add' cows and
 * their interference. then, using recursion, its somehow under the time
 * limit. (i honestly dont understand that bit tbh)
 * EDIT: I get it now. bc there are n cows total, and each cow can only
 * be usurped 2 times at most, the max amnt of usurp operations is 2n.
 * so its guaranteed to be under the time limit!
 * 
 * difficulty: pretty hard i think. but i'm proud of myself! i thought over
 * it, resisted looking at the solution, and eventually solved it <3
 */
import java.util.*;
import java.io.*;

public class HW0102 {
//	static ArrayList<ArrayList<Integer>> favoringCows; // FC[0] gives list
//	// of cows that favor cereal 0 most
//	static ArrayList<ArrayList<Integer>> favoringCows2; // FC2[0] gives list
//	// of cows that favor cereal 0 2nd most
	static int[] taken; // if cereal has not been taken its -1, else its index of cow who took
	static int[][] favCereals;
//	static int total;
	static int[] totals;
	public static void main(String[] args) throws Exception {
//		BufferedReader in = new BufferedReader(new FileReader("cereal.in"));
//		PrintWriter out = new PrintWriter(new FileWriter("cereal.out"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of cows
		int m = Integer.parseInt(st.nextToken()); // # of cereals
//		favoringCows = new ArrayList<ArrayList<Integer>>();
//		favoringCows2 = new ArrayList<ArrayList<Integer>>();
//		for(int j=0;j<m;j++) {
//			ArrayList<Integer> a = new ArrayList<Integer>();
//			ArrayList<Integer> b = new ArrayList<Integer>();
//			favoringCows.add(a);
//			favoringCows2.add(b);
//		}
		taken=new int[m];
		Arrays.fill(taken,-1);
		favCereals=new int[n][2]; // n cows, 2 fav cereals each
		
		for(int j=0;j<n;j++) { // j is cow index
			st=new StringTokenizer(in.readLine());
			int a=Integer.parseInt(st.nextToken())-1;
			int b=Integer.parseInt(st.nextToken())-1;
			favCereals[j][0]=a; // most fav
			favCereals[j][1]=b; // 2nd most fav
//			favoringCows.get(a).add(j);
//			favoringCows2.get(b).add(j);
		}
//		
//		out.println(1);
		totals = new int[n];//totals[x]=# of cows got cereal when x cows denied
		// run thru backwards
		for(int j=n-1;j>=0;j--) { // j=# of cows denied, also 1st cow in line
			if(j!=n-1)totals[j]+=totals[j+1];
			takeCereal(j,j);
		}
		for(int j=0;j<n;j++) {
			out.println(totals[j]);
		}
//		total=0;
//		for(int j=0;j<n;j++) { // simulate all n cows lining up for cereal
//			cowTakes(j);
//		} // now all the info is filled with when all n cows are lined up
//		// start simulating removing cows
//		out.print(total);
		
		in.close();
		out.close();
	}
//	static boolean cowTakes(int c){ // cow c takes a cereal. return true if taking
//		if(taken[favCereals[c][0]]==-1) { // if most fav cereal of c not taken, take
//			taken[favCereals[c][0]]=c;
//			total++;
//			return true;
//		}
//		if(taken[favCereals[c][1]]==-1) { // if 2nd fav cereal not taken, take it
//			taken[favCereals[c][1]]=c;
//			total++;
//			return true;
//		}
//		// if both taken, be sad
//		return false;
//	}
//	static void removeCow(int c) { // remove cow and replace what other cows take
//		// check if c took fav cereal, 2nd fav, or didn't take
//		int f = favCereals[c][0]; // index of c's fav cereal
//		if(taken[f]==c) { // if c took their fav cereal
//			// readjust everyone behind c so that they can take their
//			//  new free fav cereals
//			if(favoringCows.get(f).size()==1)return; 
//				// no one changes if only c likes that cereal
//			favoringCows.get(f).remove(0); // remove c's preference
//			// give to cow w next preference
//			// give cow favoringCows.get(f).get(0) the cereal as their fav
//			
//		}
////		if(taken[favCereals[c][1]]==c) { // if c took their 2nd fav
////			// TODO check if this situation is even possible...
////			//  that is if we are removing from front of line every time.
////			//   bc the front gets their first choice every time1
////		}
//		// otherwise if didn't take either of those fav cereals just remove
//		//  c quietly
//	}
	
	static void takeCereal(int c, int j) { // c tries to take cereal, j at line front
		if(taken[favCereals[c][0]]==-1) { // if fav cereal not taken
			taken[favCereals[c][0]]=c;
			totals[j]++;
			return;
		} 
		// else: someone else already took c's fav cereal
		// c tries to take fav and fails bc someone earlier than c already took it
		if(taken[favCereals[c][0]]<c) { // c tries to take 2nd fav cereal
			if(taken[favCereals[c][1]]==-1) { // if 2nd fav not taken
				taken[favCereals[c][1]]=c;
				totals[j]++;
				return;
			} // else: if 2nd fav is taken
			// if later person took it, c ousts them and makes them reevaluate
			if(taken[favCereals[c][1]]>c) {
				int stolenFrom=taken[favCereals[c][1]];
				taken[favCereals[c][1]]=c;
				takeCereal(stolenFrom,j);
			}
			// if earlier person took it, c gives up
			return;
		}
		//if c tries to take fav and fails bc someone later already has it, then
		// 	c takes its 2nd fav and ousts later person, forcing later person to reevaluate
		int stolenFrom=taken[favCereals[c][0]];
		taken[favCereals[c][0]]=c;
		takeCereal(stolenFrom,j);
	}
}
