/* at every point in your commands, 
 * if you have a L:
 * you can change to a R, shifting all future positions you go to by +2
 * you can change to a F, shifting all future positions you go to by +1 
 * if you have a R:
 * you can change to a L, shifting all future positions you go to by -2
 * you can change to a F, shifting all future positions you go to by -1 
 * if you have a F:
 * you can change to a R, shifting all future positions you go to by +1
 * you can change to a L, shifting all future positions you go to by -1 
 * 
 * [for all u also affect the # of targets u hit]
 * 
 * IK this is wrong. it only works for 5 test cases
 */
import java.util.*;
import java.io.*;

public class silver3v2 {
	public static void main(String[] args) throws Exception {
		// take input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int t = Integer.parseInt(st.nextToken()); // # of targets
		int c = Integer.parseInt(st.nextToken()); // # of commands
		
		st = new StringTokenizer(in.readLine());
		HashMap<Integer,Boolean> targets = new HashMap<>(); // locs of targets, false if not hit yet, true if hit
		for(int j=0;j<t;j++)
			targets.put(Integer.parseInt(st.nextToken()),false);
		HashMap<Integer,Boolean> targets1L = new HashMap<>(targets);
		HashMap<Integer,Boolean> targets2L = new HashMap<>(targets);
		HashMap<Integer,Boolean> targets1R = new HashMap<>(targets);
		HashMap<Integer,Boolean> targets2R = new HashMap<>(targets);
		
		char[] commands = new char[c];
		commands = in.readLine().toCharArray();
		
		int[] pos0 = new int[c]; // describes how many targets hit after each command (0shift)
		
		int[] pos1L = new int[c]; // L not F on command 1. how many targets hit after each command
		int[] pos2L = new int[c]; // L not R on command 1. how many targets hit after each command
		int[] pos1R = new int[c]; // R not F on command 1.
		int[] pos2R = new int[c]; // R not L on command 1.
		pos1L[0]=0;	pos2L[0]=0;	pos1R[0]=0;	pos2R[0]=0;
		
		int currPos=0;
		int ogTsHit=0;
		if(commands[0]=='L')currPos=-1;
		else if(commands[0]=='R')currPos=1;
		else {
			if(targets.containsKey(currPos)) {
				ogTsHit=1;
				targets.put(currPos, true);
			}
		}
		pos0[0]=ogTsHit;
		int L1=0; int L2=0; int R1=0; int R2=0;
		
		// iterate thru all commands
		for(int j=1;j<c;j++) {
			if(commands[j]=='L') {
				currPos--;
			}
			else if(commands[j]=='R') {
				currPos++;
			}
			else {
				if(targets.containsKey(currPos) && !targets.get(currPos)) {
					ogTsHit++;
					targets.put(currPos, true);
				}
				
				
				if(targets.containsKey(currPos-1) && !targets.get(currPos-1)) {
					L1++;
					targets1L.put(currPos-1, true);
				}
				if(targets.containsKey(currPos-2) && !targets.get(currPos-2)) {
					L2++;
					targets2L.put(currPos-2, true);
				}
				if(targets.containsKey(currPos+1) && !targets.get(currPos+1)) {
					R1++;
					targets1R.put(currPos+1, true);
				}
				if(targets.containsKey(currPos+2) && !targets.get(currPos+2)) {
					R2++;
					targets2R.put(currPos+2, true);
				}
			}
//			pos0[j]=currPos;
			pos0[j]=ogTsHit;
			pos1L[j]=L1;
			pos2L[j]=L2;
			pos1R[j]=R1;
			pos2R[j]=R2;
			
			
		}
		int ans=0;
		ans = Math.max(Math.max(L1, L2),Math.max(R2,Math.max(R1, ogTsHit)));
		
		out.println(ans);
		
		in.close();
		out.close();
	}
}
