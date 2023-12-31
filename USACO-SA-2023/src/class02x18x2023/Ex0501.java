package class02x18x2023;
/* Report if strings consisting of () and [] are valid
 * 
 * initial idea: make set of all valid strs consisting of () and [] up to len
 * 10^5. this uses set datastructure, but the class unit is stacks...
 * 
 * class idea: use a stack to check if a str is valid. push all ('s and ['s
 * onto a stack, and if you get a ) or ], pull the top val if it is a ( or [
 * respectively. if at any point this is not satisfied, you have a failure
 * on ur hands
 * 
 * difficulty: super ez to implement after i saw class sol of using a stack
 */
import java.util.*;
import java.io.*;

public class Ex0501 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(in.readLine()); // # of strings
		char[][] strs = new char[n][];
		for(int j=0;j<n;j++) {
			strs[j]=in.readLine().toCharArray();
		}
		for(int x=0;x<n;x++) {
			boolean works=true;
			Stack<Character> st = new Stack<>();
			for(int j=0;j<strs[x].length;j++) {
				if(strs[x][j]=='('||strs[x][j]=='[') {
					st.push(strs[x][j]);
				}
				else if (strs[x][j]==')') {
					if(!st.empty() && st.peek()=='(') st.pop();
					else {
						works=false;
						break;
					}
				}
				else {
					if(!st.empty() && st.peek()=='[')st.pop();
					else {
						works=false;
						break;
					}
				}
			}
			if(works)out.print(1);
			else out.print(0);
		}
		
		out.close();
	}
}
