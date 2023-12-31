package class11x12x2023;
/* Course Schedule COMPLETE, NOT SURE IF COMPLETELY CORRECT THO 
 * You have to take N courses. You are given M prerequisites (a,b)
 * meaning you have to take course b before course a.
 * Report true if you can finish all courses, and the order you
 * take them in. Report false if cannot take all courses.
 * 
 * idea: courses are nodes, prereq specifications are directed edges.
 * if there is a cycle anywhere, it becomes impossible.
 * if it WERE possible to go thru all the nodes then you should be able to 
 * always take a course at any level of completed courses, until all courses
 * have been taken. when a course is taken, take all the new possible courses
 * that open up. then just dfs
 * 
 * difficulty: once u think thru and get the key idea its ez
 * 
 */
import java.util.*;
import java.io.*;

public class Ex1001 {
	static int[] numOfPrereqs; // numOfPrereqs[x] gives # of prereqs still needed until x can be taken
	static ArrayList<ArrayList<Integer>> adj; // if adj[a] has b, a is a prereq to b
	static ArrayList<Integer> coursesTaken;
	
	public static void main(String[] args) throws Exception {
//		BufferedReader in = new BufferedReader(new FileReader("01.in"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken()); // # of courses
		int m = Integer.parseInt(st.nextToken()); // # of prereq specifications
		numOfPrereqs = new int[n];
		adj = new ArrayList<>();
		for(int j=0;j<n;j++) adj.add(new ArrayList<>());
		for(int j=0;j<m;j++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj.get(b).add(a); // course b is prereq for course a
			numOfPrereqs[a]++;
		}
		
		coursesTaken = new ArrayList<>(); 
		for(int j=0;j<n;j++) {
			tryToTakeCourseAndNewCoursesOpenedUpByTakingCourse(j);
		}
		
		boolean canTakeAll = true;
		for(int j=0;j<n;j++) {
			if(numOfPrereqs[j]!=0) {
				canTakeAll=false;
				break;
			}
		}
		
		if(canTakeAll) {
			out.println(true);
			// display order of courses taken
			for(int j=0;j<n;j++) {
				out.print(coursesTaken.get(j)+" ");
			}
		}
		else out.println(false);

		in.close();
		out.close();
	}
	
	static void tryToTakeCourseAndNewCoursesOpenedUpByTakingCourse(int node) { 
		if(numOfPrereqs[node]>0)return; // no dice
		if(coursesTaken.contains(node))return; // don't visit again if already visited

		coursesTaken.add(node);
		for(int pathOpenedUp : adj.get(node)) { // for every potential path opened up
			numOfPrereqs[pathOpenedUp]--;
			if(numOfPrereqs[pathOpenedUp]==0)
				tryToTakeCourseAndNewCoursesOpenedUpByTakingCourse(pathOpenedUp);
		}
		
		return;
	}
}